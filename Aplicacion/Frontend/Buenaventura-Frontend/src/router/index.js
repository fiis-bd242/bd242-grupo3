import { createRouter, createWebHistory } from 'vue-router';

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
      name: 'historial',
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
      name: 'reporte',
      component: () => import("../views/modules/7/verificacion_reporte.vue")
    },
    {
      path: '/modulogestionreportes/verificaciondatos',
      name: 'sup_datos',
      component: () => import("../views/modules/7/verificacion_datos.vue")
    },
    {
      path: '/modulogestionreportes/exportaciondatos',
      name: 'exportaciondatos',
      component: () => import("../views/modules/7/exportacion_datos.vue")
    },
  ]
})

export default router;
