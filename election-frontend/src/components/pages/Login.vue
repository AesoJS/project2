<template>
  <div class="login-container">
    <div class="login-box">
      <h2>{{ isLoggedIn ? 'Welkom, ' + email : 'Inloggen' }}</h2>

      <form v-if="!isLoggedIn" @submit.prevent="loginUser">
        <div class="form-group">
          <label for="email">E-mail</label>
          <input
              type="email"
              v-model="login.email"
              id="email"
              required
              :class="{ error: emailError }"
          />
          <p v-if="emailError" class="error-message">{{ emailError }}</p>
        </div>
        <div class="form-group">
          <label for="password">Wachtwoord</label>
          <input
              type="password"
              v-model="login.password"
              id="password"
              required
              :class="{ error: passwordError }"
          />
          <p v-if="passwordError" class="error-message">{{ passwordError }}</p>
        </div>
        <button type="submit" :disabled="loading">Inloggen</button>
      </form>

      <!-- Sign Out Button -->
      <button v-if="isLoggedIn" @click="signOut">Uitloggen</button>

      <p v-if="message">{{ message }}</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      login: {
        email: "",
        password: "",
      },
      message: "",
      email: localStorage.getItem("email") || "",
      loading: false,
      emailError: null,
      passwordError: null,
    };
  },
  computed: {
    isLoggedIn() {
      return !!localStorage.getItem("token");
    },
  },
  methods: {
    validateForm() {
      this.emailError = null;
      this.passwordError = null;

      let valid = true;

      if (!this.login.email || !/\S+@\S+\.\S+/.test(this.login.email)) {
        this.emailError = "Voer een geldig e-mailadres in.";
        valid = false;
      }

      if (!this.login.password || this.login.password.length < 6) {
        this.passwordError = "Wachtwoord moet minimaal 6 tekens bevatten.";
        valid = false;
      }

      return valid;
    },
    async loginUser() {
      if (!this.validateForm()) return;

      this.loading = true;
      try {
        const response = await axios.post("http://election-backend-cmsa.onrender.com/api/login", this.login);
        const { token, role } = response.data;

        localStorage.setItem("token", token);
        localStorage.setItem("role", role);
        localStorage.setItem("email", this.login.email);

        this.email = this.login.email;
        this.message = "Inloggen geslaagd!";
        console.log("Ingelogd met e-mailadres:", this.login.email);

        if (role === "admin") {
          this.$router.push("/admin");
        } else {
          this.$router.push("/");
        }
      } catch (error) {
        if (error.response && error.response.status === 401) {
          this.message = "Ongeldige inloggegevens.";
        } else {
          this.message = "Er is iets misgegaan. Probeer het opnieuw.";
        }
        console.error(error);
      } finally {
        this.loading = false;
        this.login.password = ""; // Clear password after attempt
      }
    },
    signOut() {
      localStorage.removeItem("token");
      localStorage.removeItem("role");
      localStorage.removeItem("email");
      this.email = "";
      this.message = "U bent succesvol uitgelogd.";
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 70vh;
  background-color: white;
}

.login-box {
  background-color: white;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 100%;
  max-width: 400px;
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

input {
  width: 100%;
  padding: 10px;
  font-size: 1em;
  border: 2px solid rgba(0, 47, 108, 0.2);
  border-radius: 5px;
  outline: none;
  transition: border 0.3s ease;
}

input:focus {
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
</style>
