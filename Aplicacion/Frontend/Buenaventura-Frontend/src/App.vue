<script setup>
import {RouterView} from 'vue-router'
import Navbar from './components/Navbar.vue';
import LateralBar from '@/components/LateralBar.vue';
import { useUserStore } from './stores/user';
const userStore = useUserStore();
userStore.initStore(); 
</script>

<template>
  <header>
    <Navbar />
  </header>
  <div class="flex">
    <LateralBar/>
    <RouterView class="overflow-y-scroll"/>
  </div>

</template>


