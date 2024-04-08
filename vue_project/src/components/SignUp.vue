<template>
    <div>
        <h1>Please Sign up:</h1>
        <form>
            <div>
                <label for="studentId">Username:</label>
                <input type="text" id="studentId" v-model="userNewRegister.username"/>
            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" id="password" v-model="userNewRegister.password"/>
            </div>
            <div>
                <label for="email">Email:</label>
                <input type="text" id="email" v-model="userNewRegister.email"/>
            </div>
        </form>
        <button type="submit" @click="signup">Ready to register</button>
        <p>{{ message }}</p>
    </div>
  </template>
<script>
  import LoginService from "../services/LoginService";
  export default {
    data(){
        return{
            userNewRegister: {username: "", password: "",email:""},
            message: ""
        };
    },
    methods:{
        signup(){
            LoginService.signup(this.userNewRegister).then(response => {
                console.log(response)
                var newUser = response.data;
                console.log(newUser);
                localStorage.setItem("uid",newUser.id);
                this.$router.push({name:"userProfile"});
            })
            .catch(e =>  {
                console.log(e)
                    this.userNewRegister.username ="";
                    this.userNewRegister.password ="";
                    this.userNewRegister.email ="";
                    this.message = e.response.data.message;
                    console.log(e.response.data);
            })
        }
    },
    mounted(){
        this.message = "";
    }
  }
</script>