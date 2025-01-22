<template>
  <div class="forum-container">
    <h1>Forum</h1>

    <!-- Formulier om een nieuw onderwerp te plaatsen -->
    <div class="new-topic-form">
      <h3>Plaats een nieuw onderwerp</h3>
      <form @submit.prevent="postNewTopic" class="form">
        <div class="form-group">
          <label for="title">Titel</label>
          <input type="text" v-model="newTopic.title" required placeholder="Voer de titel in" />
        </div>
        <div class="form-group">
          <label for="content">Inhoud</label>
          <textarea v-model="newTopic.content" required placeholder="Voer de inhoud in"></textarea>
        </div>
        <button type="submit" class="submit-btn">Plaats Onderwerp</button>
      </form>
    </div>

    <!-- Lijst van Onderwerpen -->


  </div>
  <!-- Footer Sectie -->
  <footer class="footer">
    <div class="footer-content">
      <div class="footer-section">
        <h3>Contact</h3>
        <p>Email: info@politiekgids.nl</p>
        <p>Telefoon: +31 20 123 4567</p>
      </div>
      <div class="footer-section">
        <h3>Volg Ons</h3>
        <ul>
          <li><a href="#" class="social-link">Facebook</a></li>
          <li><a href="#" class="social-link">Twitter</a></li>
          <li><a href="#" class="social-link">Instagram</a></li>
        </ul>
      </div>
      <div class="footer-section">
        <h3>Informatie</h3>
        <ul>
          <li><a href="#" class="footer-link">Privacybeleid</a></li>
          <li><a href="#" class="footer-link">Algemene Voorwaarden</a></li>
          <li><a href="#" class="footer-link">Cookiebeleid</a></li>
        </ul>
      </div>
    </div>
    <div class="footer-bottom">
      <p>&copy; 2025 Politieke Gids. Alle rechten voorbehouden.</p>
    </div>
  </footer>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      topics: [],
      newTopic: {
        title: "",
        content: "",
        createdBy:"",

      },
      email: localStorage.getItem("email"),
      createdBy: "",
    };
  },
  methods: {
    // Haal alle onderwerpen van de server op
    async fetchTopics() {
      try {
        const response = await axios.get("http://localhost:8080/api/forum/topics/latest")
        this.topics = response.data;
        console.log("response fetch", this.topics);
      } catch (error) {
        console.error("Fout bij het ophalen van onderwerpen:", error);
      }
    },

    // Plaats een nieuw onderwerp
    async postNewTopic() {
      try {
        this.newTopic.createdBy = this.createdBy;
        await axios.post("http://localhost:8080/api/forum/topics", this.newTopic);
        this.newTopic.title = "";
        this.newTopic.content = "";
        this.fetchTopics(); // Herlaad de onderwerpen na het plaatsen

      } catch (error) {
        console.error("Fout bij het plaatsen van het onderwerp:", error);
      }
    },

    async fetchUsername() {
      try {
        const response = await axios.get("http://localhost:8080/api/user", {
          params: {email: this.email},
        });
        if (response.data) {
          this.createdBy = response.data.name;
        } else {
          console.warn("Gebruiker niet gevonden");
        }
      } catch (error) {
        console.error("Fout bij het ophalen van de gebruikersnaam", error);
      }
      console.log("username:", this.createdBy);
    },
  },
  created() {
    this.fetchTopics(); // Haal de onderwerpen op wanneer de component wordt aangemaakt
    this.fetchUsername();
  },
};
</script>

<style scoped>
.forum-container {
  display: flex;
  flex-direction: column;
  justify-content: center; /* Center vertically */
  align-items: center; /* Center horizontally */
  max-width: 800px;
  margin: 50px auto; /* Center horizontally with margin at top */
  padding: 20px;
  background-color: #fff; /* Background color */
  border-radius: 8px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
}

h1, h3, h4 {
  color: #002f6c; /* Header color */
}

h1 {
  text-align: center;
  font-size: 2rem;
  margin-bottom: 20px;
}

.new-topic-form,
.topics-list {
  margin-top: 20px;
  display: flex;
  flex-direction: column; /* Stack items vertically */
  align-items: center; /* Center the items horizontally */
  width: 100%; /* Allow full width for the forms */
}

.new-topic-form {
  padding: 20px; /* Padding to match contact form */
  width: 100%; /* Ensure full width for the form */
}

.new-topic-form h3 {
  margin-bottom: 10px;
}

.form {
  width: 100%; /* Allow form to fill container */
}

.form-group {
  margin-bottom: 15px;
  width: 100%; /* Ensure full width for form group */
}

.form-group label {
  font-weight: bold;
  color: #002f6c; /* Label color */
  display: block;
  margin-bottom: 5px;
}

.form-group input,
.form-group textarea {
  width: 100%;
  padding: 10px;
  border: 2px solid #002f6c; /* Border color */
  border-radius: 5px;
  background-color: #fefefe;
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.05);
}

.form-group input:focus,
.form-group textarea:focus {
  outline: none;
  border-color: #ffd700; /* Focus border color */
}

textarea {
  min-height: 100px; /* Minimum height */
}

.submit-btn {
  padding: 10px 15px;
  border: none;
  border-radius: 5px;
  background-color: #ffd700; /* Button background color */
  color: #002f6c; /* Button text color */
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease;
  width: 100%; /* Full width button */
}

.submit-btn:hover {
  background-color: #002f6c; /* Button hover background */
  color: white; /* Button hover text color */
}

.topics-list h3 {
  font-size: 1.5rem;
  margin-bottom: 15px;
  text-align: center; /* Center align the topics list heading */
}

.topic-item {
  background-color: #f9f9f9;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
  width: 100%; /* Ensure full width for topic items */
}

.topic-item h4 {
  color: #002f6c; /* Topic title color */
  font-size: 1.3rem;
  margin-bottom: 10px;
}

.topic-item p {
  color: #555;
  font-size: 1rem;
  line-height: 1.5;
}

/* Footer Styling met Donkerblauwe Achtergrond */
.footer {
  background-color: #002f6c;
  padding: 50px 0;
  color: white;
  font-size: 1rem;
}

.footer-content {
  display: flex;
  justify-content: space-around;
  margin-bottom: 30px;
}

.footer-section {
  width: 30%;
}

.footer-section h3 {
  font-size: 1.5rem;
  margin-bottom: 10px;
}

.footer-section p,
.footer-section ul {
  font-size: 1rem;
}

.footer-section ul {
  list-style: none;
  padding-left: 0;
}

.footer-section ul li {
  margin-bottom: 10px;
}

.footer-section ul li a {
  color: white;
  text-decoration: none;
  transition: color 0.3s ease;
}

.footer-section ul li a:hover {
  color: #ffd700;
}

.footer-bottom {
  text-align: center;
  border-top: 1px solid #ddd;
  padding-top: 20px;
}

.footer-bottom p {
  font-size: 0.9rem;
  color: #ddd;
}

/* Mobiel Responsief */
@media (max-width: 900px) {
  .banner-content h1 {
    font-size: 2.5rem;
  }

  .info {
    flex-direction: column;
    align-items: center;
  }

  .info-section {
    width: 90%;
  }

  .footer-content {
    flex-direction: column;
    align-items: center;
  }

  .footer-section {
    width: 80%;
    text-align: center;
    margin-bottom: 20px;
  }
}
</style>
