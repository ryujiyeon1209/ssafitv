<template>
  <div>
    <hr />
    <b-row>
      <b-col></b-col>
      <b-col class="text-center" style="width:730px">
        <iframe 
        width="720" 
        height="480" 
        :src="`https://www.youtube.com/embed/${this.videoiddata}`" 
        title="YouTube video player" 
        frameborder="0" 
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" 
        allowfullscreen>
        </iframe>
      </b-col>
      <b-col class="text-lg-start">

        <!-- like가 0이면 하트색깔 비우기 -->
        <div v-if="this.$store.state.heart==0">
          <b-icon icon="heart" font-scale="2" variant="danger" value=0 @click="videolike" class="likebtn"></b-icon>
        </div>

        <!-- like 1이면 하트색깔 채우기 -->
        <div v-else>
          <b-icon icon="heart-fill" font-scale="2" variant="danger" value=1 @click="videounlike" class="likebtn"></b-icon>
        </div>
      </b-col>
    </b-row>
    <hr />
    <review-list :videoId="this.videoiddata"></review-list>
  </div>
</template>

<script>
import ReviewList from "@/components/review/ReviewList.vue"
export default {
  name:"VideoDetail",
  date(){
    return{
      videoiddata:"",
      videotitledata:"",
    }
  },
  components:  {
    ReviewList,
  },
  methods:{
    videolike(){
      let likevideo = { 
        videoId: `${this.videoiddata}`,
        userId: `${this.$store.state.user.id}`,
      }
      this.$store.dispatch("addLikeVideo", likevideo);

    },
    videounlike(){
      let likevideo = { 
        videoId: `${this.videoiddata}`,
        userId: `${this.$store.state.user.id}`,
      }
      this.$store.dispatch("deleteLikeVideo", likevideo);
      // this.like=Math.abs(this.like-1);
      // console.log("this.like : " +this.like)
    }
  },
  created() {
    this.videotitledata = `${this.$route.params.videotitledata}`;
    this.videoiddata = `${this.$route.params.videoiddata}`;
    let video = {
      videoId: `${this.videoiddata}`,
      title: "Hello, this is video title",
      viewCnt: 0,
      videoType: "none",
    }
    this.$store.dispatch("addVideo", video);


    let likevideo = {
      userId : `${this.$store.state.user.id}`,
      videoId : `${this.$route.params.videoiddata}`,
    }
    
    //생성되고, 현재 user가 찜한 동영상인지 확인하기
    this.$store.dispatch("getLikeVideo", likevideo);
  },
}
</script>

<style>
.likebtn{
  margin-top: 10px;
}
.likebtn:hover{
  margin-top: 10px;
  cursor:pointer;
}
</style>