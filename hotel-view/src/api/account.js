import $api from './index'
import { axios } from '@/utils/request'

const account = {
  //查询账号列表
  queryList: searchParams => {
    return axios({
      url: $api.account.queryList,
      method: 'get',
      params: searchParams,
    })
  },
  //添加
  add: params => {
    return axios({
      url: $api.account.add,
      method: 'post',
      data: params,
      headers: {
        'Content-Type': 'application/json;charset=UTF-8'
      }
    })
  },
  queryById:id=>{
    return axios({
      url: $api.account.queryById,
      method: 'get',
      params: {
        userId:id
      },
    })
  },
  //编辑
  edit:params=>{
    return axios({
      url: $api.account.edit,
      method: 'post',
      data: params,
      headers: {
        'Content-Type': 'application/json;charset=UTF-8'
      }
    })
  },
  //禁用
  operator:params=>{
    return axios({
      url: $api.account.operator,
      method: 'post',
      data: params,
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    })
  },
  //删除
  delete:params=>{
    return axios({
      url: $api.account.delete,
      method: 'post',
      data: params,
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    })
  },
  //查询该账户的全部权限
  allPermission:userId=>{
    return axios({
      url: $api.account.allPermission,
      method: 'get',
      params: {
        userId:userId
      },

    })
  },
  //判断是否有userCode重复
  hasUserCodeRepeat:userCode=>{
    return axios({
      url: $api.account.hasUserCodeRepeat,
      method: 'get',
      params: {
        userCode            },

    })
  }

}
export default account