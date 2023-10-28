<template>
    <div id="Index">
        <el-container>
            <el-header>
              <mainHeader :active="active"></mainHeader>
            </el-header>
            <el-container>
              <el-main>
                <div style="width: 1280px; margin: 0 auto">
                  <el-carousel style="margin-bottom: 10px">
                    <el-carousel-item v-for="(item, index) in adverts" :key="index">
                      <el-image :src="item.picUrl" style="width: 100%; height: 100%"></el-image>
                    </el-carousel-item>
                  </el-carousel>

                  <!--医生-->
                  <div class="title">Doctors</div>
                  <el-row :gutter="10">
                    <el-col :span="4" v-for="(o, index) in doctorList" :key="index" style="margin-bottom: 20px">
                      <el-card :body-style="{ padding: '0px' }" >


                      </el-card>
                    </el-col>
                  </el-row>

                  <div class="title">Hospital Info</div>
                  <el-row :gutter="10">

                  </el-row>
                
                </div>
                
              </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script>
import mainHeader from '../components/main-header'

export default {
  name: "Index",
  data() {
    return {
      active: '0',
      user: null,
      adverts: [],
      doctorList: [],
      hospital: {}
    };
  },
  components: {
    mainHeader
  },
  methods:{
    //查询广告
    getAdvert: function(){
      var that = this;
      this.$axios({url: this.domain + "/api/advert/list", params: {}}).then(
        function(res){
          var r = res.data;
          console.log(r)
          if(r.code == 0){
            that.adverts = r.advertList;
          }
        }
      )
    },

    detail(id){
      this.$router.push({path: "doctor", query: {id: id}});
    }
  },
  
  created(){
    var user = localStorage.getItem("fuser");
    if(user){
        this.user = JSON.parse(user);
    }
    this.getAdvert();
    //this.getDoctor();
    //this.getHospital();
    
  }
};
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
}

.title{
    border-left: 3px solid #d9534f;
    font-size: 16px;
    margin-bottom: 10px;
  }
</style>