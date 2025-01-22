<template>
  <div class="topic-detail-container">
    <h1>{{ post.title }}</h1>
    <p class="post-content">{{ post.content }}</p>
    <p class="post-date">Gepost op: {{ formatDate(post.createdAt) }}</p>

    <h2>Reacties</h2>
    <div v-if="post.replies && post.replies.length">
      <div v-for="reply in post.replies" :key="reply.id" class="reply-item">
        <p>{{ reply.content }}</p>
        <p class="reply-date">Antwoord gepost op: {{ formatDate(reply.createdAt) }}</p>
      </div>
    </div>
    <div v-else>
      <p>Er zijn nog geen reacties.</p>
    </div>

    <textarea
        v-model="newReply"
        placeholder="Schrijf je reactie..."
        rows="3"
        class="reply-textarea"
    ></textarea>
    <button @click="submitReply" class="reply-button">Plaats reactie</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      post: {}, // Specifieke post
      newReply: "", // Inhoud van nieuwe reactie
    };
  },
  methods: {
    async fetchPost() {
      try {
        const response = await axios.get(
            `http://localhost:8080/api/forum/topics/${this.$route.params.id}`
        );
        this.post = response.data;
      } catch (error) {
        console.error("Fout bij het ophalen van de post:", error);
      }
    },
    async submitReply() {
      if (!this.newReply.trim()) {
        alert("Reactie mag niet leeg zijn!");
        return;
      }

      try {
        const response = await axios.post(
            `http://localhost:8080/api/forum/topics/${this.$route.params.id}/replies`,
            { content: this.newReply }
        );
        this.post.replies.push(response.data); // Voeg de nieuwe reactie toe
        this.newReply = ""; // Reset het invoerveld
      } catch (error) {
        console.error("Fout bij het plaatsen van de reactie:", error);
      }
    },
    formatDate(dateString) {
      if (!dateString) return "Datum niet beschikbaar";
      const date = new Date(dateString);
      return date.toLocaleDateString("nl-NL", {
        year: "numeric",
        month: "long",
        day: "numeric",
        hour: "2-digit",
        minute: "2-digit",
      });
    },
  },
  created() {
    this.fetchPost();
  },
};
</script>

<style>
.topic-detail-container {
  max-width: 800px;
  margin: 50px auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
}

h1 {
  font-size: 2rem;
  margin-bottom: 10px;
  color: #002f6c;
}

.post-content {
  font-size: 1.2rem;
  color: #333;
}

.post-date,
.reply-date {
  font-size: 0.9rem;
  color: #888;
  margin-bottom: 20px;
}

.reply-item {
  background-color: #f9f9f9;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 5px;
  border-left: 4px solid #4caf50;
}

.reply-textarea {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
  resize: none;
}

.reply-button {
  display: inline-block;
  margin-top: 10px;
  padding: 10px 20px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
}

.reply-button:hover {
  background-color: #45a049;
}
</style>
