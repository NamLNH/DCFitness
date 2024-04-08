<template>
    <div class="comment-section">
      <h2>Comments</h2>
      <div v-if="comments.length === 0">No comments yet.</div>
      <ul v-else>
        <div class="comment-input">
          <input v-model="newCommentText" placeholder="Write a comment" />
          <button @click="insertCommenet">Add Comment</button>
        </div>
        <li v-for="(comment, index) in comments" :key="index" class="comment-container">
          <div class="comment">
            <div class="comment-card">
              <div class="comment-user-icon">
                <img :src="comment.user.icon" alt="User Icon" />
              </div>
              <div class="comment-content">
                <div class="comment-user">{{ comment.user.name }}</div>
                <div v-show="index != actionIndex" class="comment-text">{{ comment.text }}</div>
                <input v-show="index == actionIndex" v-model="editText" placeholder="" />
              </div>
              <div v-show="currentUser && currentUser.id == comment.user.id" class="comment-gear" @click="toggleActions(index, comment.text)">
                <img src="../assets/edit.svg" alt="Settings" />
              </div>
            </div>
          </div>
          <div class="comment-actions" v-show="index == actionIndex">
            <button @click="handleEdit(index)" class="comment-actions-edit" :disabled="!isEdited">Save</button>
            <button @click="handleCancel(index)" class="comment-actions-cancel">Cancel</button>
            <button @click="handleDelete(index)" class="comment-actions-delete">Delete</button>
          </div>
        </li>
      </ul>
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
                actionIndex: -1,
                editText: '',
            };
        },

        mounted() {
            this.fetchCurrentUser();
            this.fetchComments();
        },

        computed: {
          isEdited() {
            if(this.actionIndex >= 0) {
              return this.editText != this.comments[this.actionIndex].text;
            }
            return false;
          }
        },

        methods: {

        async addCommentToRender(commentID) {
            if (this.newCommentText.trim() !== '') {
                const id = this.currentUser.id;
                const name = this.currentUser.username;
                const icon = await this.fetchRandomUserThumbnail(id);
                this.comments.splice(0, 0, { id: commentID, user: { id, name, icon }, text: this.newCommentText });
                this.newCommentText = '';
            }
        },

        toggleActions(index, text) {
          this.actionIndex = index == this.actionIndex ? -1 : index;
          this.editText = text;
        },

        handleEdit() {
          if(this.editText != this.comments[this.actionIndex].text) {
            this.editCommenet();
          }
        },

        handleCancel() {
          this.toggleActions(this.actionIndex, "");
        },

        handleDelete() {
          this.deleteCommenet();
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
                this.comments.push({ id: i.id, user: { id, name, icon }, text});
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
                this.addCommentToRender(response.data.id);
                this.toggleActions(this.actionIndex, "");
            })
            .catch(error => {
                console.error('Error fetching comment:', error);
            })
        },
        
        /**
         * Update the comment to database
         */
         editCommenet() {
          console.log(this.actionIndex)
            const commentID = this.comments[this.actionIndex].id;
            const body = { "content": this.editText };
            CommentService.editComment(commentID, body).then(response => {
                console.log(response.data);
                this.comments[this.actionIndex].text = this.editText;
                this.toggleActions(this.actionIndex, "");
            })
            .catch(error => {
                console.error('Error editing comment:', error);
            })
        },
        
        /**
         * Delete the comment to database
         */
         deleteCommenet() {
            const commentID = this.comments[this.actionIndex].id;
            CommentService.deleteComment(commentID).then(response => {
                console.log(response.data);
                this.comments.splice(this.actionIndex, 1);
                this.toggleActions(this.actionIndex, "");
            })
            .catch(error => {
                console.error('Error deleting comment:', error);
            })
        },
        
        }
    };
  </script>
  
  <style scoped>
  .comment-section {
    margin-bottom: 20px;
  }

  .comment-container {
    list-style-type: none;
  }

  .comment {
    margin-bottom: 10px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 15px;
    display: flex;
  }

  .comment-card {
    display: flex;
    /* align-items: stretch; */
    justify-content: space-between;
    flex: auto;
  }

  .comment-gear {
  /* position: absolute; */
  /* top: 10px;
  right: 10px; */
  cursor: pointer;
}

.comment-gear img {
  width: 30px;
  height: 30px;
  margin: 0px 50px;
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

  .comment-content input {
    /* width: 74.15vw; */
    width: 100%;
    height: 30px;
  }
  
  .comment-user {
    font-weight: bold;
  }
  
  textarea {
    width: 100%;
    margin-bottom: 10px;
  }

  .comment-text {
    margin-bottom: 10px;
    width: 100%;
    /* flex-grow: 1; */
  }

  .comment-actions {
    margin: 0px 0px 20px 0px;
    /* background-color: red; */
    display: flex;
    /* align-items: flex-end; */
    justify-content: flex-end;
  }

  .comment-actions button {
    margin-right: 5px;
    cursor: pointer;
    border-radius: 20px;
    width: 100px;
  }
  
  button {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
  }

  .comment-actions-edit {
    /* width: 60px; */
    background-color: #007bff;
  }

  .comment-actions-cancel {
    /* width: 60px; */
    background-color: #007bff;
  }

  .comment-actions-delete {
    /* width: 60px; */
    background-color: red;
  }

  .comment-input {
    display: flex;
    margin-bottom: 30px;
    justify-content: space-between;
  }

  .comment-input input {
    height: 40px;
    flex-grow: 1;
    margin-right: 10px;
  }
  
  
  button:hover {
    background-color: #0056b3;
  }

  button:disabled {
    background-color: grey;
    opacity: 0.3;
  }
  </style>
  