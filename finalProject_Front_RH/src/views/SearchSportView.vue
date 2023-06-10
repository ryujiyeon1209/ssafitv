<template>
  <div>
    <div class="d-flex align-items-center">
      <div class="container position-relative">
        <div class="row d-flex justify-content-center">
          <div class="col-lg-7 text-center">
            <h2 style="font-family: 'SUITE-Regular';">Search Sports Place</h2>
            <br/>
            <div class="row equal-heights justify-content-center">
                <input style=" height:40px; border-radius: 15px;" class="col-lg-6" type="text" v-model="text" @keyup.enter="search"/>
                &nbsp;
                <b-button style=" height:40px; border-radius: 15px; font-family: 'SUITE-Regular';" variant="outline-primary" size="sm" @click="search">Search</b-button>
            </div>
            <br/>
            <p style="font-family: 'SUITE-Regular';">주변의, 운동을 할 수 있는 장소를 검색해보세요!<br/>버튼을 누르면 상세 보기가 가능합니다.</p>
          </div>
        </div>
      </div>
    </div>
    <br/>
    <div class="container">
    <div class="container-fluid p-0">
		<!-- <h1 class="h3 mb-3">검색 결과</h1> -->
		<div class="row">

			<div class="col-xl-8">
				<div class="card">
					<div class="card-header pb-0">
						
						<h5 class="card-title mb-2" style="font-family: 'SUITE-Regular';">검색결과</h5>
					</div>
					<div class="card-body">
						<table class="table table-striped" style="width:100%">
							<thead>
								<tr style="font-family: 'SUITE-Regular';">
									<th>No</th>
									<th>시설명</th>
									<th>도로명 주소</th>
								</tr>
							</thead>
							<tbody>
								<tr v-for="(space, index) in spaces" :key="index" style="font-family: 'SUITE-Regular';">
									<td>{{index + 1}}</td>
									<td style="width:40%"><b-button block squared variant="outline-secondary" @click="[infomation(space),detail(space)]" style="border:none">{{space.faci_nm}}</b-button></td>
									<td>{{space.faci_road_addr}}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<div class="col-xl-4">
				<div class="card">
					<div class="card-header">
						<h5 class="card-title mb-0" style="font-family: 'SUITE-Regular';">시설 상세정보</h5>
					</div>
					<div class="card-body">
						<div class="row g-0">
              <div id="map"></div>
						</div>
						<table class="table table-sm mt-2 mb-4">
							<tbody>
								<tr style="font-family: 'SUITE-Regular';">
									<th>시설명</th>
									<td>{{this.spacedetail.faci_nm}}</td>
								</tr>
								<tr style="font-family: 'SUITE-Regular';">
									<th>도로명주소</th>
									<td>{{this.spacedetail.faci_road_addr}}</td>
								</tr>
								<tr style="font-family: 'SUITE-Regular';">
									<th>전화번호</th>
									<td>{{this.spacedetail.faci_tel_no}}</td>
								</tr>
							</tbody>
						</table>
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
export default {
  name: "SearchSportView",
  data(){
    return{
      text: "",
      spaces:[],
      spacedetail:{},
      serviceKey:"SezCBnyjVvuyn%2Bp2nWDQKjyPI%2FSjV%2BkUjB9cAwD%2BBRKvDE2hHBuwca2BQd62On7GWmLCr3iG5ZGKgb%2FCO7iYvA%3D%3D",
      pageNo:"1",
      numOfRows:"10",
      resultType:"JSON",
      xsite:33.450701,
      ysite:126.570667,
    }
  },
  methods:{
    search(){
      const API_URL = `https://apis.data.go.kr/B551014/SRVC_OD_API_SFMS_FACI/TODZ_API_SFMS_FACI_I_NEW`;
      axios({
        url: API_URL + `?serviceKey=${this.serviceKey}&pageNo=${this.pageNo}&numOfRows=${this.numOfRows}&resultType=${this.resultType}&faci_road_addr=${this.text}`,
        method: "GET",
      })
        .then((res) => {
          this.spaces = res.data.response.body.items.item
        })
        .catch((err) => {
          console.log(err);
        });
    },
    detail(space){
      this.spacedetail = space;
      window.kakao && window.kakao.maps ? this.initMap() : this.addScript(); 
    },
    infomation(space){
      var x = parseFloat(space.faci_lot)
      var y = parseFloat(space.faci_lat)
      this.xsite = x;
      this.ysite = y;
    },
    initMap() {
        console.log(this.ysite);
       var container = document.getElementById('map');
       var options = {
         center: new kakao.maps.LatLng(this.ysite, this.xsite),
         level: 3
       };
       var map = new kakao.maps.Map(container, options);
       
       //마커추가하려면 객체를 아래와 같이 하나 만든다.
       var marker = new kakao.maps.Marker({
         position: map.getCenter()
       });
       marker.setMap(map);
    },
    addScript() {
      const script = document.createElement('script');
        /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap);
        script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7c5aa31793597917f2b098bfa78364be';
        document.head.appendChild(script);
    },
  }

}
</script>

<style>
#map {
      width: 100%;
      height: 200px;     
    }
</style>