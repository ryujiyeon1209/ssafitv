<template>
  <div>
   
    <div id="container">
      <div id="findPassBox">
        <div id="findPassBoxTitle">Find Password</div>

        <div id="inputBox">
           <div class="input-form-box">
              <span>아이디</span>
              <input type="text" name="uid" class="form-control" v-model="id">
           </div>
           <div class="input-form-box">
              <span>이메일</span>
              <input type="email" name="upw" class="form-control" v-model="email">
            </div>
            <div class="button-getpass-box" >
              <button type="button" class="btn1" style="border:none; width:100%" @click="findPass">임시 비밀번호 받기</button>
            </div>
            <div class="button-getpass-box" >
              <button type="button" class="btn2" style="border:none; width:100%" @click="goLogin">로그인 하러 가기</button>
            </div>


        </div>
      </div>

      <!-- <label id="id">아이디 : </label>
      <input type="id" v-model="id"> <br>

      <label id="email">이메일 : </label>
      <input type="email" v-model="email"> <br>

      <button @click="findPass">임시 비밀번호 받기</button>
      <button><router-link to='/login'>로그인 하러 가기</router-link></button> -->
    </div>


  </div>
</template>

<script>
import Swal from 'sweetalert2'
export default {

  data() {
    return {
      id: "",
      email: "",
    }
  },

  methods: {
    findPass() {

      if (this.id == "" || this.email == "") {
        Swal.fire({
          position: 'top',
          // title: '모든 칸을 입력해주세요!',
          html: '<div style="font-family: SUITE-Regular;">모든 칸을 입력해주세요!</div>',
          showConfirmButton: false,
          timer: 1000
        })
        return;
      }

      let findPassUser = {
        id : this.id,
        email : this.email,
      }

      //id와 맞는 사용자 가져오기
      this.$store.dispatch("getUserPass", findPassUser);


      
      


    },
    goLogin(){
      this.$router.push('/login')
    }
  }

}
</script>

<style scoped>
* { padding: 0; margin: 0; font-family: SUITE-Regular;}

html, body {
  height: 100%;
  background: #ffffff;
}

#container {
  padding : 100px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  height: 100%;
}

#findPassBox {
  width: 400px;
  text-align: center;
  background-color: #ffffff;
}

/* 비밀번호 찾기 */
#findPassBoxTitle {
  color:#000000;
  font-weight: bold;
  font-size: 35px;
  text-transform: uppercase;
  padding: 5px;
  margin-bottom: 20px;
  background: linear-gradient(to right, #94a5d8, #033074);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
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
  min-width: 75px;  
}

.form-control{
    border-radius:15px;
}

.button-getpass-box{
  width: 400px;
  margin: 10px 0;
  height: 25px;
  color: white;
}

.btn1{
  height: 40px;
  margin: 5px 0;
  background-color: #5978d4;
  border-radius:10px;
  color: #fff;
  border-radius:30px;

  /* 주변 어둡게 */
   /* box-shadow: 0px 0px 2px 2px #315cdf; */

}

/* 버튼을 누르지 않고 마우스를 올릴때 */
.btn1:hover {
    background-color:#0a1d52;
    transition: 0.7s;
  }

.btn2{
  height: 40px;
  margin: 20px 0;
  background-color: #5978d4;
  border-radius:10px;
  color: #fff;
  border-radius:30px;

}

/* 버튼을 누르지 않고 마우스를 올릴때 */
.btn2:hover {
    background-color:#0a1d52;
    transition: 0.7s;
}

#inputBox button {
  padding: 3px 5px;
}


</style>