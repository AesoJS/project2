<template>

  <div class="userProfile">
    <div id="mainInfo">
      <img src="@/assets/img/defpic3 (2).jpg" alt="default profile picture">
      <div id="infoDetails">
        <h2 class="details">Naam: {{user.name}}</h2>
        <h3 class="details">Voorstander van de partij: {{user.voorstander}}</h3>
        <h3 class="details">{{user.city}}, {{user.province}}</h3>
        <p class="details" id="description">{{user.profielOmschrijving}}</p>
      </div>
    </div>
  </div>
  <div id="platforms" class="userProfile">
    <div class="left-platforms">
      <p>{{user.social1}}</p>
      <p>{{user.social2}}</p>
    </div>
    <div class="right-platforms">
      <p>{{user.social3}}</p>
      <p>{{user.social4}}</p>
    </div>
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
import axios from 'axios';
import { defineComponent } from "vue";

export default defineComponent({
  data() {
    return {
      user: {
        name: "",
        voorstander: "",
        province: "",
        city: "",
        profielOmschrijving: "",
        social1: "",
        social2: "",
        social3: "",
        social4: "",
      },
      message: "",
    };
  },
  mounted() {
    this.fetchUserInfoByName();
  },
  methods: {
    async fetchUserInfoByName() {
      try {
        const username = this.$route.params.username;
        const response = await axios.get("http://election-backend-cmsa.onrender.com/api/user/name", {
          params: { name: username }
        });

        this.user = response.data;
      } catch (error) {
        console.error("Error fetching user information:", error);
      }
    }
  }
});
</script>

<style scoped>
.userProfile {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px;
  background-color: #f5f8fc;
  max-width: 1200px;
  margin: 80px auto;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.25);
  border-radius: 15px;
}

#platforms {
  max-width: 700px;
}

#mainInfo {
  display: flex;
  align-items: flex-start;
  background-color: white;
  width: 800px;
  padding: 40px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.10);
  border-radius: 10px;
  gap: 12vw;
}

#infoDetails {
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 10px;
}

.details {
  color: #555;
  padding: 10px 0;
  border-bottom: 1px solid #e0e0e0;
}

#platforms {
  display: flex;
  justify-content: space-between;
  width: 50%;
}

.left-platforms,
.right-platforms {
  display: flex;
  flex-direction: column;
  gap: 20px;
  font-size: 21px;
}

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
