<template>
  <div id="app">
    <header-nav></header-nav>
    <router-view id="body-content"></router-view>
    <footer-nav class="footer"></footer-nav>
  </div>
</template>

<script>
import HeaderNav from "@/components/common/HeaderNav.vue"
import FooterNav from "@/components/common/FooterNav.vue"
export default {
  name: "APP",
  components : {
    HeaderNav,
    FooterNav,
  }
}
</script>

<style>
/* //////////////////////// */
/* 여기서부터 */
html, body {
    margin: 0;
    padding: 0;
    height: 100%;
}

#app {
    min-height: 100%;
    position: relative;
}

#body-content {
    padding-bottom: 300px; /* footer의 높이 */
}

footer {
    width: 100%;
    height: 200px; /* footer의 높이 */
    position: absolute;  
    bottom: 0;
    left: 0;
    /* background-color: blue; */
}
/* 여기까지 */
/* ////////////////////////// */
@font-face {
    font-family: 'JUA';
    src: url("@/assets/fonts/BMJUA_ttf.ttf");
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'icomoon';
    src: url("@/assets/fonts/icomoon.ttf");
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'GD';
    src: url("@/assets/fonts/GowunDodum-Regular.ttf");
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'SUITE-Regular';
    src: url("@/assets/fonts/SUITE-Variable.ttf");
    font-weight: 400;
    font-style: normal;
}

</style>