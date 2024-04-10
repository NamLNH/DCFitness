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
}

export default new VideoService();