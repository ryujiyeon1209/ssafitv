<template>
  <div>

      
    <div id="container1">
      <div id="profileBox">

        <div id="myPageTitle" style="font-family: 'SUITE-Regular';">My Page</div>
        
        <div id="profileBox">
            <div class="profile-form-box">
              <span style="font-family: 'SUITE-Regular';">아이디</span>
              <input type="text" style="border-radius:15px" class="form-control" :value="`${user.id}`" readonly>
            </div>
            <div class="profile-form-box">
              <span style="font-family: 'SUITE-Regular';">이름</span>
              <input type="text" style="border-radius:15px" class="form-control" :value="`${user.name}`" readonly>
            </div>
            <div class="profile-form-box">
              <span style="font-family: 'SUITE-Regular';">닉네임</span>
              <input type="text" style="border-radius:15px" class="form-control" :value="`${user.nickname}`" readonly>
            </div>
            <div class="profile-form-box">
              <span style="font-family: 'SUITE-Regular';">이메일</span>
              <input type="text" style="border-radius:15px" class="form-control" :value="`${user.email}`" readonly>
            </div>
        </div>
      </div>
    </div>



    <div id= "container0">
        <div id="myPageBox">

                <div class="button-signup-box" >
                    <button type="button"  class="btn0" style="width:100%; border-radius:15px font-family: 'SUITE-Regular';" @click="showModify">프로필 수정</button>
                </div>

                <div class="button-signup-box" >
                    <button type="button" class="btn0" style="width:100%;  border-radius:15px font-family: 'SUITE-Regular';" @click="goMyReview">리뷰 목록</button>
                </div>
                <div class="button-signup-box" >
                    <button type="button" class="brokenBtn" style="width:100%; border-radius:15px font-family: 'SUITE-Regular';" @click="deleteUser">회원 탈퇴</button>
                </div>

        </div>
    </div>



    <div v-if="this.modify==1">

        <div id="contanier">
            <div id="modifyBox">
                <!-- <div class="input-box">
                    <span>프로필 수정</span>
                    <div class="input-form-box">
                        <input type="text" class="form-control" v-model="profile">
                        <button type="button" class="btn" style="width:80%" @click="profileModify">프로필 수정</button>
                    </div>
                </div> -->

                <div class="input-box">
                    <span style="font-family: 'SUITE-Regular';">닉네임 수정</span>
                    <div class="input-form-box">
                        <input type="text" style="border-radius:15px" class="form-control" v-model="nickname">
                        <!-- <div class="button-getpass-box" > -->
                            <button type="button" class="btn" style="width:80% border-radius:15px font-family: 'SUITE-Regular';" @click="nicknameCheck">중복 확인</button>
                            <button type="button" class="btn" style="width:80% border-radius:15px font-family: 'SUITE-Regular';" @click="nicknameModify">닉네임 수정</button>
                        <!-- </div> -->
                    </div>
                </div>

                <div class="input-box">
                    <span style="font-family: 'SUITE-Regular';">비밀번호 수정</span>
                    <div class="input-form-box">
                        <input type="text" style="border-radius:15px" class="form-control" v-model="password">
                        <button type="button" class="btn" style="width:80% border-radius:15px font-family: 'SUITE-Regular';" @click="passwordModify">비밀번호 수정</button>
                    </div>
                </div>
            
            </div>   
        </div>
    </div>

    

  </div>
</template>

