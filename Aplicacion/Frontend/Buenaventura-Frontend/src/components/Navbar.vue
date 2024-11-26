<template>
    <nav class="sticky w-full border-b-black h-[15vh] border-b-2 p-5 flex justify-between items-center" style="background-color: #5C46D8;">
      <img class="h-16 bg-transparent" src="../assets/images/buenaventura.png" alt="ba">
      <!-- Título dinámico -->
      <h2 class="text-white text-4xl font-extrabold">{{ tituloModulo }}</h2>
      <BellIcon class="mx-4" />
    </nav>
  </template>
  
  <script>
  import BellIcon from './icons/BellIcon.vue';
  
  export default {
    components: {
      BellIcon
    },
    data() {
      return {
        // Mapear rutas con títulos de módulos
        titulos: {
            //Módulo 1: Planificación del mantenimiento
          '/moduloplanificacion/crea_plan': 'Planificación del mantenimiento',
          '/moduloplanificacion/lista_planes': 'Planificación del mantenimiento',
          '/moduloplanificacion/crea_orden': 'Planificación del mantenimiento',
          '/moduloplanificacion/lista_ordenes': 'Planificación del mantenimiento',
          '/moduloplanificacion/lista_actividades': 'Planificación del mantenimiento',
          '/moduloplanificacion/dashboard_planificacion': 'Planificación del mantenimiento',
          '/moduloplanificacion/detalle_actividad': 'Planificación del mantenimiento',
          '/moduloplanificacion/detalle_orden': 'Planificación del mantenimiento',
          '/moduloplanificacion/detalle_plan': 'Planificación del mantenimiento',
          '/moduloplanificacion/edita_plan': 'Planificación del mantenimiento',
          '/moduloplanificacion/edita_orden': 'Planificación del mantenimiento',
          '/moduloplanificacion/edita_actividad': 'Planificación del mantenimiento',
          //Módulo 2: Control del mantenimiento
          //Módulo 7: Gestión de reportes e insumos
          '/modulogestionreportes/dash': 'Gestión de reportes e historial de mantenimiento',
          '/modulogestionreportes/registros': 'Gestión de reportes e historial de mantenimiento',
          '/modulogestionreportes/verificacionreporte': 'Gestión de reportes e historial de mantenimiento',
          '/modulogestionreportes/verificaciondatos': 'Gestión de reportes e historial de mantenimiento',
          '/modulogestionreportes/exportaciondatos': 'Gestión de reportes e historial de mantenimiento'
        }
      };
    },
    computed: {
      tituloModulo() {
        // Obtén el título del módulo basado en la ruta actual
        return this.titulos[this.$route.path] || 'Módulo desconocido';
      }
    }
  };
  </script>
  