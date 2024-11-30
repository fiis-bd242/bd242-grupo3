<template>
  <div class="w-full mx-auto p-4">
    <h1 class="title"><b>CREACIÓN DE NUEVO PLAN DE MANTENIMIENTO</b></h1>
    <form @submit.prevent="guardarPlan">
      <!-- Tipo de Mantenimiento -->
      <div class="form-group">
        <label for="tipoMantenimiento">Tipo de Mantenimiento:</label>
        <select v-model="mantenimiento.tipoMantenimiento" required>
          <option value=1>Preventivo</option>
          <option value=2>Correctivo</option>
          <option value=3>Predictivo</option>
        </select>
      </div>

      <!-- Código de la máquina -->
      <div class="form-group">
        <label for="codigoMaquina">Código de la Máquina:</label>
        <select v-model="id_maquina" required>
          <option value="" disabled>Seleccionar Máquina</option>
          <option v-for="id in listaMaquinas" :key="id" :value="id">
            {{ id }}
          </option>
        </select>
      </div>

      <!-- Fechas -->
      <div class="form-group">
        <label for="fecha_inicio_programado">Fecha de Inicio:</label>
        <input type="date" id="fecha_inicio_programado" v-model="mantenimiento.fecha_inicio_programado" required />

        <label for="fecha_fin_programado">Fecha de Fin:</label>
        <input type="date" id="fecha_fin_programado" v-model="mantenimiento.fecha_fin_programado" required />
      </div>

      <!-- Criticidad -->
      <div class="form-group">
        <label for="criticidad">Criticidad:</label>
        <select v-model="plan.criticidad" required>
          <option value=1>Baja</option>
          <option value=2>Media</option>
          <option value=3>Alta</option>
        </select>
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
          <div v-for="(insumo, index) in insumos" :key="index" class="row mt-2">
            <div class="col-3">
              <select class="form-control" v-model="insumo.id_insumo" @change="onInsumoChange(insumo)">
                <option value="" disabled>Seleccionar ID</option>
                <option v-for="insumoOpt in listaInsumos" :key="insumoOpt.id_insumo" :value="insumoOpt.id_insumo">
                  {{ insumoOpt.id_insumo }}
                </option>
              </select>
            </div>
            <div class="col-5">
              <select class="form-control" v-model="insumo.nombre">
                <option value="" disabled>Seleccionar Nombre</option>
                <option v-for="insumoOpt in listaInsumos" :key="insumoOpt.nombre" :value="insumoOpt.nombre">
                  {{ insumoOpt.nombre }}
                </option>
              </select>
            </div>
            <div class="col-2">
              <input type="number" class="form-control" v-model="insumo.cantidad" min="1" placeholder="Cantidad" />
            </div>
            <div class="col-2">
              <button class="btn btn-danger" @click.prevent="removerInsumo(index)">Eliminar</button>
            </div>
          </div>
          <button class="btn btn-primary mt-2" @click.prevent="agregarInsumo">Añadir Insumo</button>
        </div>

        <!-- Equipos -->
        <div class="col-md-6">
          <h5><strong>Definir equipos:</strong></h5>
          <div class="row font-weight-bold">
            <div class="col-4">ID</div>
            <div class="col-6">Nombre</div>
            <div class="col-2"></div>
          </div>
          <div v-for="(equipo, index) in equipos" :key="index" class="row mt-2">
            <div class="col-4">
              <select class="form-control" v-model="equipo.id_equipo_soporte" @change="onEquipoChange(equipo)">
                <option value="" disabled>Seleccionar ID</option>
                <option v-for="equipoOpt in listaEquipos" :key="equipoOpt.id_equipo_soporte"
                  :value="equipoOpt.id_equipo_soporte">
                  {{ equipoOpt.id_equipo_soporte }}
                </option>
              </select>
            </div>
            <div class="col-6">
              <select class="form-control" v-model="equipo.nombre_equipo_soporte">
                <option value="" disabled>Seleccionar Nombre</option>
                <option v-for="equipoOpt in listaEquipos" :key="equipoOpt.nombre_equipo_soporte"
                  :value="equipoOpt.nombre_equipo_soporte">
                  {{ equipoOpt.nombre_equipo_soporte }}
                </option>
              </select>
            </div>
            <div class="col-2">
              <button class="btn btn-danger" @click.prevent="removerEquipo(index)">Eliminar</button>
            </div>
          </div>
          <button class="btn btn-primary mt-2" @click.prevent="agregarEquipo">Añadir Equipo</button>
        </div>
      </div>


      <!-- Descripción -->
      <div class="form-group">
        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" v-model="plan.descripcion" rows="4"></textarea>
      </div>

      <!-- Botón Guardar -->
      <div class="form-actions">
        <button type="submit" class="save-button">Guardar y Programar</button>
      </div>
    </form>
  </div>
