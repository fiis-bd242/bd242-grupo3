<template>
    <div class="container-fluid w-full mx-auto p-4">
        <h1 class="text-center font-bold mb-4">EDITAR ACTIVIDAD DE TRABAJO</h1>

        <!-- Primera Fila -->
        <div class="row">
            <div class="col-md-3">
                <label for="idActividad"><b>ID: </b></label>
                <input id="idActividad" class="form-control" type="text" :value="detalleActividad.id_actvempleado"
                    disabled />
            </div>
            <div class="col-md-3">
                <label for="nombreActividad"><b>Nombre Actividad: </b></label>
                <input id="nombreActividad" class="form-control" v-model = "detalleActividad.nombre_actv" type="text" />
            </div>
            <div class="col-md-3">
                <label for="idMaquina"><b>Máquina: </b></label>
                <input id="idMaquina" class="form-control" type="text" :value="detalleActividad.id_maquina" disabled />
            </div>
        </div>

        <!-- Segunda Fila -->
        <div class="row">
            <div class="col-md-4">
                <label for="idEmpleado"><b>Empleado: ID: </b></label>
                <select id="idEmpleado" class="form-control" v-model="detalleActividad.id_empleado">
                    <option v-for="empleado in empleados" :key="empleado" :value="empleado.id_empleado">{{ empleado.id_empleado }}</option>
                </select>
            </div>
            <div class="col-md-4">
                <label for="nombreEmpleado"><b>Nombre: </b></label>
                <select id="nombreEmpleado" class="form-control" v-model="detalleActividad.nombre_empleado">
                    <option v-for="empleado in empleados" :key="empleado" :value="empleado.nombre">{{ empleado.nombre }}</option>
                </select>
            </div>
        </div>

        <!-- Tercera Fila -->
        <div class="row">
            <div class="col-md-4">
                <label for="idEquipo"><b>Equipo de soporte: ID: </b></label>
                <select id="idEquipo" class="form-control" v-model="detalleActividad.id_equipo_soporte">
                    <option v-for="equipo in equipos" :key="equipo" :value="equipo.id_equipo_soporte">{{ equipo.id_equipo_soporte }}</option>
                </select>
            </div>
            <div class="col-md-4">
                <label for="nombreEquipo"><b>Nombre: </b></label>
                <select id="nombreEquipo" class="form-control" v-model="detalleActividad.nombre_equipo_soporte">
                    <option v-for="equipo in equipos" :key="equipo" :value="equipo.nombre_equipo_soporte">{{ equipo.nombre_equipo_soporte }}</option>
                </select>
            </div>
        </div>

        <!-- Cuarta Fila -->
        <div class="row">
            <div class="col-md-4">
                <label for="fechaInicio"><b>Fecha de inicio: </b></label>
                <input id="fechaInicio" class="form-control" type="date" v-model="detalleActividad.act_fecha_inicio" />
            </div>
            <div class="col-md-4">
                <label for="fechaFin"><b>Fecha de fin: </b></label>
                <input id="fechaFin" class="form-control" type="date" v-model="detalleActividad.act_fecha_fin" />
            </div>
        </div>

        <!-- Descripción -->
        <div class="row mt-4">
            <div class="col-md-12">
                <h5><strong>Descripción</strong></h5>
                <textarea class="form-control" v-model="detalleActividad.descripcion_actv" rows="4"></textarea>
            </div>
        </div>

        <!-- Botones -->
        <div class="row mt-4">
            <div class="col-md-12 text-center">
                <button class="btn btn-success mr-2" @click="guardarActividad">Guardar</button>
                <button class="btn btn-danger" @click="eliminarActividad">Eliminar</button>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            detalleActividad: {
                id_actvempleado: null,
                id_estado: null,
                id_maquina: null,
                nombre_actv: null,
                id_empleado: null,
                nombre_empleado: null,
                act_fecha_inicio: null,
                act_fecha_fin: null,
                id_equipo_soporte: null,
                nombre_equipo_soporte: null,
                id_orden: null,
                descripcion_actv: null
            },
            equipos: [],
            empleados: []
        };
    },
    mounted() {
        this.cargarDatosIniciales();
    },
    methods: {
        async cargarDatosIniciales() {
            try {
                const idActividad = this.$route.params.id_actividad; // Obtén ID del plan desde la URL
                await Promise.all([
                    this.cargarDetalleActividad(idActividad),
                    this.cargarEquipos(),
                    this.cargarEmpleados(),
                ]);
            } catch (error) {
                console.error("Error cargando datos iniciales:", error);
            }
        },
        async cargarDetalleActividad(idActividad) {
            try {
                const response = await axios.get(`/api/planificacion/detalleActividad/${parseInt(idActividad)}`);
                this.detalleActividad = response.data;
            } catch (error) {
                console.error("Error cargando detalle de la actividad:", error);
                alert("Error al cargar el detalle de la actividad.");
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
        async cargarEmpleados() {
            try {
                const response = await axios.get("/api/planificacion/listaEmpleados");
                this.empleados = response.data;
            } catch (error) {
                console.error("Error cargando empleados:", error);
            }
        },
        async guardarActividad() {
            try {
                const idActividad = this.$route.params.id_actividad;
                const requestBody = {
                    id_actvempleado: parseInt(this.detalleActividad.id_actvempleado),
                    nombre_actv: this.detalleActividad.nombre_actv,
                    descripcion_actv: this.detalleActividad.descripcion_actv,
                    fecha_inicio: this.detalleActividad.act_fecha_inicio,
                    fecha_fin: this.detalleActividad.act_fecha_fin,
                    id_equipo_soporte: parseInt(this.detalleActividad.id_equipo_soporte),
                    id_empleado: parseInt(this.detalleActividad.id_empleado),
                    id_estado: parseInt(this.detalleActividad.id_estado),
                    id_orden: parseInt(this.detalleActividad.id_orden)
                }
                const response = await axios.post(
                    `/api/planificacion/guardarActividad/${parseInt(idActividad)}`,
                    requestBody
                );
                alert("Actividad guardada exitosamente.");
                this.$router.push("/moduloplanificacion/lista_actividades"); // Redirigir tras guardar
            } catch (error) {
                console.error("Error guardando la actividad:", error);
                alert("Error al guardar la actividad.");
            }
        },
        async eliminarActividad() {
            try {
                const idActividad = this.$route.params.id_actividad;
                if (confirm("¿Estás seguro de eliminar esta actividad?")) {
                    await axios.post(`/api/planificacion/borraractividad/${parseInt(idActividad)}`);
                    alert("actividad eliminada exitosamente.");
                    this.$router.push("/moduloplanificacion/lista_actividades"); // Redirigir tras guardar
                }
            } catch (error) {
                console.error("Error eliminando la actividad:", error);
                alert("Error al eliminar la actividad.");
            }
        },
        onEmpleadoChange(empleado) {
            const empleadoSeleccionado = this.empleados.find((i) => i.id_empleado === empleado.id_empleado);
            if (empleadoSeleccionado) empleado.nombre = empleadoSeleccionado.nombre;
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
    border: 2px solid black;
    /* Borde negro */
    border-radius: 8px;
    /* Bordes redondeados */
    padding: 8px;
    /* Espaciado interno */
    box-sizing: border-box;
    /* Ajustar el tamaño considerando el padding y borde */
}

input[disabled] {
    background-color: #f8f9fa;
    /* Fondo gris claro para deshabilitados */
    cursor: not-allowed;
    /* Mostrar que no se puede editar */
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

/* Estilo para empleados y equipos */
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