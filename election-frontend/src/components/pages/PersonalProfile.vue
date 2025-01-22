<template>
  <h3 class="page-title">Persoonlijk Profiel</h3>
  <div id="wrapper">
    <!--ProfielFoto sectie-->
    <div class="row">
      <div id="PFS">
        <img src="../../assets/img/defpic3%20(2).jpg" alt="default profile picture">
        <p class="info">Naam: {{ editMode ? editedUser.name : user.name }}</p>
        <p class="info">Voorstander: {{ editMode ? editedUser.voorstander : user.voorstander }} </p>
        <p class="info">Provincie, Stad: {{ editMode ? editedUser.province : user.province }}, {{ editMode ? editedUser.city : user.city }}</p>

      </div>

      <!--Gegevens sectie-->
      <div id="gegevens">
        <p class="section-title" v-if="!editMode">Mijn Gegevens</p>
        <p v-if="editMode" class="section-title">Nieuwe Gegevens</p>
        <div id="editMode" v-if="editMode">
         <input class="editField" v-model="editedUser.name" placeholder="Gebruikersnaam"/>
          <input class="editField" v-model="editedUser.firstname"  placeholder="Voornaam"/>
          <input class="editField" v-model="editedUser.lastname" placeholder="Achternaam" />
          <input class="editField" v-model="editedUser.password" placeholder="Wachtwoord"/>
          <input class="editField" v-model="editedUser.city" placeholder="Stad"/>
          <input class="editField" v-model="editedUser.province" placeholder="Provincie" />
          <input class="editField" v-model="editedUser.voorstander"  placeholder="Partij Voorstander" />
        </div>
        <div v-else>
          <p class="details">Gebruikersnaam: {{ user.name }}</p>
          <p class="details">Voornaam: {{ user.firstname }}</p>
          <p class="details">Achternaam: {{ user.lastname }}</p>
          <p class="details">Email: {{ email }}</p>
          <p class="details">Wachtwoord: {{ user.password }}</p>
          <p class="details">Stad: {{ user.city }}</p>
          <p class="details">Provincie: {{ user.province }}</p>
          <p class="details">Voorstander van partij: {{editedUser.voorstander}} </p>
        </div>
        <br>
        <button id="edit" v-if="!editMode" @click="toggleEdit">Bewerken</button>
        <button class="editModeButtons"  v-if="editMode" @click="saveChanges">Opslaan</button>
        <button class="editModeButtons" id="cancelButton" v-if="editMode" @click="cancelEdit">Annuleren</button>

      </div>
    </div>


    <div class="row">
      <!--Platformen sectie-->
      <div id="platformen">
        <p class="section-title">Platformen</p>
        <div v-if="!editMode">
        <p class="details" v-if="!editMode && !editedUser.social1" >Voeg je social media platformen toe!</p>
        <p class="details" v-if="editedUser.social1">{{editedUser.social1}} </p>
        <p class ="details" v-if="editedUser.social2">{{editedUser.social2}}</p>
        <p class="details" v-if="editedUser.social3">{{editedUser.social3}}</p>
        <p class="details" v-if="editedUser.social4">{{editedUser.social4}}</p>
        </div>

        <div v-if="editMode" id="mySocials">

          <div v-for="(social, index) in socialInputs" :key="index">
                <input type="url" v-model="editedUser[social]" class="socials" >
            <button @click="removeSocialInput(index)" class="remove-btn">X</button>
          </div>
          <button id="addSocials" @click="addSocialInput"> Toevoegen</button>

        </div>

      </div>

      <!--Omschrijving sectie-->
      <div id="omschrijving">
        <p class="section-title">Omschrijving</p>
        <p class="details" v-if="!editMode && !editedUser.profielOmschrijving">Nog geen omschrijving</p>
        <p v-else-if="!editMode">{{editedUser.profielOmschrijving}}</p>
        <textarea id="profileDesc"  v-model="editedUser.profielOmschrijving" v-if="editMode" />

      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'PersonalProfile',
  data() {
    return {
      email: localStorage.getItem('email'),
      user: {},
      editedUser: {},
      editMode: false,
      socialInputs: [] , //Array voor het bijhouden van toegevoegde socials
      selectedFile: null, // Hier sla je het geselecteerde bestand op
      previewImage: null, // Voor het weergeven van een voorbeeld van de afbeelding
    };
  },
  mounted() {
    this.fetchUserInfoByEmail();
  },
  methods: {
    async fetchUserInfoByEmail() {
      try {
        const response = await axios.get(`http://localhost:8080/api/user`, {
          params: { email: this.email }
        });
        this.user = response.data;
        this.editedUser = { ...response.data,
        voorstander: response.data.voorstander || "",
        social1: response.data.social1 || "",
        social2: response.data.social2 || "",
        social3: response.data.social3 || "",
        social4: response.data.social4 || "",
        profielOmschrijving: response.data.profielOmschrijving || "" };


        this.socialInputs = Object.keys(this.editedUser)
            .filter(key => key.startsWith(`social`) && this.editedUser[key]);

      } catch (error) {
        console.error('User not found:', error.response.status);
      }
    },
    toggleEdit() {
      this.editMode = true;
    },
    cancelEdit() {
      this.editedUser = { ...this.user };
      this.editMode = false;
    },
    async saveChanges() {
      try {
        const response = await axios.put(`http://localhost:8080/api/user/update`, this.editedUser);
        this.user = { ...this.editedUser };
        this.editMode = false;
      } catch (error) {
        console.error('Error updating user:', error.response.status);
      }
    },

    addSocialInput() {
      if (this.socialInputs.length < 4) {
        this.socialInputs.push(`social${this.socialInputs.length + 1}`);
      } else {
        alert("je kunt alleen 4 socials toevoegen")
      }
    },

    removeSocialInput(index) {
      const socialKey = this.socialInputs[index];
      this.editedUser[socialKey] = "";

      //Verwijder de socialkey uit de array
      this.socialInputs.splice(index, 1)
      //Verwijder de data uit editeduser
      this.$delete(this.editedUser, socialKey);
    },

  },
});
</script>

