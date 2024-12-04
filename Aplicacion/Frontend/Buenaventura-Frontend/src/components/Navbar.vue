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
          '/moduloplanificacion/detalle_actividad/:id_actividad': 'Planificación del mantenimiento',
          '/moduloplanificacion/detalle_orden/:id_orden': 'Planificación del mantenimiento',
          '/moduloplanificacion/detalle_plan/:id_plan': 'Planificación del mantenimiento',
          '/moduloplanificacion/edita_plan/:id_plan': 'Planificación del mantenimiento',
          '/moduloplanificacion/edita_orden/:id_orden': 'Planificación del mantenimiento',
          '/moduloplanificacion/edita_actividad/:id_actividad': 'Planificación del mantenimiento',
          //Módulo 2: Control del mantenimiento
          '/modulocontrol/trabajos':'Control de Mantenimiento',
          '/modulocontrol/detalles/0008' : 'Control de Mantenimiento',
          '/modulocontrol/detalles/0010' : 'Control de Mantenimiento',

          //Modulo 5: Gestion de Equipos de Soporte
          '/moduloequiposoporte/dashboard_equipos_soporte': 'Equipos de Soporte',
          '/moduloequiposoporte/ver_inventario': 'Equipos de Soporte',
          '/moduloequiposoporte/gestionar_pedidos': 'Equipos de Soporte',

          //Módulo 6: Administradores de buenaventura
          '/moduloseguridad/dashboard_admin': 'Administracion de buenaventura',
          '/moduloseguridad/crea_usuario': 'Administracion de buenaventura',
          '/moduloseguridad/lista_usuario': 'Administracion de buenaventura',
          '/moduloseguridad/ver_notificaciones': 'Administracion de buenaventura',
          '/moduloseguridad/informe_incidente': 'Administracion de buenaventura',
          '/moduloseguridad/editar_info_roles': 'Administracion de buenaventura',


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
        return this.titulos[this.$route.path] || 'Bienvenido a Buenaventura';
      }
    }
  };
  </script>
  