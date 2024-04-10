<template>
    <!--Header for web-->
    <WebHeader/>
  
    <!--end of header-->
    <div class="add-title" style="margin-left: 20px; margin-right: 20px">
      <h2>Add new video</h2>    
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
                  placeholder="Enter title for video"
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
                v-model="this.videoUrl"
                  placeholder="Enter URL of video"
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
                <select v-model="this.videoCategory" class="form-control" required>
                    <option v-for="(category, index) in categories" :key="index" v-bind:value="{'id':category.id,'name':category.name}">
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
                v-model="this.videoAuthor"
                  placeholder="Enter author of video"
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
              
              class="btn btn-primary btn-update"
              @click="addVideo"
            >
              Add Book
            </button>
          </div>
          
        </div>
      </form>
    </div>
  </template>
  
  <script>
  import WebHeader from "@/components/WebHeader.vue";
  //import VideoService from '@/services/VideoService';
  import CategoryService from '@/services/CategoryService';
import VideoService from '@/services/VideoService';
  
  export default {
    data() {
      return{
          videoTitle: null,
          videoUrl: null,
          videoAuthor: null,
          videoCategory: null,
          categories: [],
          
      }  
  
    },
    components :{
      WebHeader
    },
    methods: {
      getCategories(){
          
          CategoryService.getCategories().then(response =>{
            this.categories = response.data
  
          }).catch(error =>{
            console.log(error);
          })
      },
      addVideo(){
        const videoThumbnail = "https://i.ytimg.com/vi/"+this.videoUrl.substring(17,28)+"/default.jpg";
        const videoId = this.videoUrl.substring(17,28);
        
        const body = {
            "title" : this.videoTitle,
            "url" : this.videoUrl,
            "author" : this.videoAuthor,
            "thumbnail": videoThumbnail,
            "videoId" : videoId
        }

        VideoService.addVideo(this.videoCategory.id,body).then(response=>{
            console.log(response.data);
        }).catch(error=>{
            console.log(error)
        })
        this.$router.push({ name: "homePage" }); 

      }
    },
    mounted(){
      this.getCategories();
      
    }
  };
  </script>
  
  <style>
  </style>