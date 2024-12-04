<template>
    <div class="mx-4 my-2 w-full">
        <h3 class="font-extrabold text-3xl">Exportación de Datos</h3>
        <form @submit.prevent class="flex space-x-2 justify-end mx-2 mb-5">
            <input type="date" v-model="init_date" class="px-2 py-1 rounded ring-[2px] outline-none  ring-black duration-150 focus:ring-purple-400">
            <input type="date" v-model="final_date" class="px-2 py-1 rounded ring-[2px] outline-none  ring-black duration-150 focus:ring-purple-400">
        </form>
        <div class="rounded flex items-center w-full justify-between">
            <div class="border w-1/3 m-1 border-black p-2">
                <div class="flex items-center w-full justify-between">
                    <p class="text-xl font-bold">JSON</p>
                    <span class="bg-red-600 rounded-full size-8 border border-black "></span>
                </div>
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="icon icon-tabler icons-tabler-outline icon-tabler-json mx-auto size-48">
                    <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                    <path d="M20 16v-8l3 8v-8" />
                    <path d="M15 8a2 2 0 0 1 2 2v4a2 2 0 1 1 -4 0v-4a2 2 0 0 1 2 -2z" />
                    <path d="M1 8h3v6.5a1.5 1.5 0 0 1 -3 0v-.5" />
                    <path d="M7 15a1 1 0 0 0 1 1h1a1 1 0 0 0 1 -1v-2a1 1 0 0 0 -1 -1h-1a1 1 0 0 1 -1 -1v-2a1 1 0 0 1 1 -1h1a1 1 0 0 1 1 1" />
                </svg>
            </div>
        </div>
        <div class="flex w-full m-3 px-4 justify-between">
            <button @click="exportToJSON" class="bg-green-500 px-2 py-1 rounded border border-black text-white text-sm">Exportar JSON</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            init_date: null,
            final_date: null,
            registros_hist: [],
        }
    },
    methods: {
        async getHistorial() {
            try {
                const response = await axios.get(`/api/reportes/historial?fechaInicio=${this.init_date}&fechaFin=${this.final_date}&offset=1`);
                this.registros_hist = response.data;
                console.log("Datos obtenidos: ", this.registros_hist);
            } catch (error) {
                console.error("Error al obtener los datos: ", error);
            }
        },
        exportToJSON() {
            this.getHistorial().then(() => {
                const jsonData = JSON.stringify(this.registros_hist, null, 2);
                const blob = new Blob([jsonData], { type: 'application/json' });
                const url = URL.createObjectURL(blob);
                const a = document.createElement('a');
                a.href = url;
                a.download = 'historial_reportes.json';
                document.body.appendChild(a);
                a.click();
                document.body.removeChild(a);
                URL.revokeObjectURL(url);
            });
        }
    }
};
</script>
