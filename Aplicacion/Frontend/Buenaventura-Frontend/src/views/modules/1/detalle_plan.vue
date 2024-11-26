<template>
  
  <div class="detalle-plan-container">
    <!-- Título centrado -->
    <h1 class="title"><b>DETALLE DEL PLAN DE MANTENIMIENTO</b></h1>
    <!-- Contenedor de los botones superiores -->
    <div class="botones-superiores">
      <!-- Botón para regresar -->
      <button @click="volverListaPlanes" class="btn btn-secondary">
        Volver a la lista de planes
      </button>

      <!-- Botón para enviar notificación -->
      <button 
        @click="enviarNotificacion" 
        class="btn btn-danger" 
        :disabled="notificacionEnviada">
        Enviar notificación
      </button>
    </div>

    

    <!-- Información detallada del plan -->
    <div v-if="detallePlan">
      <div class="detalle-grid">
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
      </div>

      <!-- Equipos e insumos lado a lado -->
      <div class="listas-container">
        <!-- Lista de equipos -->
        <div class="equipos">
          <h4><b>Equipos:</b></h4>
          <ul>
            <li v-for="(equipo, index) in detallePlan.listaEquipos" :key="index">{{ equipo }}</li>
          </ul>
        </div>

        <!-- Lista de insumos -->
        <div class="insumos">
          <h4><b>Insumos:</b></h4>
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
      </div>
    </div>

    <div v-else>
      <p>Cargando detalles del plan...</p>
    </div>
  </div>
</template>

<script>
import { useUserStore } from "@/stores/user";
import axios from "axios";

export default {
  data() {
    return {
      detallePlan: null,
      idCuenta: null,
      notificacionEnviada: false
    };
  },
  methods: {
    async obtenerDetallePlan() {
      const id_plan = this.$route.params.id_plan;
      try {
        const response = await axios.get(`/api/planificacion/detallePlan/${id_plan}`);
        this.detallePlan = response.data;
      } catch (error) {
        console.error("Error al obtener los detalles del plan:", error);
      }
    },
    volverListaPlanes() {
      this.$router.push("/moduloplanificacion/lista_planes");
    },
    async enviarNotificacion() {
      try {
        const id_plan = this.$route.params.id_plan;
        await axios.post(`/api/planificacion/envioNotificacion/${this.idCuenta}/${id_plan}`);
        alert("Notificación enviada con éxito.");
        this.notificacionEnviada = true;
      } catch (error) {
        console.error("Error al enviar la notificación:", error);
        alert("Error al enviar la notificación.");
      }
    }
  },
  created() {
    this.obtenerDetallePlan();
    const userStore = useUserStore();
    this.idCuenta = userStore.user.id_cuenta;
    this.idCuenta = 1;
  }
};
</script>

<style scoped>
.detalle-plan-container {
  padding: 20px;
  position: relative;
}

.botones-superiores {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.botones-superiores button:first-child {
  background-color: gray;
  color: white;
  border: none;
  padding: 10px 15px;
  cursor: pointer;
}

.botones-superiores button:last-child {
  background-color: red;
  color: white;
  border: none;
  padding: 10px 15px;
  cursor: pointer;
}

.botones-superiores button:disabled {
  background-color: lightgray;
  cursor: not-allowed;
}


.title {
  text-align: center;
  margin-bottom: 20px;
}

.detalle-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 10px;
  margin-bottom: 20px;
}

.listas-container {
  display: flex;
  justify-content: space-between;
  gap: 20px;
}

.equipos, .insumos {
  flex: 1;
}

.table {
  width: 100%;
}
</style>
