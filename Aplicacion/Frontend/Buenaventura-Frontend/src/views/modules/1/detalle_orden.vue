<template>
  
    <div class="detalle-orden-container w-full mx-auto p-4">
      <!-- Título centrado -->
      <h1 class="title"><b>DETALLE DE LA ORDEN DE TRABAJO</b></h1>
      <!-- Contenedor de los botones superiores -->
      <div class="botones-superiores">
        <!-- Botón para regresar -->
        <button @click="volverListaOrdenes" class="btn btn-secondary">
          Volver a la lista de órdenes
        </button>
  
        <!-- Botón para acceder al detalle del plan -->
        <button 
          @click="redirigirPlan" 
          class="btn btn-danger" >
          Ver Detalle del Plan
        </button>
      </div>
  
      
  
      <!-- Información detallada del plan -->
      <div v-if="detalleOrden">
        <div class="detalle-grid">
          <p><strong>ID Orden:</strong> {{ detalleOrden.id_orden }}</p>
          <p><strong>Estado:</strong> {{ detalleOrden.estado }}</p>
          <p><strong>ID Máquina:</strong> {{ detalleOrden.id_maquina }}</p>
          <p><strong>Fecha Orden:</strong> {{ detalleOrden.fecha_plan }}</p>
          <p><strong>Tipo de Mantenimiento:</strong> {{ detalleOrden.nombre_tipo_mant }}</p>
          <p><strong>Fecha Inicio Programado:</strong> {{ detalleOrden.fecha_inicio_programado }}</p>
          <p><strong>Fecha Fin Programado:</strong> {{ detalleOrden.fecha_fin_programado }}</p>
          <p><strong>Creador:</strong> {{ detalleOrden.responsable }}</p>
          <p><strong>ID Plan:</strong> {{ detalleOrden.id_plan }}</p>
        </div>
  
          <!-- Lista de empleados -->
          <div class="empleados">
            <h4><b>Empleados:</b></h4>
            <table class="table">
              <thead>
                <tr>
                  <th>ID empleado</th>
                  <th>Nombre</th>
                  <th>ID Actividad</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(empleado, index) in detalleOrden.lista_empleados" :key="index">
                  <td>{{ empleado.id_empleado }}</td>
                  <td>{{ empleado.nombre }}</td>
                  <td>{{ empleado.id_actividad }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
  
      <div v-else>
        <p>Cargando detalles de la orden...</p>
      </div>
    </div>
  </template>
  
  <script>
  import { useUserStore } from "@/stores/user";
  import axios from "axios";
  
  export default {
    data() {
      return {
        detalleOrden: null,
        idCuenta: null,
      };
    },
    methods: {
      async obtenerDetalleOrden() {
        try {
          const id_orden = this.$route.params.id_orden;
          const response = await axios.get(`/api/planificacion/detalleOrden/${parseInt(id_orden)}`);
          this.detalleOrden = response.data;
        } catch (error) {
          console.error("Error al obtener los detalles de la orden:", error);
        }
      },
      volverListaOrdenes() {
        this.$router.push("/moduloplanificacion/lista_ordenes");
      },
      async redirigirPlan() {
        try {
            const id_plan = this.detalleOrden.id_plan;
            window.location.href = `http://localhost:5173/moduloplanificacion/detalle_plan/${id_plan.substring(3,7)}`;
        } catch (error) {
          console.error("Error al tratar de redirigir:", error);
          alert("Error al tratar de redirigir.");
        }
      }
    },
    created() {
      this.obtenerDetalleOrden();
      const userStore = useUserStore();
      this.idCuenta = userStore.user.id_cuenta;
      this.idCuenta = 1;
    }
  };
  </script>
  
  <style scoped>
  .detalle-orden-container {
    padding: 20px;
    position: relative;
  }
  
  .botones-superiores {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
  }
  
  .botones-superiores button:first-child {
    background-color: gray;
    color: white;
    border: none;
    padding: 10px 15px;
    cursor: pointer;
  }
  
  .botones-superiores button:last-child {
    background-color: blue;
    color: white;
    border: none;
    padding: 10px 15px;
    cursor: pointer;
  }
  
  .botones-superiores button:disabled {
    background-color: lightgray;
    cursor: not-allowed;
  }
  
  
  .title {
    text-align: center;
    margin-bottom: 20px;
  }
  
  .detalle-grid {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 10px;
    margin-bottom: 20px;
  }
  
  .listas-container {
    display: flex;
    justify-content: space-between;
    gap: 20px;
  }
  
  .equipos, .insumos {
    flex: 1;
  }
  
  .table {
    width: 100%;
  }
  </style>
  