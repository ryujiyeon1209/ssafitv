<template>
  <div>
    <before-login></before-login>
    <show-weather></show-weather>
  </div>
</template>

<script>
import BeforeLogin from "@/components/home/BeforeLogin.vue"
import ShowWeather from "@/components/home/ShowWeather.vue"
export default {
  name:"HomeView",
  components : {
    BeforeLogin,
    ShowWeather,
  }
}
</script>

<style>

</style>