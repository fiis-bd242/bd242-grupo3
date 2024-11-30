<template>
  
    <div class="detalle-actividad-container w-full mx-auto p-4">
      <!-- Título centrado -->
      <h1 class="title"><b>DETALLE DE LA ACTIVIDAD DE EMPLEADO</b></h1>
      <!-- Contenedor de los botones superiores -->
      <div class="botones-superiores">
        <!-- Botón para regresar -->
        <button @click="volverListaActividades" class="btn btn-secondary">
          Volver a la lista de actividades
        </button>
  
        <!-- Botón para acceder al detalle de la orden -->
        <button 
          @click="redirigirOrden" 
          class="btn btn-danger" >
          Ver Detalle de la Orden
        </button>
      </div>
  
      
  
      <!-- Información detallada de la actividad -->
      <div v-if="detalleActividad">
        <div class="detalle-grid">
          <p><strong>ID:</strong> {{ detalleActividad.id_actvempleado }}</p>
          <p><strong>Estado:</strong> {{ detalleActividad.id_estado }}</p>
          <p><strong>ID Máquina:</strong> {{ detalleActividad.id_maquina }}</p>
          <p><strong>Nombre actividad:</strong> {{ detalleActividad.nombre_actv }}</p>
          <p><strong>Empleado: ID:</strong> {{ detalleActividad.id_empleado }}</p>
          <p><strong>Nombre:</strong> {{ detalleActividad.nombre_empleado }}</p>
          <p><strong>Fecha de inicio:</strong> {{ detalleActividad.act_fecha_inicio }}</p>
          <p><strong>Fecha de fin:</strong> {{ detalleActividad.act_fecha_fin }}</p>
          <p><strong>Equipo de soporte: ID:</strong> {{ detalleActividad.id_equipo_soporte }}</p>
          <p><strong>Nombre:</strong> {{ detalleActividad.nombre_equipo_soporte }}</p>
          <p><strong>ID Orden:</strong> {{ detalleActividad.id_orden }}</p>
        </div>
  
        </div>
  
      <div v-else>
        <p>Cargando detalles de la actividad...</p>
      </div>
    </div>
  </template>
  
  <script>
  import { useUserStore } from "@/stores/user";
  import axios from "axios";
  
  export default {
    data() {
      return {
        detalleActividad: null,
        idCuenta: null,
      };
    },
    methods: {
      async obtenerDetalleActividad() {
        try {
          const id_actividad = this.$route.params.id_actividad;
          const response = await axios.get(`/api/planificacion/detalleActividad/${parseInt(id_actividad)}`);
          this.detalleActividad = response.data;
        } catch (error) {
          console.error("Error al obtener los detalles de la actividad:", error);
        }
      },
      volverListaActividades() {
        this.$router.push("/moduloplanificacion/lista_actividades");
      },
      async redirigirOrden() {
        try {
            const id_orden = this.detalleActividad.id_orden;
            window.location.href = `http://localhost:5173/moduloplanificacion/detalle_orden/${id_orden}`;
        } catch (error) {
          console.error("Error al tratar de redirigir:", error);
          alert("Error al tratar de redirigir.");
        }
      }
    },
    created() {
      this.obtenerDetalleActividad();
      const userStore = useUserStore();
      this.idCuenta = userStore.user.id_cuenta;
      this.idCuenta = 1;
    }
  };
  </script>
  
  <style scoped>
  .detalle-actividad-container {
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
  