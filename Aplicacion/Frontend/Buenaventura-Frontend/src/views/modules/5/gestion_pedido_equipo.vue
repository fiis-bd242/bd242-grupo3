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
        <button class="search-button" @click="searchParametro">Buscar</button>
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
            'estado-pendiente': item.estado === 'pendiente',
            'estado-confirmado': item.estado === 'confirmado',
            'estado-en-transito': item.estado === 'en transito',
            'estado-completado': item.estado === 'completado',
            'estado-cancelado': item.estado === 'cancelado'
          }">
            {{ item.estado }}
          </span>

        </b></td>
        <td>
          <button class="search-detalles" @click="viewDetails(item)">Ver</button>
        </td>
        <td>
          <button
              :class="['cancel-button', { 'cancel-button-disabled': item.estado !== 'pendiente' }]"
              @click="item.estado === 'pendiente' ? cancelOrder(item) : null"
              :disabled="item.estado !== 'pendiente'">
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
  </div>
</template>

<script>
export default {
  data() {
    return {
      dataList: [
        {
          id: 1, empleado: 'Empleado 1', urgencia: 'Alta', estado: 'pendiente', tipoProducto: 'Insumo', descripcion: 'Pedido urgente de insumo', productos: [
            { nombre: 'Insumo 1', cantidad: 5, precioUnitario: 10 },
            { nombre: 'Insumo 2', cantidad: 3, precioUnitario: 8 }
          ]
        },
        { id: 3, empleado: 'Empleado 3', urgencia: 'Baja', estado: 'confirmado', descripcion: '' },
        { id: 4, empleado: 'Empleado 4', urgencia: 'Alta', estado: 'en transito', descripcion: '' },
        { id: 5, empleado: 'Empleado 5', urgencia: 'Media', estado: 'completado', descripcion: '' },
        { id: 6, empleado: 'Empleado 6', urgencia: 'Baja', estado: 'completado', descripcion: '' },
        { id: 7, empleado: 'Empleado 6', urgencia: 'Baja', estado: 'completado', descripcion: '' },
        { id: 8, empleado: 'Empleado 6', urgencia: 'Baja', estado: 'pendiente', descripcion: '' },
        { id: 9, empleado: 'Empleado 6', urgencia: 'Baja', estado: 'pendiente', descripcion: '' },
        { id: 10, empleado: 'Empleado 6', urgencia: 'Baja', estado: 'completado', descripcion: '' },
        { id: 11, empleado: 'Empleado 6', urgencia: 'Baja', estado: 'completado', descripcion: '' },
        { id: 12, empleado: 'Empleado 6', urgencia: 'Baja', estado: 'completado', descripcion: '' },
        { id: 13, empleado: 'Empleado 6', urgencia: 'Baja', estado: 'completado', descripcion: '' },
        { id: 14, empleado: 'Empleado 6', urgencia: 'Baja', estado: 'completado', descripcion: '' },
        { id: 15, empleado: 'Empleado 6', urgencia: 'Baja', estado: 'completado', descripcion: '' },
        { id: 16, empleado: 'Empleado 6', urgencia: 'Baja', estado: 'cancelado', descripcion: '' },
      ],
      searchParametro: '',
      currentPage: 1,
      itemsPerPage: 10,
      totalPages: 0,
      showModal: false,
      currentItem: {},
    };
  },
  computed: {
    // Filtrar los datos según el parámetro de búsqueda
    filteredData() {
      return this.dataList.filter(item =>
          item.empleado.toLowerCase().includes(this.searchParametro.toLowerCase()) ||
          item.urgencia.toLowerCase().includes(this.searchParametro.toLowerCase()) ||
          item.estado.toLowerCase().includes(this.searchParametro.toLowerCase()) ||
          item.descripcion.toLowerCase().includes(this.searchParametro.toLowerCase())
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
    // Actualizar la paginación
    updatePagination() {
      this.totalPages = Math.ceil(this.filteredData.length / this.itemsPerPage);
    },
    viewDetails(item) {
      this.currentItem = item;
      this.showModal = true;
    },
    closeModal() {
      this.showModal = false;
    },
    cancelOrder(item) {
      item.estado = 'cancelado'; // Cambiar el estado a cancelado
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
.estado-en-transito { color: yellow; }
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