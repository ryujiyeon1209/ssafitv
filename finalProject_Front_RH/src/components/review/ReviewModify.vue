<template>
  <div>
    <div>
      <hr />
      <b-row>
      <b-col></b-col>
      <b-col class="text-center" style="width:730px">
        <iframe 
        width="720" 
        height="480" 
        :src="`https://www.youtube.com/embed/${this.$route.params.videoId}`" 
        title="YouTube video player" 
        frameborder="0" 
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" 
        allowfullscreen>
        </iframe>
      </b-col>
      <b-col class="text-lg-start">
        <div v-if="this.$store.state.heart==0">
          <b-icon icon="heart" font-scale="2" variant="danger" @click="videolike" class="likebtn"></b-icon>
        </div>
        <div v-else>
          <b-icon icon="heart-fill" font-scale="2" variant="danger" @click="videounlike" class="likebtn"></b-icon>
        </div>
      </b-col>
    </b-row>

     <hr />
    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <div class="main-box clearfix">
            <div class="table-responsive">

              <div class="input-group mb-3">
                <input type="text" class="form-control" style="height:45px; border-radius:15px; margin-top:3px; margin-left:3px" :placeholder="`${this.$store.state.review.content}`" v-model="content" aria-label="Recipient's username" aria-describedby="button-addon2" @keyup.enter="modifyReview">
                <button class="btn btn-outline-secondary" style="height:45px; border-radius:15px; margin-left:10px; margin-top:3px;" type="button" id="button-addon2" @click="modifyReview">댓글 수정</button>
              </div>
              
              <br/>
              <br/>

              <table class="table user-list">
                <thead>
                  <tr>
                    <th><span>No</span></th>
                    <th><span>닉네임</span></th>
                    <th><span>작성시간</span></th>
                    <th><span>내용</span></th>
                    <th>&nbsp;</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(review, index) in reviewList" :key="index">
                    <td>{{ index + 1 }}</td>
                    <td>{{ review.nickname }}</td>
                    <td>{{ review.reviewTime }}</td>
                    <td>{{ review.content }}</td>
                    <td style="width: 20%;"></td>
                  </tr>
                </tbody>
              </table>

            </div>
          </div>
        </div>
      </div>
    </div>
    
    <br/>

      
    <br/>
  </div>

  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {

  data(){
    return {
      content : "",
      beforevideoId:"",
    }
  },

  computed : {
    ...mapState(["reviewList"])
  },

  methods : {
    videolike() {
      let likevideo = {
        videoId: `${this.videoiddata}`,
        userId: `${this.$store.state.user.id}`,
      }
      this.$store.dispatch("addLikeVideo", likevideo);

    },
    videounlike() {
      let likevideo = {
        videoId: `${this.videoiddata}`,
        userId: `${this.$store.state.user.id}`,
      }
      this.$store.dispatch("deleteLikeVideo", likevideo);
    },

    //댓글 수정
    modifyReview(){
      let review = {
        idx : this.$store.state.review.idx,
        like : 0,
        content : this.content,
        reviewTime : "0",
        videoId : this.$store.state.review.videoId,
        nickname : this.$store.state.user.nickname,
        userId : this.$store.state.user.id,

      }
      this.$store.dispatch("modifyReview", review);
      this.$router.push({name: 'VideoDetail', params: {videoiddata: `${this.beforevideoId}`, videotitledata:`title is none`}});
    },

  },
  created(){
    const pathName = new URL(document.location).pathname.split("/");
    this.beforevideoId = pathName[pathName.length - 1];
  }
}
</script>

<style>

</style>