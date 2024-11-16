<template>
    <div class="mx-4 my-2 space-y-3 w-full">
      <h3 class="font-extrabold text-3xl">Verificación de Datos</h3>
  
      <form @submit.prevent="console.log('1');" class="flex space-x-2 justify-end mx-2 mb-5">
        <input type="datetime-local" class="px-2 py-1 rounded ring-[2px] outline-none ring-black duration-150 focus:ring-purple-400">
        <input type="datetime-local" class="px-2 py-1 rounded ring-[2px] outline-none ring-black duration-150 focus:ring-purple-400">
      </form>
      <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table class="w-full text-sm text-left rtl:text-right text-gray-500">
          <thead class="text-xs text-gray-700 uppercase bg-gray-300">
            <tr>
              <th scope="col" class="px-6 py-3">CodRegistro</th>
              <th scope="col" class="px-6 py-3">Fecha</th>
              <th scope="col" class="px-6 py-3">Actividades de Mantenimiento</th>
              <th scope="col" class="px-6 py-3">Jefe a Cargo</th>
              <th scope="col" class="px-6 py-3">Estado</th>
              <th scope="col" class="px-6 py-3">Acciones</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(registro, index) in registros" :key="index" class="bg-white border-b">
              <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">{{ registro.codigoRegistro }}</td>
              <td class="px-6 py-4">{{ registro.fechaDelDia }}</td>
              <td class="px-6 py-4">{{ registro.numeroRegistrosDia }}</td>
              <td class="px-6 py-4">{{ registro.jefe }}</td>
              <td class="px-6 py-4">{{ registro.estadoReporte }}</td>
              <td class="px-6 py-4 flex space-x-2 items-center">
                <button @click="showDetailsDialog = true" class="p-2 rounded-full bg-blue-500 hover:bg-blue-400 duration-100 border-black border text-white">
                  <PaperIcon />
                </button>
                <button @click="openConfirmationDialog(registro.codigoRegistro)" class="p-2 rounded-full bg-green-500 hover:bg-green-400 duration-100 border-black border text-white">
                  <CheckIcon />
                </button>
                <button @click="showMessageDialog = true" class="p-2 rounded-full bg-red-500 hover:bg-red-400 duration-100 border-black border text-white">
                  <MessageIcon />
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
  
      <!-- Dialogo de Confirmación (Check) -->
      <div v-if="showConfirmationDialog" class="fixed inset-0 bg-gray-800 bg-opacity-80 flex items-center justify-center z-50">
        <div class="bg-white p-6 rounded-lg shadow-lg max-w-sm w-full text-center">
          <h3 class="text-lg font-bold mb-4">Confirmar Verificación</h3>
          <p class="text-gray-700 mb-6">¿Estás seguro de que deseas verificar el registro {{ selectedCodRegistro }}?</p>
          <div class="flex justify-center space-x-4">
            <button @click="showConfirmationDialog = false" class="bg-gray-500 hover:bg-gray-600 text-white px-4 py-2 rounded-lg">Cancelar</button>
            <button @click="confirmVerification" class="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-lg">Confirmar</button>
          </div>
        </div>
      </div>
  
      <!-- Dialogo de Detalles (Paper) -->
      <div v-if="showDetailsDialog" class="fixed inset-0 bg-gray-800 bg-opacity-80 flex items-center justify-center z-50">
        <div class="bg-white p-6 rounded-lg shadow-lg max-w-lg w-full">
          <h3 class="text-2xl font-bold mb-4 text-center text-gray-700">Detalles de Registros del Día</h3>
          <table class="w-full text-left border-collapse">
            <thead>
              <tr class="bg-gray-200">
                <th class="px-2 py-1 border">CodRegistro</th>
                <th class="px-2 py-1 border">Fecha</th>
                <th class="px-2 py-1 border">Actividades de Mantenimiento</th>
                <th class="px-2 py-1 border">Jefe a Cargo</th>
                <th class="px-2 py-1 border">Estado</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="registro in registros" :key="registro.codigoRegistro" class="text-gray-700">
                <td class="px-2 py-1 border">{{ registro.codigoRegistro }}</td>
                <td class="px-2 py-1 border">{{ registro.fechaDelDia }}</td>
                <td class="px-2 py-1 border">{{ registro.numeroRegistrosDia }}</td>
                <td class="px-2 py-1 border">{{ registro.jefe }}</td>
                <td class="px-2 py-1 border">{{ registro.estadoReporte }}</td>
              </tr>
            </tbody>
          </table>
          
          {{ registros }}
          <div class="flex justify-end mt-4">
            <button @click="showDetailsDialog = false" class="bg-gray-500 hover:bg-gray-600 text-white px-4 py-2 rounded-lg">Cerrar</button>
          </div>
        </div>
      </div>
  
      <!-- Dialogo de Mensaje (Message) -->
      <div v-if="showMessageDialog" class="fixed inset-0 bg-gray-800 bg-opacity-80 flex items-center justify-center z-50">
        <div class="bg-white p-6 rounded-lg shadow-lg max-w-md w-full">
          <h3 class="text-2xl font-bold mb-4 text-center text-gray-700">Enviar Notificación</h3>
          <input v-model="subject" type="text" class="border border-gray-300 p-2 w-full rounded mb-4" placeholder="Asunto">
          <textarea v-model="description" class="border border-gray-300 p-2 w-full rounded" rows="4" placeholder="Descripción del error"></textarea>
          <div class="flex justify-end mt-4 space-x-3">
            <button @click="showMessageDialog = false" class="bg-gray-500 hover:bg-gray-600 text-white px-4 py-2 rounded-lg">Cancelar</button>
            <button @click="confirmMessage" class="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-lg">Enviar</button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import CheckIcon from '@/components/icons/CheckIcon.vue';
  import MessageIcon from '@/components/icons/MessageIcon.vue';
  import PaperIcon from '@/components/icons/PaperIcon.vue';
  import axios from 'axios';
  
  export default {
    components: {
      CheckIcon,
      PaperIcon,
      MessageIcon
    },
    data() {
      return {
        
        registros : [],
        showConfirmationDialog: false,
        showDetailsDialog: false,
        showMessageDialog: false,
        selectedCodRegistro: null,
        subject: "",
        description: ""
      };
    },
    mounted() {
      this.getReportes()
    },
    methods: {
      openConfirmationDialog(codRegistro) {
        this.selectedCodRegistro = codRegistro;
        this.showConfirmationDialog = true;
      },
      confirmVerification() {
        // Acción de confirmación para el registro seleccionado
        console.log(`Registro ${this.selectedCodRegistro} verificado.`);
        this.showConfirmationDialog = false;
      },
      confirmMessage() {
        // Acción de confirmación para enviar mensaje
        console.log(`Asunto: ${this.subject}, Descripción: ${this.description}`);
        this.showMessageDialog = false;
      },
      async getReportes(){
        await axios.get("/api/reportes/por-fecha?fechaInicial=2024-11-01&fechaFinal=2024-11-15")
        .then(response => {
          
          this.registros = response.data
          console.log(this.registros)
        })
        .catch(error => {
          console.log("Se tuvo el siguiente error " + error);
        })
      }
    },
    
  };
  </script>
  
  