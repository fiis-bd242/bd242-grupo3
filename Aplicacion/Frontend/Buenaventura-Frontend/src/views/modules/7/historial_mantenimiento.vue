<template>
    <div class="mx-4 my-2  w-full">
        <h3 class="font-extrabold text-3xl">Verificacion de Datos</h3>
        <form @submit.prevent="console.log('1');" class="flex space-x-2 mt-2 justify-end mx-2 mb-5">
            <input type="datetime-local" class="px-2 py-1 rounded ring-[2px] outline-none  ring-black duration-150 focus:ring-purple-400">
            <input type="datetime-local" class="px-2 py-1 rounded ring-[2px] outline-none  ring-black duration-150 focus:ring-purple-400">
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
                        <tr v-for="(registro, index) in Registros" :key="index" class="bg-white border-b">
                            <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap ">
                                {{formattedDate}}
                            </th>
                            <td class="px-6 py-4">
                                {{formattedDate}}
                            </td>
                            <td class="px-6 py-4">
                                {{registro.equipo }}
                            </td>
                            <td class="px-6 py-4">
                                {{registro.tecnico }}
                            </td>
                            <td class="px-6 py-4">
                                {{registro.tarea }}
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
export default {
    data() {
        return {
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
            ]
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
  }

}

</script>
