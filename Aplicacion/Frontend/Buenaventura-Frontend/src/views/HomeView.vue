<template>
  <main class="flex flex-col w-full items-center justify-center min-h-screen bg-gray-100">
    <header class="text-center mb-8">
      <h1 class="text-6xl font-bold text-purple-600">Buenaventura</h1>
      <p class="text-xl text-gray-700 mt-4">
        Líder en innovación y desarrollo sostenible.
      </p>
    </header>

    <!-- Call to Action Section -->
    <section class="text-center">
      <p class="text-lg text-gray-600 mb-6">
        Comprometidos con el progreso de las comunidades y el cuidado del medio ambiente.
      </p>
      <router-link
        to="/about"
        class="bg-purple-500 text-white px-6 py-2 rounded-md shadow-md hover:bg-purple-400 transition duration-150"
      >
        Conócenos más
      </router-link>
    </section>
  </main>
</template>

<script>
export default {
  name: "Home",
};
</script>

<style scoped>
main {
  font-family: 'Arial', sans-serif;
}
</style>
