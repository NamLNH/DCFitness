<template>
    <div>
      <h1>Video View Component</h1>
      <div>
        <VideoPlayer :youtubeLink="youtubeLink" />
        <CommentsView />
      </div>
    </div>
  </template>
  
  <script>
  import CommentsView from "./CommentsView.vue";
  import VideoPlayer from "./VideoPlayer.vue";
  
  export default {
    data() {
      return {
        youtubeLink: "https://www.youtube.com/watch?v=6JYIGclVQdw", // Provided YouTube link
      };
    },
    components: {
      CommentsView,
      VideoPlayer,
    },
  };
  </script>
  
  <style></style>
  