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
                                {{registro.fechaInicio}}
                            </th>
                            <td class="px-6 py-4">
                                {{registro.fechaFinal}}
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
    </div>
</template>
<script>
import axios from 'axios';
export default {
    data() {
        return {
            init_date: null,
            final_date : null,
            date_local : new Date("2022-03-25 11:13:00"),
            Registros : [
            {
                hora: "18:06",
                plan_mant : "M00001",
                equipo : "Compresor de Aire",
                tecnico : "Juan Perez",
                tarea : "Tarea 1",
            },
            {
                hora: "18:06",
                plan_mant : "M00001",
                equipo : "Compresor de Aire",
                tecnico : "Juan Perez",
                tarea : "Tarea 2",
            },
            {
                hora: "18:06",
                plan_mant : "M00001",
                equipo : "Compresor de Aire",
                tecnico : "Juan Perez",
                tarea : "Tarea 1",
            },
            {
                hora: "18:06",
                plan_mant : "M00001",
                equipo : "Compresor de Aire",
                tecnico : "Juan Perez",
                tarea : "Tarea 3",
            },
            ],
            registros_hist : []
        }
    },
    computed: {
    formattedDate() {
      return this.date_local.toLocaleString("es-PE", {
        day: "2-digit",
        month: "2-digit",
        year: "numeric",
        hour: "2-digit",
        minute: "2-digit",
        second: "2-digit",
        hour12: false
      });
    }
  },
  methods: {
        async getHistorial(){
            await axios.get(`/api/reportes/historial?fechaInicio=${this.init_date}&fechaFin=${this.final_date}`)
            .then(response => {
                console.log(response.data);
                this.registros_hist = response.data
            })
            .catch(error => {
                console.log(error);
            })
        }
    },

}

</script>
