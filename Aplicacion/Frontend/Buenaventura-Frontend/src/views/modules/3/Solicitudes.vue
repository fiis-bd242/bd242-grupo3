<template>
  <div class="table-container">
    <!-- Contenedor de filtros y botones -->
    <div class="flex justify-between items-center mb-4">
      <!-- Campo de Filtrado -->
      <div class="flex items-center">
        <label for="filter" class="mr-2">Filtrar Material:</label>
        <input
            type="text"
            id="filter"
            v-model="filterText"
            placeholder="Buscar material"
            class="input"
        />
      </div>

      <!-- Botones -->
      <div>
        <button class="btn-blue mr-2" @click="viewOrderSummary">
          Ver resumen de pedidos
        </button>
        <button class="btn-blue" @click="confirmOrderReservation">
          Confirmar reserva de pedidos
        </button>
      </div>
    </div>

    <!-- Tabla de insumos -->
    <table class="table-auto w-full border-collapse border border-gray-300">
      <thead>
      <tr>
        <th class="border px-4 py-2 text-center">ID</th>
        <th class="border px-4 py-2 text-center">Material</th>
        <th class="border px-4 py-2 text-center">Cantidad</th>
        <th class="border px-4 py-2 text-center">Acciones</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(item, index) in filteredAndPaginatedData" :key="index">
        <td class="border px-4 py-2 text-center">{{ item.id_insumo }}</td>
        <td class="border px-4 py-2 text-center">{{ item.nombre }}</td>
        <td class="border px-4 py-2 text-center">{{ item.cantidad }}</td>
        <td class="border px-4 py-2 text-center">
          <button class="btn-green" @click="openDialog(item)">Reservar</button>
        </td>
      </tr>
      </tbody>
    </table>

    <!-- Paginación -->
    <div class="pagination flex justify-center items-center mt-4">
      <button
          class="btn-gray mx-2"
          :disabled="currentPage === 1"
          @click="changePage(currentPage - 1)"
      >
        Anterior
      </button>
      <span class="mx-2">Página {{ currentPage }}</span>
      <button
          class="btn-gray mx-2"
          :disabled="currentPage === totalPages"
          @click="changePage(currentPage + 1)"
      >
        Siguiente
      </button>
    </div>

    <!-- Cuadro de diálogo para reservar -->
    <div v-if="showDialog" class="dialog-backdrop">
      <div class="dialog">
        <h2 class="text-lg font-bold mb-4">Reservar Material</h2>
        <p><strong>ID Insumo:</strong> {{ dialogData.id_insumo }}</p>
        <p><strong>Nombre:</strong> {{ dialogData.nombre }}</p>
        <div class="mt-4">
          <label for="cantidad" class="block text-sm font-medium">Cantidad:</label>
          <input
              id="cantidad"
              v-model="dialogData.cantidad"
              type="number"
              class="input"
              min="1"
          />
        </div>
        <div class="mt-6 flex justify-end">
          <button class="btn-green mr-4" @click="reserveMaterial()">Reservar</button>
          <button class="btn-gray" @click="closeDialog()">Cancelar</button>
        </div>
      </div>
    </div>

    <!-- Cuadro de diálogo para resumen -->
    <div v-if="showSummaryDialog" class="dialog-backdrop">
      <div class="dialog">
        <h2 class="text-lg font-bold mb-4">Resumen de Pedidos</h2>
        <table class="table-auto w-full border-collapse border border-gray-300">
          <thead>
          <tr>
            <th class="border px-4 py-2 text-center">Material</th>
            <th class="border px-4 py-2 text-center">Cantidad Reservada</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(detail, index) in summaryDetails" :key="index">
            <td class="border px-4 py-2 text-center">{{ detail.nombreInsumo }}</td>
            <td class="border px-4 py-2 text-center">{{ detail.cantidadReservada }}</td>
          </tr>
          </tbody>
        </table>
        <div class="mt-6 flex justify-end">
          <button class="btn-gray" @click="closeSummaryDialog()">Cerrar</button>
        </div>
      </div>
    </div>

    <!-- Cuadro de diálogo para confirmar reserva -->
    <div v-if="showConfirmationDialog" class="dialog-backdrop">
      <div class="dialog">
        <h2 class="text-lg font-bold mb-4">Reservación Exitosa</h2>
        <p><strong>Número de Reserva:</strong> {{ reservationNumber }}</p>
        <div class="mt-6 flex justify-end">
          <button class="btn-green mr-4" @click="copyReservationNumber">
            Copiar Número
          </button>
          <button class="btn-gray" @click="closeConfirmationDialog()">Cerrar</button>
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
      materials: [],
      currentPage: 1,
      pageSize: 10,
      showDialog: false,
      showSummaryDialog: false,
      showConfirmationDialog: false, // Para el cuadro de confirmación
      summaryDetails: [],
      dialogData: {
        id_insumo: null,
        nombre: "",
        cantidad: null,
      },
      filterText: "",
      reservationNumber: null, // Número de reserva para el cuadro de confirmación
    };
  },
  computed: {
    filteredAndPaginatedData() {
      const filtered = this.materials.filter(item =>
          item.nombre.toLowerCase().includes(this.filterText.toLowerCase())
      );
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return filtered.slice(start, end);
    },
    totalPages() {
      return Math.ceil(
          this.materials.filter(item =>
              item.nombre.toLowerCase().includes(this.filterText.toLowerCase())
          ).length / this.pageSize
      );
    },
  },
  methods: {
    async fetchMaterials() {
      try {
        const response = await axios.get(
            `/api/insumos/listar-insumo?limite=100&offset=0`
        );
        this.materials = response.data;
      } catch (error) {
        console.error("Error al cargar los materiales:", error);
      }
    },
    async fetchSummaryDetails() {
      try {
        const response = await axios.get("/api/insumos/detalles-insumos");
        this.summaryDetails = response.data;
        this.showSummaryDialog = true;
      } catch (error) {
        console.error("Error al obtener el resumen de pedidos:", error);
        alert("No se pudo cargar el resumen de pedidos.");
      }
    },
    async confirmOrderReservation() {
      try {
        // Obtener el número de reserva
        const response = await axios.get(`/api/insumos/numero-reservas`);
        this.reservationNumber = response.data + 1;

        // Mostrar cuadro de diálogo con el número
        this.showConfirmationDialog = true;
      } catch (error) {
        console.error("Error al confirmar la reserva:", error);
        alert("Ocurrió un error al confirmar la reserva.");
      }
    },
    copyReservationNumber() {
      navigator.clipboard
          .writeText(this.reservationNumber)
          .then(() => alert("Número de reserva copiado al portapapeles"))
          .catch(err => console.error("Error al copiar el número:", err));
    },
    closeDialog() {
      this.showDialog = false;
    },
    closeSummaryDialog() {
      this.showSummaryDialog = false;
    },
    closeConfirmationDialog() {
      this.showConfirmationDialog = false;
    },
    async reserveMaterial() {
      try {
        // Obtener el número actual de reservas
        const response = await axios.get(`/api/insumos/numero-reservas`);
        const idReserva = response.data + 1;

        // Realizar la reserva
        await axios.post(`/api/insumos/insertarDetalleReservaTemp`, null, {
          params: {
            cantReserv: this.dialogData.cantidad,
            idInsumo: this.dialogData.id_insumo,
            idReserva,
          },
        });

        // Actualizar la cantidad del insumo en la lista de materiales
        const material = this.materials.find(item => item.id_insumo === this.dialogData.id_insumo);
        if (material) {
          material.cantidad -= this.dialogData.cantidad; // Disminuir la cantidad
        }

        alert("Reserva realizada exitosamente");
        this.closeDialog();
      } catch (error) {
        console.error("Error al reservar material:", error);
        alert("Ocurrió un error al realizar la reserva. Inténtalo de nuevo.");
      }
    },
    changePage(page) {
      if (page >= 1 && page <= this.totalPages) {
        this.currentPage = page;
      }
    },
    openDialog(item) {
      this.dialogData.id_insumo = item.id_insumo;
      this.dialogData.nombre = item.nombre;
      this.dialogData.cantidad = null;
      this.showDialog = true;
    },
    viewOrderSummary() {
      this.fetchSummaryDetails();
    },
  },
  created() {
    this.fetchMaterials();
  },
};
</script>

<style scoped>
/* CSS */
.table-container {
  margin: 20px;
}

.dialog-backdrop {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.dialog {
  background: white;
  padding: 20px;
  border-radius: 8px;
  width: 400px;
}

.input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.btn-green {
  background-color: #28a745;
  color: white;
  padding: 5px 10px;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.btn-gray {
  background-color: #6c757d;
  color: white;
  padding: 5px 10px;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.btn-blue {
  background-color: #007bff;
  color: white;
  padding: 5px 10px;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}
</style>
