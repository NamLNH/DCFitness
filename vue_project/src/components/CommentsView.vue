<template>
    <div class="comment-section">
      <h2>Comments</h2>
      <div v-if="comments.length === 0">No comments yet.</div>
      <ul v-else>
        <li v-for="(comment, index) in comments" :key="index" class="comment">
          <div class="comment-user-icon">
            <img :src="comment.user.icon" alt="User Icon" />
          </div>
          <div class="comment-content">
            <div class="comment-user">{{ comment.user.name }}</div>
            <div class="comment-text">{{ comment.text }}</div>
          </div>
        </li>
      </ul>
      <textarea v-model="newCommentText" placeholder="Write a comment"></textarea>
      <button @click="insertCommenet">Add Comment</button>
    </div>
  </template>
  
  <script>
    import CommentService from "../services/CommentService";
    import UserDataService from '@/services/UserDataService';

    export default {
        data() {
            return {
                comments: [],
                newCommentText: '',
                currentUser: null,
            };
        },

        mounted() {
            this.fetchCurrentUser();
            this.fetchComments();
        },

        methods: {

        async addCommentToRender() {
            if (this.newCommentText.trim() !== '') {
                const id = this.currentUser.id;
                const name = this.currentUser.username;
                const icon = await this.fetchRandomUserThumbnail(id);
                this.comments.push({ user: { id, name, icon }, text: this.newCommentText });
                this.newCommentText = '';
            }
        },

        async fetchRandomUserThumbnail(userId) {
            try {
                const response = await fetch(`https://randomuser.me/api/?seed=${userId}`);
                const data = await response.json();
                return data.results[0].picture.thumbnail;
            } catch (error) {
                console.error('Error fetching random user:', error);
                throw error;
            }
        },

        /**
         * Fetching the current User from backend database
         */
        fetchCurrentUser(){
            const uid = localStorage.getItem('uid');
            UserDataService.get(uid).then(response => {
                this.currentUser = response.data;
            })
            .catch(error => {
                console.error('Error fetching user:', error);
            })
        },

        /**
         * Fetching the commets from Backend
         */
        fetchComments() {
            const videoID = localStorage.getItem('videoID') ?? 1;
            CommentService.getCommentsByVideoID(videoID).then(response => {
                console.log(response.data);
                this.addExistingCommentsToRender(response.data ?? []);
            })
            .catch(error => {
                console.error('Error fetching comment:', error);
            })
        },

        /**
         * Adding the commets fetched from Cloud to state list to render
         * @param {*} comments 
         */
        addExistingCommentsToRender(comments) {
            comments.forEach(async i => {
                const id = i.user.id ?? 1; // User ID
                const text = i.content ?? ""; // Comment
                const name = i.user.username ?? ""; // User name
                const icon = await this.fetchRandomUserThumbnail(id);
                this.comments.push({ user: { id, name, icon }, text});
            });
        },

        /**
         * Insert the comment to database
         */
        insertCommenet() {
            const videoID = localStorage.getItem('videoID') ?? 1;
            const userID = this.currentUser.id;
            const body = { "content": this.newCommentText };
            CommentService.addCommentsToVideo(videoID, userID, body).then(response => {
                console.log(response.data);
                this.addCommentToRender();
            })
            .catch(error => {
                console.error('Error fetching comment:', error);
            })
        } 
        }
    };
  </script>
  
  <style scoped>
  .comment-section {
    margin-bottom: 20px;
  }
  
  .comment {
    margin-bottom: 10px;
    padding: 10px;
    border: 1px solid #ccc;
    display: flex;
  }
  
  .comment-user-icon {
    margin-right: 10px;
  }
  
  .comment-user-icon img {
    width: 50px;
    height: 50px;
    border-radius: 50%;
  }
  
  .comment-content {
    flex-grow: 1;
  }
  
  .comment-user {
    font-weight: bold;
  }
  
  textarea {
    width: 100%;
    margin-bottom: 10px;
  }
  
  button {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  </style>
  