<style scoped>
/* Wrapper and General Styling */
#wrapper {
  font-family: 'Arial', sans-serif;
  padding: 40px;
  max-width: 1200px;
  margin: 0 auto;
  background-color: #f5f8fc;
  border-radius: 15px;
  box-shadow: 4px 15px rgba(0, 0, 0, 0.1);
}

/* Title Styling */
.page-title {
  text-align: center;
  color: #1a1a99;
  font-size: 2.5rem;
  margin-bottom: 20px;
}

/* Profile Picture Section */
#PFS {
  background-color: #ffffff;
  border-radius: 10px;
  padding: 20px;
  width: 30%;
  text-align: center;
  box-shadow: 4px 10px rgba(0, 0, 0, 0.1);
}

#PFS img {
  width: 80%;
  height: auto;
  border-radius: 50%;
  margin-bottom: 15px;
}

.info {
  color: #000054;
  font-weight: bold;
  margin-bottom: 5px;
}

/* User Details Section */
#gegevens {
  background-color: #ffffff;
  border-radius: 10px;
  padding: 20px;
  width: 65%;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
}

.section-title {
  font-size: 1.8rem;
  color: #1a1a99;
  font-weight: bold;
  margin-bottom: 15px;
}

.details {
  color: #555;
  padding: 10px 0;
  border-bottom: 1px solid #e0e0e0;
}

/* Edit Button */
#edit {
  background-color: #ff4500;
  color: white;
  border: none;
  padding: 10px 20px;
  font-size: 1rem;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-top: 15px;
}

#edit:hover {
  background-color: #ff5714;
}

.editModeButtons {
  color: #FFF;
  background-color: #34c556;
  width: 5vw;
  height: 5vh;
  border-radius: 7px;

}

#cancelButton {
  background-color: indianred;
  margin-left: 10px;
}

/* Platform and Description Sections */
#platformen, #omschrijving {
  background-color: #ffffff;
  border-radius: 10px;
  padding: 20px;
  width: 45%;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
}

#platformen {
  display: flex;
  flex-direction: column;
}

.socials {
  width: 15vw;
  height: 3vh;
  font-size: 16px;
  border-radius: 4px;
}

#mySocials {

  display: flex;
  flex-direction: column;
  gap: 10px;
  position: relative; /* Add this to enable absolute positioning for children */
  min-height: 150px; /* Optional: ensure enough space for the button */
}

#addSocials {
  position: absolute; /* Position it relative to #mySocials */
  bottom: 10px; /* Adjust as needed for spacing */
  right: 10px; /* Adjust as needed for spacing */
  width: 6vw;
  background-color: #2673c5; /* Optional: button color */
  color: white;
  border: none;
  padding: 10px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.row {
  display: flex;
  justify-content: space-between;
  gap: 20px;
  margin-bottom: 20px;
}

#editMode {
  display: flex;
  flex-direction: column;
  gap: 15px;

}

.editField {
  width: 20vw;
  height: 4vh;
  border-radius: 4px;
}

#profileDesc {
  height: 20vh;
  width: 27vw;
  resize: none;
  font-family: 'Arial', sans-serif;
  font-size: 16px;
  border-radius: 4px;
}

/* Responsive Design */
@media (max-width: 900px) {
  .row {
    flex-direction: column;
    align-items: center;
  }

  #PFS, #gegevens, #platformen, #omschrijving {
    width: 90%;
  }
}
</style>
