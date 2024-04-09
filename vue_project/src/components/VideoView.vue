<template>
    <WebHeader/>
    <div>
      <h1>Video View Component</h1>
      <div>
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
import WebHeader from './WebHeader.vue';
  
  
  export default {
    data() {
      return {
        youtubeLink: null, // Provided YouTube link
      };
    },
    components: {
      CommentsView,
      VideoPlayer,
      WebHeader
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
  