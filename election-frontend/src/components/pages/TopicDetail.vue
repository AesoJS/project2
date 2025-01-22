<template>
  <div v-if="topic">
    <h1>{{ topic.title }}</h1>
    <p>{{ topic.content }}</p>
    <p>Gepost op: {{ topic.createdAt }}</p>

    <div>
      <h2>Reacties</h2>
      <div v-if="topic.replies && topic.replies.length">
        <div v-for="reply in topic.replies" :key="reply.id">
          <p>{{ reply.content }}</p>
          <p>Antwoord gepost op: {{ reply.createdAt }}</p>
        </div>
      </div>
      <div v-else>
        <p>Geen reacties beschikbaar.</p>
      </div>
    </div>
  </div>
  <div v-else>
    <p>Topic wordt geladen...</p>
  </div>
</template>


<script>
import axios from "axios";

export default {
  data() {
    return {
      topic: null, // Het topic dat we ophalen
    };
  },
  methods: {
    async fetchTopic() {
      try {
        const { id } = this.$route.params;
        console.log("Fetching topic with ID:", id); // Log de ID
        const response = await axios.get(`http://localhost:8080/api/forum/topics/${id}`);
        console.log("Response data:", response.data); // Log de data die terugkomt
        this.topic = response.data;
      } catch (error) {
        console.error("Error fetching topic:", error);
      }
    },
  },
  created() {
    this.fetchTopic();
  },
};
</script>


<style>
.detail-container {
  max-width: 800px;
  margin: 50px auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  font-size: 2rem;
  margin-bottom: 20px;
  color: #002f6c;
}

.replies-section {
  margin-top: 30px;
}

.reply-item {
  padding: 10px;
  margin-bottom: 10px;
  background-color: #f1f1f1;
  border-radius: 5px;
  border-left: 4px solid #4caf50;
}

.reply-input-container {
  display: flex;
  gap: 10px;
  margin-top: 10px;
  align-items: center;
}

.reply-textarea {
  flex: 1;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
  resize: none;
}

.reply-submit-button {
  padding: 8px 16px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.reply-submit-button:hover {
  background-color: #45a049;
}

.reply-submit-button:focus {
  outline: none;
  box-shadow: 0 0 4px rgba(76, 175, 80, 0.8);
}
</style>
