<template>
<nav class="sticky w-full p-5 flex justify-between items-center" style="background-color: #5C46D8;">
    <img class="h-16 bg-transparent" src="../assets/images/buenaventura.png" alt="ba">
    <h2 class="text-white text-3xl font-extrabold">Reportes e historial de mantenimiento</h2>
    <BellIcon class="mx-4"/>
</nav>
</template>

<script>
import BellIcon from './icons/BellIcon.vue';

export default {
    components: {
        BellIcon
    }
}
</script>