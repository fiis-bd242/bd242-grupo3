<template>
    <div class="mx-4 my-2 space-y-3 w-full">
        <div class="flex justify-between w-full items-center">
            <h3 class="font-extrabold text-3xl">Verificacion de Registros</h3>
            <h4 class="p-2 bg-slate-300 border border-black font-bold" >{{ getCurrentDateFormatted() }}</h4>
        </div>
        

        <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
            <table class="w-full text-sm text-left rtl:text-right text-gray-500 ">
                <thead class="text-xs text-gray-700 uppercase bg-gray-50 ">
                    <tr>
                        <th scope="col" class="px-6 py-3">
                            Hora
                        </th>
                        <th scope="col" class="px-6 py-3">
                            Plan Mantenimiento
                        </th>
                        <th scope="col" class="px-6 py-3">
                            Nombre del Equipo
                        </th>
                        <th scope="col" class="px-6 py-3">
                            Tecnico Responsable
                        </th>
                        <th scope="col" class="px-6 py-3">
                            Tipo Mantenimiento
                        </th>
                        <th scope="col" class="px-6 py-3">
                            Detalles
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(registro, index) in Registros" :key="index" class="bg-white border-b">
                        <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap ">
                            {{registro.hora }}
                        </th>
                        <td class="px-6 py-4">
                            {{registro.plan_mant }}
                        </td>
                        <td class="px-6 py-4">
                            {{registro.equipo }}
                        </td>
                        <td class="px-6 py-4">
                            {{registro.tecnico }}
                        </td>
                        <td class="px-6 py-4">
                            {{registro.tipo_mant }}
                        </td>
                        <td class="px-6 py-4">
                            <a href="#" class="font-medium text-blue-600  hover:underline">Edit</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

    </div>
</template>
<script>
export default {
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
            ]
        }
    },
    methods: {
        getCurrentDateFormatted() {
            const today = new Date();
            
            const day = String(today.getDate()).padStart(2, '0');
            const month = String(today.getMonth() + 1).padStart(2, '0');
            const year = today.getFullYear();
            
            return `${day}/${month}/${year}`;
        }
    },
}

</script>