import http from "../http-common.js";

class UserDataService{
    get(uid){
        return http.get(`/users/${uid}`);
    }
}
export default new UserDataService();