<template>
  <nav class="navbar">
    <div class="navbar-container">
      <div class="logo">
        <img src="./icons/political-party.png" alt="logo" />
      </div>

      <ul class="nav-links">
        <li
            class="nav-link"
            v-for="(link, index) in navLinks"
            :key="index"
            :class="{ active: activeLink === link.name }"
        >
          <router-link :to="link.url" @click="activeLink = link.name">{{ link.name }}</router-link>
        </li>
      </ul>

      <div class="buttons">
        <div v-if="authState.isAuthenticated" class="signout-btn">
          <button @click="signOut">Uitloggen</button>
        </div>

        <div v-else>
          <div class="login-btn" v-for="(button, index) in authButtons" :key="index">
            <router-link :to="button.url" @click="activeButton = button.name">
              <button :class="{ active: activeButton === button.name }">{{ button.name }}</button>
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<script lang="ts">
import { inject } from 'vue';
import { authState } from '@/router/auth'; // Importeer de authenticatiestatus

export default {
  setup() {
    return { authState }; // Maak authState beschikbaar in de template
  },

  data() {
    return {
      navLinks: [
        { name: "Home", url: "/" },
        { name: "Verkiezingsresultaat", url: "/election-result" },
        { name: "Partijen", url: "/parties" },
        { name: "Quizz", url: "/quiz" },
        { name: "Forum", url: "/forum/overview" },
        { name: "Mijn Profiel", url: "/personal-profile" },
      ],

      authButtons: [
        { name: "Inloggen", url: "/login" },
        { name: "Registreren", url: "/register" }
      ],

      activeLink: "Home", // Standaard actieve link instellen
      activeButton: "Inloggen" // Standaard actieve knop instellen
    };
  },

  methods: {
    signOut() {
      authState.logout(); // Roep de logout-methode van authState aan
      this.$router.push('/login'); // Omleiden naar inlogpagina
    }
  }
};
</script>


<style>
/* Algemene Navbar Styling */
.navbar {
  background-color: #002f6c;
  padding: 10px 30px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
}

.navbar-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 0 auto;
  gap: 20px;
}

/* Logo */
.logo img {
  width: 50px;
}

/* Navigatie Links */
.nav-links {
  display: flex;
  justify-content: center;
  flex-grow: 1;
  gap: 30px;
  font-family: Arial, Helvetica, sans-serif;
}

.nav-link {
  list-style: none;
  white-space: nowrap;
}

.nav-link a {
  padding: 10px 15px;
  color: white;
  text-decoration: none;
  font-weight: bold;
  transition: color 0.3s ease, border-bottom 0.3s ease;
  font-size: 1.1em;
}

.nav-link a:hover,
.nav-link.active a {
  color: #ffd700;
  border-bottom: 2px solid #ffd700;
}

/* Zoekbalk */
.search {
  position: relative;
  display: flex;
  align-items: center;
  width: 200px;
}

.search input {
  padding: 8px 40px 8px 15px;
  width: 100%;
  height: 40px;
  border: none;
  border-radius: 20px;
  font-size: 1em;
  outline: none;
}

.search img {
  position: absolute;
  right: 15px;
  top: 50%;
  transform: translateY(-50%);
  width: 20px;
  height: 20px;
  pointer-events: none;
}

/* Knoppen */
.buttons {
  display: flex;
  gap: 25px;
  align-items: center;
}

.login-btn,
.signout-btn {
  display: inline-block;
}

.login-btn button,
.signout-btn button {
  padding: 10px 20px;
  font-size: 1em;
  font-weight: bold;
  border-radius: 20px;
  cursor: pointer;
  transition: background-color 0.3s ease, color 0.3s ease;
  border: 2px solid white;
  background-color: transparent;
  color: white;
}

.login-btn button:hover,
.signout-btn button:hover,
.login-btn button.active,
.signout-btn button.active {
  background-color: white;
  color: #002f6c;
}

/* Actieve knop */
button.active {
  background-color: white;
  color: #002f6c;
  border-color: #002f6c;
}

/* Burger Menu */
.burger {
  display: none;
  flex-direction: column;
  cursor: pointer;
}

.burger .line {
  width: 25px;
  height: 3px;
  background-color: white;
  margin: 4px 0;
}

/* Mobiele Menu Styling */
.mobile-menu {
  background-color: #002f6c;
  position: absolute;
  top: 85px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  width: 100%;
  box-shadow: 0px 8px 10px rgba(0, 0, 0, 0.1);
}

.mobile-menu-nav {
  list-style: none;
  white-space: nowrap;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10px 0;
}

.mobile-nav-link {
  width: 100%;
  text-align: center;
  margin: 10px 0;
}

.mobile-nav-link a {
  color: white;
  text-decoration: none;
  padding: 10px;
  font-size: 1.1em;
}

.mobile-nav-link a:hover {
  background-color: #ffd700;
  border-radius: 20px;
  color: #002f6c;
}

/* Responsief Ontwerp */
@media (max-width: 1100px) {
  .nav-links {
    display: none;
  }

  .burger {
    display: flex;
  }

  .search {
    width: 50%;
    margin: 0;
  }

  .search input {
    width: 100%;
  }

  .buttons {
    display: none;
  }

  .mobile-menu {
    display: flex;
  }
}
</style>
