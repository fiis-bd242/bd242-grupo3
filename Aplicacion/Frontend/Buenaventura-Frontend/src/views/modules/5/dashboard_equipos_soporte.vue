<template>
  <div class="dashboard w-full mx-auto p-4">
    <!-- Fila 1 -->
    <div class="row">
      <div class="card" @click="irAVerInventario">
        <h2><b>Visualización de Inventario de Equipos de Soporte</b></h2>
        <p>Vista general de las características y estados de los equipos de soporte para la gestión</p>
        <button class="btn btn-celeste">Ver inventario</button>
      </div>

      <div class="card" @click="irASolicitarEquipos">
        <h2><b>Solicitud de Pedido de Compra de Equipo</b></h2>
        <p>Pedido de compra de algun o algunos equipos de soporte solicitados</p>
        <button class="btn btn-amarillo">Solicitar Equipos</button>
      </div>
    </div>

    <!-- Fila 2 -->
    <div class="row">
      <div class="card" @click="irAGestionarPedidos">
        <h2><b>Gestion de Pedido de Compra de Equipos</b></h2>
        <p>Gestión y visualización de los pedidos de compra de equipos de soporte</p>
        <button class="btn btn-verde-claro">Gestionar Compras</button>
      </div>

    </div>

  </div>
</template>

<script>
export default {
  methods: {
    irAVerInventario(){
      this.$router.push({path: '/moduloequiposoporte/ver_inventario'});
    },
    irASolicitarEquipos(){
      this.$router.push({path: '/moduloequiposoporte/solicitar_pedidos'});
    },
    irAGestionarPedidos(){
      this.$router.push({path: '/moduloequiposoporte/gestionar_pedidos'});
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

.btn-amarillo {
  background-color: #ffc300;
}

.btn-amarillo:hover {
  background-color: #cdad00;
}

.btn-verde-claro {
  background-color: #32cd32;
}

.btn-verde-claro:hover {
  background-color: #20a91e;
}

/* Centrar el recuadro de la fila final */
.center {
  margin: 0 auto;
}
</style>