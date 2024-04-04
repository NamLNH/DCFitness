import http from "../http-common.js";

class CommentService{
    getCommentsByVideoID(videoID) {
        return http.get(`/comments/${videoID}`);
    }

    addCommentsToVideo(videoID, userID, body) {
        return http.post(`/comments/${videoID}/${userID}`, body);
    }
}
export default new CommentService();