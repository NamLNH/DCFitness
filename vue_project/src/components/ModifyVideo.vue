<template>
  <!--Header for web-->
  <WebHeader/>

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
                name="name"
                :value="this.videoTitle"
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
                :value="this.videoUrl"
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
              <select name="category" class="form-control" required>
                <option value="1">Option 1</option>
                <option value="2">Option 2</option>
                <option value="3">Option 3</option>
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
                :value="this.videoAuthor"
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
          <button
            type="submit"
            class="btn btn-primary btn-update"
            name="action"
            value="update"
          >
            Update Book
          </button>
        </div>
        
      </div>
    </form>
  </div>
  
</template>

<script>
import WebHeader from "@/components/WebHeader.vue"
import VideoService from '@/services/VideoService';

export default {
  data() {
    return{
        video: null,
        videoTitle: null,
        videoUrl: null,
        videoAuthor: null,
        videoCategory: null
    }  

  },
  components :{
    WebHeader
  },
  methods: {
    getVideoById(){
        const videoID = localStorage.getItem("videoID") ?? 1;
        VideoService.getVideoById(videoID).then(response =>{
          this.videoTitle = response.data.title;
          this.videoUrl = response.data.url;
          this.videoAuthor = response.data.author;

        }).catch(error =>{
          console.log(error);
        })
    }
  },
  mounted(){
    this.getVideoById();
  }
};
</script>

<style>
</style>