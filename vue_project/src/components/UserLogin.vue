<template>
    <WebHeader/>

  <div>
      <h1>User Login:</h1>
      <form>
          <div>
              <label for="studentId">Username:</label>
              <input type="text" id="studentId" v-model="userLoginRequest.username"/>
          </div>
          <div>
              <label for="password">Password:</label>
              <input type="password" id="password" v-model="userLoginRequest.password"/>
          </div>
      </form>
      <button type="submit" @click="login">Login</button>
      <p>{{ message }}</p>
      <router-link to="/signup" ><button>SIGN-UP</button></router-link>
  </div>
</template>
<script>
  import LoginService from "../services/LoginService";
  import WebHeader from "./WebHeader.vue";
  export default {
      data(){
      return{
          userLoginRequest: {username: "", password: ""},
          message: ""
      };
  },
  methods: {
      login(){
          LoginService.login(this.userLoginRequest).then(response => {
            console.log(response)
              var user = response.data;
              console.log(user);
              localStorage.setItem("uid",user.id);
              this.$router.push({name:"homePage"});
          })
          .catch(e => {
            console.log(e)
              this.userLoginRequest.username ="";
              this.userLoginRequest.password ="";
              this.message = e.response.data.message;
              console.log(e.response.data);
          })
      }
  },
  mounted(){
      this.message = "";
  },
  components: {
    WebHeader
  }
  }

</script>