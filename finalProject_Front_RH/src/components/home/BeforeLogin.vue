<template>
  <body>
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="4000"
      controls
      indicators
      style="text-shadow: 1px 1px 2px #333;"
    >
      <b-carousel-slide>
        <template #img>
          <img
            class="d-block img-fluid w-100"
            width="1200"
            height="360"
            :src="require('@/assets/images/carousel1.png')"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>
      
      <b-carousel-slide>
        <template #img>
          <img
            class="d-block img-fluid w-100"
            width="1200"
            height="360"
            :src="require('@/assets/images/carousel2.png')"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>
      
      <b-carousel-slide>
        <template #img>
          <img
            class="d-block img-fluid w-100"
            width="1200"
            height="360"
            :src="require('@/assets/images/배구.png')"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>
    </b-carousel>



    <div class="section sec-1" style="display:center; margin-left:100px">
        <div class="container">
            <div class="row g-0">
                <div class="col-lg-3 ml-3">
                    <h2 class="heading" style="font-size:xx-large">Video 📺</h2>
                    <p style="font-size:medium">다양한 스포츠 동영상을 시청하고 <br/> 여러가지 의견을 남겨보세요!</p>
                    <p><router-link :to="`/videomain`" style="text-decoration:none;"><div class="more-2" style="font-size:large">더 알아보기<b-icon icon="arrow-right" font-scale="1"></b-icon></div></router-link></p>
                </div>
                <div class="col-lg-7 ms-auto">
                    <img src="@/assets/images/basketball1.jpg" alt="Image" class="img-fluid img-r">
                </div>
             </div>
        </div>
    </div>

    <div class="section sec-2" style="display:center; margin-left:100px">
        <div class="container">
            <div class="row g-0">
                <div class="col-lg-6 mb-4 mb-lg-0">
                    <img src="@/assets/images/sportCenter.jpg" alt="IMage" class="img-fluid">
                </div>
                <div class="col-lg-3 ms-auto">
                    <h2 class="heading" style="font-size:x-large">운동장소 검색 🏃🏻‍♂️🏃🏻‍♀️</h2>
                    <p style="font-size:medium">주변에 어떤 운동 시설들이 있는지<br/> 살펴보세요!</p>
                    <p><router-link to="/searchsports" style="text-decoration:none;"><div class="more-2" style="font-size:large">더 알아보기<b-icon icon="arrow-right" font-scale="1"></b-icon></div></router-link></p>
                </div>
            </div>
        </div>
    </div>

    <div class="section sec-1" style="display:center; margin-left:100px">
        <div class="container">
            <div class="row g-0">
                <div class="col-lg-3 ml-3">
                    <h2 class="heading" style="font-size:x-large">Calendar 📅</h2>
                    <p style="font-size:medium">하루를 한 줄로 기록해 보세요! <br/> 5가지의 상태로 그날을 기억해요!</p>
                    <p v-if="this.$store.state.user!=null"><router-link :to="`/showcalendar`" style="text-decoration:none;">
                      <div class="more-2" style="font-size:large">더 알아보기<b-icon icon="arrow-right" font-scale="1"></b-icon></div>
                    </router-link></p> 

                    <!-- ///////////////// -->
                    <!-- 여기 고쳤다아ㅏㅏㅏ!!!!!!!! -->
                    <!-- ///////////////// -->
                    <p v-else><router-link to="" style="text-decoration:none;">
                      <div class="more-2" style="font-size:large"  @click="goCalender">더 알아보기<b-icon icon="arrow-right" font-scale="1"></b-icon></div>
                    </router-link></p> 

                    
                </div>
                <div class="col-lg-7 ms-auto">
                    <img src="@/assets/images/diary2.jpg" alt="Image" class="img-fluid img-r">
                </div>
             </div>
        </div>
    </div>

  </body>
</template>

<script>
import Swal from 'sweetalert2'
export default {
    name:"BeforeLogin",
    data() {
      return {
        slide: 0,
        sliding: null
      }
    },
    methods: {
      onSlideStart() {
        this.sliding = true
      },
      onSlideEnd() {
        this.sliding = false
      },
      goCalender(){
        if(this.$store.state.user==null){
          Swal.fire({
          html: '<div style="font-family: SUITE-Regular;">로그인을 작성하지 않았어요</div> <div style="font-family: SUITE-Regular;">로그인 후 기록을 작성할 수 있어요!</div>',
          // text: "로그인 후 기록을 작성할수 있어요",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: '로그인 하러 가기!',
          cancelButtonText: '취소',
        }).then((result) => {
          this.content="";
          if (result.isConfirmed) {
            this.$router.push('/login')
          }
        })
      }
      }
    },
}
</script>

<style scoped>
body {
  font-family: "Roboto", sans-serif;
  font-weight: 400;
  font-size: 14px;
  color: #7a7a7a;
  line-height: 1.6; }

h1, .h1, h2, .h2, h3, .h3, h4, .h4, h5, .h5, .logo {
  font-family: "Montserrat", sans-serif; }

h1, .h1, h2, .h2, h3, .h3, h4, .h4, h5, .h5 {
  color: #000; }

a {
  -webkit-transition: .3s all ease;
  -o-transition: .3s all ease;
  transition: .3s all ease;
  text-decoration: none; }
.sec-1 .heading {
  margin-bottom: 30px;
  font-weight: normal;
  position: relative;
  display: inline-block; }
  @media (max-width: 767.98px) {
    .sec-1 .heading {
      font-size: 30px;
      margin-bottom: 30px; } }
  /* .sec-1 .heading:after {
    content: "";
    position: absolute;
    height: 1px;
    width: 300%;
    background: #000;
    right: 0;
    bottom: 0; } */

.sec-1 .img-r {
  position: relative;
  right: -100px; }
  @media (max-width: 767.98px) {
    .sec-1 .img-r {
      right: auto !important; } }
.section {
  padding: 4rem 0; }
  .section .heading {
    font-size: 40px;
    font-weight: bold; }
  @media (max-width: 767.98px) {
    .section {
      padding: 2rem 0; } }

.sec-2 .heading {
  margin-bottom: 30px;
  position: relative;
  text-transform: uppercase;
  font-size: 14px;
  font-weight: bold;
  padding-bottom: 5px;
  display: inline-block; }
  @media (max-width: 767.98px) {
    .sec-2 .heading {
      font-size: 24px;
      margin-bottom: 30px; } }
  /* .sec-2 .heading:after {
    content: "";
    position: absolute;
    height: 1px;
    width: 300%;
    background: #000;
    left: 0;
    bottom: 0; } */

    
.more-2 {
  position: relative;
  padding-right: 30px;
  display: inline-block; }
  .more-2 span {
    position: absolute;
    top: 50%;
    right: 0;
    font-size: 12px;
    -webkit-transform: translateY(-50%);
    -ms-transform: translateY(-50%);
    transform: translateY(-50%);
    width: 20px;
    height: 20px;
    background: transparent;
    color: #fc5404;
    line-height: 1.6;
    text-align: center;
    -webkit-transition: .3s all ease;
    -o-transition: .3s all ease;
    transition: .3s all ease; }
  .more-2:hover {
    color: #fc5404; }
    .more-2:hover span {
      background: #fc5404;
      color: #fff; }
  .more-2.dark {
    color: #000; }
    .more-2.dark > span {
      color: #000; }
    .more-2.dark:hover {
      color: #fc5404; }
      .more-2.dark:hover span {
        color: #fff;
        background: #fc5404; }
</style>