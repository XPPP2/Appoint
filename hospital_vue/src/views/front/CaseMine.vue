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
                      <el-menu router default-active="3">
                        <el-menu-item route="user" index="1">User Info</el-menu-item>
                        <el-menu-item route="appoint_mine" index="2">My Appointment</el-menu-item>
                        <el-menu-item route="case_mine" index="3">My Result</el-menu-item>
                      </el-menu>
                    </div>
                  </el-col>
                  <el-col :span="20">
                  <el-table
                        :data="caseList"
                        style="width: 100%">
                        
                        <el-table-column
                            prop="member.realName"
                            label="Name"
                            >
                        </el-table-column>
                        <el-table-column
                            prop="member.mobile"
                            label="Phone"
                            >
                        </el-table-column>

                        <el-table-column
                            prop="result"
                            label="Result">
                        </el-table-column>

                        <el-table-column
                            prop="proposal"
                            label="Advice">
                        </el-table-column>

                        <el-table-column
                            prop="createTime"
                            label="Date">
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
  name: "CaseMine",
  data() {
    return {
      page: 1,
			pageSize: 10,
      caseList: []
    };
  },
  components: {
    mainHeader
  },
  methods:{
    getCase: function(){
      var that = this;
      this.$axios.get(that.domain + "/api/case/list", {params: {}, headers:{ token: localStorage.getItem("ftoken") }}).then(
        function(res){
          var r = res.data;
          if(r.code == 0){
            that.caseList = r.caseList;
          }
        }
      )
    }
    
  },
  created(){
    this.getCase();
  }
};
</script>

<style scoped>

</style>