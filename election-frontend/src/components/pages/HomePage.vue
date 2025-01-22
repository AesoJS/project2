<template>
  <div class="home">
    <!-- Banner Sectie -->
    <section class="banner">
      <div class="banner-content">
        <h1>Jouw Politieke Gids</h1>
        <p>Blijf geïnformeerd, neem de juiste beslissing, en volg de stemmen per partij en provincie in real-time.</p>
        <router-link to="/election-result">
          <button class="cta-btn">Bekijk Verkiezingsresultaten</button>
        </router-link>
      </div>
    </section>

    <!-- Extra Informatie Sectie -->
    <section class="intro-section">
      <div class="intro">
        <h2>Informatie die je Nodig Hebt</h2>
        <p>Verkiezingen kunnen overweldigend zijn. Hier vind je alles wat je nodig hebt om weloverwogen beslissingen te maken.</p>
        <router-link to="/overview">
          <button class="cta-btn">Meer Informatie</button>
        </router-link>
      </div>
    </section>

    <!-- Info Secties -->
    <section class="info">
      <div class="info-section">
        <h2>Ontdek Politieke Partijen</h2>
        <p>Leer meer over de partijen die deelnemen aan de verkiezingen, hun ideologieën en kernbeleid.</p>
        <router-link to="/parties">
          <button class="info-btn">Bekijk Partijen</button>
        </router-link>
      </div>

      <div class="info-section">
        <h2>Volg Stemmen per Provincie</h2>
        <p>Krijg real-time inzicht in hoe elke partij presteert in verschillende provincies.</p>
        <router-link to="/province-stats">
          <button class="info-btn">Volg Stemmen</button>
        </router-link>
      </div>

      <div class="info-section">
        <h2>Doe Mee met de Gemeenschap</h2>
        <p>Doe mee aan discussies en debatten over belangrijke politieke onderwerpen, en deel je mening.</p>
        <router-link to="/forum/overview">
          <button class="info-btn">Doe Mee aan de Discussie</button>
        </router-link>
      </div>
    </section>

    <!-- Snowflakes voor extra flair -->
    <div class="snowflakes" aria-hidden="true">
      <div class="snowflake">❅</div>
      <div class="snowflake">❆</div>
      <div class="snowflake">❄</div>
      <div class="snowflake">❅</div>
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
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'HomePage',
});
</script>

<style scoped>
/* Algemene Layout */
.home {
  font-family: 'Arial', sans-serif;
}

/* Banner Sectie */
.banner {
  background-image: url('@/assets/img/tweedekamer.jpg');
  background-size: cover;
  background-position: center;
  height: 70vh;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  color: white;
  position: relative;
}

.banner-content {
  background-color: rgba(0, 0, 0, 0.6); /* Donkere overlay */
  padding: 30px;
  border-radius: 10px;
  max-width: 80%;
  margin: 0 auto;
}

.banner-content h1 {
  font-size: 3rem;
  margin-bottom: 20px;
}

.banner-content p {
  font-size: 1.2rem;
  margin-bottom: 30px;
}

.cta-btn {
  background-color: #002f6c;
  color: white;
  border: none;
  padding: 15px 30px;
  font-size: 1.2rem;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.cta-btn:hover {
  background-color: #003a8c;
}

/* Introductie Sectie */
.intro-section {
  background-color: #f4f4f9;
  padding: 50px 0;
  text-align: center;
}

.intro-section h2 {
  font-size: 2.5rem;
  margin-bottom: 15px;
}

.intro-section p {
  font-size: 1.2rem;
  margin-bottom: 20px;
}

/* Info Secties */
.info {
  display: flex;
  justify-content: space-around;
  margin: 50px 0;
  padding: 0 30px;
  flex-wrap: wrap;
}

.info-section {
  text-align: center;
  width: 30%;
  margin-bottom: 30px;
}

.info-section h2 {
  font-size: 1.8rem;
  margin-bottom: 15px;
}

.info-section p {
  font-size: 1rem;
  margin-bottom: 20px;
}

.info-btn {
  background-color: #002f6c;
  color: white;
  border: none;
  padding: 10px 20px;
  font-size: 1rem;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.info-btn:hover {
  background-color: #003a8c;
}

/* Snowflakes voor extra flair */
.snowflakes {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  z-index: 9999;
  pointer-events: none;
  user-select: none;
}

.snowflake {
  position: fixed;
  top: -10%;
  font-size: 1.5rem;
  color: white;
  text-shadow: 0 0 1px #000;
  animation: snowflakes-fall 10s linear infinite, snowflakes-shake 3s ease-in-out infinite;
}

@keyframes snowflakes-fall {
  0% {
    top: -10%;
  }
  100% {
    top: 100%;
  }
}

@keyframes snowflakes-shake {
  0% {
    transform: translateX(0);
  }
  50% {
    transform: translateX(80px);
  }
  100% {
    transform: translateX(0);
  }
}

.snowflake:nth-child(1) {
  left: 10%;
  animation-delay: 0s, 0s;
}

.snowflake:nth-child(2) {
  left: 25%;
  animation-delay: 2s, 1s;
}

.snowflake:nth-child(3) {
  left: 50%;
  animation-delay: 4s, 2s;
}

.snowflake:nth-child(4) {
  left: 75%;
  animation-delay: 6s, 3s;
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
