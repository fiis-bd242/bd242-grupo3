<template>
    <div class="container-fluid w-full mx-auto p-4">
        <h1 class="text-center font-bold mb-4">EDITAR ORDEN DE TRABAJO</h1>

        <!-- Primera Fila -->
        <div class="row">
            <div class="col-md-3">
                <label for="idOrden"><b>ID: </b></label>
                <input id="idOrden" class="form-control" type="text" :value="detalleOrden.id_orden" disabled />
            </div>
            <div class="col-md-3">
                <label for="planMantenimiento"><b>Plan de mantenimiento: </b></label>
                <select id="planMantenimiento" class="form-control" v-model="detalleOrden.id_plan">
                    <option v-for="plan in planes" :key="plan" :value="plan">{{ plan }}</option>
                </select>
            </div>
        </div>

        <!-- Descripción -->
        <div class="row mt-4">
            <div class="col-md-12">
                <h5><strong>Descripción</strong></h5>
                <textarea class="form-control" v-model="detalleOrden.descripcion" rows="4"></textarea>
            </div>
        </div>

        <!-- Empleados -->
        <div class="col-md-6">
            <h5><strong>Definir empleados:</strong></h5>
            <div class="row font-weight-bold">
                <div class="col-3">ID</div>
                <div class="col-5">Nombre</div>
                <div class="col-3">Responsable</div>
                <div class="col-2"></div>
            </div>
            <div v-for="(empleado, index) in detalleOrden.lista_empleados" :key="index" class="row mt-2">
                <div class="col-3">
                    <select class="form-control" v-model="empleado.id_empleado" @change="onEmpleadoChange(empleado)">
                        <option v-for="empleadoOpt in empleados" :key="empleadoOpt.id_empleado"
                            :value="empleadoOpt.id_empleado">
                            {{ empleadoOpt.id_empleado }}
                        </option>
                    </select>
                </div>
                <div class="col-5">
                    <select class="form-control" v-model="empleado.nombre">
                        <option v-for="empleadoOpt in empleados" :key="empleadoOpt.nombre" :value="empleadoOpt.nombre">
                            {{ empleadoOpt.nombre }}
                        </option>
                    </select>
                </div>
                <div class="col-2 text-center">
                    <!-- Botón de selección de responsable -->
                    <input type="radio" :value="index" v-model="num_responsable" class="form-check-input" />
                </div>
                <div class="col-2">
                    <button class="btn btn-danger" @click="removeEmpleado(index)">Eliminar</button>
                </div>
            </div>
            <button class="btn btn-primary mt-2" @click="addEmpleado">Añadir empleado</button>
        </div>


        <!-- Botones -->
        <div class="row mt-4">
            <div class="col-md-12 text-center">
                <button class="btn btn-success mr-2" @click="guardarOrden">Guardar</button>
                <button class="btn btn-danger" @click="eliminarOrden">Eliminar</button>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            detalleOrden: {
                id_orden: null,
                descripcion: "",
                estado: null,
                id_maquina: null,
                fecha_orden: null,
                nombre_tipo_mant: null,
                fecha_inicio_programado: null,
                fecha_fin_programado: null,
                responsable: null,
                id_plan: null,
                lista_empleados: [],
            },
            num_responsable: 0,
            planes: [],
            empleados: [],
        };
    },
    mounted() {
        this.cargarDatosIniciales();
    },
    methods: {
        async cargarDatosIniciales() {
            try {
                const idOrden = this.$route.params.id_orden; // Obtén ID del plan desde la URL
                await Promise.all([
                    this.cargarDetalleOrden(idOrden),
                    this.cargarPlanes(),
                    this.cargarEmpleados(),
                ]);
                if (this.detalleOrden.lista_empleados.length > 0) {
                    this.num_responsable = 0; // Selecciona el primer empleado como responsable por defecto
                }
            } catch (error) {
                console.error("Error cargando datos iniciales:", error);
            }
        },
        async cargarDetalleOrden(idOrden) {
            try {
                const response = await axios.get(`/api/planificacion/detalleOrden/${parseInt(idOrden)}`);
                this.detalleOrden = response.data;
            } catch (error) {
                console.error("Error cargando detalle de la orden:", error);
                alert("Error al cargar el detalle de la orden.");
            }
        },
        async cargarPlanes() {
            try {
                const response = await axios.get("/api/planificacion/allplans");
                this.planes = response.data;
            } catch (error) {
                console.error("Error cargando planes:", error);
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
        async guardarOrden() {
            try {
                const idOrden = this.$route.params.id_orden;
                const requestBody = {
                    descripcion: this.detalleOrden.descripcion,
                    id_plan: parseInt(this.detalleOrden.id_plan.substring(3, 7)),
                    lista_empleados: this.detalleOrden.lista_empleados,
                    num_responsable: this.num_responsable
                }
                const response = await axios.post(
                    `/api/planificacion/guardarOrden/${parseInt(idOrden)}`,
                    requestBody
                );
                alert("Orden guardada exitosamente.");
                this.$router.push("/moduloplanificacion/lista_ordenes"); // Redirigir tras guardar
            } catch (error) {
                console.error("Error guardando la orden:", error);
                alert("Error al guardar la orden.");
            }
        },
        async eliminarOrden() {
            try {
                const idOrden = this.$route.params.id_orden;
                if (confirm("¿Estás seguro de eliminar esta orden?")) {
                    await axios.post(`/api/planificacion/borrarOrden/${parseInt(idOrden)}`);
                    alert("Orden eliminada exitosamente.");
                    this.$router.push("/moduloplanificacion/lista_ordenes"); // Redirigir tras guardar
                }
            } catch (error) {
                console.error("Error eliminando la orden:", error);
                alert("Error al eliminar la orden.");
            }
        },
        addEmpleado() {
            this.detalleOrden.lista_empleados.push({ id_empleado: null, nombre: "", id_actividad: null });
            if (this.detalleOrden.lista_empleados.length === 1) {
                this.num_responsable = 0;
            }
        },
        removeEmpleado(index) {
            this.detalleOrden.lista_empleados.splice(index, 1);
            if (this.num_responsable === index) {
                this.num_responsable = 0;
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