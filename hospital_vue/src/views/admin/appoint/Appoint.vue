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
                <el-button type="warning" @click="complete">Finish</el-button>
                <el-button type="danger" @click="del">Delete</el-button>
              </el-form-item>
            </el-form>

            <el-table
              :data="tableList"
              style="width: 100%"
              @selection-change="handleSelectionChange"
            >
              <el-table-column type="selection" width="55"> </el-table-column>

              <el-table-column prop="user.realName" label="User">
              </el-table-column>
              <el-table-column prop="dept.deptName" label="Department">
              </el-table-column>
              <el-table-column prop="doctor.realName" label="Doctor">
              </el-table-column>
              <el-table-column prop="appointTime" label="Date">
              </el-table-column>
              <el-table-column prop="time" label="AppointDate"> </el-table-column>
              <el-table-column prop="totalAmount" label="Fee"> </el-table-column>
              <el-table-column prop="status" label="Status">
                <template slot-scope="scope">
                  <el-tag type="info" v-if="scope.row.status == 0">Canceled</el-tag>
                  <el-tag type="primary" v-if="scope.row.status == 1"
                    >Unpaid</el-tag
                  >
                  <el-tag type="warning" v-if="scope.row.status == 2"
                    >Paid</el-tag
                  >
                  <el-tag type="danger" v-if="scope.row.status == 3"
                    >Finished</el-tag
                  >
                </template>
              </el-table-column>
              <el-table-column prop="remark" label="Note"> </el-table-column>
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
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
import mainHeader from "../../../components/admin-main-header";
import mainSidebar from "../../../components/admin-main-sidebar";
export default {
  name: "AdminRegister",
  data() {
    return {
      ids: [],
      tableList: [],
      appoint: {},
      showList: true,
      active: "6",
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
        .get(that.domain + "/appoint/list", {
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
    onSubmit(e) {
      var that = this;
      var data = this.appoint;
      var action = data.id == null ? "save" : "update";
      this.$axios
        .post(that.domain + "/appoint/" + action, data, {
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
        .get(that.domain + "/appoint/info/" + id, {
          headers: {
            token: localStorage.getItem("atoken"),
          },
          params: {},
        })
        .then(function (res) {
          if (res.data.code == 0) {
            //成功
            that.appoint = res.data.appoint;
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
        .post(that.domain + "/appoint/delete", that.ids, {
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
    complete(e){
        var that = this;
        if(this.ids.length != 1){
          this.$message.info("Please select");
          return;
        }
        var id = this.ids[0];
        this.$axios.get(this.domain + '/appoint/complete',{
            params: {
              id: id
            },
            headers: {'token': localStorage.getItem("atoken")}
        }).then(function(res){
            
            if(res.data.code == 0){//成功
              that.query();
            }else{
              
            }
        })
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