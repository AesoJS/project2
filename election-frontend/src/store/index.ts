import { createStore } from 'vuex';

export default createStore({
    state: {
        authState: {
            isAuthenticated: false,
            role: null as string | null,
            email: null as string | null,
        },
    },
    mutations: {
        login(state, { token, role, email }: { token: string; role: string; email: string }) {
            state.authState.isAuthenticated = true;
            state.authState.role = role;
            state.authState.email = email;
            localStorage.setItem('token', token);
            localStorage.setItem('role', role);
            localStorage.setItem('email', email);
        },
        logout(state) {
            state.authState.isAuthenticated = false;
            state.authState.role = null;
            state.authState.email = null;
            localStorage.removeItem('token');
            localStorage.removeItem('role');
            localStorage.removeItem('email');
        },
        initialize(state) {
            const token = localStorage.getItem('token');
            const role = localStorage.getItem('role');
            const email = localStorage.getItem('email');

            if (token && role && email) {
                state.authState.isAuthenticated = true;
                state.authState.role = role;
                state.authState.email = email;
            }
        },
    },
    actions: {
        login({ commit }, { token, role, email }: { token: string; role: string; email: string }) {
            commit('login', { token, role, email });
        },
        logout({ commit }) {
            commit('logout');
        },
        initialize({ commit }) {
            commit('initialize');
        },
    },
    getters: {
        authState: (state) => state.authState,
    },
});
