<template>
  <div class="container-fluid w-full mx-auto p-4">
    <h1 class="text-center font-bold mb-4">EDITAR PLAN DE MANTENIMIENTO</h1>

    <!-- Primera Fila -->
    <div class="row">
      <div class="col-md-3">
        <label for="idPlan"><b>ID: </b></label>
        <input id="idPlan" class="form-control" type="text" :value="detallePlan.id_plan" disabled />
      </div>
      <div class="col-md-3">
        <label for="ordenTrabajo"><b>Orden de trabajo: </b></label>
        <select id="ordenTrabajo" class="form-control" v-model="detallePlan.id_orden">
          <option v-for="orden in ordenes" :key="orden" :value="orden">{{ orden }}</option>
        </select>
      </div>
      <div class="col-md-3">
        <label for="tipoMant"><b>Tipo de Mantenimiento: </b></label>
        <select id="tipoMant" class="form-control" v-model="detallePlan.nombre_tipo_mant">
          <option value="Preventivo">Preventivo</option>
          <option value="Correctivo">Correctivo</option>
          <option value="Predictivo">Predictivo</option>
        </select>
      </div>
      <div class="col-md-3">
        <label for="criticidad"><b>Criticidad: </b></label>
        <select id="criticidad" class="form-control" v-model="detallePlan.criticidad">
          <option value="Bajo">Bajo</option>
          <option value="Medio">Medio</option>
          <option value="Alto">Alto</option>
        </select>
      </div>
    </div>

    <!-- Segunda Fila -->
    <div class="row">
      <div class="col-md-4">
        <label for="maquina"><b>Máquina: </b></label>
        <select id="maquina" class="form-control" v-model="detallePlan.id_maquina">
          <option v-for="maquina in maquinas" :key="maquina" :value="maquina">{{ maquina }}</option>
        </select>
      </div>
      <div class="col-md-4">
        <label for="fechaInicio"><b>Fecha de inicio: </b></label>
        <input id="fechaInicio" class="form-control" type="date" v-model="detallePlan.fecha_inicio_programado" />
      </div>
      <div class="col-md-4">
        <label for="fechaFin"><b>Fecha de fin: </b></label>
        <input id="fechaFin" class="form-control" type="date" v-model="detallePlan.fecha_fin_programado" />
      </div>
    </div>


    <!-- Descripción -->
    <div class="row mt-4">
      <div class="col-md-12">
        <h5><strong>Descripción</strong></h5>
        <textarea class="form-control" v-model="detallePlan.descripcion" rows="4"></textarea>
      </div>
    </div>


    <!-- Insumos y Equipos -->
    <div class="row mt-4">
      <!-- Insumos -->
      <div class="col-md-6">
        <h5><strong>Definir insumos:</strong></h5>
        <div class="row font-weight-bold">
          <div class="col-3">ID</div>
          <div class="col-5">Nombre</div>
          <div class="col-2">Cantidad</div>
          <div class="col-2"></div>
        </div>
        <div v-for="(insumo, index) in detallePlan.listaInsumos" :key="index" class="row mt-2">
          <div class="col-3">
            <select class="form-control" v-model="insumo.id_insumo">
              <option v-for="insumoOpt in insumos" :key="insumoOpt.id_insumo" :value="insumoOpt.id_insumo">{{
                insumoOpt.id_insumo }}</option>
            </select>
          </div>
          <div class="col-5">
            <select class="form-control" v-model="insumo.nombre">
              <option v-for="insumoOpt in insumos" :key="insumoOpt.nombre" :value="insumoOpt.nombre">{{ insumoOpt.nombre
                }}</option>
            </select>
          </div>
          <div class="col-2">
            <input type="number" class="form-control" v-model="insumo.cantidad" min="0" />
          </div>
          <div class="col-2">
            <button class="btn btn-danger" @click="removeInsumo(index)">Eliminar</button>
          </div>
        </div>
        <button class="btn btn-primary mt-2" @click="addInsumo">Añadir Insumo</button>
      </div>


      <!-- Equipos -->
      <div class="col-md-6">
        <h5><strong>Definir equipos:</strong></h5>
        <div class="row font-weight-bold">
          <div class="col-4">ID</div>
          <div class="col-6">Nombre</div>
          <div class="col-2"></div>
        </div>
        <div v-for="(equipo, index) in detallePlan.listaEquipos" :key="index" class="row mt-2">
          <div class="col-4">
            <select class="form-control" v-model="equipo.id_equipo_soporte" @change="onEquipoChange(equipo)">
              <option v-for="equipoOpt in equipos" :key="equipoOpt.id_equipo_soporte"
                :value="equipoOpt.id_equipo_soporte">{{ equipoOpt.id_equipo_soporte }}</option>
            </select>
          </div>
          <div class="col-6">
            <select class="form-control" v-model="equipo.nombre_equipo_soporte" @change="onNombreEquipoChange(equipo)">
              <option v-for="equipoOpt in equipos" :key="equipoOpt.nombre_equipo_soporte"
                :value="equipoOpt.nombre_equipo_soporte">{{ equipoOpt.nombre_equipo_soporte }}</option>
            </select>
          </div>
          <div class="col-2">
            <button class="btn btn-danger" @click="removeEquipo(index)">Eliminar</button>
          </div>
        </div>
        <button class="btn btn-primary mt-2" @click="addEquipo">Añadir Equipo</button>
      </div>
    </div>

    <!-- Botones -->
    <div class="row mt-4">
      <div class="col-md-12 text-center">
        <button class="btn btn-success mr-2" @click="guardarPlan">Guardar</button>
        <button class="btn btn-danger" @click="eliminarPlan">Eliminar</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      detallePlan: {
        id_plan: null,
        id_orden: null,
        nombre_tipo_mant: null,
        criticidad: null,
        id_maquina: null,
        fecha_inicio_programado: null,
        fecha_fin_programado: null,
        descripcion: "",
        listaInsumos: [],
        listaEquipos: [],
      },
      id_tipo_mant: 0,
      ordenes: [],
      maquinas: [],
      insumos: [],
      equipos: [],
    };
  },
  mounted() {
    this.cargarDatosIniciales();
  },
  methods: {
    async cargarDatosIniciales() {
      try {
        const idPlan = this.$route.params.id_plan; // Obtén ID del plan desde la URL
        await Promise.all([
          this.cargarDetallePlan(idPlan),
          this.cargarOrdenes(),
          this.cargarMaquinas(),
          this.cargarInsumos(),
          this.cargarEquipos(),
        ]);
      } catch (error) {
        console.error("Error cargando datos iniciales:", error);
      }
    },
    async cargarDetallePlan(idPlan) {
      try {
        const response = await axios.get(`/api/planificacion/detallePlan/${parseInt(idPlan)}`);
        this.detallePlan = response.data;
      } catch (error) {
        console.error("Error cargando detalle del plan:", error);
        alert("Error al cargar el detalle del plan.");
      }
    },
    async cargarOrdenes() {
      try {
        const response = await axios.get("/api/planificacion/allordenes");
        this.ordenes = response.data;
      } catch (error) {
        console.error("Error cargando órdenes:", error);
      }
    },
    async cargarMaquinas() {
      try {
        const response = await axios.get("/api/planificacion/listaMaquinas");
        this.maquinas = response.data;
      } catch (error) {
        console.error("Error cargando máquinas:", error);
      }
    },
    async cargarInsumos() {
      try {
        const response = await axios.get("/api/planificacion/listaInsumos");
        this.insumos = response.data;
      } catch (error) {
        console.error("Error cargando insumos:", error);
      }
    },
    async cargarEquipos() {
      try {
        const response = await axios.get("/api/planificacion/listaEquipos");
        this.equipos = response.data;
      } catch (error) {
        console.error("Error cargando equipos:", error);
      }
    },
    async guardarPlan() {
      try {
        const idPlan = this.$route.params.id_plan; 
        let id_criticidad; // Declarar variable con `let` en un ámbito superior
        if (this.detallePlan.criticidad === "Baja") {
            id_criticidad = 1;
        } else if (this.detallePlan.criticidad === "Media") {
            id_criticidad = 2;
        } else {
            id_criticidad = 3;
        }

        let id_tipo_mant_int; // Declarar variable con `let` en un ámbito superior
        if (this.id_tipo_mant === "Preventivo") {
            id_tipo_mant_int = 1;
        } else if (this.id_tipo_mant === "Correctivo") {
            id_tipo_mant_int = 2;
        } else {
            id_tipo_mant_int = 3;
        }

        const requestBody = {
          plan: {
            descripcion: this.detallePlan.descripcion,
            id_criticidad: id_criticidad
          },
          mant: {
            id_orden: parseInt(this.detallePlan.id_orden.substring(3,7)),
            id_tipo_mant: id_tipo_mant_int,
            id_maquina: parseInt(this.detallePlan.id_maquina.substring(3,7)),
            fecha_inicio_programado: this.detallePlan.fecha_inicio_programado,
            fecha_fin_programado: this.detallePlan.fecha_fin_programado
          },
          listaEquipos: this.detallePlan.listaEquipos,
          listaInsumos: this.detallePlan.listaInsumos

        }
        const response = await axios.post(
          `/api/planificacion/guardarPlan/${parseInt(idPlan)}`,
          requestBody
        );
        alert("Plan guardado exitosamente.");
        this.$router.push("/moduloplanificacion/lista_planes"); // Redirigir tras guardar
      } catch (error) {
        console.error("Error guardando el plan:", error);
        alert("Error al guardar el plan.");
      }
    },
    async eliminarPlan() {
      try {
        const idPlan = this.$route.params.id_plan; 
        if (confirm("¿Estás seguro de eliminar este plan?")) {
          await axios.post(`/api/planificacion/borrarPlan/${parseInt(idPlan)}`);
          alert("Plan eliminado exitosamente.");
          this.$router.push("/moduloplanificacion/lista_planes"); // Redirigir tras guardar
        }
      } catch (error) {
        console.error("Error eliminando el plan:", error);
        alert("Error al eliminar el plan.");
      }
    },
    addInsumo() {
      this.detallePlan.listaInsumos.push({ id_insumo: null, nombre: "", cantidad: 1 });
    },
    removeInsumo(index) {
      this.detallePlan.listaInsumos.splice(index, 1);
    },
    addEquipo() {
      this.detallePlan.listaEquipos.push({ id_equipo_soporte: null, nombre_equipo_soporte: "" });
    },
    removeEquipo(index) {
      this.detallePlan.listaEquipos.splice(index, 1);
    },
    onInsumoChange(insumo) {
      const insumoSeleccionado = this.insumos.find((i) => i.id_insumo === insumo.id_insumo);
      if (insumoSeleccionado) insumo.nombre = insumoSeleccionado.nombre;
    },
    onEquipoChange(equipo) {
      const equipoSeleccionado = this.equipos.find((e) => e.id_equipo_soporte === equipo.id_equipo_soporte);
      if (equipoSeleccionado) equipo.nombre_equipo_soporte = equipoSeleccionado.nombre_equipo_soporte;
    },
  },
};
</script>

