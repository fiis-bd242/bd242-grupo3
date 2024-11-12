<template>
    <div class="mx-4 my-2 space-y-3 w-full">
        <h3 class="font-extrabold text-3xl">Realizar Registros</h3>
        <form @submit.prevent="console.log(1);" class="space-y-2">
            <h4 class="text-3xl font-semibold">Fecha</h4>
            <div class="flex space-x-2">
                <input type="datetime-local" class="border rounded p-1 border-black">
                <input type="datetime-local" class="border rounded p-1 border-black">
            </div>
            <h4 class="text-3xl font-semibold">Descripcion de la Actividad</h4>
            <textarea class="border p-1 w-full border-black" placeholder="Describa el mantenimiento" name="desc" rows="4"></textarea>
            <h4 class="text-3xl font-semibold">Incidencias</h4>
            <div class="flex space-x-2 w-full items-center">
                <input v-model="messageError" type="text" class="border border-black p-2 w-96" placeholder="Escriba la incidencia presentada">
                <button @click="addErrors" class="rounded-full p-2 text-white font-bold border-black hover:bg-white duration-150 hover:text-black text-xl border-2 block size-12 bg-green-400">+</button>
            </div>
            <div class="flex space-x-2 ">
      <ErrorTags
        v-for="(message, index) in messages"
        :key="index"
        :msg="message"
        @remove="removeMessage(message)"
      />
    </div>

    <h4 class="text-3xl font-semibold">Calificación</h4>

    <div class="flex space-x-2">
      <Estrella
        v-for="(item, index) in 5"
        :key="index"
        @mouseenter="setStar(index)"
        class="text-gray-500 size-8 duration-75"
        :class="{'text-purple-500': index < ranked}"
      />
    </div>

    <div class="flex justify-end items-center w-full space-x-3">
      <button class="bg-purple-500 hover:bg-purple-600 duration-150 rounded-lg px-2 py-1 text-white border border-black">
        Seleccionar Actividad
      </button>
      <button class="bg-green-500 hover:bg-green-600 duration-150 rounded-lg px-2 py-1 text-white border border-black">
        Registrar
      </button>
    </div>
    </form>
  </div>
</template>

<script>
import ErrorTags from '@/components/ErrorTags.vue';
import Estrella from '@/components/icons/StartIcon.vue';

export default {
  components: {
    Estrella,
    ErrorTags,
  },
  data() {
    return {
      ranked: 0,
      messages: [],
      messageError: '',
    };
  },
  methods: {
    setStar(index) {
      this.ranked = index + 1;
    },
    addErrors() {
      // Evitar duplicados
      this.messages = this.messages.filter((message) => message !== this.messageError);
      this.messages.push(this.messageError);
      this.messageError = '';
    },
    removeMessage(message) {
      // Eliminar el mensaje de la lista
      this.messages = this.messages.filter((msg) => msg !== message);
    },
  },
};
</script>