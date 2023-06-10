<template>
  <div>
    <nav class="headerback">
      <div class="container">
        <div class="row align-items-center justify-content-between ">
          <div class="col-md-5 text-center">
            <b-row>
              <b-col><router-link :to="`/videomain`" style="text-decoration:none;"><div class="headerfont">SportsVideo</div></router-link></b-col>
              <b-col><router-link to="/searchsports" style="text-decoration:none;"><div class="headerfont">SearchPlace</div></router-link></b-col>
            </b-row>
          </div>
          <div class="site-logo pos-absolute">
            <router-link to="/home" style="text-decoration:none;"><div style="color:#4169e1; font-size: x-large; font-weight: 700; font-family: 'SUITE-Regular';" >SsafiTv</div></router-link>
          </div>
          <div class="col-md-5 text-center">
            <div v-if="this.$store.state.user == null">
              <b-row >
                <b-col><router-link to="/login" style="text-decoration:none;"><div class="headerfont">LogIn</div></router-link></b-col>
                <b-col><router-link to="/signUp" style="text-decoration:none;"><div class="headerfont">Sign Up</div></router-link></b-col>
              </b-row>
            </div>
            <div v-else>
              <b-row >
                <b-col><router-link to="/myProfile" style="text-decoration:none;"><div class="headerfont">Profile</div></router-link></b-col>
                <b-col @click="logout"><router-link to="/home" style="text-decoration:none;"><div class="headerfont" style="text-decoration:none;">LogOut</div></router-link></b-col>
              </b-row>
            </div>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "HeaderNav",
  data() {
    return {
      loginuser: {
        id: "",
        password: "",
      },
      temp: null,
    };
  },
  methods: {
    login() {
      this.$store.dispatch("userLogin", this.loginuser);
    },
    logout() {
      sessionStorage.removeItem("access-token");
      this.$store.commit("LOGOUT_USER");
      this.loginuser.id = "";
      this.loginuser.password = "";
    },
  },
  created() {
    this.temp = sessionStorage.getItem("access-token");
    if(this.temp==null){
      this.$store.commit("LOGOUT_USER");
    }
  },
  computed: {
    ...mapState(["user"]),
  },
};
</script>

<style scoped>

/* a {
  text-decoration: none;
} */
.headerback {
  height: 100px;
  background-color: white;
  line-height: 100px;
  padding: 0px 30px;
}
.headerfont{
  color:#495057; 
  font-weight: 400; 
  font-family: 'SUITE-Regular';
}
.headerfont:hover{
  color: #4169e1;
  font-weight: 500;
  cursor:pointer;
}
</style>