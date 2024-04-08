import http from "../http-common.js";

class VideoService {
    getAllVideos() {
        return http.get(`/videos`);
    }
}

export default new VideoService();