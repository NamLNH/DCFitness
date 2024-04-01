import { createWebHistory, createRouter } from "vue-router";
import UserLogin from "../components/UserLogin.vue";
import UserProfile from "../components/UserProfile.vue";

const routes = [
    {
        path: "/",
        alias:"/login",
        name:"login",
        component: UserLogin,
        meta: { title: 'DC Fitness' }
    },
    {
        path:"/profile",
        name:"userProfile",
        component:UserProfile,
        meta: { title: 'DC Fitness' }
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})


// Quan findings for meta above:
router.beforeEach((to) => {
    document.title = to.meta?.title ?? 'Default Title'
  })

export default router;