<template>
  <div class="w-full mx-auto p-4">
    <h1 class="title"><b>INVENTARIO DE EQUIPOS DE SOPORTE</b></h1>

    <!-- Buscadores -->
    <div class="search-container">
      <div class="search-box">
        <input
            v-model="searchParametro"
            type="text"
            placeholder="Ingresar algún parámetro"
            class="search-input"
        />
        <button class="search-button" @click="handleSearch">Buscar</button>
      </div>
    </div>

    <table class="maintenance-table">
      <thead>
      <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Tipo</th>
        <th>Estado</th>
        <th>Disponibilidad</th>
        <th>Detalle</th>
      </tr>
      </thead>
      <tbody>
      <!-- Filtrar los datos según la página actual -->
      <tr v-for="(item, index) in paginatedData" :key="index">
        <td>{{ item.id }}</td>
        <td>{{ item.nombre }}</td>
        <td>{{ item.tipo }}</td>
        <td>{{ item.estado }}</td>
        <td>
            <span :class="availabilityClass(item.disponibilidad)">
              <b>{{ item.disponibilidad }}</b>
            </span>
        </td>
        <td>
          <button class="search-detalles" @click="viewDetails(item.id)">Ver</button>
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

    <!-- Modal para ver detalles -->
    <div v-if="showModal" class="modal-overlay" @click="closeModal">
      <div class="modal-content" @click.stop>
        <button class="close-button" @click="closeModal">X</button>
        <h2><b>Detalle Equipo de Soporte</b></h2>
        <div class="modal-body">
          <p><strong>ID:</strong> {{ currentItem.id }}</p>
          <p><strong>Nombre:</strong> {{ currentItem.nombre }}</p>
          <p><strong>Tipo:</strong> {{ currentItem.tipo }}</p>
          <p><strong>Estado:</strong> {{ currentItem.estado }}</p>
          <p><strong>Horas de Uso:</strong> {{ currentItem.horasUso || 0 }}</p>
          <p><strong>Mtto Asignado:</strong> {{ currentItem.mttoAsignado || 'No asignado' }}</p>
          <h3><strong>Descripción:</strong></h3>
          <textarea v-model="currentItem.descripcion" class="textarea-description" readonly></textarea>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      dataList: [],
      searchParametro: '',
      currentPage: 1,
      itemsPerPage: 10,
      totalPages: 0,
      showModal: false,
      currentItem: {},
    };
  },
  computed: {
    filteredData() {
      return this.dataList.filter(item =>
          item.id ||
          item.nombre ||
          item.tipo ||
          item.estado ||
          item.disponibilidad
      );
    },
    // Filtrar los datos y aplicar la paginación
    paginatedData() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      return this.filteredData.slice(start, start + this.itemsPerPage);
    },
    // Calcular las páginas visibles para la paginación
    visiblePages() {
      const maxVisible = 5;
      const pages = [];
      const startPage = Math.max(this.currentPage - Math.floor(maxVisible / 2), 1);
      const endPage = Math.min(startPage + maxVisible - 1, this.totalPages);

      for (let i = startPage; i <= endPage; i++) {
        pages.push(i);
      }

      return pages;
    },
  },
  methods: {
    async fetchData(parametro = '') {
      try {
        // Construir la URL con el parámetro si existe
        const url = parametro
            ? `/api/equipo_soporte/buscarEquipos/${parametro}`
            : '/api/equipo_soporte/obtenerEquipos';
        const response = await axios.get(url);
        this.dataList = response.data; // Actualizar la lista de datos
        this.updatePagination(); // Actualizar la paginación
      } catch (error) {
        console.error("Error al obtener los datos:", error);
      }
    },

    // Metodo para cambiar de página
    changePage(page) {
      if (page >= 1 && page <= this.totalPages) {
        this.currentPage = page;
      }
    },

    // Calcular la clase de disponibilidad
    availabilityClass(disponibilidad) {
      if (disponibilidad === 'Disponible') {
        return 'text-green-500';
      } else if (disponibilidad === 'En mantenimiento') {
        return 'text-orange-500';
      }
      return 'text-red-500';
    },

    // Actualizar la paginación
    updatePagination() {
      this.totalPages = Math.ceil(this.filteredData.length / this.itemsPerPage);
    },

    // Manejar la búsqueda
    handleSearch() {
      this.fetchData(this.searchParametro); // Llamar a fetchData con el parámetro de búsqueda
      this.currentPage = 1; // Reiniciar a la primera página
    },

    // Función para obtener los detalles de un equipo
    async viewDetails(id) {
      try {
        const response = await axios.get(`/api/equipo_soporte/detalleEquipo/${id}`);
        this.currentItem = response.data;
        this.showModal = true;
      } catch (error) {
        console.error("Error al obtener los detalles del equipo:", error);
      }
    },

    closeModal() {
      this.showModal = false;
    },
  },
  watch: {
    filteredData() {
      this.updatePagination();
      this.currentPage = 1; // Regresar a la primera página al buscar
    },
  },
  mounted() {
    this.fetchData();
    this.updatePagination();
  },
};
</script>

