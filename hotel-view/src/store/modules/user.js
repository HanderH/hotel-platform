import Vue from 'vue'
import { login, getInfo, logout } from '@/api/login'
import { ACCESS_TOKEN } from '@/store/mutation-types'
import { welcome } from '@/utils/util'

const user = {
  state: {
    token: '',
    name: '',
    welcome: '',
    avatar: '',
    roles: [],
    info: {},
    permissions:[],
    permissionList:[],
  },

  actions: {
    // 登录
    Login ({ commit }, userInfo) {
      return new Promise((resolve, reject) => {
        let data = new FormData()
        data.append('userName', userInfo.username)
        data.append('userPassword', userInfo.password)
        // console.log(userInfo+"=================")
        login(data).then(response => {
          const token = response.data
          // console.log(token)
          Vue.ls.set(ACCESS_TOKEN, token, 7 * 24 * 60 * 60 * 1000)
          commit('SET_TOKEN', token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    GetInfo ({ commit }) {
      return new Promise((resolve, reject) => {
        let data = new FormData()
        data.append('token',Vue.ls.get(ACCESS_TOKEN))
        getInfo(data).then(response => {
          const result = response.data
          // console.log(result)
          // console.log(result.permissions)
          // console.log(result.permissionList)
          // console.log(result.user)
          //所有人都拥有异常权限
          result.permissionList.push('exception', 'table', 'form')
          if (result.roles && result.roles.length > 0) {
            // role.permissionList = role.permissions.map(permission => { return permission.permissionId })
            commit('SET_ROLES', result.roles)
          } else {
            reject(new Error('getInfo: roles must be a non-null array !'))
          }
          commit('SET_PERMISSIONS',result.permissions);
          commit('SET_PERMISSIONLIST',result.permissionList);
          commit('SET_NAME', { name: result.user.userName, welcome: welcome() });
          commit('SET_AVATAR','/avatar2.jpg');
          commit('SET_INFO', result.user);
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 登出
    Logout ({ commit, state }) {
      return new Promise((resolve) => {
        let data = new FormData()
        data.append('token',state.token)
        console.log(data)
        logout(data).then(() => {
          resolve()
        }).catch(() => {
          resolve()
        }).finally(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          Vue.ls.remove(ACCESS_TOKEN)
        })
      })
    }

  },

  mutations: {
    SET_NAME: (state, { name, welcome }) => {
      state.name = name
      state.welcome = welcome
    },
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_INFO: (state, info) => {
      state.info = info
    },
    SET_PERMISSIONS:(state, permissions)=> {
      state.permissions = permissions;
    },
    SET_PERMISSIONLIST:(state, permissionList)=> {
      state.permissionList = permissionList;
    }

  }
}

export default user
