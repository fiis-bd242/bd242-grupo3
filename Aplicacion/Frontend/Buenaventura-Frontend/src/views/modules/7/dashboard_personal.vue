<script setup>
import RouterBox from '@/components/RouterBox.vue';
</script>
<template>
    <div class="text-center h-[83vh] w-full space-y-4 mt-4 ">
        <h3 class="font-extrabold text-3xl">Personal</h3>
        <div class="grid grid-cols-2 gap-4 justify-center mx-auto max-w-3xl">
  <RouterBox v-for="(route, index) in list_of_routes" 
             :key="index" 
             :msg="route.msg" 
             :route_name="route.name"
             :button_name="route.button_text" 
             :index="index" 
             :description="route.description" />
</div>

    </div>
</template>
<script>
export default {
    data() {
        return {
            list_of_routes:[
                {
                    msg: "Registrar Reporte Diario",
                    name: "registros",
                    description : "Registrar los datos del mantenimiento diario",
                    button_text : "Digitalizar Datos"
                },
                {
                    msg: "Historial de Mantenimiento",
                    name: "historial",
                    description : "Visualiza el historial de Mantenimiento",
                    button_text : "Historial de Mantenimiento"
                },
                {
                    msg: "Verificar Reporte Diario",
                    name: "reporte",
                    description : "Verificacion de reporte del dia ",
                    button_text : "Verificación del Reporte"
                },
            ]
        }
    },
}
</script>
