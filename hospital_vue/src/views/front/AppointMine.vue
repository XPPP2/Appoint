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
                        <el-menu-item route="user" index="1">User Info</el-menu-item>
                        <el-menu-item route="appoint_mine" index="2">My Appointment</el-menu-item>
                        <el-menu-item route="case_mine" index="3">My Result</el-menu-item>
                      </el-menu>
                    </div>
                  </el-col>
                  <el-col :span="20">
                  <el-table
                        :data="appointList"
                        style="width: 100%">
                        
                        <el-table-column
                            prop="user.realName"
                            label="Name"
                            >
                        </el-table-column>
                        <el-table-column
                            prop="user.mobile"
                            label="Phone"
                            >
                        </el-table-column>

                        <el-table-column
                            prop="dept.deptName"
                            label="Department">
                        </el-table-column>

                        <el-table-column
                            prop="doctor.realName"
                            label="Doctor">
                        </el-table-column>
                        
                        <el-table-column
                            prop="appointTime"
                            label="Date">
                        </el-table-column>

                        <el-table-column
                            prop="time"
                            label="Time">
                        </el-table-column>

                        <el-table-column
                            prop="totalAmount"
                            label="Fee">
                        </el-table-column>

                        <el-table-column
                            prop="status"
                            label="Status">
                            <template slot-scope="scope">
                              <el-tag type="info" v-if="scope.row.status == 0">Canceled</el-tag>
                              <el-tag type="primary" v-if="scope.row.status == 1">Unpaid</el-tag>
                              <el-tag type="warning" v-if="scope.row.status == 2">Paid</el-tag>
                              <el-tag type="danger" v-if="scope.row.status == 3">Finished</el-tag>
                            </template>
                        </el-table-column>

                        <el-table-column
                            prop="opt"
                            width="80px"
                            label="Note">
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