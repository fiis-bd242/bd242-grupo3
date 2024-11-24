<template>
  <div class="mx-4 my-2 space-y-3 w-full">
    <h3 class="font-extrabold text-3xl">Realizar Registros</h3>
    <form @submit.prevent="showConfirmation" class="space-y-2">
      <h4 class="text-3xl font-semibold">Fecha</h4>
      <div class="flex space-x-2">
        <input v-model="fecha_inicial" type="datetime-local" class="border rounded p-1 border-black">
        <input v-model="fecha_registro" type="datetime-local" class="border rounded p-1 border-black">
      </div>
      <h4 class="text-3xl font-semibold">Descripcion de la Actividad</h4>
      <textarea v-model="observaciones" class="border p-1 w-full border-black" placeholder="Describa el mantenimiento" name="desc" rows="4"></textarea>
      <h4 class="text-3xl font-semibold">Incidencias</h4>
      <div class="flex space-x-2 w-full items-center">
        <input v-model="messageError" type="text" class="border border-black p-2 w-96" placeholder="Escriba la incidencia presentada">
        <button @click="addErrors" type="button" class="rounded-full p-2 text-white font-bold border-black hover:bg-white duration-150 hover:text-black text-xl border-2 block size-12 bg-green-400">+</button>
      </div>
      <div class="flex space-x-2 ">
        <ErrorTags
          v-for="(message, index) in messages"
          :key="index"
          :msg="message"
          @remove="removeMessage(message)"
        />
      </div>

      <h4 class="text-3xl font-semibold">Calificación</h4>
      <div class="flex space-x-2">
        <Estrella
          v-for="(item, index) in 5"
          :key="index"
          @mouseenter="setStar(index)"
          class="text-gray-500 size-8 duration-75"
          :class="{'text-purple-500': index < ranked}"
        />
      </div>

      <div class="flex justify-end items-center w-full space-x-3">
        <button @click="openDialog" type="button" class="bg-purple-500 hover:bg-purple-600 duration-150 rounded-lg px-2 py-1 text-white border border-black">
          Seleccionar Actividad
        </button>
        <button type="submit" class="bg-green-500 hover:bg-green-600 duration-150 rounded-lg px-2 py-1 text-white border border-black">
          Registrar
        </button>
      </div>
    </form>

    <!-- Dialogo de pantalla completa -->
    <div v-if="isDialogOpen" class="fixed inset-0 bg-gray-800 bg-opacity-80 flex items-center justify-center z-50">
      <div class="bg-white w-full h-full md:max-w-3xl md:h-auto p-6 rounded-lg shadow-lg">
        <h2 class="text-center text-gray-600 text-2xl font-bold mb-6">Seleccionar Actividad</h2>
        <div class="space-y-4">
          <p class="text-lg font-semibold text-black">Actividad de mantenimiento</p>

          <!-- Filtros -->
          <div class="flex flex-col md:flex-row md:space-x-4">
            <input @change="busquedaMantenimiento" v-model="marca" type="text" placeholder="Maquina del mantenimiento" class="border p-2 rounded-md w-full md:w-1/2">
          </div>

          <select v-model="id_act_mantto" class="border p-2 rounded-md w-full mt-4">
            <option v-for="(item, index) in actividades_disponibles" :key="index" :value="item.id_mantenimiento"> {{ item.resultado }}</option>
          </select>

          <div class="flex justify-end space-x-4 mt-6">
            <button @click="closeDialog" class="bg-gray-500 hover:bg-gray-600 text-white px-4 py-2 rounded-lg">Cerrar</button>
            <button @click="closeDialog" class="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-lg">Asignar</button>
          </div>
        </div>
      </div>
    </div>
    <div v-if="isConfirmationDialogOpen" class="fixed inset-0 bg-gray-800 bg-opacity-80 flex items-center justify-center z-50">
      <div class="bg-white p-6 rounded-lg shadow-lg max-w-sm w-full text-center">
        <h3 class="text-lg font-bold mb-4">¿Confirmar Registro?</h3>
        <p class="text-gray-700 mb-6">¿Estás seguro de que deseas registrar esta actividad?</p>
        <div class="flex justify-center space-x-4">
          <button @click="closeConfirmation" class="bg-gray-500 hover:bg-gray-600 text-white px-4 py-2 rounded-lg">Cancelar</button>
          <button @click="confirmRegistration" class="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-lg">Confirmar</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ErrorTags from '@/components/ErrorTags.vue';
