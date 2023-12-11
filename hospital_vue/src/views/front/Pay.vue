<template>
    <div id="OrderPay">
        <el-container>
            <el-header><mainHeader></mainHeader>
            </el-header>
            <el-container>
              <el-main>
                <div style="width: 1280px; margin: 0 auto; text-align: center">

        	        <div><span style="color: #d9534f;">￥{{appoint.totalAmount}}</span></div>
                  <div>
                  <el-button type="danger" @click="pay">Pay</el-button>
                  </div>
                </div>
              </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script>
import mainHeader from '../../components/main-header'
export default {
  name: "OrderPay",
  data() {
    return {
      user: null,
      totalAmount: 0,
      appoint: {},

    };
  },
  components: {
    mainHeader
  },
  methods:{
    pay: function(){
      var that = this;
      this.$axios.get(that.domain + "/api/appoint/pay", {params: {id: that.appoint.id}, headers:{ token: localStorage.getItem("ftoken") }}).then(
        function(res){
          var r = res.data;
          
          if(r.code == 0){
            alert("Success");
            that.$router.push("appoint_mine");
          }
        }
      )
    },
    getOrder: function(id){
      var that = this;
      this.$axios.get(that.domain + "/api/appoint/detail", {params: {id: id}, headers:{ token: localStorage.getItem("ftoken") }}).then(
        function(res){
          var r = res.data;
          
          if(r.code == 0){
            that.appoint = r.appoint;
          }
        }
      )
    },
      
  },
  created(){
    var id = this.$route.query.id;
    this.getOrder(id)
    
  }
};
</script>

<style scoped>

</style>