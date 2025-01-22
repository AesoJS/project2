<template>
  <div class="admin-wrapper">
    <div class="sidebar">
      <h3>Admin Panel</h3>
      <ul>
        <li><router-link to="/admin/dashboard">Dashboard</router-link></li>
        <li><router-link to="/admin/users">Users</router-link></li>
        <li><router-link to="/admin/settings">Settings</router-link></li>
      </ul>
    </div>

    <div class="admin-container">
      <h2>Users List</h2>
      <div class="table-wrapper">
        <table class="user-table">
          <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>City</th>
            <th>Province</th>
            <th>Role</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="user in users" :key="user.id">
            <td>{{ user.id }}</td>
            <td>{{ user.name }}</td>
            <td>{{ user.email }}</td>
            <td>{{ user.city }}</td>
            <td>{{ user.province }}</td>
            <td>{{ user.role }}</td>
          </tr>
          </tbody>
        </table>
        <p v-if="message" class="error-message">{{ message }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      users: [],
      message: ''
    };
  },
  async created() {
    await this.fetchUsers();
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await axios.get('http://localhost:8080/api/admin/users');
        this.users = response.data;
      } catch (error) {
        this.message = 'Error fetching users or you do not have admin privileges.';
        console.error('Error:', error.response ? error.response.data : error.message);
      }
    }
  }
};
</script>

<style scoped>
/* General Wrapper */
.admin-wrapper {
  display: flex;
  min-height: 100vh;
  background-color: #f4f7fc;
  font-family: Arial, sans-serif;
}

/* Sidebar */
.sidebar {
  width: 250px;
  background-color: #002f6c;
  padding: 20px;
  color: white;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
}

.sidebar h3 {
  font-size: 1.4rem;
  margin-bottom: 20px;
}

.sidebar ul {
  list-style-type: none;
  padding: 0;
}

.sidebar ul li {
  margin: 15px 0;
}

.sidebar ul li a {
  color: white;
  text-decoration: none;
  font-weight: bold;
  transition: color 0.3s ease;
}

.sidebar ul li a:hover {
  color: #ffd700;
}

/* Admin Container */
.admin-container {
  margin-left: 260px;
  padding: 40px;
  background-color: white;
  width: 100%;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.admin-container h2 {
  font-size: 1.8rem;
  color: #333;
  margin-bottom: 20px;
}

/* Table Styling */
.table-wrapper {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.user-table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
  background-color: #fff;
  border-radius: 8px;
  overflow: hidden;
}

.user-table th, .user-table td {
  padding: 12px 15px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.user-table th {
  background-color: #002f6c;
  color: white;
}

.user-table tr:hover {
  background-color: #f1f1f1;
}

.error-message {
  color: red;
  font-weight: bold;
  margin-top: 10px;
}
</style>
