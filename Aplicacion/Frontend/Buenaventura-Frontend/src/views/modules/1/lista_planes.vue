<template>
    <div>
      <h1>Lista de Planes de Mantenimiento</h1>
      <table border="1">
        <thead>
          <tr>
            <th>ID Plan</th>
            <th>ID Máquina</th>
            <th>Tipo de Mantenimiento</th>
            <th>Nivel de Criticidad</th>
            <th>Fecha de Inicio Programado</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in dataList" :key="index">
            <td>{{ item.id_plan }}</td>
            <td>{{ item.id_maquina }}</td>
            <td>{{ item.nombre_tipo_mant }}</td>
            <td>{{ item.nivel }}</td>
            <td>{{ item.fecha_inicio_programado }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        dataList: [],
        offset: 1 // Valor de inicio para el desplazamiento
      };
    },
    methods: {
      async fetchData() {
        try {
          const response = await axios.get(`http://localhost:8080/api/planificacion/listaplanes/1?offset=${this.offset}`);
          this.dataList = response.data;
        } catch (error) {
          console.error("Error al obtener los datos:", error);
        }
      }
    },
    mounted() {
      this.fetchData();
    }
  };
  </script>
  
  <style scoped>
  table {
    width: 100%;
    border-collapse: collapse;
  }
  th, td {
    padding: 8px;
    text-align: left;
  }
  th {
    background-color: #f2f2f2;
  }
  </style>
  