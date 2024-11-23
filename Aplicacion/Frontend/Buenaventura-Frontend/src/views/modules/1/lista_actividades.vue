<template>
  <div>
    <h1 class="title"><b>LISTA DE ACTIVIDADES DE EMPLEADO</b></h1>

    <!-- Campo desplegable para seleccionar orden de trabajo -->
    <div class="order-selection">
      <label for="order-select"><b>Seleccionar orden de trabajo:</b></label>
      <select id="order-select" v-model="selectedOrder" @change="fetchActivities">
        <option value="" disabled>Seleccione una orden</option>
        <option v-for="order in ordersList" :key="order" :value="order">{{ order }}</option>
      </select>
    </div>

    <!-- Tabla de actividades (visible solo si hay datos) -->
    <table v-if="dataList.length > 0" class="maintenance-table">
      <thead>
        <tr>
          <th>ID Actividad</th>
          <th>ID Empleado</th>
          <th>ID Equipo de Soporte</th>
          <th>Fecha de Inicio Programada</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in dataList" :key="index">
          <td>{{ item.id_actvempleado }}</td>
          <td>{{ item.id_empleado }}</td>
          <td>{{ item.id_equipo_soporte }}</td>
          <td>{{ item.fecha_inicio_programada }}</td>
          <td>
            <button class="action-button" @click="redirectToDetail(item.id_actvempleado)">Ver</button>
            <button class="action-button" @click="redirectToEdit(item.id_actvempleado)">Editar</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      dataList: [], // Lista de actividades de empleado
      ordersList: [], // Lista de órdenes de trabajo
      selectedOrder: "" // ID de la orden seleccionada
    };
  },
  methods: {
    // Cargar órdenes de trabajo al iniciar
    async fetchOrders() {
      try {
        const response = await axios.get(`/api/planificacion/allordenes`);
        this.ordersList = response.data;
      } catch (error) {
        console.error("Error al obtener las órdenes de trabajo:", error);
      }
    },
    // Obtener actividades según la orden seleccionada
    async fetchActivities() {
      if (!this.selectedOrder) return;
      try {
        const response = await axios.get(`/api/planificacion/listaactividades/${this.selectedOrder}`);
        this.dataList = response.data;
      } catch (error) {
        console.error("Error al obtener las actividades:", error);
      }
    },
    // Redirigir al detalle de la actividad
    redirectToDetail(idActivity) {
      window.location.href = `http://localhost:5173/moduloplanificacion/detalle_actividad/${idActivity}`;
    },
    // Redirigir a la edición de la actividad
    redirectToEdit(idActivity) {
      window.location.href = `http://localhost:5173/moduloplanificacion/edita_actividad/${idActivity}`;
    }
  },
  mounted() {
    // Cargar las órdenes de trabajo al montar el componente
    this.fetchOrders();
  },
  created() {
    // Si se proporciona un ID de orden a través de query params, seleccionarla automáticamente
    const urlParams = new URLSearchParams(window.location.search);
    const orderId = urlParams.get("orderId");
    if (orderId) {
      this.selectedOrder = orderId;
      this.fetchActivities();
    }
  }
};
</script>

<style scoped>
/* Estilo general */
.title {
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
}

.select-order-container {
  margin-bottom: 20px;
  display: flex;
  align-items: center;
  gap: 10px;
}

#order-select {
  padding: 5px;
  font-size: 14px;
}

/* Tabla */
.maintenance-table {
  width: 100%;
  border-collapse: collapse;
}
.maintenance-table th,
.maintenance-table td {
  padding: 8px;
  text-align: center;
  border: 1px solid #ddd;
}
.maintenance-table th {
  background-color: #f2f2f2;
}

/* Botones */
.action-button {
  background-color: green;
  color: white;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
  margin-right: 5px;
}
.action-button:hover {
  background-color: darkgreen;
}
</style>
