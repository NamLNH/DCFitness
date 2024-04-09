<template>
  <div>
    <article>
      <section class="main">
        <div class="data">
          <h2 class="book-table-title">All videos</h2>
          <table class="book-table">
            <thead>
              <tr>
                <th class="table-cover">Thumbnail</th>
                <th>Title</th>
                <th>Category</th>
                <th>Author</th>
                <th v-if="userRole === 'admin'">Delete</th>
                <th v-if="userRole === 'admin'">Modify</th>
                <!-- <th v-if="this.currentUser.role =='admin'">Modify</th> -->
              </tr>
            </thead>
            <tbody>
              <tr v-for="(video, index) in videos" :key="index">
                <td class="table-cover">
                  <span><img :src="video.thumbnail" alt="Thumbnail" /></span>
                </td>
                <td class="author" @click="toVideo(video.id)">
                  <a :href="'/video'"
                    ><strong> {{ video.title }}</strong></a
                  >
                </td>
                <td>{{ video.category.name }}</td>
                <td>{{ video.author }}</td>
                <td v-if="userRole == 'admin'">
                  <button class="detail" @click="deleteVideo(video.id)">
                    Delete
                  </button>
                </td>
                <td v-if="userRole == 'admin'">
                  <button class="borrow" @click="modifyVideo()">
                    Modify
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </section>
      <section class="side">
        <div>
          <h2 class="book-table-title">
            This is side part for chosing video by category
          </h2>
        </div>
      </section>
    </article>
  </div>
</template>
  
<script>
import VideoService from "@/services/VideoService";
import UserDataService from "@/services/UserDataService";

export default {
  data() {
    return {
      videos: [],
      error: null,
      currentUser: null,
      userRole: null,
    };
  },
  mounted() {
    this.getAllVideos();
    this.fetchCurrentUser();
  },

  methods: {
    getAllVideos() {
      VideoService.getVideos()
        .then((response) => {
          console.log(response.data);
          // this.addVideosToRender(response.data);
          this.videos = response.data;
        })
        .catch((error) => {
          this.error = error;
          console.error("Error fetching videos:", error);
        });
    },
    toVideo(id) {
      localStorage.setItem("videoID", id);
      this.$router.push({ name: "videoView" });
    },
    fetchCurrentUser() {
      const uid = localStorage.getItem("uid");
      UserDataService.get(uid)
        .then((response) => {
          this.currentUser = response.data;
          this.userRole = this.currentUser.role;
        })
        .catch((error) => {
          console.error("Error fetching user:", error);
        });
    },
    modifyVideo(id){
      localStorage.setItem("videoID", id);
      this.$router.push({ name: "modifyVideo" });
    },
    deleteVideo(id){
      VideoService.deleteVideoById(id)
      .then(response =>{
        this.videos = response.data;
        console.log("deleted");
      }).catch (error =>{
        console.log(error);
      })
      this.$router.push({ name: "homePage" });

    }
  },
};
</script>
  
  <style scoped>
/**Nam css */
body {
  margin: auto;
}

.header a {
  margin-left: 50px;
  display: block;
}

article {
  padding: 0 5px;
  display: grid;
  grid-template-columns: 55% 45%;
  grid-template-rows: unset;
}

section {
  padding: 0 10px;
}
section.main {
  grid-row: 1/2;
  grid-column: 1/2;
  border-right: 1px dotted black;
  display: flex;
  flex-wrap: wrap;
  margin: 2px;
}

section.main > div {
  margin: 10px;
  flex-grow: 1;
  flex-shrink: 1;
  flex-basis: 55%;
  align-items: baseline;
}
section.side {
  grid-row: 1/2;
  grid-column: 2/3;
  display: flex;
  flex-wrap: wrap;
  margin: 10px;
}

.detail-board {
  background-color: darkblue;
  border-radius: 10px;
  color: white;
}

.book-detail {
  vertical-align: middle;
  width: 100%;
  margin: 0 auto;
  padding-left: 20px;
}

.show-detail {
  margin-bottom: 8px;
}

.detail-image {
  max-width: 150px;
}

.detail-img {
  max-width: 100px;
  height: auto;
  margin: 20px;
}

.detail-name {
  text-align: center;
  font-size: 1.2em;
  padding-top: 20px;
  font-size: x-large;
  width: 80%;
}

section.side > div {
  margin: 10px;
  flex-grow: 1;
  flex-shrink: 1;
  flex-basis: 30%;
}

div.detail-board {
  order: 1;
}

div.data {
  order: 3;
  align-self: baseline;
}

h2.book-table-title {
  font-family: Helvetica, Arial, sans-serif;
  margin: 10px 10px 20px 10px;
}

.book-table {
  border-collapse: collapse;
  padding: 5px;
  width: 100%;
  margin-top: 30px;
}

.book-table td,
.book-table th {
  border-collapse: collapse;
  border-bottom: 1px solid rgb(126, 126, 126);
  padding: 5px;
  text-align: center;
}

.book-table .table-cover {
  text-align: center !important;
  width: 90px;
}

.book-table th {
  padding: 5px 5px;
  color: white;
  font-weight: bold;
  text-align: center;
}

.book-table td {
  font-size: 0.9em;
  padding: 5px;
  line-height: 1.4em;
}

.book-table thead {
  background-color: teal;
  line-height: 1.5em;
}

td .author {
  font-size: large;
  margin-bottom: 5px;
}

.detail,
.borrow {
  padding: 5px;
  border-radius: 3px;
  color: white;
  box-shadow: 2px 2px 2px gray;
}

.detail {
  background-color: #db9722;
}

.borrow {
  background-color: blue;
}

span img {
  width: 50px;
}

.detail-cancel {
  text-decoration: none;
  color: white;
}

.detail-header {
  display: grid;
  grid-template-rows: unset;
  grid-template-columns: 90% 10%;
}

.borrow-title {
  margin-bottom: 0 !important;
  height: 36.28px !important;
}

.borrow-table th {
  background-color: blue;
}

.detail-cancel {
  margin-top: 20px;
  width: 30px;
  height: 30px;
  border: none;
  background-color: #007bff;
  color: white;
  font-size: 20px;
  font-weight: bold;
  line-height: 30px;
  text-align: center;
  cursor: pointer;
}

.detail-cancel:hover {
  background-color: rgba(255, 255, 255, 0.3);
  border-radius: 10%;
}

.nav-link:hover {
  background-color: grey;
}
</style>