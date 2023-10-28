<template>
  <div style="width: 1280px; margin: 0 auto; display: flex" class="header">
    <el-link href="/#/" class="title">Hospital Appointment</el-link>
    <el-menu router :default-active="active" mode="horizontal"  text-color="#ffffff" active-text-color="#ffffff" background-color="#409EFF" style="flex: 1; height: 60px">
      <el-menu-item route="appoint" index="1">Make Appointment</el-menu-item>
    </el-menu>
    <span style="float: right">
    <el-link href="/#/login" v-if="!user">Login</el-link>
    <el-link href="/#/user" v-if="user">Welcome，{{user.loginName}}</el-link>
    <el-link @click="logout()" style="margin-left: 20px" v-if="user">Logout</el-link>
    <el-link href="/#/admin_index" style="margin-left: 20px" target="_blank">Manage</el-link>
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
    props: ['active'],
    methods: {
      logout(){
        localStorage.removeItem("ftoken");
        localStorage.removeItem("fuser");
        this.user = null;
        this.$router.push("/");
      }
    },
    created(){
      var user = localStorage.getItem("fuser");
      if(user){
          this.user = JSON.parse(user);
      }else{
          
      }

    }
  }
</script>

<style scoped>
.el-menu.el-menu--horizontal{
  border-bottom: none;
}

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
