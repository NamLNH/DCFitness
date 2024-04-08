import http from "../http-common.js";

class LoginService{
    login(data){
        console.log(data);
        return http.post("/login",data);
    }
    signup(data){
        return http.post("/users/signup",data);
    }

}

export default new LoginService();