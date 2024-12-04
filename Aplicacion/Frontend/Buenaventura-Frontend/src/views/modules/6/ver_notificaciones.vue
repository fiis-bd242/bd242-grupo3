<template>
  <div class="w-full mx-auto p-4">
    <h1 class="title"><b>Ver notificaciones hechas al administrador</b></h1>

    <!-- Buscadores -->
    <div class="search-container">
      <div class="search-box">
        <input
          v-model="searchAdminId"
          type="text"
          placeholder="Buscar ID de Administrador"
        />
        <button class="search-button" @click="searchByAdmin">Buscar</button>
      </div>
      <div class="search-box">
        <input
          v-model="searchDate"
          type="date"
          placeholder="Buscar por Fecha"
        />
        <button class="search-button" @click="searchByDate">Buscar</button>
      </div>
    </div>


    <!-- Tabla de notificaciones -->
    <table class="maintenance-table">
      <thead>
        <tr>
          <th>ID Notificación</th>
          <th>ID Administrador</th>
          <th>Tipo de Evento</th>
          <th>Fecha y Hora</th>
          <th>Estado</th>
          <th>Prioridad</th>
          <th>Mensaje</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in dataList" :key="index">
          <td>{{ item.idNotificacion }}</td>
          <td>{{ item.idAdministrador }}</td>
          <td>{{ item.tipoEvento }}</td>
          <td>{{ item.fechaHoraNotificacion }}</td>
          <td>{{ item.estadoNotificacion }}</td>
          <td>{{ item.prioridad }}</td>
          <td>{{ item.mensajeNotificacion }}</td>
          <td>
            <button class="action-button" @click="redirectToDetail(item.idNotificacion)">Ver</button>
            <button class="action-button" @click="redirectToEdit(item.idNotificacion)">Editar</button>
            <button class="action-button" @click="deleteNotificacion(item.idNotificacion)">Eliminar</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- Paginación -->
    <div class="pagination">
      <button
        class="pagination-button"
        @click="changePage(1)"
        :disabled="currentPage === 1"
      >
        Primero
      </button>
      <button
        class="pagination-button"
        @click="changePage(currentPage - 1)"
        :disabled="currentPage === 1"
      >
        Anterior
      </button>

      <button
        v-for="page in visiblePages"
        :key="page"
        :class="['pagination-button', { active: page === currentPage }]"
        @click="changePage(page)"
      >
        {{ page }}
      </button>

      <button
        class="pagination-button"
        @click="changePage(currentPage + 1)"
        :disabled="currentPage === totalPages"
      >
        Siguiente
      </button>
      <button
        class="pagination-button"
        @click="changePage(totalPages)"
        :disabled="currentPage === totalPages"
      >
        Último
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      dataList: [],
      currentPage: 1,
      visiblePages: [],
      itemsPerPage: 10,
      searchAdminId: "",
      searchDate: "",
    };
  },
  methods: {
    async fetchData(page) {
      try {
        const offset = page;
        const response = await axios.get(`/api/seguridad/obtenerNotificaciones?offset=${offset}`);
        this.dataList = response.data;
        this.currentPage = page;
        this.updateVisiblePages();
      } catch (error) {
        console.error("Error al obtener los datos:", error);
      }
    },

    changePage(page) {
      if (page >= 1) {
        this.fetchData(page);
      }
    },
    
    redirectToDetail(idNotificacion) {
      window.location.href = `/moduloseguridad/detalle/${idNotificacion}`;
    },
    redirectToEdit(idNotificacion) {
      window.location.href = `/moduloseguridad/editar/${idNotificacion}`;
    },
    redirectToCreate() {
      window.location.href = `/moduloseguridad/crear`;
    },
    async deleteNotificacion(idNotificacion) {
      try {
        const response = await axios.delete(`/api/seguridad/notificaciones/${idNotificacion}`);
        this.fetchData(this.currentPage);  // Refrescar la lista después de eliminar
        alert("Notificación eliminada exitosamente");
      } catch (error) {
        console.error("Error al eliminar la notificación:", error);
        alert("Error al eliminar la notificación");
      }
    },
    async searchByAdmin() {
      try {
        const response = await axios.get(`/api/seguridad/notificaciones/buscarPorAdministrador/${this.searchAdminId}`);
        this.dataList = response.data;
      } catch (error) {
        console.error("Error al buscar por administrador:", error);
      }
    },
    async searchByDate() {
      try {
        const response = await axios.get(`/api/seguridad/ver_notificaciones/buscarPorFecha/${this.searchDate}`);
        this.dataList = response.data;
      } catch (error) {
        console.error("Error al buscar por fecha:", error);
      }
    },
  },
  mounted() {
    this.fetchData(this.currentPage);
  },
};
</script>

<style scoped>
/* Estilos iguales */
.title {
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
}

.new-plan-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 15px;
  cursor: pointer;
  float: right;
  margin-bottom: 10px;
}
.new-plan-button:hover {
  background-color: #0056b3;
}

.search-container {
  display: flex;
  justify-content: flex-start;
  gap: 10px;
  margin-bottom: 20px;
}
.search-box {
  display: flex;
  align-items: center;
  gap: 5px;
}
.search-box input {
  padding: 5px;
  width: 180px;
}
.search-button {
  padding: 5px 10px;
  border: 1px solid gray;
  background-color: #f0f0f0;
  cursor: pointer;
}
.search-button:hover {
  background-color: #e0e0e0;
}

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

.pagination {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  gap: 5px;
}
.pagination-button {
  background-color: #e0f7ff;
  border: 1px solid #007bff;
  padding: 5px 10px;
  cursor: pointer;
  color: #007bff;
}
.pagination-button:hover {
  background-color: #cceeff;
}
button.active {
  background-color: #007bff;
  color: white;
}
</style>
