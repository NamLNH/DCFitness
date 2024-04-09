<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark py-4">
    <div class="container">
      <a class="navbar-brand" :href="'/home'"
        ><img src="@/assets/logo.png" alt="Logo" width="60" height="50"
      /></a>
      <a class="navbar-brand" :href="'/home'">DC Fitness</a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNavAltMarkup"
        aria-controls="navbarNavAltMarkup"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="header navbar-nav ms-auto">
          <template v-if="currentUser">
            <a class="nav-link active" :href="'/home'">Home</a>
            <a class="nav-link active" :href="'/profile'">Account</a>
            <a class="nav-link active" :href="'/home'" @click="logout()">Logout</a>
            
          </template>
          <template v-else>
            <a class="nav-link active" :href="'/login'">Login</a>
            <a class="nav-link active" :href="'/signup'">Register</a>
          </template>
        </div>
      </div>
    </div>
  </nav>
  
</template>

<script>
import UserDataService from "@/services/UserDataService";

export default {
  data() {
    return {
      currentUser: null,
    };
  },
  mounted() {
    this.fetchCurrentUser();
  },

  methods: {
    logout() {
      localStorage.removeItem("uid");
      this.$router.push({ name: "homePage" });
    },
    fetchCurrentUser() {
      const uid = localStorage.getItem("uid");
      UserDataService.get(uid)
        .then((response) => {
          this.currentUser = response.data;
        })
        .catch((error) => {
          console.error("Error fetching user:", error);
        });
    },
  },
};
</script>

<style>
</style>