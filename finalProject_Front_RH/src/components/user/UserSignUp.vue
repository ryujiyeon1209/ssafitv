<template>
  <div>
    <div id="container">
        <div id="signUpBox">
            <div id="signUpTitle">Sign Up</div>

            <div id="inputBox">
                <div class="input-box">
                    <div class="input-form-box">
                        <span>아이디</span>
                        <input type="text" class="form-control" v-model="id">
                        <div class="button-check-box" >   
                            <button type="button" class="checkBtn" style="width:30%" @click="idCheck">중복 확인</button>
                        </div>
                    </div>
                </div>

                <div class="input-form-box">
                  <span>비밀번호</span>
                  <input type="password" class="form-control" v-model="password">
                </div>

                <div class="input-box">
                    <div class="input-form-box">
                        <span>비밀번호 확인</span>
                        <input type="password" class="form-control" v-model="passwordCheck">
                        <div class="button-check-box" >
                            <button type="button" class="checkBtn" style="width:100%" @click="pwdCheck">비밀번호 확인</button>
                        </div>
                    </div>
                </div>

                <div class="input-form-box">
                      <span>이름</span>
                      <input type="email" class="form-control" v-model="name">
                </div>

                <div class="input-box">
                    <div class="input-form-box">
                        <span>닉네임</span>
                        <input type="text" class="form-control" v-model="nickname">
                        <div class="button-check-box" >
                            <button type="button" class="checkBtn" style="width:100%" @click="nicknameCheck">중복 확인</button>
                        </div>
                    </div>
                </div>

                <div class="input-form-box">
                  <span>이메일</span>
                  <input type="email" class="form-control" v-model="email">
                </div>

                 <div class="button-signup-box" >
                    <button type="button" class="btn" style="width:100%" @click="regist">회원가입</button>
                </div>
            </div>

        </div>
    </div>
  </div>
</template>

