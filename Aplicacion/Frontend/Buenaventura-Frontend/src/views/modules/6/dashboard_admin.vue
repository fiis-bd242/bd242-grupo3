<template>
  <div class="dashboard w-full mx-auto p-4">
    <!-- Fila 1 -->
    <div class="row">
      <div class="card" @click="irACrearUsuario">
        <h2><b>Crear nuevo usuario</b></h2>
        <p>Genera un nuevo usuario en el sistema</p>
        <button class="btn btn-celeste">Ir a Crear Usuario</button>
      </div>

      <div class="card" @click="irAListadoUsuarios">
        <h2><b>Listado de usuarios</b></h2>
        <p>Visualiza y gestiona los usuarios del sistema</p>
        <button class="btn btn-azul">Ir a Listado de Usuarios</button>
      </div>
    </div>

    <div class="row">
      <div class="card" @click="irAVerNotificaciones">
        <h2><b>Ver notificaciones de administrador</b></h2>
        <p>Consulta las notificaciones importantes para los administradores</p>
        <button class="btn btn-verde-claro">Ir a Ver Notificaciones</button>
      </div>

      <div class="card" @click="irACrearInformeIncidente">
        <h2><b>Crear informe de incidente cibernético</b></h2>
        <p>Genera un informe sobre incidentes cibernéticos ocurridos</p>
        <button class="btn btn-verde">Ir a Crear Informe</button>
      </div>
    </div>

    <!-- Fila 3 -->
    <div class="row">
      <div class="card center" @click="irAEditarInformacionRoles">
        <h2><b>Editar información y roles de usuario</b></h2>
        <p>Modifica la información y los roles asignados a los usuarios</p>
        <button class="btn btn-rosa">Ir a Editar Información</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    irACrearUsuario() {
      this.$router.push('/moduloseguridad/crea_usuario');
    },
    irAListadoUsuarios() {
      this.$router.push('/moduloseguridad/lista_usuario');
    },
    irAVerNotificaciones() {
      this.$router.push('/moduloseguridad/ver_notificaciones');
    },
    irACrearInformeIncidente() {
      this.$router.push('/moduloseguridad/informe_incidente');
    },
    irAEditarInformacionRoles() {
      this.$router.push('/moduloseguridad/editar_info_roles');
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
