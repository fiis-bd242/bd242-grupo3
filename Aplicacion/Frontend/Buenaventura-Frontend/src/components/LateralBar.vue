<template>
    <div class="flex h-[85vh]">
      <aside class="w-64 border-r-2 border-r-black text-gray-100 flex flex-col p-4" style="background-color: #5C46D8;">
        <h2 class="text-xl font-bold mb-6">Otros Modulos</h2>
  
        <nav class="flex-1">
          <ul>
            <li class="mb-4" v-for="item in menuItems" :key="item">
              <a href="#" class="block p-2 font-bold text-lg rounded-md duration-150 hover:bg-purple-700">
                {{ item }}
              </a>
            </li>
          </ul>
        </nav>
      </aside>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        // Lista simple de opciones del menú
        menuItems: [
          'Control de Mantenimiento',
          'Reportes e Insumos',
          'IPERC',
          'Maquinaria',
          'Reportes e Historial de Mantenimiento',
          'Usuarios'
        ],
      };
    },
  };
</script>
  