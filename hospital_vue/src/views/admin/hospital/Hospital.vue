<template>
  <div>
    <el-container>
      <el-header>
        <mainHeader></mainHeader>
      </el-header>
      <el-container>
        <mainSidebar :active="active"></mainSidebar>
        <el-main>
          <div v-if="!showList">
            <el-form :model="hospital" label-width="120px">
              <el-form-item label="图片">
                <el-upload
                  class="avatar-uploader"
                  action="http://127.0.0.1:8080/api/fileupload/upload"
                  :on-success="handleAvatarSuccess">
                  <img v-if="hospital.picUrl" :src="hospital.picUrl" style="width: 300px">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </el-upload>
              </el-form-item>
              <el-form-item label="医院名称">
                <el-input v-model="hospital.hospitalName"></el-input>
              </el-form-item>              
              
              <el-form-item label="联系电话">
                <el-input v-model="hospital.mobile"></el-input>
              </el-form-item>
              <el-form-item label="地址">
                <el-input v-model="hospital.address"></el-input>
              </el-form-item>

              <el-form-item label="详情">
                <Toolbar
                    style="border-bottom: 1px solid #ccc; width: 100%;"
                    :editor="editor"
                    :defaultConfig="toolbarConfig"
                    :mode="mode"
                    />
                <Editor
                    style="height: 500px; overflow-y: hidden; width: 100%;"
                    v-model="hospital.remark"
                    :defaultConfig="editorConfig"
                    :mode="mode"
                    @onCreated="onCreated"
                    />
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="onSubmit">保存</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>

import mainHeader from "../../../components/admin-main-header";
import mainSidebar from "../../../components/admin-main-sidebar";
import '@wangeditor/editor/dist/css/style.css' // 引入 css
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
export default {
  name: "AdminHospital",
  data() {
    return {
      ids: [],
      tableList: [],
      hospital: {},
      showList: false,
      active: "10",
      q: {
        page: 1,
        limit: 10,
      },
      total: 0,
      mode: 'simple', // 或 'simple'
      toolbarConfig: {
        
      },
      editor: null,
      editorConfig: { placeholder: '请输入内容...', MENU_CONF: {} }
    };
  },
  components: {
    Editor,
    Toolbar,
    mainHeader,
    mainSidebar,
  },
  methods: {
    //富文本编辑器
    onCreated(editor) {
      this.editor = Object.seal(editor); // 一定要用 Object.seal() ，否则会报错
    },
    onSubmit(e) {
      var that = this;
      var data = this.hospital;
      var action = data.id == null ? "save" : "update";
      this.$axios
        .post(that.domain + "/hospital/" + action, data, {
          headers: { token: localStorage.getItem("atoken") },
        })
        .then(function (res) {
          console.log(res);
          if (res.data.code == 0) {
            //成功
          } else {
            that.errorMsg = res.data.msg;
            that.$message.error(res.data.msg);
          }
        });
    },
    add() {
      this.showList = false;
    },
    getInfo(id) {
      var that = this;
      this.$axios
        .get(
          that.domain + "/hospital/info/" + id,
          {
            headers: {
              token: localStorage.getItem("atoken"),
            },
            params: {},
          }
        )
        .then(function (res) {
          if (res.data.code == 0) {
            //成功
            that.hospital = res.data.hospital;
          }
        });
    },
    update() {
      if (this.ids.length != 1) {
        this.$message.info("请选择一条数据");
        return;
      }
      this.showList = false;
      this.getInfo(this.ids[0]);
    },
    cancel() {
      this.showList = true;
    },
    del() {
      if (this.ids.length == 0) {
        this.$message.info("请选择数据");
        return;
      }
      var that = this;
      this.$axios
        .post(that.domain + "/hospital/delete", that.ids, {
          headers: { token: localStorage.getItem("atoken") },
        })
        .then(function (res) {
          console.log(res);
          if (res.data.code == 0) {
            //成功
            that.query();
          } else {
            that.errorMsg = res.data.msg;
            that.$message.error(res.data.msg);
          }
        });
    },
    handleSelectionChange(e) {
      var ids = [];
      for (var i = 0; i < e.length; i++) {
        ids.push(e[i].id);
      }
      this.ids = ids;
    },
    handleCurrentChange(e) {
      this.q.page = e;
      this.query();
    },
    handleSizeChange(e) {
      this.q.limit = e;
      this.query();
    },
    handleAvatarSuccess(e){
          this.hospital.picUrl = e.url
      }
  },
  created() {
    this.getInfo(1);
    this.editorConfig.MENU_CONF['uploadImage'] = {
     server: 'http://127.0.0.1:8080/api/fileupload/upload',
     fieldName: 'file',
     customInsert(res, insertFn) {
        // res 即服务端的返回结果

        // 从 res 中找到 url alt href ，然后插入图片
        insertFn(res.url, "", "")
      },
    }
  },
};
</script>

<style scoped>

</style>