<style scoped>
/* Título */
.title {
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
}

/* Centrar la caja de búsqueda */
.search-container {
  display: flex;
  justify-content: center; /* Centra la caja horizontalmente */
  width: 100%; /* Asegura que ocupe todo el ancho disponible */
  padding: 8px 0; /* Espaciado arriba y abajo */
}

/* Estilo de la caja de búsqueda */
.search-box {
  display: flex;
  justify-content: space-between; /* Separación entre el input y el botón */
  width: 80%; /* El 80% del ancho de la página */
  max-width: 900px; /* Establece un máximo para que no sea demasiado grande */
}

/* Estilo del campo de entrada */
.search-input {
  width: 90%; /* Ocupa el 90% de la caja */
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.search-button {
  width: 80px; /* Define un tamaño fijo para el botón */
  background-color: #3498db; /* Color de fondo */
  color: white; /* Color del texto */
  border: none;
  border-radius: 10px;
  cursor: pointer;
  font-size: 18px;
  padding: 3px;
}

.search-button:hover {
  background-color: #2980b9;
}

.search-detalles {
  width: 50px; /* Define un tamaño fijo para el botón */
  background-color: #3498db; /* Color de fondo */
  color: white; /* Color del texto */
  border: none;
  border-radius: 10px;
  cursor: pointer;
  font-size: 14px;
  padding: 4px;
}

.search-detalles:hover {
  background-color: #2980b9;
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

/* Paginación */
.pagination {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.pagination-button {
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 10px;
  padding: 10px 20px;
  margin: 0 5px;
  cursor: pointer;
  font-size: 16px;
}

.pagination-button:hover {
  background-color: #2980b9;
}

.modal-overlay {
   position: fixed;
   top: 0;
   left: 0;
   right: 0;
   bottom: 0;
   background-color: rgba(0, 0, 0, 0.5);
   display: flex;
   justify-content: center;
   align-items: center;
   z-index: 1000;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  width: 500px; /* Ajusta el tamaño del modal */
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  position: relative; /* Necesario para que el botón 'X' se posicione dentro del modal */
}

.close-button {
  position: absolute;
  top: 15px; /* Espacio desde el borde superior */
  right: 15px; /* Espacio desde el borde derecho */
  background-color: #e74c3c; /* Rojo */
  color: white;
  border: none;
  padding: 8px 15px; /* Ajuste de tamaño */
  font-size: 16px;
  font-weight: bold;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  z-index: 1050;
}

.close-button:hover {
  background-color: #c0392b; /* Rojo oscuro */
}

.modal-body p {
  margin-bottom: 10px;
}

.modal-content h2 {
  font-size: 20px; /* Tamaño de fuente más grande */
  font-weight: bold;
  text-align: center; /* Centrar el texto */
  margin-bottom: 20px; /* Espacio debajo del encabezado */
}

.description-text {
  background-color: #f9f9f9;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 16px;
  min-height: 100px; /* Ajusta según lo necesites */
}

textarea.textarea-description {
  width: 100%;
  height: 100px;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
  resize: none;
  background-color: #f4f4f4;
}

</style>