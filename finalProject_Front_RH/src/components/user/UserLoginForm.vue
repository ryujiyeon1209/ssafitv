<template>
  <div>
    
    <div id="container">
      <div id="loginBox">

        <div id="loginBoxTitle">SSAFITV LOGIN</div>
        
        <div id="inputBox">
            <div class="input-form-box">
              <span>아이디</span>
              <input type="text" class="form-control" v-model="id">
            </div>
            <div class="input-form-box">
              <span>비밀번호</span>
              <input type="password" class="form-control" v-model="password">
            </div>
            <div class="button-login-box" >
              <button type="button" class="btn1" style="width:100%" @click="login">로그인</button>
            </div>
             <div class="button-login-box" >
                <button type="button" class="btn2" style="width:100%" @click="goFindPass">비밀번호 찾기</button>
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
      id : "",
      password : "",
    }
  },

   methods: {
    
    async login() {

      
      if ( this.id === "" || this.password === "") {
        Swal.fire({
          position: 'top',
          html: '<div style="font-family: SUITE-Regular;">모든 칸을 입력해주세요!</div>',
          showConfirmButton: false,
          timer: 1000
        })
        return;
      }
      
      let user = {
        id : this. id,
        password : this.password,
      }
      
      console.log("user.id : " +user.id)
      console.log("user.pwd : " + user.password)
      this.$store.dispatch("loginSession", user);

      if(this.$store.state.user==null){
        this.id="";
        this.password="";
      }

    },

    goFindPass(){
      this.$router.push('/findPass')
    }
  },


}
</script>

<style scoped>
* { padding: 0; margin: 0; font-family: 'SUITE-Regular';}

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

#loginBox {
  width: 400px;
  text-align: center;
  background-color: #ffffff;
}
.input-form-box {
  display: flex;
  margin-bottom: 15px;
  padding-top: 10px;
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

.button-login-box {
  width: 400px;
  margin: 10px 0;
  height: 25px;
  color: white;
}

/* 로그인 버튼 */
.btn1{
  height: 40px;
  margin: 10px 10px;
  background-color: #5978d4;
  border-radius:30px;
  color: #fff;
  border : none;

  /* 주변 어둡게 */
   /* box-shadow: 0px 0px 2px 2px #315cdf; */

}

/* 버튼을 누르지 않고 마우스를 올릴때 */
.btn1:hover {
    background-color:#0a1d52;
    transition: 0.7s;
}

/* 비밀번호 찾기 버튼 */
.btn2{
  height: 40px;
  margin: 30px 10px;
  background-color: #5978d4;
  border-radius:30px;
  color: #fff;
  border : none;

}

/* 버튼을 누르지 않고 마우스를 올릴때 */
.btn2:hover {
    background-color:#0a1d52;
    transition: 0.7s;
  }


#loginBoxTitle {
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

#inputBox button {
  padding: 3px 5px;
}


</style>