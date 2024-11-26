<template>
    <div class="dashboard">
      <!-- Fila 1 -->
      <div class="row">
        <div class="card" @click="irACrearPlan">
          <h2><b>Crear nuevo plan de mantenimiento</b></h2>
          <p>Genera un nuevo plan de mantenimiento para una máquina</p>
          <button class="btn btn-celeste">Ir a Crear Plan</button>
        </div>
  
        <div class="card" @click="irAListadoPlanes">
          <h2><b>Listado de planes de mantenimiento</b></h2>
          <p>Visualiza y gestiona los planes de mantenimiento</p>
          <button class="btn btn-azul">Ir a Listado de Planes</button>
        </div>
      </div>
  
      <!-- Fila 2 -->
      <div class="row">
        <div class="card" @click="irACrearOrden">
          <h2><b>Crear nueva orden de trabajo</b></h2>
          <p>Genera una nueva orden de trabajo para un mantenimiento</p>
          <button class="btn btn-verde-claro">Ir a Crear Orden</button>
        </div>
  
        <div class="card" @click="irAListadoOrdenes">
          <h2><b>Listado de órdenes de trabajo</b></h2>
          <p>Visualiza y gestiona las órdenes de trabajo</p>
          <button class="btn btn-verde">Ir a Listado de Órdenes</button>
        </div>
      </div>
  
      <!-- Fila 3 -->
      <div class="row">
        <div class="card center" @click="irAListadoActividades">
          <h2><b>Gestionar Actividades de Mantenimiento</b></h2>
          <p>Especifica las actividades de los empleados</p>
          <button class="btn btn-rosa">Ir a Listado de Actividades</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    methods: {
      irACrearPlan() {
        this.$router.push('/moduloplanificacion/crea_plan');
      },
      irAListadoPlanes() {
        this.$router.push('/moduloplanificacion/lista_planes');
      },
      irACrearOrden() {
        this.$router.push('/moduloplanificacion/crea_orden');
      },
      irAListadoOrdenes() {
        this.$router.push('/moduloplanificacion/lista_ordenes');
      },
      irAListadoActividades() {
        this.$router.push('/moduloplanificacion/lista_actividades');
      },
    },
  };
  </script>
  
  <style scoped>
  /* Estilo general del dashboard */
  .dashboard {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 20px;
  }
  
  /* Filas de recuadros */
  .row {
    display: flex;
    justify-content: center;
    gap: 20px;
    width: 100%;
  }
  
  /* Estilo de las tarjetas */
  .card {
    background-color: #f9f9f9;
    border: 1px solid #ddd;
    border-radius: 15px;
    padding: 20px;
    width: 300px;
    text-align: center;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    transition: transform 0.2s, box-shadow 0.2s;
    cursor: pointer;
  }
  
  .card:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 12px rgba(0, 0, 0, 0.2);
  }
  
  /* Botones personalizados */
  .btn {
    display: inline-block;
    margin-top: 15px;
    padding: 10px 20px;
    border: none;
    border-radius: 25px;
    color: white;
    font-size: 14px;
    font-weight: bold;
    cursor: pointer;
    text-decoration: none;
    transition: background-color 0.3s ease;
  }
  
  .btn-celeste {
    background-color: #00bfff;
  }
  
  .btn-celeste:hover {
    background-color: #009acd;
  }
  
  .btn-azul {
    background-color: #007bff;
  }
  
  .btn-azul:hover {
    background-color: #0056b3;
  }
  
  .btn-verde-claro {
    background-color: #32cd32;
  }
  
  .btn-verde-claro:hover {
    background-color: #20a91e;
  }
  
  .btn-verde {
    background-color: #228b22;
  }
  
  .btn-verde:hover {
    background-color: #285c27;
  }
  
  .btn-rosa {
    background-color: #ff69b4;
  }
  
  .btn-rosa:hover {
    background-color: #d15292;
  }
  
  /* Centrar el recuadro de la fila final */
  .center {
    margin: 0 auto;
  }
  </style>
  