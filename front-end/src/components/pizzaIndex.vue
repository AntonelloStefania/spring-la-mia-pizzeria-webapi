
<template>
<div class="container">
    <div class="row my-5"> 
        <form class="col-6 d-flex offset-3" @submit.prevent="searchPizza">
            <input type="text" class="form-control form-input" v-model="search" placeholder="Search anything...">
        </form>  
        <div class="col-12 text-center my-5">
            <h1>Pizzas</h1>
        </div>
   
        <div class="d-flex flex-wrap justify-content-center">
            <div class="card col-3 m-2" v-for="pizza in searchPizza" 
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
import { defineProps, ref , computed} from 'vue';
import axios from 'axios';
const emits = defineEmits(["deletePizza"]);

const deletePizza = async (id) => {
  const data =  await axios.delete(`http://localhost:8080/api/v1.0/pizzas/${id}`);
    emits("deletePizza"); 
  } 
 
  const search = ref('');
  const searchPizza = computed(()=>{
    const searched = search.value.toLowerCase().trim();
    return searched ? props.pizzas.filter(pizza=>pizza.name.toLowerCase().includes(searched)) : props.pizzas 

  
});


const props = defineProps({
  pizzas: {
    type: Array,
    required: true
  }
  

});

</script>