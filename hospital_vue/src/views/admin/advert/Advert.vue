<template>
    <div>
        <el-container>
            <el-header>
              <mainHeader></mainHeader>
            </el-header>
            <el-container>
                <mainSidebar :active="active"></mainSidebar>
                <el-main>
                    <div v-if="showList">
                    <el-form :inline="true" :model="q">
                        <el-form-item>
                            <el-button type="primary" @click="add">Add</el-button>
                            <el-button type="warning" @click="update">Modify</el-button>
                            <el-button type="danger" @click="del">Delete</el-button>
                        </el-form-item>
                    </el-form>
                        <el-table
                        :data="advertList"
                        style="width: 100%"
                        @selection-change="handleSelectionChange">
                        <el-table-column
                          type="selection"
                          width="55">
                        </el-table-column>
                        <el-table-column
                            prop="picUrl"
                            label="Picture"
                            >
                            <template slot-scope="scope">
                                <el-image
                                    style="width: 150px"
                                    :src="scope.row.picUrl"></el-image>
                            </template>
                        </el-table-column>
                        <el-table-column
                            prop="sort"
                            label="Order"
                            >
                        </el-table-column>
                        <el-table-column
                            prop="enable"
                            label="Status">
                            <template slot-scope="scope">
                              <el-tag type="info" v-if="scope.row.enable == 0">Unable</el-tag>
                              <el-tag type="primary" v-if="scope.row.enable == 1">Enable</el-tag>
                            </template>
                        </el-table-column>
                        <el-table-column
                            prop="createTime"
                            label="CreateDate"
                            >
                        </el-table-column>
                        </el-table>
                        <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page="q.page"
                            :page-sizes="[10, 50, 100]"
                            :page-size="q.limit"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="total">
                        </el-pagination>
                        </div>
                        <div v-if="!showList">
                        <el-form :model="advert" label-width="120px">
                            <el-form-item label="Headpic">
                                <el-upload
                                class="avatar-uploader"
                                action="http://127.0.0.1:8080/api/fileupload/upload"
                                :on-success="handleAvatarSuccess"
                                :before-upload="beforeAvatarUpload">
                                <img v-if="advert.picUrl" :src="advert.picUrl" style="width: 300px">
                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                </el-upload>
                            </el-form-item>
                            <el-form-item>
                                <el-radio-group v-model="advert.enable">
                                  <el-radio :label="0">Unable</el-radio>
                                  <el-radio :label="1">Enable</el-radio>
                                </el-radio-group>

                            </el-form-item>
                        <el-form-item label="Order">
                            <el-input v-model="advert.sort"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="onSubmit">Save</el-button>
                            <el-button @click="cancel">Cancel</el-button>
                        </el-form-item>
                        </el-form>
                        </div>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script>
import mainHeader from '../../../components/admin-main-header'
import mainSidebar from '../../../components/admin-main-sidebar'
export default {
  name: "AdminAdvert",
  data() {
    return {
      advertList: [],
      q: {
        page: 1,
        limit: 10
      },
      showList: true,
      advert: {
          picUrl: ''
      },
      active: '3',
      total: 0
    };
  },
  components: {
    mainHeader,
    mainSidebar
  },
  methods:{
      query(){
        var that = this;
        this.$axios.get(this.domain + '/advert/list',
        {
            headers:{
                'token': window.localStorage.getItem('atoken')
            },
            params: that.q
        }
        ).then(function(res){
            if(res.data.code == 0){//成功
              that.advertList = res.data.rows
              that.total = res.data.total
            }else{
              
            }
        })
      },
      queryAdvert(id){
        var that = this;
        this.$axios.get(this.domain + '/advert/info/'+id,
        {
            headers:{ token: localStorage.getItem("atoken") }
        }).then(function(res){
            if(res.data.code == 0){//成功
              that.advert = res.data.advert
            }else{
              
            }
        })
      },
      onSubmit(e){
        var that = this;
        var data = this.advert;
        var action = data.id == null ? "save" : "update";
        this.$axios.post(this.domain + '/advert/'+action, 
            data,
            {
                headers: {'token': localStorage.getItem("atoken")}
            }
        ).then(function(res){
            console.log(res)
            if(res.data.code == 0){//成功
              that.showList = true;
              that.query();
            }else{
              that.errorMsg = res.data.msg;
              that.$message.error(res.data.msg);
            }
        })
      },
      add(){
          this.showList = false
          this.advert = {}
      },
      update(){
        if(this.ids.length != 1){
            this.$message.info("请选择一条数据");
            return;
        }
        this.showList = false;
        this.queryAdvert(this.ids[0]);
      },
      cancel(){
          this.showList = true
      },
      del(){
        if(this.ids.length == 0){
            this.$message.info("请选择数据");
            return;
        }
        var that = this;
        this.$axios.post(this.domain + '/advert/delete',
            that.ids,
            {
                headers: {'token': localStorage.getItem("atoken")}
            }).then(function(res){
            console.log(res)
            if(res.data.code == 0){//成功
              that.query();
            }else{
              that.errorMsg = res.data.msg;
              that.$message.error(res.data.msg);
            }
        })
      },
      handleAvatarSuccess(e){
          this.advert.picUrl = e.url
      },
      beforeAvatarUpload(e){

      },
      handleSelectionChange(e){
        var ids = [];
        for(var i = 0; i < e.length; i++){
            ids.push(e[i].id)
        }
        this.ids = ids;
      },
      logout(e){
        localStorage.removeItem("auser");
        this.$router.push("admin_login");
      },
      handleCurrentChange(e){
          this.q.page = e;
          this.query();
      },
      handleSizeChange(e){
          this.q.limit = e;
          this.query();
      }
  },
  created(){
    
    this.query()
  }
};
</script>

<style scoped>

</style>