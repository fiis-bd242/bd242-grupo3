<template>
    <div>
      <h1 class="title"><b>CREACIÓN DE ORDEN DE TRABAJO</b></h1>
      <form @submit.prevent="guardarOrden">
        <!-- Relacionar a un plan de mantenimiento -->
        <div class="form-group">
          <label for="planMantenimiento">Relacionar a un Plan de Mantenimiento (Opcional):</label>
          <select v-model="orden.id_plan">
            <option value="" disabled>Seleccionar un Plan</option>
            <option v-for="plan in listaPlanes" :key="plan.id" :value="plan.id">
              {{ plan.nombre }}
            </option>
          </select>
        </div>
  
        <!-- Descripción de la Orden -->
        <div class="form-group">
          <label for="descripcion">Descripción de la Orden:</label>
          <textarea id="descripcion" v-model="orden.descripcion" rows="4" required></textarea>
        </div>
  
        <!-- Definir Empleados -->
        <div class="form-group">
          <h3>Definir Empleados:</h3>
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Nombre</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(empleado, index) in empleados" :key="index">
                <td>
                  <select v-model="empleados[index].id" @change="sincronizarEmpleado(index, 'id')">
                    <option value="" disabled>Seleccionar ID</option>
                    <option v-for="emp in listaEmpleados" :key="emp.id" :value="emp.id">
                      {{ emp.id }}
                    </option>
                  </select>
                </td>
                <td>
                  <select v-model="empleados[index].nombre" @change="sincronizarEmpleado(index, 'nombre')">
                    <option value="" disabled>Seleccionar Nombre</option>
                    <option v-for="emp in listaEmpleados" :key="emp.nombre" :value="emp.nombre">
                      {{ emp.nombre }}
                    </option>
                  </select>
                </td>
              </tr>
            </tbody>
          </table>
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
      orden: {
        descripcion: "",
        id_plan: null,
      },
      empleados: [{ id: "", nombre: "" }],
      listaPlanes: [],
      listaEmpleados: [],
    };
  },
  methods: {
    async cargarDatos() {
      try {
        // Cargar lista de planes
        const planesResponse = await axios.get("/api/planificacion/allplans");
        this.listaPlanes = planesResponse.data;

        // Cargar lista de empleados
        const empleadosResponse = await axios.get("/api/empleados/lista");
        this.listaEmpleados = empleadosResponse.data.map((empleado) => ({
          id: empleado.id_empleado,
          nombre: empleado.nombre,
        }));
      } catch (error) {
        console.error("Error al cargar datos:", error);
      }
    },
    sincronizarEmpleado(index, campo) {
      const empleadoSeleccionado = this.listaEmpleados.find((emp) => 
        emp[campo] === this.empleados[index][campo]
      );
      if (empleadoSeleccionado) {
        this.empleados[index] = { ...empleadoSeleccionado };
        if (index === this.empleados.length - 1 && this.empleados[index].id) {
          this.empleados.push({ id: "", nombre: "" });
        }
      }
    },
    async guardarOrden() {
      try {
        const requestBody = {
          orden: {
            descripcion: this.orden.descripcion,
          },
          id_plan: this.orden.id_plan || null,
          lista_empleados: this.empleados
            .filter((empleado) => empleado.id)
            .map((empleado) => empleado.id),
        };

        const response = await axios.post("/api/ordenes/nuevaOrden", requestBody);
        alert(response.data.message || "Orden creada exitosamente.");
        this.$router.push("/moduloordenes/lista_ordenes"); // Redirigir tras guardar
      } catch (error) {
        console.error("Error al guardar la orden:", error);
        alert("Error al guardar la orden. Por favor, inténtalo de nuevo.");
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

table {
  width: 100%;
  border-collapse: collapse;
}

table th,
table td {
  padding: 10px;
  border: 1px solid #ccc;
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
  