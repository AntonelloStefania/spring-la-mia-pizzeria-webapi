
<template>
  <pizzaIndex :pizzas="pizzas"/>
</template>

<script setup>
import {  onMounted , ref} from 'vue';
import axios from "axios";


import pizzaIndex from './components/pizzaIndex.vue';

    const pizzas = ref(null);


  const updatePizzas = async () => {
  const data = await axios.get("http://localhost:8080/api/v1.0/pizzas");
  pizzas.value = data.data;
};

onMounted(updatePizzas);

</script>