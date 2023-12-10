<template>
    <div id="Index">
        <el-container>
            <el-header><mainHeader></mainHeader>
            </el-header>
            <el-container>
              <el-main>
                <div style="width: 1280px; margin: 0 auto">
                <el-row style="margin-top: 20px" :gutter="10">
                  <el-col :span="4">
                    <div>
                      <el-menu router default-active="2">
                        <el-menu-item route="user" index="1">用户信息</el-menu-item>
                        <el-menu-item route="appoint_mine" index="2">我的挂号</el-menu-item>
                        <el-menu-item route="case_mine" index="3">我的病例</el-menu-item>
                      </el-menu>
                    </div>
                  </el-col>
                  <el-col :span="20">
                  <el-table
                        :data="appointList"
                        style="width: 100%">
                        
                        <el-table-column
                            prop="user.realName"
                            label="姓名"
                            >
                        </el-table-column>
                        <el-table-column
                            prop="user.mobile"
                            label="电话"
                            >
                        </el-table-column>

                        <el-table-column
                            prop="dept.deptName"
                            label="科室">
                        </el-table-column>

                        <el-table-column
                            prop="doctor.realName"
                            label="医生">
                        </el-table-column>
                        
                        <el-table-column
                            prop="appointTime"
                            label="日期">
                        </el-table-column>

                        <el-table-column
                            prop="time"
                            label="时间">
                        </el-table-column>

                        <el-table-column
                            prop="totalAmount"
                            label="金额">
                        </el-table-column>

                        <el-table-column
                            prop="status"
                            label="状态">
                            <template slot-scope="scope">
                              <el-tag type="info" v-if="scope.row.status == 0">已取消</el-tag>
                              <el-tag type="primary" v-if="scope.row.status == 1">待付款</el-tag>
                              <el-tag type="warning" v-if="scope.row.status == 2">预约中</el-tag>
                              <el-tag type="danger" v-if="scope.row.status == 3">已完成</el-tag>
                            </template>
                        </el-table-column>

                        <el-table-column
                            prop="opt"
                            width="60px"
                            label="操作">
                            <template slot-scope="scope">
                              <el-link type="primary" v-if="scope.row.status == 1" @click="pay(scope.row.id)">支付</el-link>
                             
                            </template>
                        </el-table-column>
                        </el-table>
                  </el-col>
                </el-row>
                </div>
              </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script>
import mainHeader from '../../components/main-header'
export default {
  name: "AppointMine",
  data() {
    return {
      page: 1,
			pageSize: 10,
      appointList: []
    };
  },
  components: {
    mainHeader
  },
  methods:{
    getAppoint: function(){
      var that = this;
      this.$axios.get(that.domain + "/api/appoint/list", {params: {}, headers:{ token: localStorage.getItem("ftoken") }}).then(
        function(res){
          var r = res.data;
          console.log(r)
          if(r.code == 0){
            that.appointList = r.appointList;
          }
        }
      )
    },

    pay(id){
      this.$router.push({path: "pay", query: {id: id}});
    }
    
  },
  created(){
    this.getAppoint();
  }
};
</script>

<style scoped>

</style>