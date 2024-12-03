<template>
  <div class="w-full mx-auto p-4">
    <h1 class="title"><b>SOLICITUD DE COMPRA</b></h1>

    <div class="form-group">
      <label for="tipoProducto">Tipo de producto</label>
      <select
          v-model="tipoProducto"
          id="tipoProducto"
          @change="habilitarBotonAñadir"
      >
        <option disabled value="">Seleccionar...</option>
        <option value="equipo">Equipo de Soporte</option>
        <option value="insumo">Insumo</option>
      </select>
    </div>

    <div class="form-group">
      <label for="tipoUrgencia">Tipo de urgencia</label>
      <select v-model="tipoUrgencia" id="tipoUrgencia">
        <option disabled value="">Seleccionar...</option>
        <option value="baja">Baja</option>
        <option value="media">Media</option>
        <option value="alta">Alta</option>
        <option value="critica">Crítica</option>
      </select>
    </div>

    <h3><b>Lista de Productos</b></h3>
    <table class="tabla-productos">
      <thead>
      <tr>
        <th>Producto</th>
        <th>Cantidad</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(producto, index) in listaProductos" :key="index">
        <td>{{ producto.nombre }}</td>
        <td>{{ producto.cantidad }}</td>
      </tr>
      </tbody>
    </table>

    <div class="centrado-boton">
      <button
          :disabled="!botonAñadirHabilitado"
          @click="mostrarModalAgregarProductos"
          class="btn-anadir-producto"
      >
        Añadir Producto
      </button>
      <button
          @click="limpiarProductos"
          class="btn-anadir-producto limpiar-btn"
      >
        Limpiar
      </button>
    </div>

    <div class="form-group">
      <label for="descripcion">Descripción</label>
      <textarea id="descripcion" v-model="descripcion"></textarea>
    </div>

    <div class="derecha-boton">
      <button
          :disabled="!canCrearPedido"
          @click="crearPedido"
          class="btn-crear-pedido"
      >
        Crear Pedido
      </button>
    </div>

    <!-- Modal para agregar productos -->
    <div v-if="mostrarModal" class="modal-overlay">
      <div class="modal">
        <button class="close-btn" @click="cerrarModal">X</button>
        <h3><b>{{ tituloModal }}</b></h3>
        <div class="form-group1">
          <input
              type="text"
              v-model="busqueda"
              placeholder="Ingrese el ID o nombre"
          />
          <button @click="buscarProducto">Buscar</button>
        </div>

        <table class="tabla-productos modal-table">
          <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Cantidad</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="producto in productosFiltrados" :key="producto.id">
            <td>{{ producto.id }}</td>
            <td>{{ producto.nombre }}</td>
            <td>
              <button class="btn-modificar btn-menor" @click="modificarCantidad(producto, -1)">−</button>
              {{ producto.cantidad }}
              <button class="btn-modificar btn-mayor" @click="modificarCantidad(producto, 1)">+</button>
            </td>
          </tr>
          </tbody>
        </table>

        <div class="paginacion">
          <button @click="irAPrimeraPagina">Primero</button>
          <button @click="irPaginaAnterior">Anterior</button>
          <span>{{ paginaActual }} / {{ totalPaginas }}</span>
          <button @click="irPaginaSiguiente">Siguiente</button>
          <button @click="irAUltimaPagina">Último</button>
        </div>

        <div class="derecha-boton">
          <button class="btn-añadir" @click="agregarProductos">Añadir</button>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tipoProducto: "",
      tipoUrgencia: "",
      descripcion: "",
      listaProductos: [],
      botonAñadirHabilitado: false,
      mostrarModal: false,
      busqueda: "",
      paginaActual: 1,
      productosFiltrados: [],
      productosDisponibles: [
        { id: 1, nombre: "Nombre 1", cantidad: 0 },
        { id: 2, nombre: "Nombre 2", cantidad: 0 },
        { id: 3, nombre: "Nombre 3", cantidad: 0 },
        { id: 4, nombre: "Nombre 4", cantidad: 0 },
        { id: 5, nombre: "Nombre 5", cantidad: 0 },
        { id: 6, nombre: "Nombre 6", cantidad: 0 },
        { id: 7, nombre: "Nombre 7", cantidad: 0 },
        { id: 8, nombre: "Nombre 8", cantidad: 0 },
        { id: 9, nombre: "Nombre 9", cantidad: 0 },
        { id: 10, nombre: "Nombre 10", cantidad: 0 },
        { id: 11, nombre: "Nombre 11", cantidad: 0 },
        { id: 12, nombre: "Nombre 12", cantidad: 0 },
      ],
    };
  },
  computed: {
    tituloModal() {
      return this.tipoProducto === "equipo"
          ? "Asignar Equipos de Soporte"
          : "Asignar Insumos";
    },
    totalPaginas() {
      return Math.ceil(this.productosDisponibles.length / 5);
    },
    canCrearPedido() {
      return (
          this.tipoProducto &&
          this.tipoUrgencia &&
          this.listaProductos.length > 0 &&
          this.descripcion
      );
    },
  },
  methods: {
    habilitarBotonAñadir() {
      this.botonAñadirHabilitado = !!this.tipoProducto;
    },
    mostrarModalAgregarProductos() {
      this.mostrarModal = true;
      this.actualizarProductosFiltrados();
    },
    cerrarModal() {
      this.mostrarModal = false;
    },
    buscarProducto() {
      this.actualizarProductosFiltrados();
    },
    modificarCantidad(producto, cantidad) {
      const nuevaCantidad = producto.cantidad + cantidad;
      if (nuevaCantidad >= 0) {
        producto.cantidad = nuevaCantidad;
      }
    },
    actualizarProductosFiltrados() {
      const inicio = (this.paginaActual - 1) * 5;
      const fin = inicio + 5;

      const productosFiltrados = this.productosDisponibles.filter((producto) =>
          producto.nombre.toLowerCase().includes(this.busqueda.toLowerCase())
      );

      this.productosFiltrados = productosFiltrados.slice(inicio, fin);
    },
    irAPrimeraPagina() {
      this.paginaActual = 1;
      this.actualizarProductosFiltrados();
    },
    irAUltimaPagina() {
      this.paginaActual = this.totalPaginas;
      this.actualizarProductosFiltrados();
    },
    irPaginaAnterior() {
      if (this.paginaActual > 1) {
        this.paginaActual--;
        this.actualizarProductosFiltcrados();
      }
    },
    irPaginaSiguiente() {
      if (this.paginaActual < this.totalPaginas) {
        this.paginaActual++;
        this.actualizarProductosFiltrados();
      }
    },
    agregarProductos() {
      const productosSeleccionados = this.productosDisponibles.filter(
          (producto) => producto.cantidad > 0
      );
      this.listaProductos = productosSeleccionados;
      this.cerrarModal();
    },
    crearPedido() {
      this.listaProductos = this.productosDisponibles.filter(
          (producto) => producto.cantidad > 0
      );
      this.cerrarModal();
    },
    limpiarProductos() {
      this.productosDisponibles.forEach(producto => producto.cantidad = 0);
      this.listaProductos = [];
    },
  },
};
</script>

