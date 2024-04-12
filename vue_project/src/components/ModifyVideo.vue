<template>
  <!--Header for web-->
  <WebHeader />

  <!--end of header-->
  <div class="add-title" style="margin-left: 20px; margin-right: 20px">
    <h2>Modify video</h2>
  </div>

  <div class="card-body" style="margin-left: 20px; margin-right: 20px">
    <form method="" action="">
      <div class="row">
        <div class="col">
          <div class="mb-3 row">
            <label
              class="col-lg-2 col-md-6 col-sm-12 col-form-label title-label"
              >Title:</label
            >
            <div class="col-lg-10 col-md-6 col-sm-12 title-input">
              <input
                v-model="this.videoTitle"
                type="text"
                class="form-control"
                required
              />
            </div>
          </div>
        </div>
        <div class="col">
          <div class="mb-3 row">
            <label class="col-lg-2 col-md-6 col-sm-12 col-form-label"
              >Url:
            </label>
            <div class="col-lg-10 col-md-6 col-sm-12">
              <input
                name="isbn"
                v-model="this.videoUrl"
                type="text"
                class="form-control"
                required
              />
            </div>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col">
          <div class="mb-3 row">
            <label class="col-lg-2 col-md-6 col-sm-12 col-form-label"
              >Category:
            </label>
            <div class="col-lg-10 col-md-6 col-sm-12">
              <select
                v-model="this.videoCategory"
                class="form-control"
                required
              >
                <option
                  v-for="(category, index) in categories"
                  :key="index"
                  v-bind:value="{ id: category.id, name: category.name }"
                  :selected="this.videoCategory.name === category.name"
                >
                  {{ category.name }}
                </option>
              </select>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="mb-3 row">
            <label class="col-lg-2 col-md-6 col-sm-12 col-form-label"
              >Author:
            </label>
            <div class="col-lg-10 col-md-6 col-sm-12">
              <input
                name="quantity"
                v-model="this.videoAuthor"
                type="text"
                class="form-control"
                required
              />
            </div>
          </div>
        </div>
      </div>
      <div class="submit-div">
        <div class="btn update-div">
          <button class="btn btn-primary btn-update" @click="updateVideo">
            Update Book
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import WebHeader from "@/components/WebHeader.vue";
import VideoService from "@/services/VideoService";
import CategoryService from "@/services/CategoryService";

export default {
  data() {
    return {
      video: null,
      videoTitle: null,
      videoUrl: null,
      videoAuthor: null,
      videoCategory: {},
      categories: [],
    };
  },
  components: {
    WebHeader,
  },
  methods: {
    getVideoById() {
      const videoID = localStorage.getItem("videoID") ?? 1;
      VideoService.getVideoById(videoID)
        .then((response) => {
          this.videoTitle = response.data.title;
          this.videoUrl = response.data.url;
          this.videoAuthor = response.data.author;
          this.videoCategory = response.data.category;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getCategories() {
      CategoryService.getCategories()
        .then((response) => {
          this.categories = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateVideo() {
      const id = localStorage.getItem("videoID") ?? 1;
      const videoThumbnail =
        "https://i.ytimg.com/vi/" +
        this.videoUrl.substring(17, 28) +
        "/default.jpg";
      const videoId = this.videoUrl.substring(17, 28);

      const body = {
        title: this.videoTitle,
        url: this.videoUrl,
        author: this.videoAuthor,
        thumbnail: videoThumbnail,
        videoId: videoId,
        category: this.videoCategory,
      };

      VideoService.updateVideoByID(id, body)
        .then((response) => {
          console.log(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
      this.$router.push({ name: "homePage" });
    },
  },
  mounted() {
    this.getVideoById();
    this.getCategories();
  },
};
</script>

<style>
</style>