import http from "../http-common.js";

class VideoService {
    getVideos() {
        return http.get("/videos");
    }
    getVideoById(id){
        return http.get(`/videos/${id}`)
    }
}

export default new VideoService();