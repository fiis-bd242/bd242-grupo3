<template>
    <div class="w-full mx-auto p-4">
      <h1 class="title"><b>CREACIÓN DE USUARIO</b></h1>
      <form @submit.prevent="guardarEmpleado">
        
        <!-- Nombre -->
        <div class="form-group">
          <label for="nombre">Nombre:</label>
          <input type="text" id="nombre" v-model="empleado.nombre" required class="input-borde-grueso"/>
        </div>
  
        <!-- DNI -->
        <div class="form-group">
          <label for="dni">DNI:</label>
          <input type="text" id="dni" v-model="empleado.dni" required maxlength="8" class="input-borde-grueso"/>
        </div>
  
        <!-- Dirección -->
        <div class="form-group">
          <label for="direccion">Dirección:</label>
          <input type="text" id="direccion" v-model="empleado.direccion" class="input-borde-grueso"/>
        </div>
  
        <!-- Estado Civil -->
        <div class="form-group">
          <label for="estadoCivil">Estado Civil:</label>
          <input type="text" id="estadoCivil" v-model="empleado.estadoCivil" class="input-borde-grueso"/>
        </div>
  
        <!-- Sexo -->
        <div class="form-group">
          <label for="sexo">Sexo:</label>
          <select v-model="empleado.sexo" required class="input-borde-grueso">
            <option value="Masculino">Masculino</option>
            <option value="Femenino">Femenino</option>
            <option value="Otro">Otro</option>
          </select>
        </div>
  
        <!-- Fecha de Nacimiento -->
        <div class="form-group">
          <label for="fechaNacimiento">Fecha de Nacimiento:</label>
          <input type="date" id="fechaNacimiento" v-model="empleado.fechaNacimiento" required class="input-borde-grueso"/>
        </div>
  
        <!-- Fecha de Ingreso -->
        <div class="form-group">
          <label for="fechaIngreso">Fecha de Ingreso:</label>
          <input type="date" id="fechaIngreso" v-model="empleado.fechaIngreso" required class="input-borde-grueso"/>
        </div>
  
        <!-- Número de Contacto -->
        <div class="form-group">
          <label for="numeroContacto">Número de Contacto:</label>
          <input type="text" id="numeroContacto" v-model="empleado.numeroContacto" required class="input-borde-grueso"/>
        </div>
  
        <!-- Email de Contacto -->
        <div class="form-group">
          <label for="emailContacto">Email de Contacto:</label>
          <input type="email" id="emailContacto" v-model="empleado.emailContacto" class="input-borde-grueso"/>
        </div>
  
        <!-- Contraseña -->
        <div class="form-group">
          <label for="contraseña">Contraseña:</label>
          <input type="password" id="contraseña" v-model="empleado.contraseña" required class="input-borde-grueso"/>
        </div>
  
        <!-- Cargo -->
        <div class="form-group">
          <label for="cargo">Cargo:</label>
          <select v-model="empleado.idCargo" required class="input-borde-grueso">
            <option value="1">Jefe</option>
            <option value="2">Técnico</option>
            <option value="3">Supervisor</option>
            <option value="4">Encargado de sistema</option>
          </select>
        </div>
  
        <!-- Rol -->
        <div class="form-group">
          <label for="role">Rol:</label>
          <select v-model="empleado.role" required class="input-borde-grueso">
            <option value="USER">USER</option>
            <option value="ADMIN">ADMIN</option>
          </select>
        </div>
  
        <!-- Botón Guardar -->
        <div class="form-actions">
          <button type="submit" class="save-button">Guardar Empleado</button>
        </div>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  export default {
    data() {
      return {
        empleado: {
          nombre: '',
          dni: '',
          direccion: '',
          estadoCivil: '',
          sexo: 'Masculino',
          fechaNacimiento: '',
          fechaIngreso: '',
          numeroContacto: '',
          emailContacto: '',
          contraseña: '',
          idCargo: 1, 
          role: 'USER',
        },
      };
    },
    methods: {
      async guardarEmpleado() {
        try {
          const response = await axios.post('/api/seguridad/register', this.empleado);
          alert(response.data.message || "Empleado creado exitosamente.");
          this.$router.push({name: "dashboard_admin"}); 
        } catch (error) {
          console.error("Error al guardar el empleado:", error);
          alert("Error al guardar el empleado. Por favor, inténtalo de nuevo.");
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .input-borde-grueso {
    border: 2px solid #000;
    padding: 8px;
    width: 100%;
    margin-bottom: 10px;
    border-radius: 4px;
  }
  
  .save-button {
    background-color: #007bff;
    color: white;
    padding: 10px 15px;
    border: none;
    cursor: pointer;
  }
  
  .save-button:hover {
    background-color: #0056b3;
  }
  </style>
  