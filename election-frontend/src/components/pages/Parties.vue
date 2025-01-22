<template>
  <div class="parties-container">
    <h1>Politieke Partijen in Nederland</h1>
    <div class="parties-grid">
      <div v-for="party in parties" :key="party.name" class="party-item">
        <img :src="party.image" :alt="party.name" class="party-logo" />
        <h2>{{ party.name }}</h2>
        <p><strong>Hoofd:</strong> {{ party.leader }}</p>
        <button @click="showMoreInfo(party)">Meer info</button>
      </div>
    </div>
    <div v-if="selectedParty" class="modal" @click.self="closeModal">
      <div class="modal-content">
        <h2>{{ selectedParty.name }}</h2>
        <img :src="selectedParty.image" :alt="selectedParty.name" class="modal-image" />
        <p><strong>Hoofd:</strong> {{ selectedParty.leader }}</p>
        <p><strong>Beschrijving:</strong> {{ selectedParty.description }}</p>
        <p><strong>Belangrijke Standpunten:</strong></p>
        <ul>
          <li v-for="(point, index) in selectedParty.keyPoints" :key="index">{{ point }}</li>
        </ul>
        <button @click="closeModal">Sluiten</button>
      </div>
    </div>
  </div>

  <div class="parties">
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

<script>
import logoVVD from '@/assets/img/vvd.png';
import logoSGP from '@/assets/img/sgp.png';
import logoPVV from '@/assets/img/logo-pvv.jpg';
import logoGroenLinks from '@/assets/img/groenlinks.png';
import logoFVD from '@/assets/img/fvd.png';
import logoD66 from '@/assets/img/d66.png';
import logoBBB from '@/assets/img/bbb.png';
import logoCU from '@/assets/img/CU.png';

export default {
  data() {
    return {
      parties: [
        {
          name: 'VVD',
          leader: 'Dylan Yesilgöz',
          description: 'Vrijheid en verantwoordelijkheid, economische groei, en een kleinere overheid.',
          keyPoints: [
            'Economische groei stimuleren',
            'Focus op nationale veiligheid',
            'Meer werkgelegenheid',
          ],
          image: logoVVD,
        },
        {
          name: 'SGP',
          leader: 'Kees van der Staaij',
          description: 'Christelijk-politieke partij die zich inzet voor de Bijbel en het gezinsbeleid.',
          keyPoints: [
            'Zorg voor de zwakken in de samenleving',
            'Behoud van christelijke normen',
            'Aandacht voor het milieu',
          ],
          image: logoSGP,
        },
        {
          name: 'PVV',
          leader: 'Geert Wilders',
          description: 'Streng immigratiebeleid en een kritische houding ten opzichte van de EU.',
          keyPoints: [
            'Beperken immigratie',
            'Meer politie op straat',
            'Strengere aanpak van criminaliteit',
          ],
          image: logoPVV,
        },
        {
          name: 'GroenLinks',
          leader: 'Jesse Klaver',
          description: 'Duurzaamheid en sociale gelijkheid staan centraal in het beleid.',
          keyPoints: [
            'Klimaatverandering aanpakken',
            'Eerlijke verdeling van rijkdom',
            'Investeren in schone energie',
          ],
          image: logoGroenLinks,
        },
        {
          name: 'Forum voor Democratie',
          leader: 'Thierry Baudet',
          description: 'Conservatief en eurosceptisch met nadruk op nationale soevereiniteit.',
          keyPoints: [
            'Afname van belastingdruk',
            'Eurosceptisch beleid',
            'Versterking van de nationale identiteit',
          ],
          image: logoFVD,
        },
        {
          name: 'D66',
          leader: 'Rob Jetten',
          description: 'Progressieve waarden, klimaatverandering, en onderwijsvernieuwing.',
          keyPoints: [
            'Investeren in onderwijs',
            'Klimaatbeleid versterken',
            'Meer gelijkheid in de samenleving',
          ],
          image: logoD66,
        },
        {
          name: 'BBB',
          leader: 'Caroline van der Plas',
          description: 'Betrokken bij landbouw en plattelandsontwikkeling in Nederland.',
          keyPoints: [
            'Behoud van boerenbedrijven',
            'Platteland versterken',
            'Eerlijke milieumaatregelen',
          ],
          image: logoBBB,
        },
        {
          name: 'ChristenUnie',
          leader: 'Gert-Jan Segers',
          description: 'Partij met een christelijk-sociaal beleid en aandacht voor kwetsbaren.',
          keyPoints: [
            'Zorg voor kwetsbare groepen',
            'Investeren in gezinnen',
            'Balans tussen geloof en politiek',
          ],
          image: logoCU,
        },
      ],
      selectedParty: null,
    };
  },
  methods: {
    showMoreInfo(party) {
      this.selectedParty = party;
    },
    closeModal() {
      this.selectedParty = null;
    },
  },
};
</script>

<style scoped>
.parties-container {
  max-width: 1200px;
  margin: 20px auto;
  padding: 20px;
  background-color: #fff; /* Achtergrondkleur is nu wit */
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
  color: black; /* Zwarte tekst */
}

h1 {
  text-align: center;
  font-size: 2.5rem;
  margin-bottom: 30px;
}

.parties-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
}

.party-item {
  background-color: #f9f9f9; /* Achtergrondkleur van de partijitem is lichtgrijs */
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.party-item:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

.party-logo {
  width: 120px;
  height: 120px;
  object-fit: contain;
  margin-bottom: 15px;
}

button {
  margin-top: 20px;
  padding: 10px 15px;
  background-color: #ffd700;
  color: #002f6c;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #bfa500;
}

.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 10;
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 10px;
  width: 80%;
  max-width: 500px;
  text-align: center;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
}

.modal-image {
  width: 150px;
  height: auto;
  margin-bottom: 20px;
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
