import http from "../http-common.js";

class VideoService {
    getVideos() {
        return http.get("/videos");
    }
}

export default new VideoService();