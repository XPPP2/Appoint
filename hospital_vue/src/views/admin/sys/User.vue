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
              <el-form-item label="Username">
                <el-input v-model="q.loginName" placeholder="Username"></el-input>
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

              <el-table-column prop="username" label="Username">
              </el-table-column>

              <el-table-column prop="email" label="Email"> </el-table-column>
              <el-table-column prop="mobile" label="Phone"> </el-table-column>
              <el-table-column prop="status" label="Status">
                <template slot-scope="scope">
                  <el-tag type="info" v-if="scope.row.status == 0">Unable</el-tag>
                  <el-tag type="primary" v-if="scope.row.status == 1"
                    >Enable</el-tag
                  >
                </template>
              </el-table-column>
              <el-table-column prop="createTime" label="CreateDate">
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
            <el-form :model="user" label-width="120px">
              <el-form-item label="Username">
                <el-input v-model="user.username"></el-input>
              </el-form-item>
              <el-form-item label="Password">
                <el-input v-model="user.password"></el-input>
              </el-form-item>

              <el-form-item label="Email">
                <el-input v-model="user.email"></el-input>
              </el-form-item>
              <el-form-item label="Phone">
                <el-input v-model="user.mobile"></el-input>
              </el-form-item>
              <el-form-item label="Status">
                <el-radio-group v-model="user.status">
                  <el-radio :label="0">Unable</el-radio>
                  <el-radio :label="1">Enable</el-radio>
                </el-radio-group>
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
  name: "AdminUser",
  data() {
    return {
      tableList: [],
      q: {},
      user: {},
      showList: true,
      active: "99",
      q: {
        page: 1,
        limit: 10,
      },
      total: 0,
    };
  },
  components: {
    mainHeader,
    mainSidebar,
  },
  methods: {
    query() {
      var that = this;
      this.$axios
        .get(that.domain + "/sys/user/list", {
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
          } else {
          }
        });
    },
    onSubmit(e) {
      var that = this;
      var data = this.user;
      var action = data.id == null ? "save" : "update";
      this.$axios
        .post(that.domain + "/sys/user/" + action, data, {
          headers: { token: localStorage.getItem("atoken") },
        })
        .then(function (res) {
          console.log(res);
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
    add() {
      this.showList = false;
    },
    getInfo(id) {
      var that = this;
      this.$axios
        .get(
          that.domain + "/sys/user/info/" + id,
          {
            headers: {
              token: localStorage.getItem("atoken"),
            },
          },
          {
            params: {},
          }
        )
        .then(function (res) {
          if (res.data.code == 0) {
            //成功
            res.data.user.password = "";
            that.user = res.data.user;
          } else {
          }
        });
    },
    update() {
      if (this.ids.length != 1) {
        this.$message.info("Please select");
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
        this.$message.info("Please select");
        return;
      }
      var that = this;
      this.$axios
        .post(that.domain + "/sys/user/delete", that.ids, {
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
  },
  created() {
    this.query();
  },
};
</script>

<style scoped>
</style>