<script>
import {mapState} from 'vuex';
import Swal from 'sweetalert2'
export default {
    data() {
        return {
            profile: "",
            nickname:"",
            password: "",

            nicknameCheckNum : 0,
            modify : 0,

        }
    },

    computed : {
        ...mapState(["user"])
    },

    methods : {

        //프로필 수정 버튼
        showModify(){
            this.modify=Math.abs(this.modify-1);
        },

        //내가 작성한 리뷰 목록으로 가기 
        goMyReview(){
            this.$router.push('/myProfile/review')
        },

        // 프로필 수정
        profileModify(){
             let user = {
                id: this.$store.state.user.id,
                password: this.$store.state.user.password,
                name: this.$store.state.user.name,
                email: this.$store.state.user.profile,
                nickname: this.$store.state.user.nickname,
                profile: this.profile,
            };
            this.profile=""
            this.$store.dispatch("modifyProfile", user);
            this.$store.dispatch("getUser", user.id);
            
        },

        ////////////////////////////
        //닉네임 중복확인 > 이거 바꿈!
        ////////////////////////////
        nicknameCheck() {
            this.$store.dispatch("nickNameCheck", this.nickname);
        },

        //닉네임 수정
        nicknameModify() {
            if(this.$store.state.userNickNameCheck ===0){
                Swal.fire({
                  position: 'top',
                  // title: '닉네임 중복확인 해주세요!',
                  html: '<div style="font-family: SUITE-Regular;">닉네임 중복확인 해주세요!</div>',
                  showConfirmButton: false,
                  timer: 1000
                })
                return;
            }

            let user = {
                id: this.$store.state.user.id,
                password: this.$store.state.user.password,
                name: this.$store.state.user.name,
                email: this.$store.state.user.profile,
                nickname: this.nickname,
                profile: this.$store.state.user.profile,
            };
            this.nickname="";
            this.$store.dispatch("modifyNickname", user);

        },

        //비밀번호 수정
        passwordModify() {
            if(this.password==""){
              Swal.fire({
                position: 'top',
                // title: '비밀번호를 입력해주세요!',
                html: '<div style="font-family: SUITE-Regular;">비밀번호를 입력해주세요!</div>',
                showConfirmButton: false,
                timer: 1000
            })
              return;
            }

            let user = {
                id: this.$store.state.user.id,
                password: this.password,
                name: this.$store.state.user.name,
                email: this.$store.state.user.profile,
                nickname: this.$store.state.user.nickname,
                profile: this.$store.state.user.nickname,
            };
            this.password="";
            this.$store.dispatch("modifyPassword", user);
            Swal.fire({
              position: 'top',
              // title: '비밀번호 수정완료!',
              html: '<div style="font-family: SUITE-Regular;">비밀번호 수정 완료!</div>',
              showConfirmButton: false,
              timer: 1000
            })
            this.$store.dispatch("getUser", user.id);
        },

        //회원 탈퇴
        deleteUser(){

          Swal.fire({
            // title: '정말로 탈퇴하실건가요?',
            // text: "탈퇴를 한다면 여태까지의 기록이 영영 삭제됩니다!",
            html: '<div style="font-family: SUITE-Regular;">정말로 탈퇴하실건가요?</div><div style="font-family: SUITE-Regular;">탈퇴를 한다면 여태까지의 기록이 영영 삭제됩니다!</div>',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '탈퇴하기',
            cancelButtonText: '취소',
          }).then((result) => {
            if (result.isConfirmed) {
            
              console.log(typeof (this.$store.state.user.id))
              this.$store.dispatch("deleteUser", this.$store.state.user.id);

              sessionStorage.removeItem("access-token");
              this.$store.commit("LOGOUT_USER");


              //회원탈퇴 후 새로운 userList를 state의 userList로 바꾸기
              this.$store.dispatch("getUserList")
              console.log(this.$store.state.userList)
          
            }
        })
        },
    },

}
</script>


<style scoped>
* { padding: 0; margin: 0; }


html, body {
    height: 100%;
  background: #ffffff;
}

#myPageTitle {
    color:#000000;
    text-align: center;
    font-weight: bold;
    font-size: 35px;
    text-transform: uppercase;
    padding: 5px;
    margin-bottom: 20px;
    background: linear-gradient(to right, #94a5d8, #033074);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
}

#container {
  padding : 10px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  height: 100%;
}

#container0{
    padding : 20px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    height: 100%;
}

#container1 {
  padding : 20px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  height: 100%;
}

#profileBox {
  width: 420px;
  text-align: center;
  background-color: #ffffff;
}

#myPageBox {
    width:  550px;
    display: flex;
    text-align: center;
    flex-direction: column; 
    align-items: center; 
    justify-content: center;
}

#modifyBox {
 width:  600px;
  margin: 0 auto; /* 가운데 정렬을 위한 margin 속성 추가 */
  text-align: center;
  justify-content: center;
  align-items: center; 
  background-color: #ffffff;
}

/* 수정하기 버튼 */
 .button-signup-box{ 
    width: 70%;
    display: block;
    text-align: center;
    justify-content: center;
    align-items: center; 
    /* background-color: blue; */
} 


/* 본격적인 수정*/
.input-box {
  width: 70%;
  margin: auto;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.input-form-box {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 15px;
}

.input-form-box .btn {
  width: 80%;
  margin: 0px, 15px;
}

.input-box > span {
  display: block;
  text-align: left;
  padding-top: 5px;
  min-width: 75px;
  align-self: flex-start; /* Align the span to the left */
}

.input-form-box .form-control {
    margin-top: 5px;
    margin-right: 10px;
}

.input-form-box .btn {
  margin-left: 3px;
}



.profile-form-box {
  display: flex;
  margin-bottom: 15px;
}

.profile-form-box > span {
  display: block;
  text-align: left;
  padding-top: 5px;

  /* 아이디 글자와 input 박스와 거리차이 */
  min-width: 75px;  
}




.input-form-box .btn {
    height: 40px;
    background-color: #5978d4;
    border-radius:10px;
    color: #fff;
}

.input-form-box .btn:hover {
    background-color:#0a1d52;
    transition: 0.7s;
    color: white;
}



.button-signup-box .brokenBtn {
    height: 40px;
    margin: 10px 0;
    background-color: #7c7e85;
    border-radius:10px;
    color: #fff;
    border:none;

    text-align: center;
    align-items: center; 
    justify-content: space-around;
}

.brokenBtn:hover {
    background-color:#01050f;
    transition: 0.7s;
    color: white;
}




.button-signup-box .btn0{
    border : none;
    height: 40px;
    margin: 10px 0;
    background-color: #5978d4;
    border-radius:10px;
    color: #fff;

    text-align: center;
    align-items: center; 
    justify-content: space-around;
}

.btn0:hover {
    background-color:#0a1d52;
    /* border: #0a1d52; */
    transition: 0.7s;
    color: white;
  }
</style>