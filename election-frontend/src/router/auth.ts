// src/router/auth.ts
import { reactive } from 'vue';

export const authState = reactive({
    isAuthenticated: false,
    role: null as string | null,  // typeer role als string | null
    email: null as string | null,  // typeer email als string | null

    // Methode om in te loggen
    login(token: string, role: string, email: string) {
        this.isAuthenticated = true;
        this.role = role;
        this.email = email;
        localStorage.setItem('token', token);
        localStorage.setItem('role', role);
        localStorage.setItem('email', email);
    },

    // Methode om uit te loggen
    logout() {
        this.isAuthenticated = false;
        this.role = null;
        this.email = null;
        localStorage.removeItem('token');
        localStorage.removeItem('role');
        localStorage.removeItem('email');
    },

    // Methode om de authenticatiestatus te herstellen van localStorage
    initialize() {
        const token = localStorage.getItem('token');
        const role = localStorage.getItem('role');
        const email = localStorage.getItem('email');

        if (token && role && email) {
            this.isAuthenticated = true;
            this.role = role;
            this.email = email;
        }
    }
});

// Initialiseer de authState bij het laden van de app
authState.initialize();
