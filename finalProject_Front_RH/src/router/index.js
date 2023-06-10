import Vue from 'vue'
import VueRouter from 'vue-router'
import SearchSportView from "@/views/SearchSportView.vue"
import CalendarView from "@/views/CalendarView.vue"
import VideoMain from "@/components/video/VideoMain.vue"
import HomeView from "@/views/HomeView.vue"
import UserSignUp from "@/components/user/UserSignUp.vue"
import UserLoginForm from "@/components/user/UserLoginForm.vue"
import UserFindPass from "@/components/user/UserFindPass.vue"
import MyProfile from "@/components/user/MyProfile.vue"
import MyReview from "@/components/user/MyReview.vue"
import VideoDetail from "@/components/video/VideoDetail.vue"
import ReviewModify from "@/components/review/ReviewModify.vue"
import ShowWeather from "@/components/home/ShowWeather.vue"
Vue.use(VueRouter)

const routes = [
  //메인 화면
  {
    path : '/',
    name : 'HomeView',
    component : HomeView,
  },
  //메인 화면
  {
    path : '/home',
    name : 'HomeView',
    component : HomeView,
  },
  //회원가입 화면
  {
    path : '/signUp',
    name : 'UserSignUp',
    component : UserSignUp,
  },

  //로그인 화면
  {
    path : '/login',
    name : 'UserLoginForm',
    component : UserLoginForm,
  },

  //비밀번호 찾기 화면
  {
    path : '/findPass',
    name : 'UserFindPass',
    component : UserFindPass,
  },
  //영상 홈 화면
  {
    path:"/videomain",
    name:"VideoMain",
    component: VideoMain,
  },
  //영상 상세화면
  {
    path : '/videodetail',
    name : 'VideoDetail',
    component : VideoDetail,
  },
  //리뷰 수정화면
  {
    path : '/review/modify/:videoId',
    name : 'ReviewModify',
    component : ReviewModify,
  },

  //사용자 프로필 화면
  {
    path : '/myProfile',
    name : 'MyProfile',
    component : MyProfile,
  },

  //사용자 리뷰 목록화면
  {
    path : '/myProfile/review',
    name : 'MyReview',
    component : MyReview,
  },
  //스포츠센터 찾기 화면
  {
    path:"/searchsports",
    name:"SearchSportView",
    component: SearchSportView,
  },
  //사용자 운동 기록 화면
  {
    path:"/showcalendar",
    name:"CalendarView",
    component: CalendarView,
  },
  //날씨 연습용 화면
  {
    path:"/weather",
    name:"ShowWeather",
    component: ShowWeather,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
