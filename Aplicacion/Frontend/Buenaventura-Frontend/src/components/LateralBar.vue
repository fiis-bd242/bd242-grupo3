<template>
    <div class="flex h-screen">
      <aside class="w-64 text-gray-100 flex flex-col p-4" style="background-color: #5C46D8;">
        <h2 class="text-xl font-bold mb-6">Otros Modulos</h2>
  
        <nav class="flex-1">
          <ul>
            <li class="mb-4" v-for="item in menuItems" :key="item">
              <a href="#" class="block p-2 rounded-md hover:bg-purple-700">
                {{ item }}
              </a>
            </li>
          </ul>
        </nav>
      </aside>
  
      <!-- Main content -->
      <main class="flex-1 p-4 bg-gray-100">
        <slot></slot>
      </main>
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
  