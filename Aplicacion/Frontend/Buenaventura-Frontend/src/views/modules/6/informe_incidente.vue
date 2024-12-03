<template>
  <div class="mx-4 my-2 space-y-3 w-full">
    <h3 class="font-extrabold text-3xl">Informe de Incidente Cibernético</h3>
    <form @submit.prevent="generateReport" class="space-y-2">
      <h4 class="text-3xl font-semibold">Descripción del Incidente</h4>
      <textarea 
        v-model="incident.description" 
        class="border p-1 w-full border-black" 
        placeholder="Describa el incidente" 
        name="description" 
        rows="4">
      </textarea>
      
      <h4 class="text-3xl font-semibold">Fecha del Incidente</h4>
      <input 
        v-model="incident.date" 
        type="datetime-local" 
        class="border rounded p-1 border-black" 
      />

      <h4 class="text-3xl font-semibold">Criticidad</h4>
      <select 
        v-model="incident.criticality" 
        class="border p-2 rounded-md w-full mt-2"
      >
        <option value="alta">Alta</option>
        <option value="media">Media</option>
        <option value="baja">Baja</option>
      </select>

      <h4 class="text-3xl font-semibold">Usuario Involucrado (Opcional)</h4>
      <input 
        v-model="incident.userInvolved" 
        type="text" 
        class="border p-2 w-full border-black" 
        placeholder="Nombre del usuario (opcional)"
      />

      <h4 class="text-3xl font-semibold">Medidas Tomadas</h4>
      <textarea 
        v-model="incident.actionsTaken" 
        class="border p-1 w-full border-black" 
        placeholder="Describa las medidas tomadas" 
        name="actionsTaken" 
        rows="4">
      </textarea>

      <div class="flex justify-end items-center w-full space-x-3">
        <button 
          type="submit" 
          class="bg-green-500 hover:bg-green-600 duration-150 rounded-lg px-2 py-1 text-white border border-black"
        >
          Generar Informe
        </button>
      </div>
    </form>

    <!-- Confirmación de Registro -->
    <div v-if="isConfirmationDialogOpen" class="fixed inset-0 bg-gray-800 bg-opacity-80 flex items-center justify-center z-50">
      <div class="bg-white p-6 rounded-lg shadow-lg max-w-sm w-full text-center">
        <h3 class="text-lg font-bold mb-4">¿Confirmar el Informe?</h3>
        <p class="text-gray-700 mb-6">¿Estás seguro de que deseas generar este informe?</p>
        <div class="flex justify-center space-x-4">
          <button @click="closeConfirmation" class="bg-gray-500 hover:bg-gray-600 text-white px-4 py-2 rounded-lg">Cancelar</button>
          <button @click="confirmReport" class="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-lg">Confirmar</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useToastStore } from '@/stores/toast';
import axios from 'axios';

export default {
  setup() {
    const toastStore = useToastStore();
    return {
      toastStore,
    };
  },
  data() {
    return {
      incident: {
        description: '',
        date: '',
        criticality: 'alta',
        userInvolved: '',
        actionsTaken: '',
      },
      isConfirmationDialogOpen: false,
    };
  },
  methods: {
    async generateReport() {
      this.isConfirmationDialogOpen = true;
    },
    async confirmReport() {
      // Enviar el informe a la API
      await axios
        .post('/api/incidentes', {
          descripcion: this.incident.description,
          fecha: this.incident.date,
          criticidad: this.incident.criticality,
          usuario_involucrado: this.incident.userInvolved,
          medidas_tomadas: this.incident.actionsTaken,
        })
        .then((response) => {
          console.log('Informe generado con éxito');
          this.toastStore.showToast(3000, 'Informe creado', 'Check', 'bg-green-600');
          this.resetForm();
          this.isConfirmationDialogOpen = false;
        })
        .catch((error) => {
          console.log('Error al generar el informe:', error);
        });
    },
    closeConfirmation() {
      this.isConfirmationDialogOpen = false;
    },
    resetForm() {
      this.incident = {
        description: '',
        date: '',
        criticality: 'alta',
        userInvolved: '',
        actionsTaken: '',
      };
    },
  },
};
</script>
