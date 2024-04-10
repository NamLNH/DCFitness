import { createWebHistory, createRouter } from "vue-router";
import UserLogin from "../components/UserLogin.vue";
import UserProfile from "../components/UserProfile.vue";
import VideoView from "../components/VideoView.vue";
import HomePage from "@/components/HomePage.vue";
import SignUp from "@/components/SignUp.vue";
import ModifyVideo from "@/components/ModifyVideo.vue";
import AddVideo from "@/components/AddVideo.vue";

const routes = [
    {
        path:"/login",
        name:"login",
        component: UserLogin,
        meta: { title: 'DC Fitness' }
    },
    {
        path:"/profile",
        name:"userProfile",
        component:UserProfile,
        meta: { title: 'DC Fitness' }
    },
    {
        path: "/video",
        name: "videoView",
        component: VideoView,
        meta: { title: 'DC Fitness' }
    },
    {
        path: "/",
        alias: "/home",
        name: "homePage",
        component: HomePage,
        meta: { titile: 'DC Fitness'}
    },
    {
        path: "/signup",
        name: "signUpPage",
        component: SignUp,
        meta: { titile: 'DC Fitness'}
    },
    {
        path: "/modifyVideo",
        name: "modifyVideo",
        component: ModifyVideo,
        meta: { title: 'DC Fitness'}
    },
    {
        path: "/addVideo",
        name: "addVideo",
        component: AddVideo,
        meta: { title: 'DC Fitness'}
    },
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