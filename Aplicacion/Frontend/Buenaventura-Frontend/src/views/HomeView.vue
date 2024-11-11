<script setup>


</script>
<template>
    <main>

      <router-link class="p-3 m-1 blue" :to="{name: a}"></router-link>
      <h1 class="text-5xl">Buenaventura</h1>
      <input class="rounded ring-1 ring-black mx-2 px-2 py-1 w-96" placeholder="El nombre de algun pokemon" type="text" name="mode" id="a" v-model="name_of">
      <button @click="getPokemons(name_of)" class="bg-blue-500 p-1 text-white hover:bg-blue-400 duration-150 rounded-md border border-black">Presioname pa tener datos</button>
      <img :src="image" v-if="image" alt="im">
    </main>
   
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      name_of : "",
      image: ""
    }
  },
  methods: {
  async getPokemons(data){
      await axios.get(`pokemon/${data.toLowerCase()}`)
      .then(response => {
        console.log(response);
        console.log(response.data);
        this.image = response.data.sprites.front_default
      }).catch(error => {
        console.log(error);
      })
  },
  
  },
}

</script>