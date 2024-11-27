<script setup>
    const toastStore = useToastStore()
</script>
<template>
    <div class="mx-4 my-2  w-full">
        <h3 class="font-extrabold text-3xl">Verificacion de Datos</h3>
        <form @submit.prevent="console.log('1');" class="flex space-x-2 mt-2 justify-end mx-2 mb-5">
            <input @change="getHistorial" v-model="init_date" type="date" class="px-2 py-1 rounded ring-[2px] outline-none  ring-black duration-150 focus:ring-purple-400">
            <input @change="getHistorial" v-model="final_date" type="date" class="px-2 py-1 rounded ring-[2px] outline-none  ring-black duration-150 focus:ring-purple-400">
        </form>
        <div class="relative overflow-x-auto mt-4 shadow-md sm:rounded-lg">
                <table class="w-full text-sm text-left rtl:text-right text-gray-500 ">
                    <thead class="text-xs text-gray-700 uppercase bg-gray-50 ">
                        <tr>
                            <th scope="col" class="px-6 py-3">
                                Fecha de Inicio
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Fecha Final
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Nombre del Equipo
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Tecnico Responsable
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Tarea
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Detalles
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(registro, index) in registros_hist.slice(0,10)" :key="index" class="bg-white border-b">
                            <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap ">
                                {{formatDateTime(registro.fechaInicio)}}
                            </th>
                            <td class="px-6 py-4">
                                {{formatDateTime(registro.fechaFinal)}}
                            </td>
                            <td class="px-6 py-4">
                                {{registro.nombreMaquina }}
                            </td>
                            <td class="px-6 py-4">
                                {{registro.tecnicoResponsable }}
                            </td>
                            <td class="px-6 py-4">
                                {{registro.tipoMantenimiento }}
                            </td>
                            <td class="px-6 py-4">
                                <a href="#" class="font-medium cursor-pointer block text-center no-underline hover:bg-blue-400 duration-100 text-white  px-2 py-1 rounded-md bg-blue-600 hover:underline">Detalles</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
        </div>
        <div class="flex space-x-2 my-5 items-center justify-end">
        <button
          @click="backPage"  
          class="border p-3 border-black "
          :disabled="offset === 1"
        ><</button>
        <p class="">{{ offset }}</p>
        <button 
        @click="nextPage"
        :disabled=" activate && registros_hist.length === 0"
        class="border p-3 border-black">></button>
      </div>
    </div>
    
</template>
<script>
import { useToastStore } from '@/stores/toast';
import axios from 'axios';
export default {
    data() {
        return {
            init_date: null,
            final_date : null,
            date_local : new Date("2022-03-25 11:13:00"),
            registros_hist : [],
            offset : 1,
            activate : false,
        }
    },
  methods: {
    formatDateTime(dateTimeString) {
        const date = new Date(dateTimeString); 
        const year = date.getFullYear(); 
        const month = String(date.getMonth() + 1).padStart(2, '0'); 
        const day = String(date.getDate()).padStart(2, '0'); 
        const hours = String(date.getHours()).padStart(2, '0'); 
        const minutes = String(date.getMinutes()).padStart(2, '0'); 

        return `${year}-${month}-${day} ${hours}:${minutes}`; 
    },

        async getHistorial(){
            await axios.get(`/api/reportes/historial?fechaInicio=${this.init_date}&fechaFin=${this.final_date}&offset=${this.offset}`)
            .then(response => {
                console.log(response.data);
                this.registros_hist = response.data
            })
            .catch(error => {
                console.log(error);
            })
        },
        nextPage() {
        console.log("asdas");
        this.offset +=1
        this.getHistorial()
      },
    backPage() {
        this.offset -=1
        this.getHistorial()
      },
    },
    

}

</script>
