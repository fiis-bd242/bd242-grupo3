<script setup>
defineProps({
    msg : {
        type: String,
        required :true
    },
    
})
const emit = defineEmits(['remove']);


</script>
<template>
    <div 
        @click="emit('remove')"
        class="bg-purple-500 px-2 py-1 cursor-pointer rounded-lg border hover:bg-red-500 duration-150 border-black text-white text-sm font-bold">{{ msg }}</div>    
</template>