</template>
<script>
import { useUserStore } from "@/stores/user";
import axios from "axios";

export default {
  data() {
    return {
      plan: {
        criticidad: 0,
        descripcion: "",
      },
      mantenimiento: {
        tipoMantenimiento: 0,
        fecha_inicio_programado: "",
        fecha_fin_programado: ""
      },
      id_maquina: "",
      insumos: [{ id_insumo: "", nombre: "", cantidad: 1 }],
      equipos: [{id_equipo_soporte: "", nombre_equipo_soporte: ""}],
      listaMaquinas: [""],
      listaInsumos: [],
      listaEquipos: [],
    };
  },
  methods: {
    async cargarDatos() {
      try {
        const maquinasResponse = await axios.get("/api/planificacion/listaMaquinas");
        this.listaMaquinas = maquinasResponse.data;

        const insumosResponse = await axios.get("/api/planificacion/listaInsumos");
        this.listaInsumos = insumosResponse.data;

        const equiposResponse = await axios.get("/api/planificacion/listaEquipos");
        this.listaEquipos = equiposResponse.data;
      } catch (error) {
        console.error("Error al cargar listas:", error);
      }
    },
    agregarInsumo() {
      this.insumos.push({ id_insumo: "", nombre: "", cantidad: 1 });
    },
    removerInsumo(index) {
      this.insumos.splice(index, 1);
    },
    agregarEquipo() {
      this.equipos.push({ id_equipo_soporte: "", nombre_equipo_soporte: "" });
    },
    removerEquipo(index) {
      this.equipos.splice(index, 1);
    },
    onInsumoChange(insumo) {
      const insumoSeleccionado = this.listaInsumos.find((i) => i.id_insumo === insumo.id_insumo);
      if (insumoSeleccionado) insumo.nombre = insumoSeleccionado.nombre;
    },
    onEquipoChange(equipo) {
      const equipoSeleccionado = this.listaEquipos.find((e) => e.id_equipo_soporte === equipo.id_equipo_soporte);
      if (equipoSeleccionado) equipo.nombre_equipo_soporte = equipoSeleccionado.nombre_equipo_soporte;
    },
    async guardarPlan() {
      try {
        const listaInsumos = this.insumos
          .filter((insumo) => insumo.id_insumo && insumo.cantidad > 0)
          .map((insumo) => ({
            id_insumo: parseInt(insumo.id_insumo), // Aseguramos que sea numérico
            cantidad: insumo.cantidad,
          }));

        const requestBody = {
          plan: {
            id_criticidad: this.plan.criticidad,
            descripcion: this.plan.descripcion
          },
          mantenimiento: {
            id_tipo_mant: this.mantenimiento.tipoMantenimiento,
            fecha_inicio_programado: this.mantenimiento.fecha_inicio_programado,
            fecha_fin_programado: this.mantenimiento.fecha_fin_programado
          },
          listaEquipos: this.equipos,
          listaInsumos,
          id_usuario: this.idCuenta,
          id_maquina: this.id_maquina
        };

        const response = await axios.post("/api/planificacion/nuevoPlan", requestBody);

        alert(response.data.message || "Plan creado exitosamente.");
        this.$router.push("/moduloplanificacion/lista_planes"); // Redirigir tras guardar
      } catch (error) {
        console.error("Error al guardar el plan:", error);
        alert("Error al guardar el plan. Por favor, inténtalo de nuevo.");
      }
    },
  },
  mounted() {
    this.cargarDatos();
  },
  created() {
    const userStore = useUserStore();
    this.idCuenta = userStore.user.id_cuenta;
    this.idCuenta = 1;
  }
};
</script>
<style scoped>
.title {
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
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

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

input,
select,
textarea {
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.save-button {
  background-color: #007bff;
  color: white;
  padding: 10px 15px;
  border: none;
  cursor: pointer;
  float: right;
}

.save-button:hover {
  background-color: #0056b3;
}
</style>