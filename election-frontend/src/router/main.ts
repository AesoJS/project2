import '../assets/main.css';

import { createApp } from 'vue';
import router from '@/router/index';
import store from '@/store'; // Zorg ervoor dat je Vuex store correct importeert
import App from '@/components/App.vue';

const app = createApp(App);

// Voeg de store toe als plugin
app.use(store);  // Dit voegt de store correct toe
app.use(router); // Gebruik de router
app.mount('#app');
