import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import("../views/HomeView.vue")
    },
    {
      path: '/casa/',
      name: 'casa',
      component: () => import("../views/hola.vue")
    },
    {
      path: '/modulogestionreportes/historial',
      name: 'histoailad',
      component: () => import("../views/modules/7/historial_mantenimiento.vue")
    },
    {
      path: '/modulogestionreportes/dash',
      name: 'dash_perso',
      component: () => import("../views/modules/7/dashboard_personal.vue")
    },
    {
      path: '/modulogestionreportes/registros',
      name: 'registros',
      component: () => import("../views/modules/7/realizar_registros.vue")
    },
    {
      path: '/modulogestionreportes/verificacionreporte',
      name: 'registros',
      component: () => import("../views/modules/7/verificacion_reporte.vue")
    },
    {
      path: '/moduloplanificacion/listaplanes',
      name: 'listaplanes',
      component: () => import("../views/modules/1/lista_planes.vue")
    }
  ]
})

// Cambia el título de la página al navegar
router.afterEach((to) => {
  document.title = to.name || 'Mi Aplicación'; // Coloca un título por defecto si no hay uno definido
});

export default router
