<template>
<div>
  <div v-if="this.videos.length!=0" style="display:flex; align-items:center;">
    <h3 class="fw-light text-center text-lg-start" style="width:150px; font-family: 'SUITE-Regular';">😍<hr/>Like</h3>
    &nbsp; &nbsp;
    <div class="container" >
        <div class="row text-center text-lg-start" v-if="this.videos.length>=4">
            <div class="col-lg-3 col-md-4" v-for="(video,index) in videos" :key="index">
                <router-link :to="{name: 'VideoDetail', params: {videoiddata: `${video.videoId}`, videotitledata:`JJIMVIDEOS`}}" class="d-block mb-4 mr-2 h-100" v-if="index<=3" ><img :src="`https://img.youtube.com/vi/${video.videoId}/0.jpg`" class="img-thumbnail"></router-link>
            </div>
        </div>
        <div class="row text-center text-lg-start" v-else-if="this.videos.length==3">
            <div class="col-lg-3 col-md-4" v-for="(video,index) in videos" :key="index">
                <router-link :to="{name: 'VideoDetail', params: {videoiddata: `${video.videoId}`, videotitledata:`JJIMVIDEOS`}}" class="d-block mb-4 mr-2 h-100"><img :src="`https://img.youtube.com/vi/${video.videoId}/0.jpg`" class="img-thumbnail"></router-link>
            </div>
            <div class="col-lg-3 col-md-4">
                <div class="d-block mb-4 h-100"><img :src="require('@/assets/images/빈이미지.png')" class="img-fluid img-thumbnail"></div>
            </div>
        </div>
        <div class="row text-center text-lg-start" v-else-if="this.videos.length==2">
            <div class="col-lg-3 col-md-4" v-for="(video,index) in videos" :key="index">
                <router-link :to="{name: 'VideoDetail', params: {videoiddata: `${video.videoId}`, videotitledata:`JJIMVIDEOS`}}" class="d-block mb-4 mr-2 h-100"><img :src="`https://img.youtube.com/vi/${video.videoId}/0.jpg`" class="img-thumbnail"></router-link>
            </div>
            <div class="col-lg-3 col-md-4">
                <div class="d-block mb-4 h-100"><img :src="require('@/assets/images/빈이미지.png')" class="img-fluid img-thumbnail"></div>
            </div>
            <div class="col-lg-3 col-md-4">
                <div class="d-block mb-4 h-100"><img :src="require('@/assets/images/빈이미지.png')" class="img-fluid img-thumbnail"></div>
            </div>
        </div>
        <div class="row text-center text-lg-start" v-else-if="this.videos.length==1">
            <div class="col-lg-3 col-md-4" v-for="(video,index) in videos" :key="index">
                <router-link :to="{name: 'VideoDetail', params: {videoiddata: `${video.videoId}`, videotitledata:`JJIMVIDEOS`}}" class="d-block mb-4 mr-2 h-100"><img :src="`https://img.youtube.com/vi/${video.videoId}/0.jpg`" class="img-thumbnail"></router-link>
            </div>
            <div class="col-lg-3 col-md-4">
                <div class="d-block mb-4 h-100"><img :src="require('@/assets/images/빈이미지.png')" class="img-fluid img-thumbnail"></div>
            </div>
            <div class="col-lg-3 col-md-4">
                <div class="d-block mb-4 h-100"><img :src="require('@/assets/images/빈이미지.png')" class="img-fluid img-thumbnail"></div>
            </div>
            <div class="col-lg-3 col-md-4">
                <div class="d-block mb-4 h-100"><img :src="require('@/assets/images/빈이미지.png')" class="img-fluid img-thumbnail"></div>
            </div>
        </div>
    </div>
    </div>
  </div>
</template>

<script>
export default {
    name:"VideoList",
    data(){
        return {
            videos:[],
        }
    },
    methods: {
  },
  created(){
    this.$store.dispatch("getLikeVideoList",this.$store.state.user.id);
    this.videos=this.$store.state.likevideoList;
    console.log(this.videos.length);
  },
  beforeMount(){
    
  },
  updated(){
    
  },
  mounted(){
    
  },
  watch:{
        videos: function(){
            this.$store.dispatch("getLikeVideoList",this.$store.state.user.id);
            this.videos=this.$store.state.likevideoList;
        }
    }
}

</script>

<style></style>