<script>
import Swal from 'sweetalert2'
export default {

   data() {
    return {
        id: "",
        password: "",
        passwordCheck: "",
        name:"",
        email: "",
        nickname: "",
        profile: "대비용",

        //중복확인 체크 - 0 : 중복체크 전, 1 : 중복체크 후
        idCheckNum : 0,
        passCheckNum : 0,
        nicknameCheckNum : 0,
    }
    },

    methods: {
        // email_check( email ) {    
        //         var regex=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        //         return (email != '' && email != 'undefined' && regex.test(email)); 
        // },

        regist() {
            if (
                this.id === "" ||
                this.password === "" ||
                this.passwordCheck === "" ||
                this.name === "" ||
                this.email === "" ||
                this.nickname === "" ||
                this.profile === "" 
            ) {
                Swal.fire({
                position: 'top',
                html: '<div style="font-family: SUITE-Regular;">모든 칸을 입력해주세요!</div>',
                showConfirmButton: false,
                timer: 1000 ,
                font: "SUITE-Regular",  
                })
                return;
            } else if( this.$store.state.userIdCheck===0 ){
                 Swal.fire({
                    position: 'top',
                    // title: '아이디 중복확인 해주세요!',
                    html: '<div style="font-family: SUITE-Regular;">아이디 중복확인 해주세요!</div>',
                    showConfirmButton: false,
                    timer: 1000,
                    
                })
                this.id="";
                return;
            } else if (this.$store.state.userNickNameCheck === 0) {
                Swal.fire({
                    position: 'top',
                    // title: '닉네임 중복확인 해주세요!',
                    html: '<div style="font-family: SUITE-Regular;">닉네임 중복확인 해주세요!</div>',
                    showConfirmButton: false,
                    timer: 1000
                })
                this.nickname="";
                return;
            } else if(this.passCheckNum === 0){
                 Swal.fire({
                    position: 'top',
                    // title: '비밀번호를 확인 해주세요!',
                    html: '<div style="font-family: SUITE-Regular;">비밀번호를 확인 해주세요!</div>',
                    showConfirmButton: false,
                    timer: 1000
                })
                return;
            } else if(!this.checkEmail(this.email)) {
                Swal.fire({
                    position: 'top',
                    // title: '이메일 형식을 확인 해주세요!',
                    html: '<div style="font-family: SUITE-Regular;">이메일 형식을 확인 해주세요!</div>',
                    showConfirmButton: false,
                    timer: 1000,
                })
                return;
            }

            let user = {
                id: this.id,
                password: this.password,
                name:this.name,
                email: this.email,
                nickname: this.nickname,
                profile: this.profile,
            };

            console.log(user)

            this.$store.dispatch("createUser", user);
        },

        idCheck(){
            this.$store.dispatch("idCheck", this.id);

            console.log("this.idCheckNum : " +this.idCheckNum)
        },

        nicknameCheck(){
            this.$store.dispatch("nickNameCheck", this.nickname);
            console.log("this.nicknameCheckNum : " +this.nicknameCheckNum)
        },

        pwdCheck(){
            if(this.password!==this.passwordCheck) {
                Swal.fire({
                    position: 'top',
                    // title: '비밀번호를 일치하게 입력해주세요!',
                    html: '<div style="font-family: SUITE-Regular;">비밀번호를 일치하게 입력해주세요!</div>',
                    showConfirmButton: false,
                    timer: 1000
                })
                this.passwordCheck="";
                this.passwordCheckNum=0;
            } else {
                Swal.fire({
                    position: 'top',
                    // title: '확인되었습니다!',
                    html: '<div style="font-family: SUITE-Regular;">확인되었습니다!</div>',
                    showConfirmButton: false,
                    timer: 1000
                })
                this.passCheckNum = 1;
            }

        },
        checkEmail(email) {
            var regExp = /^[-!#$%&'*+./0-9=?A-Z^_a-z{|}~]+@[-!#$%&'*+/0-9=?A-Z^_a-z{|}~]+.[-!#$%&'*+./0-9=?A-Z^_a-z{|}~]+$/;
            if(regExp.test(email)) { // test 는 정규 Methods 입니다. test는 변경없음
            return true;
            } else {
            return false;
            }
        },
    }

}
</script>

<style scoped>
* { padding: 0; margin: 0; font-family: 'SUITE-Regular';}


#container {
  padding : 100px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  height: 100%;
}

#signUpBox {
  width: 420px;
  text-align: center;
  background-color: #ffffff;
}

/* 버튼 큰 묶음 */
.input-box{
    display: flex;
}

.input-form-box {
  display: flex;
  margin-bottom: 15px;
}
.input-form-box > span {
  display: block;
  text-align: left;
  padding-top: 5px;

  /* 아이디 글자와 input 박스와 거리차이 */
  min-width: 120px;  
}

.form-control{
    width:300px;
    border-radius:15px;
}

#signUpTitle {
    width: 480px;
  color:#000000;
  text-align: center;
  font-weight: bold;
  font-size: 35px;
  text-transform: uppercase;
  padding: 5px;
  margin-bottom: 20px;
  margin-right: 30px;
  background: linear-gradient(to right, #94a5d8, #033074);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.btn{
  height: 45px;
  margin: 20px 30px;
  background-color: #5978d4;
  border-radius:30px;
  color: #fff;

  /* 주변 어둡게 */
   /* box-shadow: 0px 0px 2px 2px #315cdf; */

}

/* 버튼을 누르지 않고 마우스를 올릴때 */
.btn:hover {
    background-color:#0a1d52;
    transition: 0.7s;
    color: white;
  }

.checkBtn{
    width:15px;
    height: 40px;
  margin-left: 5px;
  /* display: flex;
  margin-bottom: 15px; */
   border-radius:15px;
  min-width: 150px;  
  background-color: white;
  border-radius:5px;
  color: #5978d4;
  border : none;

}

.checkBtn:hover {
    border-radius:15px;
    background-color:#95adf7;
    transition: 0.7s;
    color: white;
  }

</style>
