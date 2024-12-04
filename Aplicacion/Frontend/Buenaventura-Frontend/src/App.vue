<script setup>
import { useRoute } from 'vue-router';
import { useEmployeeStore } from './stores/user';
import Navbar from './components/Navbar.vue';
import LateralBar from '@/components/LateralBar.vue';
import PopupToast from './components/PopupToast.vue';

const route = useRoute();
const empleadoStore = useEmployeeStore();

empleadoStore.initStore();

const isAuthenticated = empleadoStore.idEmpleado !== null;
</script>

<template>
  <header>
    <Navbar />
  </header>
  <div class="flex">
    <LateralBar v-if="isAuthenticated && route.path !== '/'"/>
    <RouterView class="overflow-y-scroll max-h-[80vh] w-full" />
    <PopupToast />
  </div>
</template>