<style>
/* Estilo para cuadros de texto con borde negro redondeado */
input[type="text"],
input[type="number"],
input[type="date"],
textarea,
select {
  border: 2px solid black; /* Borde negro */
  border-radius: 8px; /* Bordes redondeados */
  padding: 8px; /* Espaciado interno */
  box-sizing: border-box; /* Ajustar el tamaño considerando el padding y borde */
}

input[disabled] {
  background-color: #f8f9fa; /* Fondo gris claro para deshabilitados */
  cursor: not-allowed; /* Mostrar que no se puede editar */
}

/* Contenedor general para filas con varios campos */
.row {
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
  margin-bottom: 16px;
}

.col-md-3,
.col-md-4,
.col-md-6,
.col-md-12 {
  flex: 1;
  /* Para distribuir equitativamente */
  min-width: 200px;
  /* Evitar que los campos sean demasiado pequeños */
}

/* Estilo para insumos y equipos */
.row.font-weight-bold {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.row.mt-2 {
  display: flex;
  align-items: center;
  gap: 16px;
}

textarea {
  resize: none;
  width: 100%;
  margin-top: 10px;
}

/* Estilo de botones */
button {
  padding: 8px 16px;
  margin: 5px;
  border: none;
  border-radius: 4px;
  font-size: 14px;
  cursor: pointer;
}

button.btn-primary {
  background-color: #007bff;
  color: white;
}

button.btn-danger {
  background-color: #dc3545;
  color: white;
}

button.btn-success {
  background-color: #28a745;
  color: white;
}

button:hover {
  opacity: 0.9;
}

/* Campo de texto para descripción */
textarea.form-control {
  width: 100%;
}
</style>