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
        <button class="search-button" @click="searchParametro">Buscar</button>
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
              {{ item.disponibilidad }}
            </span>
        </td>
        <td>
          <button class="search-detalles" @click="viewDetails(item)">Ver</button>
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
export default {
  data() {
    return {
      dataList: [
        { id: 1, nombre: 'Nombre 1', tipo: 'Tipo 1', estado: 'Óptimo', disponibilidad: 'Disponible' },
        { id: 2, nombre: 'Nombre 2', tipo: 'Tipo 1', estado: 'Bueno', disponibilidad: 'Disponible' },
        { id: 3, nombre: 'Nombre 3', tipo: 'Tipo 2', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 4, nombre: 'Nombre 4', tipo: 'Tipo 3', estado: 'Óptimo', disponibilidad: 'Disponible' },
        { id: 5, nombre: 'Nombre 5', tipo: 'Tipo 3', estado: 'Regular', disponibilidad: 'Ocupado' },
        { id: 6, nombre: 'Nombre 6', tipo: 'Tipo 3', estado: 'Bueno', disponibilidad: 'Ocupado' },
        { id: 7, nombre: 'Nombre 7', tipo: 'Tipo 1', estado: 'Óptimo', disponibilidad: 'Disponible' },
        { id: 8, nombre: 'Nombre 8', tipo: 'Tipo 2', estado: 'Deficiente', disponibilidad: 'Disponible' },
        { id: 9, nombre: 'Nombre 9', tipo: 'Tipo 3', estado: 'Bueno', disponibilidad: 'Disponible' },
        { id: 10, nombre: 'Nombre 10', tipo: 'Tipo 1', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 11, nombre: 'Nombre 11', tipo: 'Tipo 1', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 12, nombre: 'Nombre 12', tipo: 'Tipo 2', estado: 'Bueno', disponibilidad: 'Disponible' },
        { id: 13, nombre: 'Nombre 13', tipo: 'Tipo 3', estado: 'Óptimo', disponibilidad: 'Ocupado' },
        { id: 14, nombre: 'Nombre 14', tipo: 'Tipo 1', estado: 'Deficiente', disponibilidad: 'Disponible' },
        { id: 15, nombre: 'Nombre 15', tipo: 'Tipo 2', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 16, nombre: 'Nombre 15', tipo: 'Tipo 2', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 17, nombre: 'Nombre 15', tipo: 'Tipo 2', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 18, nombre: 'Nombre 15', tipo: 'Tipo 2', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 19, nombre: 'Nombre 15', tipo: 'Tipo 2', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 20, nombre: 'Nombre 15', tipo: 'Tipo 2', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 21, nombre: 'Nombre 15', tipo: 'Tipo 2', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 22, nombre: 'Nombre 15', tipo: 'Tipo 2', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 23, nombre: 'Nombre 15', tipo: 'Tipo 2', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 24, nombre: 'Nombre 15', tipo: 'Tipo 2', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 25, nombre: 'Nombre 15', tipo: 'Tipo 2', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 26, nombre: 'Nombre 15', tipo: 'Tipo 2', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 27, nombre: 'Nombre 15', tipo: 'Tipo 2', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 28, nombre: 'Nombre 15', tipo: 'Tipo 2', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 29, nombre: 'Nombre 15', tipo: 'Tipo 2', estado: 'Regular', disponibilidad: 'Disponible' },
        { id: 30, nombre: 'Nombre 15', tipo: 'Tipo 2', estado: 'Deficiente', disponibilidad: 'Disponible' },

      ],
      searchParametro: '',
      currentPage: 1,
      itemsPerPage: 10,
      totalPages: 0,
    };
  },
  computed: {
    // Filtrar los datos según el parámetro de búsqueda
    filteredData() {
      return this.dataList.filter(item =>
          item.nombre.toLowerCase().includes(this.searchParametro.toLowerCase()) ||
          item.tipo.toLowerCase().includes(this.searchParametro.toLowerCase()) ||
          item.estado.toLowerCase().includes(this.searchParametro.toLowerCase()) ||
          item.disponibilidad.toLowerCase().includes(this.searchParametro.toLowerCase())
      );
    },
    // Filtrar los datos y aplicar la paginación
    paginatedData() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      return this.filteredData.slice(start, start + this.itemsPerPage);
    },
    // Calcular las páginas visibles para la paginación
    visiblePages() {
      const pages = [];
      for (let i = 1; i <= this.totalPages; i++) {
        pages.push(i);
      }
      return pages;
    },
  },
  methods: {
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
  },
  watch: {
    // Cada vez que cambie el parámetro de búsqueda, actualiza la paginación
    filteredData() {
      this.updatePagination();
      this.currentPage = 1; // Regresar a la primera página al buscar
    },
  },
  mounted() {
    this.updatePagination(); // Actualizar la paginación al inicio
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

/* Estilo del botón de búsqueda */
.search-button {
  width: 80px; /* Define un tamaño fijo para el botón */
  background-color: #3498db; /* Color de fondo */
  color: white; /* Color del texto */
  border: none;
  border-radius: 10px;
  cursor: pointer;
  font-size: 18px;
  padding: 10px;
}

/* Efecto al pasar el ratón sobre el botón */
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

/* Efecto al pasar el ratón sobre el botón */
.search-detalles:hover {
  background-color: #2980b9;
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

</style>
