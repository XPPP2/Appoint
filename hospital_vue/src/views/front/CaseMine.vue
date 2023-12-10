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
                        <el-menu-item route="user" index="1">用户信息</el-menu-item>
                        <el-menu-item route="appoint_mine" index="2">我的挂号</el-menu-item>
                        <el-menu-item route="case_mine" index="3">我的病例</el-menu-item>
                      </el-menu>
                    </div>
                  </el-col>
                  <el-col :span="20">
                  <el-table
                        :data="caseList"
                        style="width: 100%">
                        
                        <el-table-column
                            prop="member.realName"
                            label="姓名"
                            >
                        </el-table-column>
                        <el-table-column
                            prop="member.mobile"
                            label="电话"
                            >
                        </el-table-column>

                        <el-table-column
                            prop="result"
                            label="诊断结果	">
                        </el-table-column>

                        <el-table-column
                            prop="proposal"
                            label="建议">
                        </el-table-column>

                        <el-table-column
                            prop="createTime"
                            label="时间">
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