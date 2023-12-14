<template>
    <div class="container">
        <div class="row my-5">
            <div class="my-3 text-start col-12">
                <button class="btn btn-sm btn-warning" type="button" @click="$emit('back')">Back</button>
            </div>
            <div class="col-12 text-center my-4">
                <h1>Aggiungi una nuova pizza</h1>
            </div>
            <div class="col-6 offset-3 card p-5">
                <form class="fw-bold" @submit.prevent="submit">
                    <div class="form-group my-3">
                        <label for="name" class="d-block">Nome</label>
                        <input type="text" name="name" id="name" class="form-control" v-model="newPizza.name">
                    </div>
                    <div class="form-group my-3">
                        <label for="description">Descrizione</label>
                        <textarea name="description" id="description" class="form-control"  v-model="newPizza.description"/>
                    </div>
                    <div class="form-group my-3">
                        <label for="photo">Foto</label>
                        <input type="text" name="photo" id="photo" class="form-control" v-model="newPizza.photo">
                    </div>
                    <div class="form-group my-3">
                        <label for="price">prezzo</label>
                        <input type="number" id="price" min="0" step="0.01" class="form-control" v-model="newPizza.price">
                    </div>
                    <div class="my-5">
                        <button class="btn btn-sm btn-success" type="submit">Submit</button>
                    </div>
                  
                </form>
            </div>
        </div>
    </div>
</template>
<script setup>
import { defineEmits, ref } from 'vue';
import axios from 'axios';

const emits = defineEmits(["back", "created"]);

const newPizza = ref({
    name: '',
    description: '',
    photo: '',
    price: 0
});

const submit = async()=>{
    const data = await axios.post( "http://localhost:8080/api/v1.0/pizzas", newPizza.value);
    emits("created");
    return;
}

</script>