<template>
  <main>
    <HelloWorld />
    <HelloWorld />
    <HelloWorld />
    <HelloWorld />
    <HelloWorld />
    <router-link class="p-3 m-1 blue" :to="{name: a}"></router-link>
    <img src="../assets/images/buenaventura.png" alt="ba">
    <h1 class="text-5xl">Buenaventura</h1>
    <input class="rounded ring-1 ring-black mx-2 px-1" type="text" name="mode" id="a" v-model="name_of">
    <button @click="getPokemons(name_of)">Presioname pa tener datos</button>
    <img :src="image" v-if="image" alt="im">
  </main>
</template>

<script>
import axios from 'axios';
import HelloWorld from '@/components/HelloWorld.vue';

export default {
  data() {
    return {
      name_of : "",
      image: ""
    }
  },
  components: {

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