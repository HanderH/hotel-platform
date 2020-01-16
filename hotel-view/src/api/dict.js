import $api from './index'
import { axios } from '@/utils/request'

const dict = {

  queryByType: type => {
    return axios({
      url: '/dict/queryByType',
      method: 'get',
      params: {
        type
      }
    })
  },
}

export default dict