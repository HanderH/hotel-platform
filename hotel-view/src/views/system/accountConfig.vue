<template>
  <a-card :bordered="false">
    <div v-show="!routeShow">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="6" :sm="24">
              <a-form-item label="名称">
                <a-input v-model="queryParam.userName" placeholder=""/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item label="身份证号">
                <a-input v-model="queryParam.userCode" > </a-input>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item label="性别">
                <a-select v-model="queryParam.userSex" placeholder="请选择">
                  <a-select-option :value="data.codeValue" v-for="(data,index) in userSexs" :key="index">{{data.codeName}}</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col>
              <a-button type="primary" @click="handerSearch">查询</a-button>
              <a-button style="margin-left: 8px" @click="() => queryParam = {}">重置</a-button>
            </a-col>
          </a-row>
          <a-row>
            <a-button type="primary"  icon="plus" @click="handerSave" style="margin-bottom:10px" >新建</a-button>
          </a-row>
          <a-row>
            <a-table
              :columns="columns"
              :rowKey="record => record.userId"
              :dataSource="data"
              :pagination="pagination"
              :loading="loading"
              @change="handlerTableChange"
              :scroll="{  x: '1300px' }"
            >
              <!--<template slot="roles" slot-scope="text, record">
                 {{showRole(record.roles)}}
              </template>-->
              <template slot="roles" slot-scope="text,record">{{show(record.roles)}}</template>
              <template slot="userStatus" slot-scope="text,record">
                <span>
                   <a-tag  :color="record.userStatus==='启用' ? 'green' : 'volcano'">{{record.userStatus}}</a-tag>
                 </span>
              </template>
              <template slot="action" slot-scope="text, record" >
                  <a href="javascript:;" @click="handerEdit(record)">
                    <a-tooltip placement="top">
                      <a-icon type="edit" style="font-size: 15px"/>
                      <a-divider type="vertical"/>
                      <template slot="title">
                        <span>修改</span>
                      </template>
                    </a-tooltip>
                  </a>
                  <a href="javascript:;" @click="handerDelete(record)">
                    <a-tooltip placement="top">
                      <a-icon type="delete" style="font-size: 15px"/>
                      <a-divider type="vertical"/>
                      <template slot="title">
                          <span>修改</span>
                      </template>
                    </a-tooltip>
                  </a>
              </template>
            </a-table>
          </a-row>
        </a-form>
      </div>
    </div>
    <router-view></router-view>
  </a-card>
</template>
<script>

import ARow from "ant-design-vue/es/grid/Row";
import $account from "@/api/account";
import $dict from "@/api/dict"
function changeShow(to) {
  if (to.path == "/system/account/config") {
    return false;
  } else {
    return true;
  }
}

export default {
  name: 'AccountConfig',
  components: {ARow},
  data(){
    return{
      userSexs:[],
      queryParam:{ },
      routeShow: false,
      // roles:[],
      advanced:false,
      form: this.$form.createForm(this),
      columns: [
        {
          title: "账号名称",
          dataIndex: "userName",
          width: "150px",
          fixed: 'left'
        },
        {
          title: "身份证号",
          dataIndex: "userCode",
          width: "200px"
        },
        {
          title: "账号角色",
          dataIndex: "roles",
          scopedSlots: { customRender: "roles" },
          width: "200px"
        },
        {
          title: "账号状态",
          dataIndex: "userStatus",
          scopedSlots: { customRender: "userStatus" },
          width: "150px"
        },
        {
          title: "联系电话",
          dataIndex: "userTel",
          width: "200px"
        },
        {
          title: "创建人",
          dataIndex: "createUser",
          width: "150px"
        },
        {
          title: "创建时间",
          dataIndex: "createTime",
          width: "200px"
        },
        {
          title: "操作",
          dataIndex: "action",
          width: "150px",
          scopedSlots: { customRender: "action" },
        }
      ],
      data:[],
      pagination:{
        current: 1,
        pageSizeOptions: ['5', '10', '15', '20'],
        showQuickJumper: true,
        showSizeChanger: true,
        hideOnSinglePage: true,
        pageSize: 5,
      },
      loading: false

    }
  },
  methods:{
    handerSearch(){

      let searchParams = this.queryParam;
      searchParams.page = this.pagination.current;
      searchParams.rows = this.pagination.pageSize;
      console.log(searchParams)
      $account.queryList(searchParams)
              .then(res =>{
                this.data = res.data.data
                console.log(this.data)
              })
    },
    toggleAdvanced(){
      this.advanced = !this.advanced;
    },
    loadData(flag){
      if (!flag){
        let params = {...this.queryParam}

        params.page = 1;
        params.rows = 5;

        $account.queryList(params)
          .then(res =>{
            this.data = res.data.data
            // this.roles = this.data.roles
            console.log(this.data)
          })
      }
    },
    loadDict(){
      $dict.queryByType("user_sex")
        .then(res =>{
            this.userSexs = res.data
           // console.log(this.userSex);
        })
    },
    //展示角色
    show(roles) {
      let roleArr = [];
      for (let index in roles) {
        roleArr.push(roles[index].roleName);
      }
      return roleArr.join(", ");
    },
    handerEdit(record){
      this.routeShow = true;
      let userId = record.userId
      this.$router.push({
        name: 'AccountEdit',
        query:{
          userId
        }
      })
    },
    handerSave(){
      this.routeShow = true;
      this.$router.push({
        name: 'AccountAdd',
      })
    },
    handerDelete(record){
      let formData = new formatDate();
      formData.append("userId",record.userId);
      this.$confirm({
        title: "提示",
        content: h => {
          console.log(h);
          return h("div", {
            domProps: {
              innerHTML: "你确定要删除这条数据吗?"
            },
            style: {
              fontSize: "16px",
              paddingLeft: "42px",
              paddingBottom: "10px",
              paddingTop: "10px"
            }
          });
        },
        okText: "确定",
        okType: "danger",
        cancelText: "取消",
        onOk: () => {
          return new Promise((resolve, reject) => {
            setTimeout(resolve, 1000);
          })
            .then(()=>{
              $account
                .delete(formData)
                .then(res => {
                  this.$message.success("删除成功");
                  this.loadData(this.routeShow, this);
                })
                .catch(() => {});
            })
            .catch(() => console.log("Oops errors!"));
        },
        onCancel() {
          console.log("Cancel");
        }
      });
    },
    handlerTableChange(){
      this.handerSearch();
    }
  },
  created() {
    this.routeShow = changeShow(this.$route);
    this.loadData(this.routeShow);
    this.loadDict();
  },
  watch: {
    $route(to, from) {
      this.routeShow = changeShow(to);
      this.loadData(this.routeShow, this);
    }
}
}

</script>