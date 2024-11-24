<template>
    <div>
      <h1 class="title"><b>CREACIÓN DE NUEVO PLAN DE MANTENIMIENTO</b></h1>
      <form @submit.prevent="guardarPlan">
        <!-- Tipo de Mantenimiento -->
        <div class="form-group">
          <label for="tipoMantenimiento">Tipo de Mantenimiento:</label>
          <select v-model="plan.tipoMantenimiento" required>
            <option value="Preventivo">Preventivo</option>
            <option value="Correctivo">Correctivo</option>
            <option value="Predictivo">Predictivo</option>
          </select>
        </div>
  
        <!-- Código de la máquina -->
        <div class="form-group">
          <label for="codigoMaquina">Código de la Máquina:</label>
          <input
            type="text"
            id="codigoMaquina"
            v-model="plan.codigoMaquina"
            required
          />
        </div>
  
        <!-- Fechas -->
        <div class="form-group">
          <label for="fechaInicio">Fecha de Inicio:</label>
          <input type="date" id="fechaInicio" v-model="plan.fechaInicio" required />
  
          <label for="fechaFin">Fecha de Fin:</label>
          <input type="date" id="fechaFin" v-model="plan.fechaFin" required />
        </div>
  
        <!-- Criticidad -->
        <div class="form-group">
          <label for="criticidad">Criticidad:</label>
          <select v-model="plan.criticidad" required>
            <option value="1">Baja</option>
            <option value="2">Media</option>
            <option value="3">Alta</option>
          </select>
        </div>
  
        <!-- Insumos Necesarios -->
        <div class="form-group">
          <h3>Insumos Necesarios:</h3>
          <div v-for="(insumo, index) in insumos" :key="index">
            <select v-model="insumos[index]" @change="agregarCampoInsumo(index)">
              <option value="" disabled>Seleccionar Insumo</option>
              <option v-for="item in listaInsumos" :key="item" :value="item">
                {{ item }}
              </option>
            </select>
          </div>
        </div>
  
        <!-- Equipos de Soporte -->
        <div class="form-group">
          <h3>Equipos de Soporte:</h3>
          <div v-for="(equipo, index) in equipos" :key="index">
            <select v-model="equipos[index]" @change="agregarCampoEquipo(index)">
              <option value="" disabled>Seleccionar Equipo</option>
              <option v-for="id in listaEquipos" :key="id" :value="id">
                {{ id }}
              </option>
            </select>
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
import axios from "axios";

export default {
  data() {
    return {
      plan: {
        tipoMantenimiento: "",
        codigoMaquina: "",
        fechaInicio: "",
        fechaFin: "",
        criticidad: "",
        descripcion: "",
      },
      insumos: [""],
      equipos: [""],
      listaInsumos: [],
      listaEquipos: [],
    };
  },
  methods: {
    async cargarDatos() {
      try {
        const insumosResponse = await axios.get("/api/planificacion/listaInsumos");
        this.listaInsumos = insumosResponse.data;

        const equiposResponse = await axios.get("/api/planificacion/listaEquipos");
        this.listaEquipos = equiposResponse.data;
      } catch (error) {
        console.error("Error al cargar listas:", error);
      }
    },
    agregarCampoInsumo(index) {
      if (this.insumos[index] && index === this.insumos.length - 1) {
        this.insumos.push("");
      }
    },
    agregarCampoEquipo(index) {
      if (this.equipos[index] && index === this.equipos.length - 1) {
        this.equipos.push("");
      }
    },
    async guardarPlan() {
      try {
        const requestBody = {
          plan: {
            tipoMantenimiento: this.plan.tipoMantenimiento,
            codigoMaquina: this.plan.codigoMaquina,
            fechaInicio: this.plan.fechaInicio,
            fechaFin: this.plan.fechaFin,
            criticidad: this.plan.criticidad,
            descripcion: this.plan.descripcion,
          },
          listaEquipos: this.equipos.filter((equipo) => equipo),
          listaInsumos: this.insumos.filter((insumo) => insumo),
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
  