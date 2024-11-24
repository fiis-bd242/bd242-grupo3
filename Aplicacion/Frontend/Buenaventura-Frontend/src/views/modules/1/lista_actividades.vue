<template>
  <div>
    <h1 class="title"><b>LISTA DE ACTIVIDADES DE EMPLEADO</b></h1>

    <!-- Campo para seleccionar la orden de trabajo -->
    <div class="order-selection">
      <label for="order-select"><b>Seleccionar orden de trabajo:</b></label>
      <select id="order-select" v-model="selectedOrderString" @change="onOrderChange">
        <option value="" disabled>Seleccione una orden</option>
        <option v-for="order in ordersList" :key="order" :value="order">
          {{ order }}
        </option>
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
          <td>{{ item.empleado }}</td>
          <td>{{ item.id_equipo_soporte }}</td>
          <td>{{ item.fecha_inicio }}</td>
          <td>
            <button class="action-button" @click="redirectToDetail(item.id_actvempleado)">Ver</button>
            <button class="action-button" @click="redirectToEdit(item.id_actvempleado)">Editar</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- Controles de paginación -->
    <div class="pagination-controls" v-if="dataList.length > 0 || currentPage > 1">
      <button @click="fetchActivities(1)" :disabled="currentPage === 1">Primero</button>
      <button @click="fetchActivities(currentPage - 1)" :disabled="currentPage === 1">Anterior</button>
      <span>Página {{ currentPage }}</span>
      <button @click="fetchActivities(currentPage + 1)">Siguiente</button>
      <button @click="fetchActivities(totalPages)" :disabled="currentPage === totalPages">Último</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      dataList: [], // Lista de actividades
      ordersList: [], // Lista completa de órdenes de trabajo (en formato OT-XXXX)
      selectedOrderString: "", // Orden seleccionada (cadena OT-XXXX)
      selectedOrderId: null, // ID numérica de la orden seleccionada
      currentPage: 1, // Página actual
      totalPages: 1, // Total de páginas (opcional si tu backend lo soporta)
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
    // Manejar cambio en la selección de órdenes
    onOrderChange() {
      if (this.selectedOrderString) {
        // Extraer la ID numérica de la cadena OT-XXXX
        this.selectedOrderId = parseInt(this.selectedOrderString.split("-")[1]);
        this.currentPage = 1; // Reiniciar a la primera página
        this.fetchActivities(1);
      }
    },
    // Obtener actividades basadas en la orden seleccionada y la página actual
    async fetchActivities(page) {
        try {
            const offset = (page - 1) * 10 + 1;
            console.log(page);
            const response = await axios.get(`/api/planificacion/listaactividades/${this.selectedOrderId}/${offset}`);
            this.dataList = response.data;
            console.log(this.dataList);
            this.currentPage = page;
            this.updateVisiblePages();
        } catch (error) {
            console.error("Error al obtener actividades:", error);
        }
    },
    async fetchTotalPages() {
        try {
            const response = await axios.get(`/api/planificacion/conteoActividad/${this.selectedOrderId}`);
            const totalItems = response.data;
            this.totalPages = Math.ceil(totalItems / 10);
            this.updateVisiblePages();
        } catch (error) {
            console.error("Error al obtener el total de páginas:", error);
        }
    },
    updateVisiblePages() {
        const maxVisible = 5; // Número máximo de botones visibles en la paginación
        const pages = [];
        const startPage = Math.max(this.currentPage - Math.floor(maxVisible / 2), 1);
        const endPage = Math.min(startPage + maxVisible - 1, this.totalPages);

        for (let i = startPage; i <= endPage; i++) {
            pages.push(i);
        }
        this.visiblePages = pages;
    },
    changePage(page) {
        if (page >= 1 && page <= this.totalPages) {
            this.fetchActivities(page);
        }
    },
    handleOrderSelection(orderId) {
        this.selectedOrderId = orderId;
        this.currentPage = 1;
        this.fetchTotalPages();
        this.fetchActivities(this.currentPage);
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
    this.fetchOrders();
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

.order-selection {
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

/* Controles de paginación */
.pagination-controls {
  display: flex;
  justify-content: center;
  gap: 10px;
  margin-top: 20px;
}
.pagination-controls button {
  padding: 5px 10px;
  cursor: pointer;
}
.pagination-controls button:disabled {
  cursor: not-allowed;
  opacity: 0.5;
}
</style>
