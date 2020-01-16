const api = {
  // Login: '/auth/login',
  // Logout: '/auth/logout',
  // ForgePassword: '/auth/forge-password',
  // // Register: '/auth/register',
  // twoStepCode: '/auth/2step-code',
  // SendSms: '/account/sms',
  // SendSmsErr: '/account/sms_err',
  // get my info
  // UserInfo: '/user/info'
  //账户
  account: {
    queryList: '/user/queryAllUserByPage',
    add: '/user/add',
    queryById: '/user/queryById',
    edit: '/user/edit',
    operator: '/user/operator',
    delete: '/user/delete',
    allPermission: '/user/all_permission',
    hasUserCodeRepeat:'/user/hasUserCodeRepeat'
  },
  //角色
  role: {
    queryAll: '/api/role/queryAll',
    queryList: '/role/list',
    operator: '/role/operator',
    add: '/role/add',
    queryById: '/role/query_by_id',
    delete: '/role/delete',
    edit: '/role/edit'
  },
}
export default api
