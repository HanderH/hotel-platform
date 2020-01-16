<template>
    <a-card :bordered="false">
      <a-form layout="horizontal" :form="form" @submit="handleSubmit">
        <a-input type="hidden" v-model="userId" />
        <a-row :gutter="48">
          <a-col :md="24" :sm="24">
            <a-form-item label="账号姓名" :label-col="{span:8}" :wrapper-col="{md:9,sm:24}">
              <a-input
                placeholder="请输入账号姓名"
                v-decorator="['userName',
             { rules:
              [{ required: true, message: '请输入账号姓名' }],
              }]"
              />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="24" :sm="24">
            <a-form-item label="身份证号" :label-col="{span:8}" :wrapper-col="{md:9,sm:24}">
              <a-input
                :disabled="!isAdd"
                placeholder="请输入身份证号"
                v-decorator="['userCode',
             { rules:
              [{required:'true',message:'请输入身份证号'},{validator:(rule, value, callback)=>{
                hasUserCodeRepeat(value,callback)
              }}],validateTrigger:'blur'
              }]"
              />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="24" :sm="24">
            <a-form-item label="账号密码" :label-col="{span:8}" :wrapper-col="{md:9,sm:24}">
              <a-input
                type='password'
                placeholder="请输入账号密码"
                v-decorator="['userPassword',
             { rules:
              [{ required: true, message: '请输入密码'}],
              }]"
              />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="24" :sm="24">
            <a-form-item label="联系电话" :label-col="{span:8}" :wrapper-col="{md:9,sm:24}">
              <a-input
                placeholder="请输入联系电话"
                v-decorator="['userTel',
             { rules:
              [{ required: true, message: '请输入正确的手机号码' ,pattern:new RegExp('^1(3|4|5|7|8)\\d{9}$')}],
              }]"
              />
            </a-form-item>
          </a-col>
        </a-row>

        <a-row :gutter="48">
          <a-col :md="24" :sm="24">
            <a-form-item label="账号状态" :label-col="{span:8}" :wrapper-col="{md:12,sm:24}">
              <a-switch
                checkedChildren="启用"
                unCheckedChildren="禁用"
                :defaultChecked="true"
                v-decorator="['userStatus',
             { rules:[{ required: true }]}]
              "
              />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="24" :sm="24">
            <a-form-item label="账号角色" :labelCol="{span:8}" :wrapperCol="{md:9,sm:24}">
              <a-select
                mode="multiple"
                placeholder="请选择"
                :allowClear="true"
                :wrapperCol="{md:12,sm:24}"
                v-decorator="['roles',
             { rules:
              [{ required: true,message:'请选择角色'}],
              }]"
              >
                <a-select-option
                  v-for="(data ,index) in userRoles"
                  :key="index"
                  :value="data.codeValue"
                >{{data.codeName}}</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>

        <a-row :gutter="48">
          <a-col :md="24" :sm="24" style="text-align:center">
            <a-form-item>
              <a-button @click="handlerCancel">取消</a-button>
              <a-button
                :loading="spinning"
                type="primary"
                html-type="submit"
                style="margin-left:16px"
              >保存</a-button>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </a-card>
</template>

<script>
  import $role from "@/api/role";
  import $account from "@/api/account";
  import $dict from "@/api/dict";
  export default {
    name: "AccountAdd",
    data() {
      return {
        userId: "",
        fetching: false,
        //是否为添加
        isAdd: false,
        //加载状态
        spinning: false,
        //角色列表
        userRoles: [],
        //表单对象
        form: this.$form.createForm(this)
      };
    },
    methods: {
      //form提交
      handleSubmit(e) {
        e.preventDefault();
        this.form.validateFields((err, values) => {
          console.log('values',values)
          if (!err) {
            this.spinning = true;
            let params = { ...values };
            params.userId = this.userId;
            /*if (params.userRoles.constructor == String) {
              params.userRoles = [params.userRoles];
            }*/
            console.log(params)
            if (this.isAdd) {
              $account
                .add(params)
                .then(res => {
                  this.spinning = false;
                  this.$router.push({
                    name: "AccountConfig"
                  });
                  this.$message.success("保存成功", 2);
                })
                .catch(msg => {
                  this.$message.warn(msg, 2);
                })
                .finally(() => {
                  this.spinning = false;
                });
            } else {
              $account
                .edit(params)
                .then(res => {
                  this.spinning = false;
                  this.$router.push({
                    name: "AccountConfig"
                  });
                  this.$message.success("修改成功", 2);
                })
                .catch(() => {});
            }
          }
        });
      },
      handlerCancel() {
        this.$router.go(-1);
      },
      async hasUserCodeRepeat(value, callback) {
        if (this.isAdd) {
          let count = await $account.hasUserCodeRepeat(value).then(res => {
            return res.data;
          });
          if (count > 0) {
            callback("已存在身份证号，请重新输入");
          } else {
            callback();
          }
        }else{
          callback()
        }
      },
      loadDict(){
        $dict.queryByType("role_type")
          .then(res =>{
            this.userRoles = res.data
            console.log(this.userRoles)
          })
      }
    },
    //钩子
    created() {

  /*    $role.queryAll()
        .then(res => {
          this.userRoles = res.data;
        })
        .catch(() => {});*/

      this.loadDict();
      if (this.$route.query.userId) {
        this.isAdd = false;
        this.spinning = true;
        $account
          .queryById(this.$route.query.userId)
          .then(res => {
            let data = res.data;
            console.log(data)
            this.form.setFieldsValue({
              userName: data.userName,
              userCode: data.userCode,
              userTel: data.userTel,
              userStatus: data.userStatus,
              roles: data.roles,
            });
            this.userId = data.userId;
            this.spinning = false;
          })
          .catch(() => {})
      } else {
        this.isAdd = true;
      }
    },
  };
</script>