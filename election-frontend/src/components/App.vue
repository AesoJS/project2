<template>
  <div class="app-container">
    <!-- Navbar Component -->
    <navbar></navbar>

    <!-- Main Content Area (dynamically changing based on route) -->
    <div class="content">
      <router-view></router-view>
    </div>

    <!-- Display Backend Status -->
    <div class="backend-status">
      <div v-if="backendStatus">
        <p>{{ backendStatus.message }}</p>
      </div>
      <div v-else>
        <p>Loading backend status...</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import navbar from "@/components/NavBar.vue";
import axios from 'axios';

// Define the interface for the backend status response
interface BackendStatus {
  message: string;
}

export default defineComponent({
  components: {
    navbar,
  },
  data() {
    return {
      backendStatus: null as BackendStatus | null,  // Type explicitly to allow backend status data
    };
  },
  mounted() {
    this.fetchBackendStatus();  // Fetch backend status once the component is mounted
  },
  methods: {
    // Fetch the backend status using Axios
    fetchBackendStatus() {
      const apiUrl = process.env.VUE_APP_API_URL || "https://election-backend-cmsa.onrender.com";  // Fallback to your Render backend URL
      axios.get(`${apiUrl}/test`)  // Make the API request to the backend `/test` endpoint
          .then((response) => {
            this.backendStatus = response.data;  // Set the response data as the backend status
          })
          .catch((error) => {
            console.error("Error fetching backend status:", error);
            this.backendStatus = { message: "Error connecting to backend" };  // Fallback error message
          });
    },
  },
});
</script>

<style scoped>
.backend-status {
  margin-top: 20px;
}

.error-message {
  color: red;
  font-size: 1.2rem;
  margin-top: 10px;
}
</style>
