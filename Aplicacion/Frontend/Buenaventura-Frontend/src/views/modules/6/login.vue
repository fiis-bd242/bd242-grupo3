<template>
    <div class="login w-full mx-auto p-4">
      <h1 class="title">Bienvenido de vuelta</h1>
      <form class="form" @submit.prevent="login">
        <label class="form-label" for="email">Usuario</label>
        <input
          v-model="email"
          class="form-input"
          type="email"
          id="email"
          required
          placeholder="Ingrese su usuario"
        />
        <label class="form-label" for="password">Contraseña</label>
        <input
          v-model="password"
          class="form-input"
          type="password"
          id="password"
          required
          placeholder="Ingrese su contraseña"
        />
        <p v-if="error" class="error">
          {{ errorMessage }}
        </p>
        <input class="form-submit" type="submit" value="Iniciar sesión" />
        <p class="forgot-password">¿Has olvidado tu contraseña?</p>
      </form>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        email: "",
        password: "",
        error: false,
        errorMessage: "",
      };
    },
    methods: {
      async login() {
        try {
          const response = await axios.post("http://localhost:5173/api/login", {
            email: this.email,
            password: this.password,
          });
  
          alert("Login exitoso");
        } catch (error) {
          console.error(error);
          this.error = true;
          if (error.response && error.response.data && error.response.data.message) {
            this.errorMessage = error.response.data.message;
          } else {
            this.errorMessage = "Error al iniciar sesión. Inténtalo de nuevo.";
          }
        }
      },
    },
  };
  </script>
  
  <style lang="scss" scoped>
  /* Estilo del contenedor principal */
  .login {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    min-height: 100vh;
    background-color: #4a3c8c; /* Color púrpura de fondo */
    padding: 2rem;
  }
  
  /* Título */
  .title {
    text-align: center;
    color: white;
    font-size: 2rem;
    font-weight: bold;
    margin-bottom: 2rem;
  }
  
  /* Estilo del formulario */
  .form {
    background-color: #5036a2; /* Fondo del formulario */
    border-radius: 10px;
    padding: 2rem;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5);
    width: 100%;
    max-width: 400px;
  }
  
  /* Estilo de las etiquetas */
  .form-label {
    color: white;
    font-weight: bold;
    margin-bottom: 0.5rem;
  }
  
  /* Campos de entrada */
  .form-input {
    width: 100%;
    padding: 10px 15px;
    margin-bottom: 1rem;
    border: none;
    border-radius: 5px;
    background-color: #342d63; /* Fondo oscuro */
    color: white;
    font-size: 1rem;
    outline: none;
    transition: border-color 0.3s;
  }
  
  .form-input::placeholder {
    color: #a29bc1; /* Texto del placeholder */
  }
  
  .form-input:focus {
    border: 2px solid #2b94c4; /* Borde azul al enfoque */
  }
  
  /* Botón de envío */
  .form-submit {
    width: 100%;
    padding: 10px;
    background-color: #1e1ed6; /* Azul oscuro */
    color: white;
    font-size: 1rem;
    font-weight: bold;
    text-transform: uppercase;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  
  .form-submit:hover {
    background-color: #0f0fad; /* Azul más intenso al pasar el mouse */
  }
  
  /* Mensaje de error */
  .error {
    color: red;
    font-size: 0.9rem;
    margin-bottom: 1rem;
    text-align: center;
  }
  
  /* Enlace de contraseña olvidada */
  .forgot-password {
    color: white;
    text-align: center;
    margin-top: 1rem;
    font-size: 0.9rem;
    cursor: pointer;
  }
  
  .forgot-password:hover {
    text-decoration: underline;
  }
  </style>
  