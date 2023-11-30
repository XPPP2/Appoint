<template>
    <div id="Index">
        <el-container>
            <el-header><mainHeader></mainHeader>
            </el-header>
            <el-container>
              <el-main>
                <div style="width: 1280px; margin: 0 auto">
                <el-row :gutter="10">
                <el-col :span="6" :offset="4">
                  <div class="grid-content bg-purple">
                    <el-avatar
                      style="width: 250px; height: 250px"
                      :src="doctor.picUrl"></el-avatar>
                  </div>
                </el-col>
                <el-col :span="8">
                  <div class="grid-content bg-purple">
                    
                    <div>
                      <p>{{doctor.realName}}</p>  
                      <div>Department：{{doctor.dept.deptName}}</div>
                      <div>Name：{{doctor.realName}}</div>
                      <div>Phone：{{doctor.mobile}}</div>
                    </div>
                  </div>
                </el-col>
              </el-row>
              <el-row>
                <div class="title">Skill</div>
                <div style="margin-bottom: 20px">{{doctor.specialty}}</div>

                <div class="title">Info</div>
                <div>{{doctor.remark}}</div>
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
  name: "Index",
  data() {
    return {
      doctor: {
        
      },
      user: null
    };
  },
  components: {
    mainHeader
  },
  methods:{
    
    getDoctor: function(id){
      var that = this;
      this.$axios.get(that.domain + "/api/doctor/detail", {params: {id: id}}).then(
        function(res){
          var r = res.data;
          console.log(r)
          if(r.code == 0){
            that.doctor = r.doctor;
          }
        }
      )
    }
  },
  created(){
    var user = localStorage.getItem("fuser");
    if(user){
        this.user = JSON.parse(user);
    }

    var id = this.$route.query.id;
    this.getDoctor(id)
  }
};
</script>

<style scoped>

.time{
  color: #999;
  font-size: 12px
}

.avatar{
  width: 30px;
  height: 30px;
  border-radius: 50%;
}

.comment{
  display: flex;
  align-items: center;
  border-bottom: 1px solid #ddd;
  padding: 10px;
}

.comment .content{
  flex: 1;
  padding-left: 6px;
}


.comment:last-child {
  border-bottom: none;
}

.title{
    border-left: 3px solid #d9534f;
    font-size: 16px;
    margin-bottom: 10px;
  }
</style>