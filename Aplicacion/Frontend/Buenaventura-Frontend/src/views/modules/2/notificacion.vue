<template>
    <div class="p-6 relative">
      <!-- Título centrado -->
      <h1 class="text-center text-2xl font-bold mb-6">ESTADO Y DISPONIBILIDAD</h1>
  
      <!-- Botón superior -->
      <div class="flex justify-between mb-6">
        <button @click="volverListaPlanes" class="bg-gray-600 text-white py-2 px-4 rounded">
          Regresar
        </button>
      </div>
  
      <!-- Información detallada del plan -->
      <div v-if="detallePlan">
        <!-- Grid de detalles -->
        <div class="grid grid-cols-2 gap-4 mb-6">
          <p><strong>ID Orden:</strong> {{ detallePlan.id_orden }}</p>
          <p><strong>Tipo de Mantenimiento:</strong> {{ detallePlan.nombre_tipo_mant }}</p>
          <p><strong>ID Máquina:</strong> {{ detallePlan.id_maquina }}</p>
          <p><strong>Creador:</strong> {{ detallePlan.creador }}</p>
          <p><strong>Estado:</strong> {{ detallePlan.estado }}</p>
          <p><strong>Fecha Plan:</strong> {{ detallePlan.fecha_plan }}</p>
          <p><strong>Fecha Inicio Programado:</strong> {{ detallePlan.fecha_inicio_programado }}</p>
          <p><strong>Fecha Fin Programado:</strong> {{ detallePlan.fecha_fin_programado }}</p>
          <p><strong>Responsable:</strong> {{ detallePlan.responsable }}</p>
          <p><strong>Criticidad:</strong> {{ detallePlan.criticidad }}</p>
          <p><strong>ID Plan:</strong> {{ detallePlan.id_plan }}</p>
          <p><strong>Descripción:</strong> {{ detallePlan.descripcion }}</p>
        </div>
  
        <!-- Botones de disponibilidad -->
        <div class="text-center mt-6">
          <p class="text-lg font-semibold mb-4">Disponibilidad:</p>
          <div class="flex justify-center gap-4">
            <button @click="aceptarPlan" class="bg-green-500 hover:bg-green-600 text-white py-2 px-6 rounded">
              Aceptar
            </button>
            <button @click="rechazarPlan" class="bg-red-500 hover:bg-red-600 text-white py-2 px-6 rounded">
              Rechazar
            </button>
          </div>
        </div>
  
        <!-- Visualización de estado -->
        <div class="text-center mt-8">
          <p class="text-lg font-semibold mb-4">Estado:</p>
          <div class="flex justify-center gap-4">
            <button class="bg-green-500 text-white py-2 px-6 rounded">Completada</button>
            <button class="bg-yellow-400 text-black py-2 px-6 rounded">En curso</button>
            <button class="bg-red-500 text-white py-2 px-6 rounded">Pendiente</button>
          </div>
        </div>
      </div>
  
      <div v-else>
        <p class="text-center text-gray-500">Cargando detalles del trabajo...</p>
      </div>
    </div>
  </template>
  
  <script>
  import { useUserStore } from "@/stores/user";
  import { useToastStore } from "@/stores/toast";
  import axios from "axios";
  
  export default {
    setup() {
      const toastStore = useToastStore();
      return {
        toastStore,
      };
    },
    data() {
      return {
        detallePlan: null,
        idCuenta: null,
      };
    },
    methods: {
      async obtenerDetallePlan() {
        const id_plan = this.$route.params.id_plan;
        try {
          const response = await axios.get(`/api/control/detalles/${id_plan}`);
          this.detallePlan = response.data;
        } catch (error) {
          console.error("Error al obtener los detalles del plan:", error);
        }
      },
      volverListaPlanes() {
        this.$router.push("/modulocontrol/trabajos");
      },
      async aceptarPlan() {
        try {
          const id_plan = this.$route.params.id_plan;
          await axios.post(`/api/control/aceptarNotificacion/${this.idCuenta}/${id_plan}`);
          this.toastStore.showToast(3000, "Aceptado", "Check", "bg-green-600");
        } catch (error) {
          this.toastStore.showToast(3000, "Error al aceptar", "Check", "bg-red-600");
        }
      },
      async rechazarPlan() {
        try {
          const id_plan = this.$route.params.id_plan;
          await axios.post(`/api/control/rechazarNotificacion/${this.idCuenta}/${id_plan}`);
          this.toastStore.showToast(3000, "Rechazado", "Wrong", "bg-red-600");
        } catch (error) {
          console.error("Error al rechazar el plan:", error);
          alert("Error al rechazar el plan.");
        }
      },
    },
    created() {
      this.obtenerDetallePlan();
      const userStore = useUserStore();
      this.idCuenta = userStore.user.id_cuenta;
      this.idCuenta = 1; // Simulación de usuario
    },
  };
  </script>
  
  <style scoped>
  /* Sin estilos adicionales, Tailwind lo maneja todo */
  </style>
  