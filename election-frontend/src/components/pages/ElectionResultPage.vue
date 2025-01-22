<template>
  <section class="election-page">
    <!-- Left Column -->
    <div class="left-column">
      <!-- Election Result Map -->
      <aside class="map-section" id="uitslag-kaart">
        <h2>Uitslag per gemeente</h2>
        <div class="datawrapper-embed" id="datawrapper-vis-LtnSM">
          <datawrapper-visualization></datawrapper-visualization>
        </div>
        <div class="year-toggle">
          <a href="#" class="btn-year btn-2021" @click.prevent="showYear('2021')">2021</a>
          <a href="#" class="btn-year btn-2023" @click.prevent>2023</a>
        </div>
      </aside>

      <!-- Turnout Map -->
      <aside class="map-section" id="opkomst">
        <h2>Bekijk de opkomst</h2>
        <div class="datawrapper-embed" id="datawrapper-vis-22j33">
          <datawrapper-visualization></datawrapper-visualization>
        </div>
        <div class="year-toggle">
          <a href="#" class="btn-year btn-2021" @click.prevent="toggleOpkomst('2021')">2021</a>
          <a href="#" class="btn-year btn-2023" @click.prevent>2023</a>
        </div>
      </aside>

      <!-- Special Municipalities Results -->
      <aside class="map-section" id="bes">
        <h2>Uitslag bijzondere gemeenten</h2>
        <div class="datawrapper-embed" id="datawrapper-vis-cpQ6h">
          <datawrapper-visualization></datawrapper-visualization>
        </div>
      </aside>
    </div>

    <!-- Right Column: Party List -->
    <aside class="party-list-section">
      <h2>Alle Partijen</h2>
      <ul class="party-list">
        <li v-for="party in partyList" :key="party" class="party-item">{{ party }}</li>
      </ul>
    </aside>
  </section>
</template>

<script>
export default {
  data() {
    return {
      show2023: true,
      partyList: [
        "VVD", "D66", "PVV", "GroenLinks", "SP",
        "PvdA", "ChristenUnie", "Partij voor de Dieren",
        "SGP", "DENK", "50PLUS", "FvD", "JA21",
        "Volt", "BBB", "BIJ1", "Nieuw Sociaal Contract"
      ],
    };
  },
  methods: {
    showYear(year) {
      this.show2023 = year === '2023';
    },
    toggleOpkomst(year) {
      // Logic to toggle opkomst year, similar to showYear
    },
    loadScripts() {
      // Load external scripts for each Datawrapper map
      const scripts = [
        { id: 'datawrapper-vis-LtnSM', src: 'https://datawrapper.dwcdn.net/LtnSM/embed.js?v=6' },
        { id: 'datawrapper-vis-22j33', src: 'https://datawrapper.dwcdn.net/22j33/embed.js?v=4' },
        { id: 'datawrapper-vis-cpQ6h', src: 'https://datawrapper.dwcdn.net/cpQ6h/embed.js' },
      ];

      scripts.forEach(({ id, src }) => {
        const script = document.createElement('script');
        script.src = src;
        script.defer = true;
        document.getElementById(id).appendChild(script);
      });
    },
  },
  mounted() {
    this.loadScripts();
  },
};
</script>

<style scoped>
/* Main container layout */
.election-page {
  display: grid;
  grid-template-columns: 1fr 300px;
  gap: 20px;
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.left-column {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

/* Each map section styling */
.map-section {
  background: #f7f9fa;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.map-section h2 {
  font-size: 1.4em;
  color: #0a4c6a;
  margin-bottom: 16px;
}

.datawrapper-embed {
  background: #ffffff;
  padding: 10px;
  border-radius: 8px;
  min-height: 400px;
}

.year-toggle {
  display: flex;
  justify-content: space-around;
  margin-top: 15px;
}

.btn-year {
  padding: 8px 12px;
  border-radius: 5px;
  font-weight: 600;
  cursor: pointer;
  text-align: center;
  box-shadow: 0px 1px 5px rgba(0, 0, 0, 0.15);
}

.btn-2021 {
  background: #c3d1d5;
  color: #000;
}

.btn-2023 {
  background: #0a4c6a;
  color: #fff;
}

/* Right column styling for the party list */
.party-list-section {
  background: #e3e8eb;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.party-list-section h2 {
  font-size: 1.4em;
  color: #0a4c6a;
  margin-bottom: 16px;
}

.party-list {
  list-style: none;
  padding: 0;
}

.party-item {
  font-size: 1.1em;
  padding: 8px 12px;
  margin: 6px 0;
  background: #ffffff;
  border-radius: 5px;
  box-shadow: 0px 1px 5px rgba(0, 0, 0, 0.1);
  color: #333;
  text-align: left;
}

.party-item:hover {
  background: #d0e0e6;
}
</style>
