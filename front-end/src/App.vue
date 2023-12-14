
<template>
  <div class="container">
    <div class="row">
      <div class="col-12 text-center">

        
        <pizzaForm
          v-if="creatingPizza"
          @back="creatingPizza = false" 
          @created="pizzaCreated"
        />
        <div v-else>
          <div class="col-12 text-end my-5">
            <button @click="creatingPizza = true" class="btn btn-sm btn-primary">Aggiungi una nuova pizza</button>
          </div>
          <pizzaIndex :pizzas="pizzas" @deletePizza="deletePizza" @search="search = true" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {  onMounted , ref} from 'vue';
import axios from "axios";


import pizzaIndex from './components/pizzaIndex.vue';
import pizzaForm from './components/pizzaForm.vue';

    const pizzas = ref(null);
    const creatingPizza = ref(false);
    const search = ref(true)


  const indexPizzas = async () => {
    const data = await axios.get("http://localhost:8080/api/v1.0/pizzas");
    pizzas.value = data.data;
 
 
};

const pizzaCreated = () =>{
  creatingPizza.value = false;
  indexPizzas();
}

const deletePizza = () =>{
  indexPizzas();
}




onMounted(indexPizzas);

</script>