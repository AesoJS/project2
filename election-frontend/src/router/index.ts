import { createRouter, createWebHistory } from 'vue-router';
import HomePage from "@/components/pages/HomePage.vue";
import ElectionResultPage from "@/components/pages/ElectionResultPage.vue";
import Login from "@/components/pages/Login.vue";
import Register from "@/components/pages/Register.vue";
import Quiz from "@/components/pages/Quiz.vue";
import ForumPage from "@/components/pages/ForumPage.vue";
import OverviewPage from "@/components/pages/OverviewPage.vue"; // Correct import
import AdminPanel from "@/components/pages/AdminPanel.vue";
import NotFound from "@/components/pages/NotFound.vue";
import Parties from "@/components/pages/Parties.vue";
import PersonalProfile from "@/components/pages/PersonalProfile.vue";
import UserProfile from "@/components/pages/UserProfile.vue"

const routes = [
    { path: '/', component: HomePage },
    { path: '/election-result', component: ElectionResultPage },
    { path: '/parties', component: Parties },
    { path: '/quiz', component: Quiz },
    { path: '/forum', name: 'ForumPage', component: ForumPage },
    { path: '/forum', component: ForumPage }, // Route for forum page
    { path: '/forum/overview', component: OverviewPage },
    { path: '/login', component: Login },
    { path: '/register', component: Register },
    { path: '/admin', component: AdminPanel },
    { path: '/:pathMatch(.*)*', component: NotFound },  // Wildcard for unmatched routes
    { path: '/personal-profile', component: PersonalProfile},
    { path: '/user-profile/:username', name: 'UserProfile', component: UserProfile},
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
