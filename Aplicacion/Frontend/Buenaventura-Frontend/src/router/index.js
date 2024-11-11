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
      path: '/modulogestionreportes/dash/personal',
      name: 'dash_perso',
      component: () => import("../views/modules/7/dashboard_personal.vue")
    },

  ]
})

export default router
