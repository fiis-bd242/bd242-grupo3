<template>
    <div class="mx-4 my-2 space-y-3 w-full">
      <div class="flex justify-between w-full items-center">
        <h3 class="font-extrabold text-3xl">Verificación de Registros</h3>
        <h4 class="p-2 bg-slate-300 border border-black font-bold">{{ getCurrentDateFormatted() }}</h4>
      </div>
  
      <div class="px-2 flex w-full justify-end items-center">
        <button @click="showConfirmationDialog = true" class="bg-green-500 text-white px-3 py-2 border border-black">Registrar Datos</button>
      </div>
  
      <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table class="w-full text-sm text-left rtl:text-right text-gray-500">
          <thead class="text-xs text-gray-700 uppercase bg-gray-50">
            <tr>
              <th scope="col" class="px-6 py-3">Hora</th>
              <th scope="col" class="px-6 py-3">Plan Mantenimiento</th>
              <th scope="col" class="px-6 py-3">Nombre del Equipo</th>
              <th scope="col" class="px-6 py-3">Técnico Responsable</th>
              <th scope="col" class="px-6 py-3">Tipo Mantenimiento</th>
              <th scope="col" class="px-6 py-3">Acciones</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(registro, index) in Registros" :key="index" class="bg-white border-b">
              <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">{{ registro.hora }}</td>
              <td class="px-6 py-4">{{ registro.plan_mant }}</td>
              <td class="px-6 py-4">{{ registro.equipo }}</td>
              <td class="px-6 py-4">{{ registro.tecnico }}</td>
              <td class="px-6 py-4">{{ registro.tipo_mant }}</td>
              <td class="px-6 py-4 flex space-x-2">
                <button @click="showDetailsDialog = true" class="p-2 rounded-full bg-blue-500 hover:bg-blue-400 duration-100 border-black border text-white">
                  <PaperIcon />
                </button>
                <button @click="showEditDialog = true" class="p-2 rounded-full bg-yellow-500 hover:bg-yellow-400 duration-100 border-black border text-white">
                  <PencilIcon />
                </button> 
                <button @click="showMessageDialog = true" class="p-2 rounded-full bg-red-500 hover:bg-red-400 duration-100 border-black border text-white">
                  <MessageIcon />
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
  
      <!-- Dialogo de Confirmación -->
      <div v-if="showConfirmationDialog" class="fixed inset-0 bg-gray-800 bg-opacity-80 flex items-center justify-center z-50">
        <div class="bg-white p-6 rounded-lg shadow-lg max-w-sm w-full text-center">
          <h3 class="text-lg font-bold mb-4">¿Confirmar Registro?</h3>
          <p class="text-gray-700 mb-6">¿Estás seguro de que deseas registrar estos datos?</p>
          <div class="flex justify-center space-x-4">
            <button @click="showConfirmationDialog = false" class="bg-gray-500 hover:bg-gray-600 text-white px-4 py-2 rounded-lg">Cancelar</button>
            <button @click="confirmRegistration" class="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-lg">Confirmar</button>
          </div>
        </div>
      </div>
  
      <!-- Dialogo de Detalles (Paper) -->
      <div v-if="showDetailsDialog" class="fixed inset-0 bg-gray-800 bg-opacity-80 flex items-center justify-center z-50">
        <div class="bg-white p-6 rounded-lg shadow-lg max-w-lg w-full">
          <h3 class="text-2xl font-bold mb-4 text-center text-gray-700">Detalles de Registro</h3>
          <table class="w-full text-left border-collapse">
            <tbody>
              <tr><th class="border-b px-2 py-1">Hora:</th><td class="border-b px-2 py-1">18:06</td></tr>
              <tr><th class="border-b px-2 py-1">Plan Mantenimiento:</th><td class="border-b px-2 py-1">M00001</td></tr>
              <tr><th class="border-b px-2 py-1">Nombre del Equipo:</th><td class="border-b px-2 py-1">Compresor de Aire</td></tr>
              <tr><th class="border-b px-2 py-1">Técnico Responsable:</th><td class="border-b px-2 py-1">Juan Perez</td></tr>
              <tr><th class="border-b px-2 py-1">Tipo Mantenimiento:</th><td class="border-b px-2 py-1">Preventivo</td></tr>
          
            </tbody>
             </table>
          <div class="flex justify-end mt-4">
            <button @click="showDetailsDialog = false" class="bg-gray-500 hover:bg-gray-600 text-white px-4 py-2 rounded-lg">Cerrar</button>
          </div>
        </div>
      </div>
  
      <!-- Dialogo de Edición (Pencil) -->
      <div v-if="showEditDialog" class="fixed inset-0 bg-gray-800 bg-opacity-80 flex items-center justify-center z-50">
        <div class="bg-white p-6 rounded-lg shadow-lg max-w-md w-full">
          <h3 class="text-2xl font-bold mb-4 text-center text-gray-700">Editar Registro</h3>
          <h4 class="font-semibold">Incidencias</h4>
          <input v-model="incident" type="text" class="border border-gray-300 p-2 w-full rounded mb-4" placeholder="Escriba la incidencia">
          <h4 class="font-semibold">Actividad Realizada</h4>
          <textarea class="border border-gray-300 p-2 w-full rounded" rows="3"></textarea>
          <div class="flex justify-end mt-4 space-x-3">
            <button @click="showEditDialog = false" class="bg-gray-500 hover:bg-gray-600 text-white px-4 py-2 rounded-lg">Cancelar</button>
            <button @click="confirmEdit" class="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-lg">Guardar</button>
          </div>
        </div>
      </div>
  
      <!-- Dialogo de Mensaje (Message) -->
      <div v-if="showMessageDialog" class="fixed inset-0 bg-gray-800 bg-opacity-80 flex items-center justify-center z-50">
        <div class="bg-white p-6 rounded-lg shadow-lg max-w-md w-full">
          <h3 class="text-2xl font-bold mb-4 text-center text-gray-700">Enviar Mensaje</h3>
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
  import PencilIcon from '@/components/icons/PencilIcon.vue';
  import PaperIcon from '@/components/icons/PaperIcon.vue';
  import MessageIcon from '@/components/icons/MessageIcon.vue';
  import axios from 'axios';
  export default {
    components: {
      PencilIcon,
      PaperIcon,
      MessageIcon
    },
    data() {
      return {
        Registros : [
            {
                hora: "18: 06",
                plan_mant : "M00001",
                equipo : "Compresor de Aire",
                tecnico : "Juan Perez",
                tipo_mant : "Preventivo",
            },
            {
                hora: "18: 06",
                plan_mant : "M00001",
                equipo : "Compresor de Aire",
                tecnico : "Juan Perez",
                tipo_mant : "Preventivo",
            },
            {
                hora: "18: 06",
                plan_mant : "M00001",
                equipo : "Compresor de Aire",
                tecnico : "Juan Perez",
                tipo_mant : "Preventivo",
            },
            {
                hora: "18: 06",
                plan_mant : "M00001",
                equipo : "Compresor de Aire",
                tecnico : "Juan Perez",
                tipo_mant : "Preventivo",
            },
        ],
        showConfirmationDialog: false,
        showDetailsDialog: false,
        showEditDialog: false,
        showMessageDialog: false,
        incident: "",
        subject: "",
        description: ""
      };
    },

    mounted() {
      this.getReportes()
    },
    methods: {
      getCurrentDateFormatted() {
        const today = new Date();
        const day = String(today.getDate()).padStart(2, '0');
        const month = String(today.getMonth() + 1).padStart(2, '0');
        const year = today.getFullYear();
        return `${day}/${month}/${year}`;
      },
      confirmRegistration() {
        // Acción de confirmación de registro
        this.showConfirmationDialog = false;
      },
      confirmEdit() {
        // Acción de confirmación de edición
        this.showEditDialog = false;
      },
      confirmMessage() {
        // Acción de confirmación de envío de mensaje
        this.showMessageDialog = false;
      },

      async getReportes(){
        await axios.get("/api/reportes/por-fecha?fechaInicial=2024-11-01&fechaFinal=2024-11-15")
        .then(response => {
          console.log(response.data);
        })
        .catch(error => {
          console.log("Se tuvo el siguiente error " + error);
        })
      }
    }
  };
  </script>
