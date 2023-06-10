<template>
  <div>
    <div class="d-flex align-items-center">
      <div class="container position-relative">
        <div class="row d-flex justify-content-center">
          <div class="col-lg-7 text-center">
            <h2 style="font-family: 'SUITE-Regular';">Sports Video List</h2>
            <br/>
            <div class="row equal-heights justify-content-center">
                <input style=" height:40px; border-radius: 15px;" class="col-lg-6" type="text" v-model="searchkeyword" @keyup.enter="setkeyword"/>
                &nbsp;
                <b-button style=" height:40px; border-radius: 15px;" variant="outline-primary"  @click="setkeyword">Search</b-button>
            </div>
            <br/>
            <p style="font-family: 'SUITE-Regular';">다양한 스포츠 동영상을 확인하고, 필요한 동영상을 검색해보세요!<br/>
                로그인을 하면 댓글을 작성해 사람들과 의견을 나눌 수 있습니다.
            </p>
          </div>
        </div>
      </div>
    </div>
    <br/>
    <b-row class="row d-flex justify-content-center align-items-center" v-if="keyword != ``">
        <h3 class="fw-light text-center text-lg-start" style="width:150px; font-family: 'SUITE-Regular';">🔎<hr/>Search</h3>
        &nbsp; &nbsp;
        <div v-if="keyword!=`축구` && keyword!=`농구` && keyword!=`야구` && keyword!=`배구` && keyword!=`탁구` && keyword!=`수영` && keyword!=`배드민턴` && keyword!=`테니스`">
            <video-list :keyword="keyword+`운동`"></video-list>
        </div>
        <div v-else>
            <video-list :keyword="keyword"></video-list>
        </div>
    </b-row>
    <b-row class="row d-flex justify-content-center align-items-center" v-if="this.$store.state.user != null && this.$store.state.likevideoList!=null">
        
        <video-jjim></video-jjim>
    </b-row>
    <b-row class="row d-flex justify-content-center align-items-center">
        <h3 class="fw-light text-center text-lg-start" style="width:150px; font-family: 'SUITE-Regular';">⚽<hr/>Soccer</h3>
        &nbsp; &nbsp;
        <video-list :keyword="`축구`"></video-list>
    </b-row>
    <b-row class="row d-flex justify-content-center align-items-center">
        <h3 class="fw-light text-center text-lg-start" style="width:150px; font-family: 'SUITE-Regular';">🏀<hr/>Basketball</h3>
        &nbsp; &nbsp;
        <video-list :keyword="`농구`"></video-list>
    </b-row>
    <b-row class="row d-flex justify-content-center align-items-center">
        <h3 class="fw-light text-center text-lg-start" style="width:150px; font-family: 'SUITE-Regular';">⚾<hr/>Baseball</h3>
        &nbsp; &nbsp;
        <video-list :keyword="`야구`"></video-list>
    </b-row>
    <b-row class="row d-flex justify-content-center align-items-center">
        <h3 class="fw-light text-center text-lg-start" style="width:150px; font-family: 'SUITE-Regular';">🏐<hr/>Volleyball</h3>
        &nbsp; &nbsp;
        <video-list :keyword="`배구`"></video-list>
    </b-row>
    <b-row class="row d-flex justify-content-center align-items-center">
        <h3 class="fw-light text-center text-lg-start" style="width:150px; font-family: 'SUITE-Regular';">🏓<hr/>Ping-pong</h3>
        &nbsp; &nbsp;
        <video-list :keyword="`탁구`"></video-list>
    </b-row>




    <!-- <div>검색</div> -->
    <!-- <video-list :keyword="keyword"></video-list> -->
    <!-- <div>축구</div> -->
    <!-- <video-list :keyword="`축구`"></video-list> -->
        
    <!-- <div>농구</div>
    <video-list :keyword="`농구`"></video-list>
    <div>야구</div>
    <video-list :keyword="`야구`"></video-list>
    <div>배구</div>
    <video-list :keyword="`배구`"></video-list> -->
  </div>
</template>

<script>

import VideoList from "@/components/video/VideoList.vue";
import VideoJjim from "@/components/video/VideoJjim.vue";

export default {
    name:"VideoMain",
    components:{
        VideoList,
        VideoJjim,
    },
    data(){
        return {
            keyword: "",
            searchkeyword: "",

        }
    },
    methods: {
        setkeyword(){
            this.keyword=this.searchkeyword
        }
    },
}
</script>

<style>

</style>