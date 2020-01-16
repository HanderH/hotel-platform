import $api from "@/api/index"
import { axios } from '@/utils/request'
// 表单请求 'Content-Type': 'application/x-www-form-urlencoded'
// json请求 'Content-Type': 'application/json;charset=UTF-8'
// 文档上传 'Content-Type': 'multipart/form-data'

const role = {
    //查询全部角色
    queryAll: () => {
        return axios({
            url: '/api/role/queryAll',
            method: 'get',
        })
    },
    //列表查询
    queryList:(searchParams)=>{
        return axios({
            url: $api.role.queryList,
            method: 'get',
            params:searchParams,
        })
    },
    //操作角色状态
    operator:params=>{
        return axios({
            url: $api.role.operator,
            method: 'post',
            data: params,
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            }
        })
    },
    //删除角色
    delete:params=>{
        return axios({
            url: $api.role.delete,
            method: 'post',
            data: params,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
    },
    //添加角色
    add:params=>{
        return axios({
            url: $api.role.add,
            method: 'post',
            data: params,
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            }
        })
    },
    //通过id查询角色
    queryById:roleId=>{
        return axios({
            url: $api.role.queryById,
            method: 'get',
            params: {
                roleId:roleId
            },
        })
    },
    //编辑
    edit:params=>{
        return axios({
            url: $api.role.edit,
            method: 'post',
            data: params,
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            }
        })
    }

}
export default role