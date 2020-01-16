import $menu from "@/api/menu"

const menu = {
    state: {
        permissionList: undefined,
    },

    mutations: {
        SET_PERMISSION_LIST: (state, data) => {
            state.permissionList = data
        },
    },

    actions: {
        GetPermissionList: ({ commit, state }) => {
            if (state.city) {
                return new Promise(resolve => {
                    resolve(state.city)
                })
            } else {
                return new Promise(resolve => {
                    $menu.queryAllPermissionList().then(res => {
                        let list =res.data.push('exception', 'table', 'form')
                        commit('SET_PERMISSION_LIST', res.data)
                        resolve(res.data)
                    })
                })
            }
        },
    },
    getters: {
        permissionList: state => state.permissionList,
        
    }
}
export default menu