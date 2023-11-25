<template>
  <div id="Appoint">
    <el-container>
      <el-header><mainHeader :active="active"></mainHeader> </el-header>
      <el-container>
        <el-main>
          <div style="width: 1280px; margin: 0 auto">
            <el-row style="margin-top: 20px" :gutter="10">
              <el-col :span="24">
                <el-form :model="appoint" label-width="120px">
                  <el-form-item label="Name">
                    <el-input v-model="appoint.realName"></el-input>
                  </el-form-item>
                  <el-form-item label="Phone">
                    <el-input v-model="appoint.mobile"></el-input>
                  </el-form-item>
                  <el-form-item label="Gender" hidden>
                    <el-radio-group v-model="appoint.gender">
                      <el-radio label="0" value="0">F</el-radio>
                      <el-radio label="1" value="1">M</el-radio>
                    </el-radio-group>
                  </el-form-item>
                  <el-form-item label="Department">
                    <el-select
                      v-model="appoint.deptId"
                      placeholder="Choose"
                      @change="changeDept"
                    >
                      <el-option
                        v-for="item in deptList"
                        :key="item.id"
                        :label="item.deptName"
                        :value="item.id"
                      >
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="Doctor">
                    <el-select
                      v-model="appoint.doctorId"
                      placeholder="Choose"
                      @change="changeDoctor"
                    >
                      <el-option
                        v-for="item in doctorList"
                        :key="item.id"
                        :label="item.realName"
                        :value="item.id"
                      >
                      </el-option>
                    </el-select>
                  </el-form-item>

                  <el-form-item label="Date">
                    <el-date-picker
                      v-model="appoint.appointTime"
                      type="date"
                      value-format="yyyy-MM-dd"
                      @change="changeDate"
                      placeholder="Appointment Date"
                    >
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item label="Time">
                    <el-col
                      :span="4"
                      v-for="time in timeList"
                      v-bind:key="time.id"
                    >
                      <div
                        class="time"
                        :class="timeId == time.id ? 'time active' : 'time'"
                        @click="clickTime(time.id)"
                      >
                        <div>{{ time.startTime }}~{{ time.endTime }}</div>
                        <span>Remain:{{ time.maxPeople - time.people }}</span>
                      </div>
                    </el-col>
                  </el-form-item>

                  <el-form-item label="Note">
                    <el-input v-model="appoint.remark" type="textarea"></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="onSubmit"
                      >Appoint</el-button
                    >
                  </el-form-item>
                </el-form>
              </el-col>
            </el-row>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
import mainHeader from "../../components/main-header";
export default {
  name: "Appoint",
  data() {
    return {
      active: "1",
      user: null,
      appoint: {
        deptId: "",
        doctorId: "",
        appointTime: "",
        gender: "0",
        totalAmount: 10
      },
      deptList: [],
      doctorList: [],
      timeList: [],
      timeId: "",
    };
  },
  components: {
    mainHeader,
  },
  methods: {
    queryDept() {
      var that = this;
      this.$axios
        .get(that.domain + "/api/dept/list", { headers: {}, params: {} })
        .then(function (res) {
          if (res.data.code == 0) {
            //成功
            that.deptList = res.data.deptList;
          }
        });
    },
    queryDoctor() {
      var that = this;
      var deptId = this.appoint.deptId;
      this.$axios
        .get(that.domain + "/api/doctor/list", {
          headers: {},
          params: { deptId: deptId },
        })
        .then(function (res) {
          if (res.data.code == 0) {
            //成功
            that.doctorList = res.data.doctorList;
          }
        });
    },
    queryTime() {
      var that = this;
      var appointDate = this.appoint.appointTime;
      if (!appointDate) {
        return;
      }
      var doctorId = this.appoint.doctorId;
      if (!doctorId) {
        return;
      }
      this.$axios
        .get(that.domain + "/api/time/list", {
          headers: {},
          params: { appointDate: appointDate, doctorId: doctorId },
        })
        .then(function (res) {
          if (res.data.code == 0) {
            //成功
            that.timeList = res.data.timeList;
          }
        });
    },
    changeDept(e) {
      this.queryDoctor();
    },
    changeDoctor(e) {
      this.queryTime();
    },
    changeDate(e) {
      this.queryTime();
    },
    clickTime(timeId) {
      this.timeId = timeId;
    },

    getSelectedTime() {
      var time = "";
      for (var i = 0; i < this.timeList.length; i++) {
        if (this.timeList[i].id == this.timeId) {
          return this.timeList[i].startTime + "~" + this.timeList[i].endTime;
        }
      }
    },
    onSubmit(e) {
      var that = this;
      var data = this.appoint;
      data.timeId = this.timeId;
      data.time = this.getSelectedTime();
      this.$axios
        .post(that.domain + "/api/appoint/save", data, {
          headers: { token: localStorage.getItem("ftoken") },
        })
        .then(function (res) {
          if (res.data.code == 0) {
            //成功
            that.$router.push({path: "pay", query: {id: res.data.id}});
          } else {
            that.errorMsg = res.data.msg;
            that.$message.error(res.data.msg);
          }
        });
    },
  },
  created() {
    var user = localStorage.getItem("fuser");
    if (user) {
      user = JSON.parse(user);
      this.appoint.realName = user.realName;
      this.appoint.mobile = user.mobile;
    }
    this.queryDept();
    this.queryDoctor();
  },
};
</script>

<style scoped>
.time {
  border: 2px solid #ddd;
  border-radius: 6px;
  text-align: center;
  padding-top: 15px;
  padding-bottom: 15px;
  background: #fff;
}
.time.active {
  color: #d9534f;
}
</style>