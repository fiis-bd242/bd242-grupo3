<template>
  <div class="flex h-[85vh]">
    <aside class="w-64 border-r-2 border-r-black text-gray-100 flex flex-col p-4" style="background-color: #5C46D8;">
      <h2 class="text-xl font-bold mb-6">Otros Modulos</h2>
  
      <nav class="flex-1">
        <ul>
          <li class="mb-4" v-for="item in menuItems" :key="item.route_name">
            <router-link 
              :to="{ name: item.route_name }"
              class="block p-2 font-bold text-lg rounded-md duration-150 hover:bg-purple-700"
              :class="{
                'text-yellow-300': isActiveRoute(item)
              }">
              {{ item.name }} 
            </router-link>
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
      menuItems: [
        { name: 'Planificacion de Mantenimiento', route_name: 'dashboard_planificacion', module: 'moduloplanificacion' },
        { name: 'Control de Mantenimiento', route_name: 'dash_perso', module: 'modulogmantenimiento' },
        { name: 'Reportes e Insumos', route_name: 'dash_perso', module: 'moduloreportesinsumos' },
        { name: 'IPERC', route_name: 'dash_perso', module: 'moduloiperc' },
        { name: 'Maquinaria', route_name: 'dash_perso', module: 'modulomaquinaria' },
        { name: 'Reportes e Historial de Mantenimiento', route_name: 'dash_perso', module: 'modulogestionreportes' },
        { name: 'Usuarios', route_name: 'dash_perso', module: 'otrosmodulos' },
      ],
    };
  },
  methods: {
    isActiveRoute(item) {
      const currentRoute = this.$route.path;
      return currentRoute.includes(item.module);
    },
  },
};
</script>
