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
            <el-form :inline="true" :model="q" class="demo-form-inline">
              <el-form-item label="Doctor">
                <el-input v-model="q.realName" placeholder="Doctor"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="query">Search</el-button>
                <el-button type="warning" @click="add">Add</el-button>
                <el-button type="success" @click="update">Modify</el-button>
                <el-button type="danger" @click="del">Delete</el-button>
              </el-form-item>
            </el-form>

            <el-table
              :data="tableList"
              style="width: 100%"
              @selection-change="handleSelectionChange"
            >
              <el-table-column type="selection" width="55"> </el-table-column>

              <el-table-column prop="doctor.realName" label="Doctor">
              </el-table-column>
              <el-table-column prop="startTime" label="Start">
              </el-table-column>
              <el-table-column prop="endTime" label="End">
              </el-table-column>
              <el-table-column prop="maxPeople" label="Seats">
              </el-table-column>
            </el-table>
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="q.page"
              :page-sizes="[10, 50, 100]"
              :page-size="q.limit"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total"
            >
            </el-pagination>
          </div>
          <div v-if="!showList">
            <el-form :model="setting" label-width="120px">
              <el-form-item label="Doctor">
                <el-select v-model="setting.doctorId" placeholder="Please select">
                  <el-option
                    v-for="item in doctorList"
                    :key="item.id"
                    :label="item.realName"
                    :value="item.id"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="Start time">
                <el-input v-model="setting.startTime"></el-input>
              </el-form-item>
              <el-form-item label="End time">
                <el-input v-model="setting.endTime"></el-input>
              </el-form-item>
              <el-form-item label="Seats number">
                <el-input v-model="setting.maxPeople"></el-input>
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
import mainHeader from "../../../components/admin-main-header";
import mainSidebar from "../../../components/admin-main-sidebar";
export default {
  name: "AdminSetting",
  data() {
    return {
      ids: [],
      tableList: [],
      setting: {},
      showList: true,
      active: "8",
      q: {
        page: 1,
        limit: 10,
      },
      doctorList: [],
      total: 0,
    };
  },
  components: {
    mainHeader,
    mainSidebar,
  },
  methods: {
    //查询
    query() {
      var that = this;
      this.$axios
        .get(that.domain + "/setting/list", {
          headers: {
            token: localStorage.getItem("atoken"),
          },
          params: that.q,
        })
        .then(function (res) {
          if (res.data.code == 0) {
            //成功
            that.tableList = res.data.rows;
            that.total = res.data.total;
          }
        });
    },

    queryDoctor() {
      var that = this;
      this.$axios
        .get(that.domain + "/doctor/listAll", {
          headers: {
            token: localStorage.getItem("atoken"),
          },
          params: that.q,
        })
        .then(function (res) {
          if (res.data.code == 0) {
            //成功
            that.doctorList = res.data.doctorList;
          }
        });
    },

    //保存
    onSubmit(e) {
      var that = this;
      var data = this.setting;
      var action = data.id == null ? "save" : "update";
      this.$axios
        .post(that.domain + "/setting/" + action, data, {
          headers: { token: localStorage.getItem("atoken") },
        })
        .then(function (res) {
          if (res.data.code == 0) {
            //成功
            that.showList = true;
            that.query();
          } else {
            that.errorMsg = res.data.msg;
            that.$message.error(res.data.msg);
          }
        });
    },

    //新增
    add() {
      this.showList = false;
    },

    //查询
    getInfo(id) {
      var that = this;
      this.$axios
        .get(that.domain + "/setting/info/" + id, {
          headers: {
            token: localStorage.getItem("atoken"),
          },
          params: {},
        })
        .then(function (res) {
          if (res.data.code == 0) {
            //成功
            that.setting = res.data.setting;
          }
        });
    },

    //更新
    update() {
      if (this.ids.length != 1) {
        this.$message.info("Please select");
        return;
      }
      this.showList = false;
      this.getInfo(this.ids[0]);
    },

    //取消
    cancel() {
      this.showList = true;
    },

    //删除
    del() {
      if (this.ids.length == 0) {
        this.$message.info("Please select");
        return;
      }
      var that = this;
      this.$axios
        .post(that.domain + "/setting/delete", that.ids, {
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

    //选择
    handleSelectionChange(e) {
      var ids = [];
      for (var i = 0; i < e.length; i++) {
        ids.push(e[i].id);
      }
      this.ids = ids;
    },

    //分页
    handleCurrentChange(e) {
      this.q.page = e;
      this.query();
    },

    //分页
    handleSizeChange(e) {
      this.q.limt = e;
      this.query();
    },
  },
  created() {
    this.query();
    this.queryDoctor();
  },
};
</script>

<style scoped>
</style>