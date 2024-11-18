<script setup>
import RouterBox from '@/components/RouterBox.vue';
import { useUserStore } from '@/stores/user';

const userStore = useUserStore();

const list_of_routes = [
  {
    msg: "Registrar Reporte Diario",
    name: "registros",
    description: "Registrar los datos del mantenimiento diario",
    button_text: "Digitalizar Datos",
    role_needed: "Student",
  },
  {
    msg: "Historial de Mantenimiento",
    name: "historial",
    description: "Visualiza el historial de Mantenimiento",
    button_text: "Historial de Mantenimiento",
    role_needed: "Student",
  },
  {
    msg: "Verificar Reporte Diario",
    name: "reporte",
    description: "Verificación de reporte del día",
    button_text: "Verificación del Reporte",
    role_needed: "Student",
  },
  {
    msg: "Verificar Datos",
    name: "sup_datos",
    description: "Verificación de los registros entregados",
    button_text: "Verificación de Datos",
    role_needed: "Student",
  },
  {
    msg: "Exportar Datos",
    name: "exportaciondatos",
    description: "Exportar los datos del Historial",
    button_text: "Exportar Datos",
    role_needed: "Jefe Personal",
  },
];

const list_of_routes_availables = list_of_routes.filter(route => route.role_needed === userStore.user.role)
</script>

<template>
  <div class="text-center h-[83vh] w-full space-y-4 mt-4">
    <h3 class="font-extrabold text-3xl">Personal</h3>
    <div class="grid grid-cols-2 gap-4 justify-center mx-auto max-w-3xl">
      <RouterBox
        v-for="(route, index) in list_of_routes_availables"
        :key="index"
        :msg="route.msg"
        :route_name="route.name"
        :button_name="route.button_text"
        :index="index"
        :description="route.description"
      />
    </div>
    <p>Role actual: {{ userStore.user.role }}</p>
  </div>
</template>
