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
  
        <!-- Insumos Necesarios -->
        <div class="form-group">
          <h3>Insumos Necesarios:</h3>
          <div v-for="(insumo, index) in insumos" :key="index" class="insumo-item" style="display: flex; gap: 10px;">
            <select
              v-model="insumo.id_insumo"
              @change="agregarCampoInsumo(index)"
              required
              style="flex: 2;"
            >
              <option value="" disabled>Seleccionar Insumo</option>
              <option v-for="item in listaInsumos" :key="item.id_insumo" :value="item.id_insumo">
              {{ item.nombre }}
              </option>
            </select>
            <input
              type="number"
              v-model="insumo.cantidad"
              min="1"
              placeholder="Cantidad"
              required
              style="flex: 1;"
            />
            <button type="button" @click="removerCampoInsumo(index)" v-if="index > 0">Eliminar</button>
          </div>
        </div>
  
        <!-- Equipos de Soporte -->
        <div class="form-group">
          <h3>Equipos de Soporte:</h3>
          <div v-for="(equipo, index) in equipos" :key="index">
            <select v-model="equipos[index].id_equipo_soporte" @change="agregarCampoEquipo(index)">
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
      insumos: [{ id_insumo: "", cantidad: 1 }],
      equipos: [""],
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
    agregarCampoInsumo(index) {
      if (
        this.insumos[index].id_insumo &&
        this.insumos[index].cantidad &&
        index === this.insumos.length - 1
      ) {
        this.insumos.push({ id_insumo: "", cantidad: 1 });
      }
    },
    removerCampoInsumo(index) {
      this.insumos.splice(index, 1);
    },
    agregarCampoEquipo(index) {
      if (this.equipos[index] && index === this.equipos.length - 1) {
        this.equipos.push("");
      }
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
          listaEquipos: this.equipos.filter((equipo) => equipo),
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
  