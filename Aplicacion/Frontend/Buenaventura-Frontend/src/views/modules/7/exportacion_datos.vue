<template>
    <div class="mx-4 my-2 w-full">
        <h3 class="font-extrabold text-3xl">Exportación de Datos</h3>
        <form @submit.prevent="console.log('1');" class="flex space-x-2 justify-end mx-2 mb-5">
            <input type="datetime-local" class="px-2 py-1 rounded ring-[2px] outline-none  ring-black duration-150 focus:ring-purple-400">
            <input type="datetime-local" class="px-2 py-1 rounded ring-[2px] outline-none  ring-black duration-150 focus:ring-purple-400">
        </form>
        <div class="rounded flex items-center w-full justify-between">
            <div class=" border w-1/3 m-1 border-black  p-2">
                <div class="flex items-center w-full justify-between">
                    <p class="text-xl font-bold">Excel</p>
                    <span class="bg-red-600 rounded-full size-8 border border-black "></span>
                </div>
                <svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="1"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-file-spreadsheet mx-auto size-48"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M14 3v4a1 1 0 0 0 1 1h4" /><path d="M17 21h-10a2 2 0 0 1 -2 -2v-14a2 2 0 0 1 2 -2h7l5 5v11a2 2 0 0 1 -2 2z" /><path d="M8 11h8v7h-8z" /><path d="M8 15h8" /><path d="M11 11v7" /></svg>
            </div>
            <div class=" border w-1/3 m-1 border-black  p-2">
                <div class="flex items-center w-full justify-between">
                    <p class="text-xl font-bold">PDF</p>
                    <span class="bg-red-600 rounded-full size-8 border border-black "></span>
                </div>
                <svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-pdf mx-auto size-48"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M10 8v8h2a2 2 0 0 0 2 -2v-4a2 2 0 0 0 -2 -2h-2z" /><path d="M3 12h2a2 2 0 1 0 0 -4h-2v8" /><path d="M17 12h3" /><path d="M21 8h-4v8" /></svg>            </div>
            <div class=" border w-1/3 m-1 border-black  p-2">
                <div class="flex items-center w-full justify-between">
                    <p class="text-xl font-bold">JSON</p>
                    <span class="bg-red-600 rounded-full size-8 border border-black "></span>
                </div>
                <svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-json mx-auto size-48"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M20 16v-8l3 8v-8" /><path d="M15 8a2 2 0 0 1 2 2v4a2 2 0 1 1 -4 0v-4a2 2 0 0 1 2 -2z" /><path d="M1 8h3v6.5a1.5 1.5 0 0 1 -3 0v-.5" /><path d="M7 15a1 1 0 0 0 1 1h1a1 1 0 0 0 1 -1v-2a1 1 0 0 0 -1 -1h-1a1 1 0 0 1 -1 -1v-2a1 1 0 0 1 1 -1h1a1 1 0 0 1 1 1" /></svg>            </div>
        </div>
        <div class="flex w-full m-3 px-4 justify-between">
            <select class="text-sm font-bold" name="" id="">
                <option value="None">Otros Formatos</option>
                <option value="SQL">SQL</option>
                <option value="CSV">CSV</option>
                <option value="Word">Word</option>
            </select>
            <button class="bg-green-500 px-2 py-1 rounded border border-black text-white text-sm">Exportar Datos</button>
        </div>
        
    </div>
    
</template>