<template>
    <div>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                <div class="main-box clearfix">
                    <div class="table-responsive">
                    
                     <div class="text-center" style="height: 100px; margin-top : 40px">
                        <h2>My Review</h2>
                    </div>

                    <br/>

                    <table class="table user-list text-center">
                        <thead>
                        <tr>
                            <th style="width: 5%"><span>No</span></th>
                            <th style="width: 20%" ><span>영상</span></th>
                            <th style="width: 25%"><span>작성시간</span></th>
                            <th style="width: 30%"><span>내용</span></th>
                            <th style="width: 20%">&nbsp;</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(review, index) in reviewList" :key="index">
                            <td style="width: 5%; line-height: 100px;">{{index + 1}}</td>
                            <td style="width: 20%"> <b-col class="text-center">
                                <router-link :to="{name: 'VideoDetail', params: {videoiddata: `${review.videoId}`, videotitledata:`title`}}">
                                    <img :src="`https://img.youtube.com/vi/${review.videoId}/0.jpg`" class="img-fluid"  style="width : 200px; height : 120px;" ></router-link>
                            </b-col></td>
                            <td style="width: 25%; line-height: 100px;">{{review.reviewTime}}</td>
                            <td style="width: 30%; line-height: 100px;">{{review.content}}</td>
                            <td style="width: 20%; line-height: 100px;" v-if="review.nickname != `UNKNOWN`">
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
    </div>

    

</template>

<script>
import {mapState} from "vuex";
export default {
    name:"myReview",

    created(){
        this.$store.dispatch("getUserReviewList", this.$store.state.user.id);
    },

    beforeMount(){
         this.$store.dispatch("getUserReviewList", this.$store.state.user.id);
    },
    
    computed : {
    ...mapState(["reviewList"])
    },

    methods: {
        //댓글 수정
        modifyUserCheck(review){
        
            this.$store.commit("GET_REVIEW", review)
            this.$router.push(`/review/modify/${review.videoId}`)
        },

        //댓글 삭제
        deleteUserCheck(review){
        this.$store.commit("GET_REVIEW", review)

            this.$store.dispatch("deleteReview2", review);

        
        },
    }

}
</script>

<style>

</style>