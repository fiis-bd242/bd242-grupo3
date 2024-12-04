import { defineStore } from "pinia";

export const useEmployeeStore = defineStore({
  id: "employee",
  state: () => ({
    employee: {
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
      this.employee.idEmpleado = localStorage.getItem("employee.idEmpleado");
      this.employee.nombre = localStorage.getItem("employee.nombre");
      this.employee.dni = localStorage.getItem("employee.dni");
      this.employee.direccion = localStorage.getItem("employee.direccion");
      this.employee.estadoCivil = localStorage.getItem("employee.estadoCivil");
      this.employee.sexo = localStorage.getItem("employee.sexo");
      this.employee.fechaNacimiento = localStorage.getItem("employee.fechaNacimiento");
      this.employee.fechaIngreso = localStorage.getItem("employee.fechaIngreso");
      this.employee.numeroContacto = localStorage.getItem("employee.numeroContacto");
      this.employee.emailContacto = localStorage.getItem("employee.emailContacto");
      this.employee.contraseña = localStorage.getItem("employee.contraseña");
      this.employee.idCargo = localStorage.getItem("employee.idCargo");
      this.employee.idAcceso = localStorage.getItem("employee.idAcceso");

      // Verifica si el empleado está autenticado
      if (this.employee.emailContacto) {
        this.employee.isAuthenticated = true;
      }
    },

    // Login y guardar la información del empleado en el store y en localStorage
    login(data) {
      this.employee.idEmpleado = data.idEmpleado;
      this.employee.nombre = data.nombre;
      this.employee.dni = data.dni;
      this.employee.direccion = data.direccion;
      this.employee.estadoCivil = data.estadoCivil;
      this.employee.sexo = data.sexo;
      this.employee.fechaNacimiento = data.fechaNacimiento;
      this.employee.fechaIngreso = data.fechaIngreso;
      this.employee.numeroContacto = data.numeroContacto;
      this.employee.emailContacto = data.emailContacto;
      this.employee.contraseña = data.contraseña;
      this.employee.idCargo = data.idCargo;
      this.employee.idAcceso = data.idAcceso;

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

      this.employee.isAuthenticated = true;
    },

    // Logout y eliminar la información del empleado del store y localStorage
    logout() {
      this.employee.idEmpleado = null;
      this.employee.nombre = null;
      this.employee.dni = null;
      this.employee.direccion = null;
      this.employee.estadoCivil = null;
      this.employee.sexo = null;
      this.employee.fechaNacimiento = null;
      this.employee.fechaIngreso = null;
      this.employee.numeroContacto = null;
      this.employee.emailContacto = null;
      this.employee.contraseña = null;
      this.employee.idCargo = null;
      this.employee.idAcceso = null;
      this.employee.isAuthenticated = false;

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
