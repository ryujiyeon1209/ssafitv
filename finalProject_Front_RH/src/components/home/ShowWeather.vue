<template>
  <div>
    <div class="container">
      <div class="card card-2">
        <div class="row" style="align-items: center; align-self: center;">
          <div class="col" style="margin-right:50px"><h5>오늘의 날씨</h5></div>
          <div v-for="(wds, index) in weatherdatasky" :key="index">
            <div class="col" >
              <div class="row row3">{{index}}시간 후</div>
              <div class="row row2" v-if="`${wds.fcstValue}==1`"><img class="img-fluid" src="https://img.icons8.com/ios/100/000000/sun.png"/></div>
              <div class="row row2" v-else-if="`${wds.fcstValue}==3`"><img class="img-fluid" src="https://img.icons8.com/windows/100/000000/cloud.png"/></div>
              <div class="row row2" v-else-if="`${wds.fcstValue}==4`"><img class="img-fluid" src="https://img.icons8.com/cotton/64/000000/rain--v3.png"/></div>
              <div v-for="(wdt, index) in weatherdatat1h" :key="index">
                <div v-show="makeresult(wds.fcstTime,wdt.fcstTime)">
                    <div class="row row1">{{wdt.fcstValue}}&deg;C</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

const todaydate = new Date();
const year = todaydate.getFullYear();
const month = todaydate.getMonth() + 1;
const date = todaydate.getDate();
export default {
  name: "ShowWeather",
  data(){
    return{
      weatherdatapty: [],
      weatherdatasky: [],
      weatherdatat1h: [],
      serviceKey: "SezCBnyjVvuyn%2Bp2nWDQKjyPI%2FSjV%2BkUjB9cAwD%2BBRKvDE2hHBuwca2BQd62On7GWmLCr3iG5ZGKgb%2FCO7iYvA%3D%3D",
      pageNo: "1",
      numOfRows: "6",
      dataType: "JSON",
      base_date: `${year}${month >= 10 ? month : '0' + month}${date >= 10 ? date : '0' + date}`,
      base_time: "0800",
      nx: "55",
      ny: "127",
    }
  },
  created(){
    const API_URL = `http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst`;
    axios({
      url: API_URL + `?serviceKey=${this.serviceKey}&pageNo=5&numOfRows=${this.numOfRows}&dataType=${this.dataType}&base_date=${this.base_date}&base_time=${this.base_time}&nx=${this.nx}&ny=${this.ny}`,
      method: "GET",
    })
      .then((res) => {
        this.weatherdatat1h = res.data.response.body.items.item;
      })
      .catch((err) => {
        console.log(err);
      });
    axios({
      url: API_URL + `?serviceKey=${this.serviceKey}&pageNo=4&numOfRows=${this.numOfRows}&dataType=${this.dataType}&base_date=${this.base_date}&base_time=${this.base_time}&nx=${this.nx}&ny=${this.ny}`,
      method: "GET",
    })
      .then((res) => {
        this.weatherdatasky = res.data.response.body.items.item;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods:{
    makeresult(a,b){
      if(a==b){
        return true;
      }
      else{
        return false;
      }
    },
  }
}
</script>

<style scoped>
  body{
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    /* background-color: rgba(238, 237, 237, 0.438); */
    color: rgb(105, 104, 104);
    font-family: sans-serif;
}
.container
{
    width: 100%;
}
.card{
    padding: 1.2rem 3rem 1rem 3rem;
    margin: 1rem;
    border-radius: 0.5rem;
    /* border-color: rgba(238, 237, 237, 0.438); */
    /* box-shadow: 5px 8px 10px #80808033; */
}
@media(max-width:767px){
    .card{
        height: 10rem;
        padding: 1.5rem 1.5rem 1rem 1.5rem;
    }
}
.carousel-indicators li {
    width: 5px;
    height: 6px;
    border-radius: 100%;
    opacity: 0.2;
    background-color: #000000;
}
.carousel-indicators{
    margin-bottom: 0;
}
.temp{
    font-size: 5rem;
    color: rgb(57, 57, 58);
}
.card-1{
    background: linear-gradient(to right, #ffffff 50%, rgba(241, 224, 190, 0.507));
}
@media(max-width:767px)
{
    .temp{
        font-size: 3rem;
    }
}
.location{
    margin-bottom: 1.5rem;
}
@media(max-width:767px)
{
    .location{
        font-size: 0.75rem;
    }
}
.img-fluid{
    float: right;
    width: 65%;
    display: flex;
    align-items: center;
}
.row2 .img-fluid{
    margin: 10% 0;
    opacity: 0.5;
    width: 40%;
}
.card-2 .row{
    justify-content: center;
}
.card-3 .row{
    justify-content: center;
}
.row1{
    font-size: 0.7rem;
    font-weight: bold;
    color: black;
}
.row3{
    font-size: 0.9rem;
    font-weight: bold;
    color: black;
}
@media(max-width:400px)
{
    .row3{
        font-size: 0.7rem;
        font-weight: bold;
        color: black;
    }
}
@media(max-width:320px)
{
    .row3{
        font-size: 0.6rem;
        font-weight: bold;
        color: black;
    }
}
.row4{
    font-size: 0.6rem;
    color: rgb(196, 196, 196);
    margin: 5% 0 2.5rem;
}
.card-3 .row3{
    margin: 5% 0 2.5rem;
}
.col{
    overflow: visible;
}
.row{
    overflow: visible;
}
</style>