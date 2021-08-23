import IWebSaas from '@jangod/iweb-saas'
import actions from './actions'
import mutations from './mutations'
import getters from './getters'
/**
 * 使用示例:
 state:{
      code:""
   }
 actions文件:
 setCode: ({commit}, code) =>{
            commit("code",code)
        }
 getters文件:
 getCode(state){
            return state.code;
        }
 mutations文件:
 code: (state,code)=>{
            state.code = code;
        }
 获取：this.$store.getters.getCode
 写入：this.$store.dispatch("setCode",val)
 */
export function createStore () {
    let store = new IWebSaas.Store({
        state:{
        },
        actions,
        getters,
        mutations
    })
    return store
}
