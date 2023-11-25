<template>
    <div>
        <el-container>
            <el-header><mainHeader></mainHeader>
            </el-header>
            <el-container>
                <mainSidebar :active="active"></mainSidebar>
                <el-main>
                    <el-row :gutter="12">
                      <el-col :span="8">
                        <el-card shadow="always">
                          Doctor Number
                          <div style="font-size: 20px; margin-top: 10px; font-weight: blod">{{statistics.doctorTotal}}</div>
                        </el-card>
                      </el-col>
                      <el-col :span="8">
                        <el-card shadow="always">
                          User Number
                          <div style="font-size: 20px; margin-top: 10px; font-weight: blod">{{statistics.userTotal}}</div>
                        </el-card>
                      </el-col>
                      <el-col :span="8">
                        <el-card shadow="always">
                          Appointment Number
                          <div style="font-size: 20px; margin-top: 10px; font-weight: blod">{{statistics.appointTotal}}</div>
                        </el-card>
                      </el-col>
                    </el-row>
                  <el-row>
                    <div id="main" style="width: 100%; height: 400px; margin-top: 20px"></div>
                  </el-row>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script>
import echarts from 'echarts'
import mainHeader from '../../components/admin-main-header'
import mainSidebar from '../../components/admin-main-sidebar'
export default {
  name: "AdminIndex",
  data() {
    return {
      statistics: {},
      user: null,
      asideStyle: {
        height: '500px'
      },
      active: '1'
    };
  },
  components: {
    mainHeader,
    mainSidebar
  },
  methods:{
    logout: function(){
      localStorage.removeItem("auser");
      localStorage.removeItem("atoken");
      app.user = null;
    },
    query: function() {
      var that = this;
      this.$axios.get(that.domain + "/statistics/query", {headers: {
            'token': localStorage.getItem("atoken")
        }}).then(
        function(res){
          var r = res.data;
          console.log(r)
          if(r.code == 0){
            that.statistics = r.statistics;
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: 'Statistic'
                },
                tooltip: {},
                legend: {
                    data:['People']
                },
                xAxis: {
                    data: r.statistics.appointCountList.map(function(v){return v.createTime})
                },
                yAxis: {
                    minInterval: 1,
                },
                series: [{
                    name: 'Appointment Number',
                    type:'line',
                    data: r.statistics.appointCountList.map(function(v){return v.count})
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
          }
        }
      )
		}
  },
  created(){
    var docHeight = document.documentElement.clientHeight;
    this.asideStyle.height = docHeight - 61 + "px";
    var user = localStorage.getItem("auser");
    if(user){
        this.user = JSON.parse(user);
    }else{
        this.$router.push("admin_login");
    }
    this.query();
    
  }
};
</script>

<style scoped>

</style>