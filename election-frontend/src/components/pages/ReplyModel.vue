<template>
  <div v-if="showModal" class="modal-overlay">
    <div class="modal">
      <h2>Reacties voor: {{ currentTopic?.title }}</h2>

      <!-- Scrollbare reactiescontainer -->
      <div class="replies-container">
        <div v-if="currentTopic?.replies && currentTopic.replies.length">
          <div v-for="reply in currentTopic.replies" :key="reply.id" class="reply-item">
            <p>{{ reply.content }}</p>
            <p class="reply-date">Antwoord gepost op: {{ formatDate(reply.createdAt) }}</p>
          </div>
        </div>
        <div v-else>
          <p>Er zijn nog geen reacties.</p>
        </div>
      </div>

      <!-- Reactie invoerveld -->
      <div class="reply-input-container">
        <!-- Gebruik :modelValue en @update:modelValue om met v-model te werken -->
        <textarea
            :value="modelValue"
            @input="$emit('update:modelValue', $event.target.value)"
            placeholder="Schrijf je reactie..."
            rows="3"
        ></textarea>
        <button @click="submitReply(currentTopic.id)" class="submit-reply-button">
          Plaats reactie
        </button>
      </div>

      <!-- Sluitknop -->
      <button class="close-button" @click="closeModal">Sluiten</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    showModal: Boolean,
    currentTopic: Object,
    modelValue: String, // Hier gebruik je modelValue in plaats van newReplyContent
  },
  methods: {
    formatDate(dateString) {
      if (!dateString) return "Datum niet beschikbaar";
      const date = new Date(dateString);
      if (isNaN(date)) return "Ongeldige datum";

      const options = {
        year: "numeric",
        month: "long",
        day: "numeric",
        hour: "2-digit",
        minute: "2-digit",
      };
      return date.toLocaleDateString("nl-NL", options);
    },

    closeModal() {
      this.$emit('closeModal');
    },

    submitReply(topicId) {
      this.$emit('submitReply', topicId, this.modelValue);
    }
  }
};
</script>

<style scoped>
/* Modal styling */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  max-width: 600px;
  width: 100%;
  max-height: 80vh;
  overflow-y: auto;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
}

.replies-container {
  flex-grow: 1;
  max-height: 300px;
  overflow-y: auto;
  margin-bottom: 15px;
}

.reply-item {
  background-color: #f1f1f1;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 5px;
  border-left: 4px solid #4caf50;
}

.reply-input-container textarea {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.submit-reply-button {
  margin-top: 10px;
  padding: 10px 15px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
  font-weight: bold;
}

.submit-reply-button:hover {
  background-color: #45a049;
}

.submit-reply-button:active {
  background-color: #3e8e41;
}

.close-button {
  margin-top: 15px;
  padding: 8px 12px;
  background-color: red;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.close-button:hover {
  background-color: darkred;
}
</style>
