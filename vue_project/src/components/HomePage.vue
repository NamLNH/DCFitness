<template>
  <div>
    <article>
      <section class="main">
        <div>
          <h2>Videos</h2>
          <div v-if="videos.length === 0">No videos yet.</div>
          <ul v-else>
            <li v-for="(video, index) in videos" :key="index">
              <div>
                <img :src="video.thumbnail" alt="Thumbnail" />
              </div>
              <div class="">
                <div class="">{{ video.title }}</div>
                <div class="">{{ video.category.name }}</div>                
              </div>
            </li>
          </ul>
        </div>
      </section>
      <section class="side">
        <div>This is side part</div>
      </section>
    </article>
  </div>
</template>
  
  <script>
import VideoService from "@/services/VideoService";

export default {
  data() {
    return {
      videos: [],
      error: null
    };
  },
  mounted() {
    this.getAllVideos();
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
    addVideosToRender(videos) {
      videos.forEach((i) => {
        const title = i.title ?? ""; // video title
        const url = i.url ?? ""; // url
        const category = i.category.name ?? ""; // video category
        const thumbnail = i.thumbnail ?? ""; //video thumbmail
        const uploadDate = i.uploadDate ?? "";
        const author = i.author ?? "";
        this.videos.push({
          title,
          url,
          thumbnail,
          uploadDate,
          author,
          category,
        });
      });
    },
  },
};
</script>
  
  <style scoped>
/**Nam css */
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
</style>