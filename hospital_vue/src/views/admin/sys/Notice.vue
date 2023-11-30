<template>
    <div>
        <el-container>
            <el-header>
            <mainHeader></mainHeader>
            </el-header>
            <el-container>
                <mainSidebar :active="active"></mainSidebar>
					<el-main>
                        <div v-if="showList">
                    <el-form :inline="true" :model="q">
                        <el-form-item label="标题">
                            <el-input v-model="q.title" placeholder="标题"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="query">查询</el-button>
                            <el-button type="warning" @click="add">新增</el-button>
                            <el-button type="success" @click="update">修改</el-button>
                            <el-button type="danger" @click="del">删除</el-button>
                        </el-form-item>
                    </el-form>
    
					<el-table
                        :data="tableList"
                        style="width: 100%"
                        @selection-change="handleSelectionChange">
                        <el-table-column
                          type="selection"
                          width="55">
                        </el-table-column>
						
																								                        <el-table-column
                            prop="title"
                            label="标题"
                            >
                        </el-table-column>
																		                        <el-table-column
                            prop="content"
                            label="内容"
                            >
                        </el-table-column>
																		                        <el-table-column
                            prop="createTime"
                            label="创建时间"
                            >
                        </el-table-column>
												                        </el-table>
                        <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page="q.page"
                            :page-sizes="[10, 50, 100]"
                            :page-size="q.limit"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="total">
                        </el-pagination>
                        </div>
                        <div v-if="!showList">
                        <el-form :model="notice" label-width="120px">
                        																								<el-form-item label="标题">
                            <el-input v-model="notice.title"></el-input>
                        </el-form-item>
                        																		<el-form-item label="内容">
                            <el-input v-model="notice.content"></el-input>
                        </el-form-item>
                        																	
                        						                        <el-form-item>
                            <el-button type="primary" @click="onSubmit">保存</el-button>
                            <el-button @click="cancel">取消</el-button>
                        </el-form-item>
                        </el-form>
                        </div>
                </el-main>
            </el-container>
        </el-container>
		</div>
</template>
<script>
import mainHeader from '../../../components/admin-main-header'
import mainSidebar from '../../../components/admin-main-sidebar'
export default {
  name: "AdminNotice",
  data() {
    return {
      ids: [],
      tableList: [],
      notice: {},
      showList: true,
      active: '11',
      q: {
        page: 1,
        limit: 10
      },
      total: 0
    };
  },
  components: {
    mainHeader,
    mainSidebar
  },
  methods:{
      query(){
        var that = this;
        this.$axios.get(that.domain + '/notice/list',
        {headers: {
            'token': localStorage.getItem("atoken")
        },
            params: that.q
        }).then(function(res){
            if(res.data.code == 0){//成功
              that.tableList = res.data.rows
              that.total = res.data.total
            }else{}
        })
      },
      onSubmit(e){
        var that = this;
        var data = this.notice;
        var action = data.id == null ? "save" : "update";
        this.$axios.post(that.domain + '/notice/' + action, 
            data,
            {
                headers: {'token': localStorage.getItem("atoken")}
            }
        ).then(function(res){
            console.log(res)
            if(res.data.code == 0){//成功
              that.showList = true;
              that.query();
            }else{
              that.errorMsg = res.data.msg;
              that.$message.error(res.data.msg);
            }
        })
      },
      add(){
          this.showList = false
      },
      getInfo(id){
        var that = this;
        this.$axios.get(that.domain + '/notice/info/'+id,
        {headers: {
            'token': localStorage.getItem("atoken")
        }},{
            params: {}
        }).then(function(res){
            if(res.data.code == 0){//成功
              that.notice = res.data.notice
            }else{
              
            }
        })
      },
      update(){
      	if(this.ids.length != 1){
	        this.$message.info("请选择一条数据");
	        return;
        }
        this.showList = false
        this.getInfo(this.ids[0])
      },
      cancel(){
          this.showList = true
      },
      del(){
        if(this.ids.length == 0){
            this.$message.info("请选择数据");
            return;
        }
        var that = this;
        this.$axios.post(that.domain + '/notice/delete',
            that.ids,
            {
                headers: {'token': localStorage.getItem("atoken")}
            }).then(function(res){
            console.log(res)
            if(res.data.code == 0){//成功
              that.query();
            }else{
              that.errorMsg = res.data.msg;
              that.$message.error(res.data.msg);
            }
        })
      },
      handleSelectionChange(e){
        var ids = [];
        for(var i = 0; i < e.length; i++){
            ids.push(e[i].id)
        }
        this.ids = ids;
      },
      handleCurrentChange(e){
          this.q.page = e;
          this.query();
      },
      handleSizeChange(e){
          this.q.limit = e;
          this.query();
      }
  },
  created(){
    this.query()
  }
};
</script>

<style scoped>

</style>