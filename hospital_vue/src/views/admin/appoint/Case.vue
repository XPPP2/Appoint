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
              <el-form-item label="Name">
                <el-input v-model="q.realName" placeholder="Name"></el-input>
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

              <el-table-column prop="member.realName" label="User">
              </el-table-column>

              <el-table-column prop="result" label="Result"> </el-table-column>
              <el-table-column prop="proposal" label="Advice"> </el-table-column>
              <el-table-column prop="createTime" label="Date">
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
            <el-form :model="hcase" label-width="120px">
              <el-form-item label="User">
                <el-select v-model="hcase.memberId" placeholder="Please select">
                  <el-option
                    v-for="item in appointList"
                    :key="item.userId"
                    :label="item.user.realName"
                    :value="item.id"
                  >
                  </el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="Result">
                <el-input v-model="hcase.result" type="textarea"></el-input>
              </el-form-item>
              <el-form-item label="Advice">
                <el-input v-model="hcase.proposal" type="textarea"></el-input>
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
  name: "AdminCase",
  data() {
    return {
      ids: [],
      tableList: [],
      hcase: {},
      appointList: [],
      showList: true,
      active: "7",
      q: {
        page: 1,
        limit: 10,
      },
      total: 0,
      member: {}
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
        .get(that.domain + "/case/list", {
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
    formatNumber(n) {
      n = n.toString()
      return n[1] ? n : '0' + n
    },
    getUser() {
      var that = this;
      this.$axios.get(that.domain + "/api/member/info", {params: {}, headers:{ token: localStorage.getItem("ftoken") }}).then(
        function(res){
          var r = res.data;
          console.log(r)
          if(r.code == 0){
            that.member = r.member;
          }
        }
      )
    },
    queryAppoint() {
      var that = this;
      var date = new Date();
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var day = date.getDate();
      var d = [year, month, day].map(that.formatNumber).join('-');
      this.$axios
        .get(that.domain + "/appoint/listAll?appointTime=" + d, {
          headers: {
            token: localStorage.getItem("atoken"),
          },
          params: {},
        })
        .then(function (res) {
          if (res.data.code == 0) {
            //成功
            that.appointList = res.data.appointList;
          } else {
          }
        });
    },
    onSubmit(e) {
      var that = this;
      var data = this.hcase;
      var action = data.id == null ? "save" : "update";
      this.$axios
        .post(that.domain + "/case/" + action, data, {
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
          that.domain + "/case/info/" + id,
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
            that.hcase = res.data.hcase;
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
        .post(that.domain + "/case/delete", that.ids, {
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
    this.queryAppoint();
    this.getUser();
  },
};
</script>

<style scoped>
</style>