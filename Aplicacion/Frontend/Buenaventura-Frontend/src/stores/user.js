import { defineStore } from "pinia";

export const useUserStore = defineStore({
  id: "employee",
  state: () => ({
    user: {
      isAuthenticated: false,
      idEmpleado: null,
      nombre: null,
      dni: null,
      direccion: null,
      estadoCivil: null,
      sexo: null,
      fechaNacimiento: null,
      fechaIngreso: null,
      numeroContacto: null,
      emailContacto: null,
      contraseña: null,
      idCargo: null,
      idAcceso: null,
    },
  }), 

  actions: {
    // Inicializar el store con los datos de localStorage
    initStore() {
      this.user.idEmpleado = localStorage.getItem("employee.idEmpleado");
      this.user.nombre = localStorage.getItem("employee.nombre");
      this.user.dni = localStorage.getItem("employee.dni");
      this.user.direccion = localStorage.getItem("employee.direccion");
      this.user.estadoCivil = localStorage.getItem("employee.estadoCivil");
      this.user.sexo = localStorage.getItem("employee.sexo");
      this.user.fechaNacimiento = localStorage.getItem("employee.fechaNacimiento");
      this.user.fechaIngreso = localStorage.getItem("employee.fechaIngreso");
      this.user.numeroContacto = localStorage.getItem("employee.numeroContacto");
      this.user.emailContacto = localStorage.getItem("employee.emailContacto");
      this.user.contraseña = localStorage.getItem("employee.contraseña");
      this.user.idCargo = localStorage.getItem("employee.idCargo");
      this.user.idAcceso = localStorage.getItem("employee.idAcceso");

      // Verifica si el empleado está autenticado
      if (this.user.emailContacto) {
        this.user.isAuthenticated = true;
      }
    },

    // Login y guardar la información del empleado en el store y en localStorage
    login(data) {
      this.user.idEmpleado = data.idEmpleado;
      this.user.nombre = data.nombre;
      this.user.dni = data.dni;
      this.user.direccion = data.direccion;
      this.user.estadoCivil = data.estadoCivil;
      this.user.sexo = data.sexo;
      this.user.fechaNacimiento = data.fechaNacimiento;
      this.user.fechaIngreso = data.fechaIngreso;
      this.user.numeroContacto = data.numeroContacto;
      this.user.emailContacto = data.emailContacto;
      this.user.contraseña = data.contraseña;
      this.user.idCargo = data.idCargo;
      this.user.idAcceso = data.idAcceso;

      // Guardar en localStorage
      localStorage.setItem("employee.idEmpleado", data.idEmpleado);
      localStorage.setItem("employee.nombre", data.nombre);
      localStorage.setItem("employee.dni", data.dni);
      localStorage.setItem("employee.direccion", data.direccion);
      localStorage.setItem("employee.estadoCivil", data.estadoCivil);
      localStorage.setItem("employee.sexo", data.sexo);
      localStorage.setItem("employee.fechaNacimiento", data.fechaNacimiento);
      localStorage.setItem("employee.fechaIngreso", data.fechaIngreso);
      localStorage.setItem("employee.numeroContacto", data.numeroContacto);
      localStorage.setItem("employee.emailContacto", data.emailContacto);
      localStorage.setItem("employee.contraseña", data.contraseña);
      localStorage.setItem("employee.idCargo", data.idCargo);
      localStorage.setItem("employee.idAcceso", data.idAcceso);

      this.user.isAuthenticated = true;
    },

    // Logout y eliminar la información del empleado del store y localStorage
    logout() {
      this.user.idEmpleado = null;
      this.user.nombre = null;
      this.user.dni = null;
      this.user.direccion = null;
      this.user.estadoCivil = null;
      this.user.sexo = null;
      this.user.fechaNacimiento = null;
      this.user.fechaIngreso = null;
      this.user.numeroContacto = null;
      this.user.emailContacto = null;
      this.user.contraseña = null;
      this.user.idCargo = null;
      this.user.idAcceso = null;
      this.user.isAuthenticated = false;

      // Eliminar datos de localStorage
      localStorage.removeItem("employee.idEmpleado");
      localStorage.removeItem("employee.nombre");
      localStorage.removeItem("employee.dni");
      localStorage.removeItem("employee.direccion");
      localStorage.removeItem("employee.estadoCivil");
      localStorage.removeItem("employee.sexo");
      localStorage.removeItem("employee.fechaNacimiento");
      localStorage.removeItem("employee.fechaIngreso");
      localStorage.removeItem("employee.numeroContacto");
      localStorage.removeItem("employee.emailContacto");
      localStorage.removeItem("employee.contraseña");
      localStorage.removeItem("employee.idCargo");
      localStorage.removeItem("employee.idAcceso");
    },
  },
});
