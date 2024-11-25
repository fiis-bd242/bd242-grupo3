<template>
    <div class="detalle-plan-container">
      <!-- Botón para regresar -->
      <button @click="volverListaPlanes" class="btn btn-secondary">
        Volver a la lista de planes
      </button>
      
      <!-- Botón para enviar notificación -->
      <button @click="enviarNotificacion" class="btn btn-danger float-end">
        Enviar notificación
      </button>
  
      <h2>Detalle del Plan de Mantenimiento</h2>
  
      <!-- Información detallada del plan -->
      <div v-if="detallePlan">
        <p><strong>ID Plan:</strong> {{ detallePlan.id_plan }}</p>
        <p><strong>Tipo de Mantenimiento:</strong> {{ detallePlan.nombre_tipo_mant }}</p>
        <p><strong>ID Máquina:</strong> {{ detallePlan.id_maquina }}</p>
        <p><strong>Creador:</strong> {{ detallePlan.creador }}</p>
        <p><strong>Estado:</strong> {{ detallePlan.estado }}</p>
        <p><strong>Fecha Plan:</strong> {{ detallePlan.fecha_plan }}</p>
        <p><strong>Fecha Inicio Programado:</strong> {{ detallePlan.fecha_inicio_programado }}</p>
        <p><strong>Fecha Fin Programado:</strong> {{ detallePlan.fecha_fin_programado }}</p>
        <p><strong>Responsable:</strong> {{ detallePlan.responsable }}</p>
        <p><strong>Criticidad:</strong> {{ detallePlan.criticidad }}</p>
        <p><strong>ID Orden:</strong> {{ detallePlan.id_orden }}</p>
  
        <!-- Lista de equipos -->
        <h4>Equipos:</h4>
        <ul>
          <li v-for="(equipo, index) in detallePlan.listaEquipos" :key="index">{{ equipo }}</li>
        </ul>
  
        <!-- Lista de insumos -->
        <h4>Insumos:</h4>
        <table class="table">
          <thead>
            <tr>
              <th>Nombre Insumo</th>
              <th>Cantidad</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(insumo, index) in detallePlan.listaInsumos" :key="index">
              <td>{{ insumo.nombre_insumo }}</td>
              <td>{{ insumo.cantidad }}</td>
            </tr>
          </tbody>
        </table>
      </div>
  
      <div v-else>
        <p>Cargando detalles del plan...</p>
      </div>
    </div>
  </template>
  
  <script>
  import { useUserStore } from "@/stores/user";
  
  export default {
    data() {
      return {
        detallePlan: null,
        idCuenta: null
      };
    },
    methods: {
      async obtenerDetallePlan() {
        const id_plan = this.$route.params.id_plan;
        try {
            console.log("Antes del await");
          const response = await fetch(`/api/planificacion/detallePlan/${id_plan}`);
          console.log("Despues del await");
            if (!response.ok) {
            console.error("Error en la respuesta del servidor:", await response.text());
            throw new Error(`HTTP Error! Status: ${response.status}`);
            }

            const responseBody = await response.json();
            console.log("Respuesta del backend:", responseBody);
            this.detallePlan = responseBody;

          this.detallePlan = await response.json();
        } catch (error) {
          console.error("Error al obtener los detalles del plan:", error);
        }
      },
      volverListaPlanes() {
        this.$router.push("/planificacion/listaplanes/1");
      },
      async enviarNotificacion() {
        try {
          const id_plan = this.$route.params.id_plan;
          const response = await fetch(
            `/api/planificacion/envioNotificacion/${this.idCuenta}/${id_plan}`,
            { method: "POST" }
          );
          const data = await response.json();
          alert(data.message);
        } catch (error) {
          console.error("Error al enviar la notificación:", error);
          alert("Error al enviar la notificación.");
        }
      }
    },
    created() {
      this.obtenerDetallePlan();
  
      // Obtener id_cuenta del store de usuario
      const userStore = useUserStore();
      this.idCuenta = userStore.user.id_cuenta;
    }
  };
  </script>
  
  <style scoped>
  .detalle-plan-container {
    padding: 20px;
  }
  
  .float-end {
    float: right;
    margin-bottom: 20px;
  }
  </style>
  