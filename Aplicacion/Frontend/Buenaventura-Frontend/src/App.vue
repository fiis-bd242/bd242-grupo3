<script setup>
import {RouterView} from 'vue-router'
import Navbar from './components/Navbar.vue';
import LateralBar from '@/components/LateralBar.vue';
</script>

<template>
  <header>
    <Navbar />
  </header>
  <div class="flex h-screen ">
    <LateralBar/>
    <RouterView />
  </div>

</template>


