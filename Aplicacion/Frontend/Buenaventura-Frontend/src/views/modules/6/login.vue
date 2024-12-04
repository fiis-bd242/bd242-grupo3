<template>
  <div class="flex items-center justify-center min-h-screen bg-purple-700 p-4">
    <div class="bg-purple-800 p-8 rounded-lg shadow-xl w-full max-w-md">
      <h1 class="text-white text-2xl font-bold text-center mb-6">Bienvenido de vuelta</h1>
      <form @submit.prevent="login">
        <!-- Usuario -->
        <div class="mb-4">
          <label for="email" class="block text-white font-semibold mb-2">Usuario</label>
          <input
            v-model="email"
            type="email"
            id="email"
            required
            placeholder="Ingrese su usuario"
            class="w-full p-3 bg-purple-600 text-white rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>

        <!-- Contraseña -->
        <div class="mb-6">
          <label for="password" class="block text-white font-semibold mb-2">Contraseña</label>
          <input
            v-model="password"
            type="password"
            id="password"
            required
            placeholder="Ingrese su contraseña"
            class="w-full p-3 bg-purple-600 text-white rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>

        <!-- Error -->
        <p v-if="error" class="text-red-500 text-center mb-4">
          {{ errorMessage }}
        </p>

        <!-- Botón de enviar -->
        <input
          type="submit"
          value="Iniciar sesión"
          class="w-full py-3 bg-blue-600 text-white font-semibold rounded-lg cursor-pointer hover:bg-blue-500 transition"
        />
        
        <!-- Enlace de contraseña olvidada -->
        <p class="text-white text-center mt-4 cursor-pointer hover:underline">¿Has olvidado tu contraseña?</p>
      </form>
    </div>
  </div>
</template>

<script>
import { useEmployeeStore } from "@/stores/user";  // Importar el store ya creado
import { defineComponent } from "vue";
import axios from "axios";

export default defineComponent({
  data() {
    return {
      email: "",
      password: "",
      error: false,
      errorMessage: "",
    };
  },
  computed: {
    error() {
      const employeeStore = useEmployeeStore();
      return employeeStore.error;
    },
    errorMessage() {
      const employeeStore = useEmployeeStore();
      return employeeStore.errorMessage;
    },
  },
  methods: {
    async login() {
      try {
        const response = await axios.post("http://localhost:8080/api/seguridad/login", {
          email: this.email,
          password: this.password,
        });

        const employeeStore = useEmployeeStore();
        employeeStore.login(response.data);

        if (employeeStore.employee.isAuthenticated) {
          this.$router.push("/inicio");  
        }
      } catch (error) {
        console.error(error);
        this.error = true;
        this.errorMessage = error.response?.data?.message || "Error al iniciar sesión. Inténtalo de nuevo.";
      }
    },
  },
});
</script>
