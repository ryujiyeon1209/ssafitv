<template>
  <div>
    <div class="container">
        <div class="row text-center text-lg-start">
            <div class="col-lg-3 col-md-4 col-6" v-for="video in videos" :key="video.id.videoId">
                <router-link :to="{name: 'VideoDetail', params: {videoiddata: `${video.id.videoId}`, videotitledata:`${video.snippet.title}`}}" class="d-block mb-4 h-100"><img :src="`https://img.youtube.com/vi/${video.id.videoId}/0.jpg`" class="img-fluid img-thumbnail"></router-link>
            </div>
            <!-- <div class="col-lg-3 col-md-4 col-6">
                <router-link :to="{name: 'VideoDetail', params: {videoiddata: `aaaaaa`, videotitledata:`aaaaa`}}" class="d-block mb-4 h-100"><img src="https://img.youtube.com/vi/lOBAbBcPK8E/0.jpg" class="img-fluid img-thumbnail"></router-link>
            </div>
            <div class="col-lg-3 col-md-4 col-6">
                <router-link :to="{name: 'VideoDetail', params: {videoiddata: `aaaaaa`, videotitledata:`aaaaa`}}" class="d-block mb-4 h-100"><img src="https://img.youtube.com/vi/lOBAbBcPK8E/0.jpg" class="img-fluid img-thumbnail"></router-link>
            </div>
            <div class="col-lg-3 col-md-4 col-6">
                <router-link :to="{name: 'VideoDetail', params: {videoiddata: `aaaaaa`, videotitledata:`aaaaa`}}" class="d-block mb-4 h-100"><img src="https://img.youtube.com/vi/lOBAbBcPK8E/0.jpg" class="img-fluid img-thumbnail"></router-link>
            </div>
            <div class="col-lg-3 col-md-4 col-6">
                <router-link :to="{name: 'VideoDetail', params: {videoiddata: `aaaaaa`, videotitledata:`aaaaa`}}" class="d-block mb-4 h-100"><img src="https://img.youtube.com/vi/lOBAbBcPK8E/0.jpg" class="img-fluid img-thumbnail"></router-link>
            </div> -->
        </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
    name:"VideoList",
    data(){
        return {
            videos:[],
        }
    },
    props:{
        keyword: String,
    },
    methods: {
        
  },
  created(){
    const URL = "https://www.googleapis.com/youtube/v3/search";
        const API_KEY = process.env.VUE_APP_YOUTUBE_API_KEY;
        axios({
            url: URL,
            method: "GET",
            params: {
            key: API_KEY,
            part: "snippet",
            q: this.keyword,
            type: "video",
            maxResults: 4,
            },
        })
            .then((res) => {
            this.videos = res.data.items
            })
            .catch((err) => console.log(err));
  },
  beforeMount(){
    
  },
  updated(){
    
  },
  mounted(){
    
  },
  watch:{
    keyword: function(){
        const URL = "https://www.googleapis.com/youtube/v3/search";
        const API_KEY = process.env.VUE_APP_YOUTUBE_API_KEY;
        axios({
            url: URL,
            method: "GET",
            params: {
            key: API_KEY,
            part: "snippet",
            q: this.keyword,
            type: "video",
            maxResults: 4,
            },
        })
            .then((res) => {
            this.videos = res.data.items
            })
            .catch((err) => console.log(err));
    }
  }

}
</script>

<style></style>