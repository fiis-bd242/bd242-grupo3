<template>
  <div class="w-full mx-auto p-4">
    <h1 class="title"><b>CONTROL DE PEDIDOS DE COMPRA</b></h1>

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
        <th>Empleado</th>
        <th>Urgencia</th>
        <th>Estado</th>
        <th>Detalles</th>
        <th>Cancelar</th>
      </tr>
      </thead>
      <tbody>
      <!-- Filtrar los datos según la página actual -->
      <tr v-for="(item, index) in paginatedData" :key="index">
        <td>{{ item.id }}</td>
        <td>{{ item.empleado }}</td>
        <td>{{ item.urgencia }}</td>
        <td><b>
          <span :class="{
            'estado-pendiente': item.estado === 'Pendiente',
            'estado-confirmado': item.estado === 'Confirmado',
            'estado-en-transito': item.estado === 'En transito',
            'estado-completado': item.estado === 'Completado',
            'estado-cancelado': item.estado === 'Cancelado'
          }">
            {{ item.estado }}
          </span>

        </b></td>
        <td>
          <button class="search-detalles" @click="viewDetails(item.id)">Ver</button>
        </td>
        <td>
          <button
              :class="['cancel-button', { 'cancel-button-disabled': item.estado !== 'Pendiente' }]"
              @click="item.estado === 'Pendiente' ? cancelOrder(item) : null"
              :disabled="item.estado !== 'Pendiente'">
            X
          </button>
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

    <div v-if="showModal" class="modal-overlay" @click="closeModal">
      <div class="modal-content" @click.stop>
        <button class="close-button" @click="closeModal">X</button>
        <h2><b>Detalle del Pedido de Compra</b></h2>

        <div class="modal-body">

          <h3><strong><b>Tipo de Producto:</b></strong> {{ currentItem.tipoProducto }}</h3>

          <h3><b>Lista de Productos:</b></h3>
          <table class="productos-table">
            <thead>
            <tr>
              <th>Producto</th>
              <th>Cantidad</th>
              <th>Precio Unitario</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(producto, index) in currentItem.productos" :key="index">
              <td>{{ producto.nombre }}</td>
              <td>{{ producto.cantidad }}</td>
              <td>{{ producto.precioUnitario | currency }}</td>
            </tr>
            </tbody>
          </table>

          <h3><strong><b>Descripción:</b></strong></h3>
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
            ? `/api/equipo_soporte/buscarPedidos/${parametro}`
            : '/api/equipo_soporte/obtenerPedidos';
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
        const response = await axios.get(`/api/equipo_soporte/detallePedido/${id}`);
        this.currentItem = response.data;
        this.showModal = true;
      } catch (error) {
        console.error("Error al obtener los detalles del equipo:", error);
      }
    },

    closeModal() {
      this.showModal = false;
    },

    async cancelOrder(item) {
      try {
        // Realizar una solicitud PUT para cancelar el pedido
        const response = await axios.put(`/api/equipo_soporte/cancelarPedido/${item.id}`);

        if (response.status === 200) {
          item.estado = 'Cancelado';
        } else {
          alert('No se pudo cancelar el pedido');
        }
      } catch (error) {
        console.error('Error al cancelar el pedido:', error);
        alert('Hubo un error al intentar cancelar el pedido.');
      }
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

.cancel-button {
  width: 30px;
  background-color: #e74c3c; /* Rojo */
  color: white;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  font-size: 16px;
  padding: 4px;
}

.cancel-button:hover {
  background-color: #c0392b; /* Rojo oscuro */
}

.cancel-button-disabled {
  background-color: #f2d7d5; /* Rojo más claro */
  cursor: not-allowed;
}

.search-button,
.search-detalles,
.pagination-button {
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 10px;
  cursor: pointer;
}

.search-button:hover,
.search-detalles:hover,
.pagination-button:hover {
  background-color: #2980b9;
}

.estado-pendiente { color: blue; }
.estado-confirmado { color: orange; }
.estado-en-transito { color: #dddd32; }
.estado-completado { color: green; }
.estado-cancelado { color: red; }

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

.modal-content h2 {
  font-size: 20px; /* Tamaño de fuente más grande */
  font-weight: bold;
  text-align: center; /* Centrar el texto */
  margin-bottom: 20px; /* Espacio debajo del encabezado */
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

.modal-body {
  margin-top: 20px;
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

.productos-table {
  width: 100%;
  margin-top: 10px;
  border-collapse: collapse;
}

.productos-table th, .productos-table td {
  padding: 5px;
  border: 1px solid #ddd;
  text-align: center;
}

.productos-table th {
  background-color: #f2f2f2;
}

h3 {
  font-size: 15px;
  margin-top: 15px;
  font-weight: bold;
}

</style>