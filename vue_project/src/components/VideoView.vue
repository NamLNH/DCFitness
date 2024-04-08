<template>
    <div>
      <h1>Video View Component</h1>
      <div>
        <h2>youtube video ID: {{ this.youtubeLink }}</h2>
        <VideoPlayer :youtubeLink="youtubeLink" />
      </div>
      <div>        
        <CommentsView />
      </div>
    </div>
  </template>
  
  <script>
  import VideoService from '@/services/VideoService';
  import CommentsView from "./CommentsView.vue";
  import VideoPlayer from "./VideoPlayer.vue";
  
  
  export default {
    data() {
      return {
        youtubeLink: null, // Provided YouTube link
      };
    },
    components: {
      CommentsView,
      VideoPlayer,
    },
    mounted(){
      this.getVideoUrl();
    },
    methods:{
      getVideoUrl(){
        const videoID = localStorage.getItem("videoID") ?? 1;
        VideoService.getVideoById(videoID).then(response =>{
          this.youtubeLink = response.data.videoId;
        }).catch(error =>{
          console.log(error);
        })
      }
    }
  };
  </script>
  
  <style></style>
  