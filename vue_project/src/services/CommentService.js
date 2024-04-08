import http from "../http-common.js";

class CommentService{
    getCommentsByVideoID(videoID) {
        return http.get(`/comments/${videoID}`);
    }

    addCommentsToVideo(videoID, userID, body) {
        return http.post(`/comments/${videoID}/${userID}`, body);
    }

    editComment(commentID, body) {
        return http.put(`/comments/${commentID}`, body);
    }

    deleteComment(commentID) {
        return http.delete(`/comments/${commentID}`);
    }
}
export default new CommentService();