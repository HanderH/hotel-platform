import $api from './index'
import { axios } from '@/utils/request'
// 表单请求 'Content-Type': 'application/x-www-form-urlencoded'
// json请求 'Content-Type': 'application/json;charset=UTF-8'
// 文档上传 'Content-Type': 'multipart/form-data'
const menu ={
     //查询全部角色
     queryMenuTree: () => {
        return axios({
            url: $api.menu.queryMenuTree,
            method: 'get',
        })
    },
    queryAllPermissionList: () => {
        return axios({
            url: $api.menu.queryAllPermissionList,
            method: 'get',
        })
    },
}

export default menu