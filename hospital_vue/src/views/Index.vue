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


                  <div class="title">Doctor</div>
                  <el-row :gutter="10">
                    <el-col :span="4" v-for="(o, index) in doctorList" :key="index" style="margin-bottom: 20px">
                      <el-card :body-style="{ padding: '0px' }" >
                        <!-- <img :src="o.picUrl" class="image" style="width: 100%">
                        <div style="padding: 14px;">
                          <div class="name">{{o.realName}}</div>
                          <div class="bottom clearfix">
                            <el-button type="text" class="button" @click="detail(o.id)">Detail>></el-button>
                          </div>
                        </div> -->
                      </el-card>
                    </el-col>
                  </el-row>

                  <div class="title">Hospital Info</div>
                  <el-row :gutter="10">
                    <!-- <el-col :span="16" v-html="hospital.remark"></el-col>
                    <el-col :span="8">
                      <h3>{{hospital.hospitalName}}</h3>
                      <p>Phone：{{hospital.mobile}}</p>
                      <p>Address：{{hospital.address}}</p>
                    </el-col> -->
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
    //查询医生
    getDoctor: function(){
      var that = this;
      this.$axios({url: this.domain + "/api/doctor/list", params: {}}).then(
        function(res){
          var r = res.data;
          console.log(r)
          if(r.code == 0){
            that.doctorList = r.doctorList;
          }
        }
      )
    },
    //查询医院
    getHospital: function(){
      var that = this;
      this.$axios({url: this.domain + "/api/hospital/detail", params: {id: 1}}).then(
        function(res){
          var r = res.data;
          console.log(r)
          if(r.code == 0){
            that.hospital = r.hospital;
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
    this.getAdvert()
    this.getDoctor();
    this.getHospital()
    
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