<template>
  <div>
    <h1 class="title"><b>LISTA DE PLANES DE MANTENIMIENTO</b></h1>

    <!-- Botón de nuevo plan -->
    <button class="new-plan-button" @click="redirectToCreate">Nuevo plan</button>

    <!-- Buscadores -->
    <div class="search-container">
      <div class="search-box">
        <input
          v-model="searchMachineId"
          type="text"
          placeholder="Buscar ID de Máquina"
        />
        <button class="search-button" @click="searchByMachine">Buscar</button>
      </div>
      <div class="search-box">
        <input
          v-model="searchDate"
          type="date"
          placeholder="Buscar por Fecha Programada"
        />
        <button class="search-button" @click="searchByDate">Buscar</button>
      </div>
    </div>

    <!-- Tabla de planes -->
    <table class="maintenance-table">
      <thead>
        <tr>
          <th>ID Plan</th>
          <th>ID Máquina</th>
          <th>Tipo de Mantenimiento</th>
          <th>Nivel de Criticidad</th>
          <th>Fecha de Inicio Programado</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in dataList" :key="index">
          <td>{{ item.id_plan }}</td>
          <td>{{ item.id_maquina }}</td>
          <td>{{ item.nombre_tipo_mant }}</td>
          <td>{{ item.nivel }}</td>
          <td>{{ item.fecha_inicio_programado }}</td>
          <td>
            <button class="action-button" @click="redirectToDetail(item.id_plan)">Ver</button>
            <button class="action-button" @click="redirectToEdit(item.id_plan)">Editar</button>
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
      totalPages: 0,
      visiblePages: [],
      itemsPerPage: 10,
      searchMachineId: "",
      searchDate: ""
    };
  },
  methods: {
    async fetchData(page) {
      try {
        const offset = (page - 1) * this.itemsPerPage + 1;
        const response = await axios.get(`/api/planificacion/listaplanes/${offset}`);
        this.dataList = response.data;
        this.currentPage = page;
        this.updateVisiblePages();
      } catch (error) {
        console.error("Error al obtener los datos:", error);
      }
    },
    async fetchTotalPages() {
      try {
        const response = await axios.get(`/api/planificacion/conteoPlan`);
        const totalItems = response.data;
        this.totalPages = Math.ceil(totalItems / this.itemsPerPage);
        this.updateVisiblePages();
      } catch (error) {
        console.error("Error al obtener el conteo total:", error);
      }
    },
    changePage(page) {
      if (page >= 1 && page <= this.totalPages) {
        this.fetchData(page);
      }
    },
    updateVisiblePages() {
      const maxVisible = 5;
      const pages = [];
      const startPage = Math.max(this.currentPage - Math.floor(maxVisible / 2), 1);
      const endPage = Math.min(startPage + maxVisible - 1, this.totalPages);

      for (let i = startPage; i <= endPage; i++) {
        pages.push(i);
      }

      this.visiblePages = pages;
    },
    redirectToDetail(idPlan) {
      window.location.href = `http://localhost:5173/moduloplanificacion/detalle_plan/${idPlan}`;
    },
    redirectToEdit(idPlan) {
      window.location.href = `http://localhost:5173/moduloplanificacion/edita_plan/${idPlan}`;
    },
    redirectToCreate() {
      window.location.href = "http://localhost:5173/moduloplanificacion/crea_plan";
    },
    async searchByMachine() {
      try {
        const offset = (page - 1) * this.itemsPerPage + 1;
        const response = await axios.get(`/api/planificacion/listaPlanesPorMaquina/${offset}/${this.searchMachineId}`);
        this.dataList = response.data;
      } catch (error) {
        console.error("Error al buscar por máquina:", error);
      }
    },
    async searchByDate() {
      try {
        const offset = (page - 1) * this.itemsPerPage + 1;
        const response = await axios.get(`/api/planificacion/listaPlanesPorFecha/${offset}/${this.searchDate}`);
        this.dataList = response.data;
      } catch (error) {
        console.error("Error al buscar por fecha:", error);
      }
    }
  },
  mounted() {
    this.fetchTotalPages();
    this.fetchData(this.currentPage);
  }
};
</script>

<style scoped>
/* Título */
.title {
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
}

/* Botón nuevo plan */
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

/* Buscadores */
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

/* Botones de acción */
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

/* Paginación */
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
