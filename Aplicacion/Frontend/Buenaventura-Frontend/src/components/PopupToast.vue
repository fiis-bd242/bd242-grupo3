<script setup>
import { useToastStore } from '@/stores/toast'

const store = useToastStore()
</script>

<template>

    <div v-if="store.isVisible" :class="store.classes"
        class="transition ease-in-out border border-black font-bold  text-white z-50 delay-500 flex items-center space-x-2 duration-500 px-6 py-6 fixed top-full right-8 rounded-xl shadow-xl">
        <span>
            <IconAlert v-if="store.type === 'Alert'" />
            <IconCheck v-if="store.type === 'Check'" />
            <IconWrong v-if="store.type === 'Wrong'" />
            <IconSurprise v-if="store.type === 'Surprise'" />
        </span>
        <span>
            <p>{{ store.message }}</p>
        </span>

    </div>

</template>

<script>
import IconAlert from './icons/AlertIcon.vue';
import IconCheck from './icons/CheckIcon.vue';
import IconWrong from './icons/WrongIcon.vue';
import IconSurprise from './icons/IconSurprise.vue';

export default {
    components: {
        IconAlert,
        IconCheck,
        IconWrong,
        IconSurprise,
    }
}
</script>