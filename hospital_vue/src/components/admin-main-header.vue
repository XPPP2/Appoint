<template>
  <div style="width: 100%" class="header">
    <el-link class="title">Hospital Appointment</el-link>
    <span style="float: right">
      <el-link v-if="user">Welcome，{{user.username}}</el-link>
      <el-link @click="logout" style="margin-left: 20px">Quit</el-link>
    </span>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        user: null
      }
    },
    methods: {
      logout(e){
        localStorage.removeItem("atoken");
        localStorage.removeItem("auser");
        this.$router.push("admin_login");
      }
    },
    created(){
      var user = localStorage.getItem("auser");
      if(user){
          this.user = JSON.parse(user);
      }else{
          
      }

    }
  }
</script>
<style scoped>
.header, .footer {
  background-color: #409EFF;
  line-height: 58px;
}

.el-link{
  color: #fff;
}
.el-link:hover{
  color: #fff;
}

.title{
  font-size: 18px;
}
</style>