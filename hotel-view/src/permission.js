import Vue from 'vue'
import router from './router'
import store from './store'

import NProgress from 'nprogress' // progress bar
import '@/components/NProgress/nprogress.less' // progress bar custom style
import notification from 'ant-design-vue/es/notification'
import { setDocumentTitle, domTitle } from '@/utils/domUtil'
import { ACCESS_TOKEN } from '@/store/mutation-types'
import { copiedAsyncMap } from '@/config/router.config'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['login', 'register', 'registerResult'] // no redirect whitelist
const defaultRoutePath = '/dashboard/workplace'

router.beforeEach((to, from, next) => {
  NProgress.start() // start progress bar
  to.meta && (typeof to.meta.title !== 'undefined' && setDocumentTitle(`${to.meta.title} - ${domTitle}`))
  if (Vue.ls.get(ACCESS_TOKEN)) {
    /* has token */
    if (to.path === '/user/login') {
      next({ path: defaultRoutePath })
      NProgress.done()
    } else {
      if (store.getters.roles.length === 0) {
        store
          .dispatch('GetInfo')
          .then(res => {
              const permissionList = res.data.permissionList
              // console.log(permissionList)
              store.dispatch('GenerateRoutes', { permissionList }).then(() => {
                // 根据roles权限生成可访问的路由表
                // 动态添加可访问路由表
                router.addRoutes(store.getters.addRouters)
                const redirect = decodeURIComponent(from.query.redirect || to.path)
                if (to.path === redirect) {
                  // hack方法 确保addRoutes已完成 ,set the replace: true so the navigation will not leave a history record
                  next({ ...to, replace: true })
                } else {
                  // 跳转到目的路由
                  next({ path: redirect })
                }
              })

          })
          .catch(() => {
            notification.error({
              message: '错误',
              description: '请求用户信息失败，请重试'
            })
            store.dispatch('Logout').then(() => {
              next({ path: '/user/login', query: { redirect: to.fullPath } })
            })
          })
      } else {
        let flag = hasRoute(copiedAsyncMap,to)
          //to.matched:数组，包含当前路由的所有嵌套路径片段的路由记录
         // 路由记录就是 routes 配置数组中的对象副本 (还有在 children 数组)。
        if(flag &&to.matched.length==0){
          next({
            name:'Exception403'
          })
        }else if(!flag){
          next({
            name:'Exception404'
          })
        }else{
          next()
        }
      }
    }
  } else {
    if (whiteList.includes(to.name)) {
      // 在免登录白名单，直接进入
      next()
    } else {
      next({ path: '/user/login', query: { redirect: to.fullPath } })
      NProgress.done() // if current page is login will not trigger afterEach hook, so manually handle it
    }
  }
})
//判断是否存在该路由
function hasRoute(asyncRouterMap,tarRoute){
  // console.log(asyncRouterMap)
  for(let index = 0 ;index<asyncRouterMap.length;index++){
    if(asyncRouterMap[index].path.startsWith(tarRoute.path)){
      return true
    }else if(asyncRouterMap[index].children && asyncRouterMap[index].children.length){
      if(hasRoute(asyncRouterMap[index].children,tarRoute)){
        return true
      }
    }
  }
  return false
}
router.afterEach(() => {
  NProgress.done() // finish progress bar
})
