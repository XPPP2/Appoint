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
              <el-form-item label="姓名">
                <el-input v-model="q.realName" placeholder="姓名"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="query">查询</el-button>
                <el-button type="warning" @click="complete">完成</el-button>
                <el-button type="danger" @click="del">删除</el-button>
              </el-form-item>
            </el-form>

            <el-table
              :data="tableList"
              style="width: 100%"
              @selection-change="handleSelectionChange"
            >
              <el-table-column type="selection" width="55"> </el-table-column>

              <el-table-column prop="user.realName" label="用户">
              </el-table-column>
              <el-table-column prop="dept.deptName" label="科室">
              </el-table-column>
              <el-table-column prop="doctor.realName" label="医生">
              </el-table-column>
              <el-table-column prop="appointTime" label="预约日期">
              </el-table-column>
              <el-table-column prop="time" label="预约时间"> </el-table-column>
              <el-table-column prop="totalAmount" label="金额"> </el-table-column>
              <el-table-column prop="status" label="状态">
                <template slot-scope="scope">
                  <el-tag type="info" v-if="scope.row.status == 0">已取消</el-tag>
                  <el-tag type="primary" v-if="scope.row.status == 1"
                    >待付款</el-tag
                  >
                  <el-tag type="warning" v-if="scope.row.status == 2"
                    >预约中</el-tag
                  >
                  <el-tag type="danger" v-if="scope.row.status == 3"
                    >已完成</el-tag
                  >
                </template>
              </el-table-column>
              <el-table-column prop="remark" label="备注"> </el-table-column>
              <el-table-column prop="createTime" label="创建时间">
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
          this.$message.info("请选择一条数据");
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