
<template>
<div class="container">
    <div class="row my-5"> 
        <div class="col-6 d-flex">
            <input type="search" id="search" class="form-control" v-model="search">
            <button for="search" class=" btn btn-sm btn-success fw-bold" @click="searchPizza" >cerca </button>
        </div>  
        <div class="col-12 text-center my-5">
            <h1>Pizzas</h1>
        </div>
   
          <div class="d-flex flex-wrap justify-content-center">
            <div class="card col-3 m-2" v-for="pizza in pizzas" 
                :key="pizza.id">
                <div class="card-top">
                   
                    <img :src="pizza.photo" class="card-img-top" width="100%" height="200px" >
                </div>
                <div class="card-body my-3 d-flex flex-column justify-content-between">
                    <h4>{{ pizza.name }}</h4>
                    <p>{{ pizza.description }}</p>

                    <div class="text-end">
                        <span>prezzo: {{ pizza.price }}€</span>
                    </div>
                    <div class="text-center my-3">
                        <button class="btn btn-sm btn-danger" @click="deletePizza(pizza.id)">Cancella</button>
                    </div>
                </div>
            </div>
          </div>
      </div>
  </div>
</template>

<script setup>
import { defineProps, ref } from 'vue';
import axios from 'axios';
const emits = defineEmits(["deletePizza", "search"]);

const deletePizza = async (id) => {
  const data =  await axios.delete(`http://localhost:8080/api/v1.0/pizzas/${id}`);
    emits("deletePizza"); 
  } 
 
  const search = ref('');
const searchPizza = async()=>{
    const data = await axios.get(`http://localhost:8080/api/v1.0/pizzas?name=${search.value}`)

    console.log(data)
    props.pizzas = data.data;
}


const props = defineProps({
  pizzas: {
    type: Array,
    required: true
  }
  

});

</script>