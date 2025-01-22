<template>
  <div>
    <div v-for="(message, index) in messages" :key="index">{{ message }}</div>
    <input v-model="message" @keyup.enter="sendMessage" placeholder="Type a message..." />
  </div>
</template>

<script>
export default {
  data() {
    return {
      message: "",
      messages: [],
      socket: null
    };
  },
  created() {
    this.socket = new WebSocket("ws://election-backend-cmsa.onrender.com/chat");
    this.socket.onmessage = (event) => {
      this.messages.push(event.data);
    };
  },
  methods: {
    sendMessage() {
      if (this.message.trim() !== "") {
        this.socket.send(this.message);
        this.messages.push(this.message);
        this.message = "";
      }
    }
  }
};
</script>

<style scoped>
div {
  padding: 20px;
}

input {
  width: 100%;
  padding: 10px;
}
</style>
