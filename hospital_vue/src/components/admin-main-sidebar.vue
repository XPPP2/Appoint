<template>
<el-aside width="200px" height="100%">
  <el-menu
      :default-active="active"
      :style="asideStyle"
      router>
      <el-menu-item index="1" route="/admin_index">
          <i class="el-icon-menu"></i>
          <span slot="title">Statistic</span>
      </el-menu-item>
      <el-menu-item index="2" route="admin_member">
          <i class="el-icon-menu"></i>
          <span slot="title">Account</span>
      </el-menu-item>
      <el-menu-item index="3" route="admin_advert">
          <i class="el-icon-menu"></i>
          <span slot="title">Adv</span>
      </el-menu-item>
      <el-menu-item index="4" route="admin_dept">
          <i class="el-icon-menu"></i>
          <span slot="title">Department</span>
      </el-menu-item>
      <el-menu-item index="5" route="admin_doctor">
          <i class="el-icon-menu"></i>
          <span slot="title">Doctor</span>
      </el-menu-item>
      <el-menu-item index="6" route="admin_appoint">
          <i class="el-icon-menu"></i>
          <span slot="title">Appointment</span>
      </el-menu-item>
      <el-menu-item index="7" route="admin_case">
          <i class="el-icon-menu"></i>
          <span slot="title">Result</span>
      </el-menu-item>
      <el-menu-item index="8" route="admin_setting">
          <i class="el-icon-menu"></i>
          <span slot="title">AppointSetting</span>
      </el-menu-item>
      <el-menu-item index="10" route="admin_hospital">
          <i class="el-icon-menu"></i>
          <span slot="title">HospitalSetting</span>
      </el-menu-item>
      <el-menu-item index="99" route="admin_user">
          <i class="el-icon-menu"></i>
          <span slot="title">Manager</span>
      </el-menu-item>
  </el-menu>
</el-aside>
</template>

<script>
  export default {
    data () {
      return {
        user: null,
        asideStyle: {
          height: '500px'
        },
      }
    },
    props: ['active'],
    methods: {
      
    },
    created(){
      var docHeight = document.documentElement.clientHeight;
      this.asideStyle.height = docHeight - 61 + "px";
    }
  }
</script>