import Estrella from '@/components/icons/StartIcon.vue';
import axios from 'axios';

export default {
  components: {
    Estrella,
    ErrorTags,
  },
  data() {
    return {
      actividades_disponibles : [],
      fecha_registro: "",
      fecha_inicial: "",
      id_act_mantto: 4,
      observaciones: "",
      marca: "",
      ranked: 0,
      messages: [],
      messageError: '',
      isDialogOpen: false,         
      isConfirmationDialogOpen: false 
    };
  },
  methods: {
    
    convertirFechaAFormatoISOConZonaHoraria(fechaISO) {
      const fecha = new Date(fechaISO); // Crear un objeto Date a partir de la fecha proporcionada
      const offset = fecha.getTimezoneOffset(); // Desplazamiento en minutos

      const offsetHoras = Math.abs(Math.floor(offset / 60));
      const offsetMinutos = Math.abs(offset % 60);
      
      // Construir el desplazamiento del huso horario
      const signo = offset > 0 ? "-" : "+";
      const offsetString = `${signo}${String(offsetHoras).padStart(2, '0')}:${String(offsetMinutos).padStart(2, '0')}`;
      
      // Formatear la fecha a `YYYY-MM-DDTHH:mm:ss`
      const fechaFormateada = fecha.toISOString().replace("Z", ""); // Eliminar la Z del final
      const fechaSinMilisegundos = fechaFormateada.split(".")[0]; // Quitar milisegundos

      return `${fechaSinMilisegundos}${offsetString}`;
    },

    async busquedaMantenimiento(){
      await axios.get(`/api/reportes/resultados?nombreMarca=${this.marca}`)
      .then(response => {
        this.actividades_disponibles = response.data
      })
      .catch(error => {
        console.log(error);
      })
    },

    async crearRegistro(){
      let to_message = []
      for (const message of this.messages){
        to_message.push({incidencia : message})
      }
      
      await axios.post("/api/reportes/nuevo-con-incidencias", {
        registro: {
          fecha_registro: this.convertirFechaAFormatoISOConZonaHoraria(this.fecha_registro),
          fecha_inicial : this.convertirFechaAFormatoISOConZonaHoraria(this.fecha_inicial),
          id_empleado: 4,
          id_act_mantto: this.id_act_mantto,
          calificacion: this.ranked,
          observaciones: this.observaciones
        },
        incidencias : to_message
      })
      .then(response => {
        console.log("POST realizado con exito");
      })
      .catch(error => {
        console.log(error);
      })
    },
    setStar(index) {
      this.ranked = index + 1;
    },
    addErrors() {
      // Evitar duplicados
      this.messages = this.messages.filter((message) => message !== this.messageError);
      this.messages.push(this.messageError);
      
      this.messageError = '';
    },
    removeMessage(message) {
      // Eliminar el mensaje de la lista
      this.messages = this.messages.filter((msg) => msg !== message);
    },
    openDialog() {
      this.isDialogOpen = true;
    },
    closeDialog() {
      this.isDialogOpen = false;
    },
    showConfirmation() {
      this.isConfirmationDialogOpen = true;
    },
    closeConfirmation() {
      this.isConfirmationDialogOpen = false;
    },
    confirmRegistration() {
      this.crearRegistro()
      this.isConfirmationDialogOpen = false; 
    },
    
  },
};
</script>
