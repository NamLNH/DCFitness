import http from "../http-common.js";

class VideoService {
    getVideos() {
        return http.get("/videos");
    }
    getVideoById(id){
        return http.get(`/videos/${id}`);
    }
    deleteVideoById(id) {
        return http.delete(`/videos/${id}`);
    }
    addVideo(categoryId, body){
        return http.post(`/videos/${categoryId}`,body)
    }
    getVideosByCategoryId(categoryId){
        return http.get(`/videos/category/${categoryId}`)
    }
    updateVideoByID(videoId, body){
        return http.put(`/videos/${videoId}`,body)
    }
}

export default new VideoService();