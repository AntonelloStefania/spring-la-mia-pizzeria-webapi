
<template>
  <div>
    <h1>Pizzas</h1>
    <ul>
      <li v-for="pizza in pizzas" 
        :key="pizza.id">
        <p>{{ pizza.name }}</p>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';  

const props = defineProps({
    pizzas: {
        type: Array,
        required:true
    }

});
</script>