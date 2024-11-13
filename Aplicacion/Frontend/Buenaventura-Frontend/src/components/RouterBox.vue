<script setup>
  defineProps({
    msg: {
      type: String,
      required: true
    },
    description: {
        type: String,
        required: true
    },
    route_name: {
        type: String,
        required: true
    },
    button_name: {
        type: String,
        required: true
    },
    index: {
        type: Int16Array,
        required: true
    },
  })
</script>
<template>
    <div class="border border-black rounded-xl flex flex-col justify-between align-middle space-y-2 m-2 mx-auto size-[275px] p-2">
        <h4 class="font-extrabold text-3xl">{{ msg }}</h4>
        <p class="text-lg">{{description}}</p>
        <router-link :to="{name: route_name}"
            class="block no-underline rounded-xl duration-200 p-1 border border-black font-bold"
            :class="{
     'bg-blue-400 hover:bg-blue-500': index === 0,
     'bg-green-400 hover:bg-green-500': index === 1,
     'bg-red-400 hover:bg-red-500': index === 2,
     'bg-yellow-400 hover:bg-yellow-500': index === 3
   }">Ir a {{button_name}}</router-link>
    </div>    
</template>