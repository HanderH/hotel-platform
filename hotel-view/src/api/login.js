import api from './index'
import { axios } from '@/utils/request'

/**
 * login func
 * parameter: {
 *     username: '',
 *     password: '',
 *     remember_me: true,
 *     captcha: '12345'
 * }
 * @param parameter
 * @returns {*}
 */
export function login (parameter) {
  return axios({
    url: '/login',
    method: 'post',
    data: parameter,
    headers:{
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}

export function getSmsCaptcha (parameter) {
  return axios({
    url: api.SendSms,
    method: 'post',
    data: parameter
  })
}

export function getInfo (params) {
  return axios({
    url: '/user/info',
    method: 'post',
    data:params,
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}

export function getCurrentUserNav (token) {
  return axios({
    url: '/user/nav',
    method: 'get'
  })
}

export function logout (params) {
  return axios({
    url: '/logout',
    data: params,
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}

/**
 * get user 2step code open?
 * @param parameter {*}
 */
export function get2step (parameter) {
  return axios({
    url: api.twoStepCode,
    method: 'post',
    data: parameter
  })
}
export function register (parameter) {
  return axios({
    url: '/register',
    method: 'post',
    data: parameter,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}
