<template>
  <div class="register-container">
    <div class="register-box">
      <h2>Registreren</h2>
      <form @submit.prevent="registerUser">
        <div class="form-group">
          <label for="firstname">Voornaam</label>
          <input type="text" v-model="user.firstname" id="firstname" required />
        </div>

        <div class="form-group">
          <label for="lastname">Achternaam</label>
          <input type="text" v-model="user.lastname" id="lastname" required />
        </div>

        <div class="form-group">
          <label for="name">Gebruikersnaam</label>
          <input type="text" v-model="user.name" id="name" required />
        </div>

        <div class="form-group">
          <label for="email">E-mail</label>
          <input type="email" v-model="user.email" id="email" required />
        </div>

        <div class="form-group">
          <label for="password">Wachtwoord</label>
          <input type="password" v-model="user.password" id="password" required />
        </div>

        <div class="form-group">
          <label for="birthDate">Geboortedatum</label>
          <input type="date" v-model="user.birthDate" id="birthDate" required />
        </div>

        <div class="form-group">
          <label for="province">Provincie</label>
          <select v-model="user.province" id="province" @change="fetchCities" required>
            <option value="" disabled>Selecteer een provincie</option>
            <option v-for="province in provinces" :key="province" :value="province">{{ province }}</option>
          </select>
        </div>

        <div class="form-group" v-if="cities.length > 0">
          <label for="city">Stad</label>
          <select v-model="user.city" id="city" required>
            <option value="" disabled>Selecteer een stad</option>
            <option v-for="city in cities" :key="city" :value="city">{{ city }}</option>
          </select>
        </div>

        <div class="form-group">
          <label for="role">Rol</label>
          <select v-model="user.role" id="role" required>
            <option value="" disabled>Selecteer een rol</option>
            <option value="visitor">Bezoeker</option>
            <option value="user">Gebruiker</option>
          </select>
        </div>

        <button type="submit">Registreren</button>
      </form>
      <p v-if="message">{{ message }}</p>
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
import axios from "axios";

export default {
  data() {
    return {
      user: {
        firstname: "",
        lastname: "",  // Added lastname field
        name: "",
        email: "",
        password: "",
        birthDate: "",
        province: "",
        city: "",
        role: "",
      },
      provinces: [
        "Drenthe", "Flevoland", "Friesland", "Gelderland", "Groningen",
        "Limburg", "Noord-Brabant", "Noord-Holland", "Overijssel",
        "Utrecht", "Zeeland", "Zuid-Holland"
      ],
      cities: [],
      message: "",
      citiesByProvince: {
        "Drenthe": ["Assen", "Emmen", "Hoogeveen", "Meppel", "Coevorden", "Klazienaveen"],
        "Flevoland": ["Almere", "Lelystad", "Dronten", "Zeewolde", "Swifterbant", "Biddinghuizen"],
        "Friesland": ["Leeuwarden", "Sneek", "Drachten", "Heerenveen", "Harlingen", "Franeker"],
        "Gelderland": ["Arnhem", "Nijmegen", "Apeldoorn", "Ede", "Doetinchem", "Tiel", "Zutphen"],
        "Groningen": ["Groningen", "Delfzijl", "Winschoten", "Veendam", "Leek", "Appingedam"],
        "Limburg": ["Maastricht", "Venlo", "Roermond", "Sittard", "Heerlen", "Weert"],
        "Noord-Brabant": ["Eindhoven", "Tilburg", "Breda", "Helmond", "’s-Hertogenbosch", "Oss"],
        "Noord-Holland": ["Amsterdam", "Haarlem", "Alkmaar", "Zaandam", "Hilversum", "Purmerend"],
        "Overijssel": ["Zwolle", "Enschede", "Deventer", "Hengelo", "Ommen", "Raalte", "Hardenberg"],
        "Utrecht": ["Utrecht", "Amersfoort", "Nieuwegein", "Veenendaal", "Zeist", "Houten"],
        "Zeeland": ["Middelburg", "Vlissingen", "Goes", "Maren-Kessel", "Borsele", "Terneuzen"],
        "Zuid-Holland": ["Rotterdam", "Den Haag", "Leiden", "Delft", "Zoetermeer", "Schiedam", "Gouda"]
      }
    };
  },
  methods: {
    registerUser() {
      axios
          .post("http://election-backend-cmsa.onrender.com/api/register", this.user)
          .then((response) => {
            this.message = response.data;
          })
          .catch((error) => {
            this.message = error.response.data;
          });
    },
    fetchCities() {
      this.cities = this.citiesByProvince[this.user.province] || [];
    },
  },
};
</script>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
}

.register-box {
  background-color: white;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 100%;
  max-width: 500px;
}

h2 {
  margin-bottom: 20px;
  color: #002f6c;
  font-size: 1.8em;
  font-weight: bold;
}

.form-group {
  margin-bottom: 15px;
  text-align: left;
}

label {
  display: block;
  font-size: 1.1em;
  color: #002f6c;
  margin-bottom: 5px;
}

input,
select {
  width: 100%;
  padding: 10px;
  font-size: 1em;
  border: 2px solid rgba(0, 47, 108, 0.2);
  border-radius: 5px;
  outline: none;
  transition: border 0.3s ease;
}

input:focus,
select:focus {
  border-color: #002f6c;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #002f6c;
  color: white;
  font-size: 1.2em;
  font-weight: bold;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #004494;
}

p {
  margin-top: 20px;
  color: #000000;
  font-size: 1em;
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