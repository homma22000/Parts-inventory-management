<script setup>
import { itemComposable } from "@/composable/itemComposable.js";
import { ref } from "vue";

const { items, error, fetchItems, createItem } = itemComposable();
let codeRef = ref('')
let nameRef = ref('')

fetchItems();

const registerParts = async () => {
  const code = codeRef.value;
  const name = nameRef.value;
  try {
    error.value = null;
    await createItem({ code, name });
  } catch (err) {
    error.value = err.message;
  }
  finally {
    codeRef.value = "";
    nameRef.value = "";
  }
};

</script>
<template>
  <h1>部品管理</h1>
  <form id="itemRegisterForm" @submit.prevent="registerParts">
    <label for="itemCodeField">コード</label>
    <input id="itemCodeField" type="text" v-model="codeRef">
    <label for="itemNameField">名称</label>
    <input id="itemNameField" type="text" v-model="nameRef">
    <button>登録</button>
  </form>
  <hr>
  <div v-if="error" class="error-message">
    {{ error }}
  </div>
  <div>
    <table>
      <thead>
      <tr>
        <th>コード</th>
        <th>名称</th>
      </tr>
      </thead>
      <tbody id="itemList">
      <tr v-for="item in items" :key="item.code">
        <td>{{ item.code }}</td>
        <td>{{ item.name }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>