<style scoped>

.title {
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
}

.sidebar h3 {
  color: #f1c40f;
  text-align: center;
}

.sidebar ul {
  list-style-type: none;
  padding: 0;
  margin-top: 20px;
}

.sidebar ul li {
  padding: 10px 0;
  text-align: left;
  font-size: 16px;
}

.sidebar ul li a {
  color: #fff;
  text-decoration: none;
}

.sidebar ul li a:hover {
  color: #f1c40f;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
  color: #2c3e50;
}

.form-group {
  margin-bottom: 20px;
  padding-top: 10px;
}

.form-group1 {
  display: flex; /* Usamos Flexbox para alinear los elementos en la misma línea */
  justify-content: space-between; /* Distribuye el espacio entre los elementos */
  align-items: center; /* Alinea verticalmente los elementos */
  margin-bottom: 15px;
}

.form-group1 input {
  width: 80%; /* El ancho de la caja de texto ocupa el 80% del espacio disponible */
  padding: 8px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.form-group1 button {
  width: 15%; /* El botón de búsqueda ocupa el 15% del espacio disponible */
  padding: 8px;
  font-size: 14px;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.form-group1 button:hover {
  background-color: #2980b9; /* Cambio de color al pasar el mouse */
}

/* Espacio entre la caja de búsqueda y el botón */
.form-group1 input, .form-group1 button {
  margin-right: 10px; /* Espacio entre la caja y el botón */
}

label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

select, textarea, input {
  width: 100%;
  padding: 8px;
  margin-top: 5px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.1);
}

textarea {
  min-height: 100px;
  resize: vertical;
}

button {
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
  color: white;
  background-color: #3498db;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #2980b9;
}

button:disabled {
  background-color: #bdc3c7;
  cursor: not-allowed;
}

/* Tabla de productos */
.tabla-productos {
  width: 100%;
  border-collapse: collapse;
  margin: 10px 0;
  text-align: center; /* Centra el contenido de todas las celdas de la tabla */
}

.tabla-productos th, .tabla-productos td {
  padding: 8px; /* Ajuste de padding para las celdas */
  border: 1px solid #ddd;
  font-size: 14px; /* Tamaño de fuente ajustado */
}

.tabla-productos th {
  background-color: #f4f4f4;
  font-weight: bold;
}

.tabla-productos tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal {
  background: white;
  padding: 15px;
  border-radius: 10px;
  width: 800px;
  max-width: 90%;
  height: auto; /* Ajustamos la altura para que no se sobrecargue */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  position: relative;
  padding-top: 40px; /* Ajuste de padding superior para evitar que el contenido se solape con el botón */
}

.paginacion {
  display: flex;
  justify-content: center; /* Centra los botones horizontalmente */
  align-items: center; /* Alinea los botones verticalmente */
  margin-top: 10px;
  font-size: 12px;
}

.paginacion button {
  padding: 8px 18px; /* Menos padding para hacerlo más pequeño */
  font-size: 14px; /* Tamaño de fuente más pequeño */
  background-color: #3498db; /* Mismo color que los botones principales */
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin: 0 5px; /* Espacio entre los botones */
}

.paginacion button:hover {
  background-color: #2980b9; /* Cambio de color al pasar el mouse */
}

.paginacion button:disabled {
  background-color: #bdc3c7; /* Color para el botón deshabilitado */
  cursor: not-allowed;
}

.tabla-productos .btn-modificar {
  font-size: 12px; /* Tamaño de fuente ajustado */
  padding: 4px 8px; /* Reducción de padding para hacerlos más pequeños */
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin: 0 5px;
}

/* Botón - */
.tabla-productos .btn-menor {
  background-color: #e74c3c; /* Rojo */
  color: white;
}

.tabla-productos .btn-menor:hover {
  background-color: #c0392b; /* Rojo oscuro */
}

/* Botón + */
.tabla-productos .btn-mayor {
  background-color: #2ecc71; /* Verde */
  color: white;
}

.tabla-productos .btn-mayor:hover {
  background-color: #27ae60; /* Verde oscuro */
}

.close-btn {
  position: absolute; /* Para posicionarlo en la esquina superior derecha */
  top: 15px; /* Espacio desde el borde superior */
  right: 15px; /* Espacio desde el borde derecho */
  background-color: #e74c3c; /* Rojo */
  color: white;
  border: none;
  padding: 8px 15px; /* Ajuste de tamaño */
  font-size: 16px;
  font-weight: bold;
  border-radius: 5px; /* Mantener un borde redondeado pero no circular */
  cursor: pointer;
  transition: background-color 0.3s ease;
  z-index: 1050;
}

.close-btn:hover {
  background-color: #c0392b; /* Rojo oscuro */
}

.btn-añadir {
  background-color: #2ecc71; /* Verde */
  color: white;
  font-size: 14px;
  font-weight: bold;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  text-align: center; /* Asegura que el texto se alinee correctamente */
  margin: 0; /* Elimina márgenes adicionales */
  transition: background-color 0.3s ease;
}

.btn-añadir:hover {
  background-color: #27ae60; /* Verde oscuro */
}

.centrado-boton {
  display: flex;
  justify-content: center; /* Centra el botón horizontalmente */
  margin-top: 20px;
}

.limpiar-btn {
  margin-left: 10px; /* Ajusta la separación entre los botones */
}

.derecha-boton{
  text-align: right;
  margin-top: 20px;
}

.btn-crear-pedido {
  background-color: #3498db; /* Azul */
  color: white;
  font-size: 16px;
  font-weight: bold;
  padding: 12px 30px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  text-align: center; /* Asegura que el texto se alinee correctamente */
  margin: 0; /* Elimina márgenes adicionales */
}

.btn-crear-pedido:hover {
  background-color: #2980b9; /* Azul más oscuro al pasar el mouse */
}

.btn-crear-pedido:disabled {
  background-color: #bdc3c7; /* Deshabilitado */
  cursor: not-allowed;
}

</style>