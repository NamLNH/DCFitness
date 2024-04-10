<template>
  <WebHeader/>
    
    <div class="container">
      <h1 v-if="userInsider">Good Day: {{ userInsider.username }}</h1>
      <div class="info">
        <h4 v-if="userInsider">Email: {{ userInsider.email }}</h4>
        <h4 v-if="userInsider">ID: {{ userInsider.id }}</h4>
      </div>
    </div>    
  
</template>

<script>
import UserDataService from "@/services/UserDataService";
import WebHeader from "./WebHeader.vue";


export default {
  data() {
    return {
      userInsider: null,
      videos: [],
    };
  },
  mounted() {
    this.retrieveUser();
    
  },

  methods: {
    retrieveUser() {
      const uid = localStorage.getItem("uid");
      console.log("User ID is: " + uid);
      UserDataService.get(uid)
        .then((response) => {
          console.log(response);
          this.userInsider = response.data;
          console.log(this.userInsider);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    logout(event) {
      event.preventDefault();
      localStorage.removeItem("uid");
    }    
  },
  components:{
    WebHeader
  }
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

h1 {
  color: rgb(61, 13, 13);
  text-align: center;
}

h4 {
  color: rgb(152, 68, 100);
  margin: 0;
}
</style>