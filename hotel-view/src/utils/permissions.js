export function actionToObject (json) {
  try {
    return JSON.parse(json)
  } catch (e) {
    console.log('err', e.message)
  }
  return []
}
export function isSuper(roles){
  for(let index in roles){
    let role = roles[index]
    if(role.roleType=='S'){
      return true
    }
  }
  return false
}