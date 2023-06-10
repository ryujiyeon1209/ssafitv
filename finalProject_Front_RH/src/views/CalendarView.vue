<template>
  <div>
    <div class="text-center" style="height: 80px; margin-top : 40px">
      <h2>오늘 하루는 어땠나요?</h2>
    </div>
    
    <div>
      <b-row style="height: 400px">
        <b-col></b-col>
        <b-col cols=6  class="text-center"> 
            <b-calendar block selected-variant="primary" today-variant="success" nav-button-variant="primary"
            class="border rounded p-2" v-model="ydd" @context="onContext">
            
            </b-calendar>
          </b-col>
          <b-col></b-col>
        </b-row>
        
        <hr />

          <b-col>
          <div class="diary">
            
            <!-- 기록이 있다면 -->
            <div v-if="`${this.$store.state.calender}` != `fail`">
              <div class="mb-3 fa-2x">
                    <div v-if="`${this.$store.state.calender.mood}` == `verybad`">
                      <h1>😭</h1>
                    </div>
                    <div v-if="`${this.$store.state.calender.mood}` == `bad`">
                      <h1>😢</h1>
                    </div>
                    <div v-if="`${this.$store.state.calender.mood}` == `soso`">
                      <h1>🤨</h1>
                    </div>
                    <div v-if="`${this.$store.state.calender.mood}` == `good`">
                      <h1>🙂</h1>
                    </div>
                    <div v-if="`${this.$store.state.calender.mood}` == `verygood`">
                      <h1>😁</h1>
                    </div>
              </div>
              <div class="input-group mb-3" style="margin-top:20px">
                <input type="text" class="form-control" style="font-weight: Semi Bold; font-size:20px; height:50px; border-radius:15px;" :value="`${this.$store.state.calender.coment}`" readonly aria-label="Recipient's username" aria-describedby="button-addon2">
              </div>
            </div>
                  
            <!-- 기록이 없는데 과거나 현재 -->
            <div v-else-if="`${this.$store.state.calender}` == `fail` && this.refusal!=1">
                    
              <div class="mb-3">
                <label for="formGroupExampleInput" class="form-label"></label>
                <b-button-group>
                  <b-button variant="outline-warning" @click="selectmood" value="verybad">😭</b-button>
                  <b-button variant="outline-warning" @click="selectmood" value="bad">😢</b-button>
                  <b-button variant="outline-warning" @click="selectmood" value="soso">🤨</b-button>
                  <b-button variant="outline-warning" @click="selectmood" value="good">🙂</b-button>
                  <b-button variant="outline-warning" @click="selectmood" value="verygood">😁</b-button>
                </b-button-group>
                &nbsp;&nbsp;
                {{mood}}
              </div>

              <div class="input-group mb-3" style="height=50px; margin-top:20px; ">
                  <input type="text" class="form-control" style="font-weight: Semi Bold; font-size:20px; height:50px; border-radius:15px;" placeholder="오늘을 기록해보아요" v-model="coment" aria-label="Recipient's username" aria-describedby="button-addon2" @keyup.enter="regist">
                  <button class="btn btn-outline-secondary" style="font-weight: Semi Bold; font-size:20px; height:50px; border-radius:15px; margin-left:10px" type="button" id="button-addon2" @click="regist">기록하기</button>
              </div>        
            </div>

            <!-- 기록이 없는데 미래 -->
            <div v-else-if="`${this.$store.state.calender}` == `fail` && this.refusal==1">
                    
              <div class="mb-3">
                <label for="formGroupExampleInput" class="form-label"></label>
                <b-button-group>
                  <b-button variant="outline-warning" @click="selectmood" value="verybad">😭</b-button>
                  <b-button variant="outline-warning" @click="selectmood" value="bad">😢</b-button>
                  <b-button variant="outline-warning" @click="selectmood" value="soso">🤨</b-button>
                  <b-button variant="outline-warning" @click="selectmood" value="good">🙂</b-button>
                  <b-button variant="outline-warning" @click="selectmood" value="verygood">😁</b-button>
                </b-button-group>
              </div>

              <div class="input-group mb-3" style="height=50px; margin-top:20px; ">
                  <input type="text" class="form-control" style="font-weight: Semi Bold; font-size:20px; height:50px; border-radius:15px;" placeholder="미래는 작성할 수 없어요" aria-label="Recipient's username" aria-describedby="button-addon2" readonly>
                  <button class="btn btn-outline-secondary" style="font-weight: Semi Bold; font-size:20px; height:50px; border-radius:15px; margin-left:10px" type="button" id="button-addon2" >기록하기</button>
              </div>        
            </div>


            </div>
          </b-col>

    
      </div>

  </div>
</template>

<script>
const todaydate = new Date();
const year = todaydate.getFullYear();
const month = todaydate.getMonth() + 1;
const date = todaydate.getDate();
export default {
  name: "CalendarView",
  data() {
    return {
      ydd: `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`,
      todayYdd : "",
      context: "",  //날짜
      mood:"",
      coment:"",

      refusal : 0,

    }
  },
  methods: {
    //날짜 클릭하기
    //이 날의 기록을 보여주기 > 없으면X > 오늘이면 input창 띄우기
    onContext(ctx) {
      console.log("this.todayYdd : "+this.todayYdd )
      console.log("this.ydd : " +this.ydd)


      this.context = ctx.selectedYMD
      
      if(new Date(this.ydd)>new Date(this.todayYdd)){
        this.refusal=1;
      } else {
        this.refusal=0;
      }



      let calender = {
        date : this.ydd,
        coment : "0",
        mood : "0",
        userId : `${this.$store.state.user.id}`
      }

      this.$store.dispatch("getCalender", calender);

    },

    //이모티콘 클릭하기
    selectmood(event){
      this.mood =`${event.target.value}`
    },

    //제출 누르기
    regist(){


      let calender = {
        date : this.context,
        coment : this.coment,
        mood : this.mood,
        userId : `${this.$store.state.user.id}`
      }

      console.log(calender)
      this.$store.dispatch("createCalender", calender);

      this.coment="";

    },


  },

  // create
  created(){
    this.todayYdd =  `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`;
  }
}
</script>

<style>
  *{
    font-family: SUITE-regular;
    font-weight: Black;
  }
  
  .helllo{
    z-index: -1 !important;
  }

  .diary{ 
    width: 800px;
    margin-top: 50px;
    margin-left: auto;
    margin-right: auto;
  }
</style>