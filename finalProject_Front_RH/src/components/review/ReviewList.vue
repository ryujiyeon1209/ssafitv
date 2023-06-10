<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <div class="main-box clearfix">
            <div class="table-responsive">

              <div class="input-group mb-3">
                <input type="text" class="form-control" style="height:45px; border-radius:15px; margin-top:3px; margin-left:3px" placeholder="댓글을 작성해주세요!" v-model="content" aria-label="Recipient's username" aria-describedby="button-addon2" @keyup.enter="createReview">
                <button class="btn btn-outline-secondary" style="height:45px; border-radius:15px; margin-left:10px; margin-top:3px;" type="button" id="button-addon2" @click="createReview">댓글 등록</button>
              </div>

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
                    <td>{{index + 1}}</td>
                    <td>{{review.nickname}}</td>
                    <td>{{review.reviewTime}}</td>
                    <td>{{review.content}}</td>
                    <td style="width: 20%;" v-if="review.nickname != `UNKNOWN`">
                      <b-button variant="outline-primary" class="mr-1 mb-1" size="sm" @click="modifyUserCheck(review)">
                        수정 <b-icon icon="pencil" aria-hidden="true"></b-icon>
                      </b-button>
                      <b-button variant="outline-danger" class="ml-1 mb-1" size="sm" @click="deleteUserCheck(review)">
                        삭제 <b-icon icon="trash" aria-hidden="true"></b-icon>
                      </b-button>
                    </td>
                    <td v-else></td>
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
    <br/>
  </div>
</template>

<script>
import {mapState} from "vuex";
import Swal from 'sweetalert2'
export default {
   data() {
    return {
      content: "",
    }
  },
  props : {
    videoId : String
  },

  computed : {
    ...mapState(["reviewList"]),
    rows() {
        return this.items.length
      }
  },

  methods: {
    //댓글 등록
    createReview() {

      //만약 로그인이 되어 있지 않다면 로그인 하라고 알려주기!
      if(this.$store.state.user==null){
          Swal.fire({
          title: '로그인을 하지 않았어요!',
          text: "로그인 후 댓글을 작성할수 있어요",
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

      let review = {
        idx: this.$store.state.allReviewList.length+1,
        like: 0,
        content: this.content,
        reviewTime: "0",
        videoId: this.videoId,  //경로에 있는 videoId 가져오기
        nickname: this.$store.state.user.nickname,
        userId: this.$store.state.user.id,

      }

      this.$store.dispatch("createReview", review);
      this.content="";
    },

    //댓글 수정
    modifyUserCheck(review){
      
      this.$store.commit("GET_REVIEW", review)
      console.log(review)

      if(review.userId != this.$store.state.user.id ){
        Swal.fire({
          position: 'top',
          icon: 'warning',
          title: '작성자만 수정할 수 있어요!',
          showConfirmButton: false,
          timer: 1000
        })
        return;
      } else {
        this.$router.push(`/review/modify/${this.videoId}`)
      }
    },

    //댓글 삭제
    deleteUserCheck(review){
      this.$store.commit("GET_REVIEW", review)
        console.log("리뷰 삭제 : " +review)

        if(review.userId != this.$store.state.user.id ){
           Swal.fire({
              position: 'top',
              icon: 'warning',
              title: '작성자만 삭제할 수 있어요!',
              showConfirmButton: false,
              timer: 1000
        })
          return;
        } else {
          this.$store.dispatch("deleteReview", review);
        }
    },



  },

  created(){
    this.$store.dispatch("getVidepReviewList", this.videoId)
    console.log(this.$store.state.reviewList)
    
  },

  beforeMount() {
    console.log("리뷰리스트 가져오기")
    this.$store.dispatch("getVidepReviewList", this.videoId)
  },

}
</script>

<style>

</style>