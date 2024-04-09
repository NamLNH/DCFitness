<template>
    <div>
      <h2>Video Player</h2>
      <div class="video-container">
        <iframe
          width="560"
          height="315"
          :src="embedUrl"
          frameborder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
          allowfullscreen
        ></iframe>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      youtubeLink: String,
    },
    computed: {
      embedUrl() {
        // Extract video ID from the YouTube link
        // Nam commented it
        //const videoId = this.extractVideoId(this.youtubeLink);
        // Construct the embed URL for the YouTube video
        return `https://www.youtube.com/embed/${this.youtubeLink}`;
      },
    },
    methods: {
        extractVideoId(link) {
  // Extract video ID from YouTube link
  const regExp = /^.*(youtu\.be\/|v\/|u\/\w\/|embed\/|watch\?v=|&v=)([^#&?]*).*/;
  const match = link.match(regExp);
  if (match && match[2].length === 11) {
    return match[2];
  } else {
    // Handle invalid YouTube link
    console.error('Invalid YouTube link');
    return '';
  }
}
    },
  };
  </script>
  
  <style scoped>
  .video-container {
    position: relative;
    width: 100%;
    padding-top: 56.25%; /* 16:9 Aspect Ratio (divide 9 by 16 = 0.5625) */
  }
  
  iframe {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
  }
  </style>
  