<script setup>
import { ref, computed } from "vue";
import { itemComposable } from "@/composable/itemComposable.js";
import { inventoryComposable } from "@/composable/inventoryComposable.js";
import { transactionComposable } from "@/composable/transactionComposable.js";

const { items, fetchItems } = itemComposable();
const { inventories, error, fetchInventories } = inventoryComposable();
const { createTransaction } = transactionComposable()

const newTransaction = ref({
  item: {
    code: '',
    name: ''
  },
  type: '',
  quantity: '',
  description: ''
});

const nameMatchCode = computed(() => {
  const match = items.value.find(item => item.code.toLowerCase() === newTransaction.value.item.code.toLowerCase());
  if (match) {
    return match.name;
  }
  return '';
});
const registerInventory = async () => {
  try {
    error.value = null;
    if ("ISSUE" == newTransaction.value.type) {
      newTransaction.value.quantity *= -1;
    }
    await createTransaction(newTransaction.value);
  } catch (err) {
    error.value = err.message;
  }
  finally {
    newTransaction.value.item.code = '';
    newTransaction.value.item.name = '';
    newTransaction.value.type = '';
    newTransaction.value.quantity = '';
    newTransaction.value.description = '';
  }
};

fetchItems()
fetchInventories();
</script>
<template>
  <h1>在庫管理</h1>
  <section id="itemPage">
    <form id="itemRegisterForm" @submit.prevent="registerInventory">
      <label for="itemCode">コード</label>
      <input id="itemCodeField" type="text" list="codeDatalist" v-model="newTransaction.item.code">
      <label for="itemName">名称</label>
      <input id="itemNameField" type="text" v-model="nameMatchCode" readonly required>
      <label for="itemSection">区分</label>
      <select id="itemSectionField" v-model="newTransaction.type" required>
        <option value="RECEIPT">入庫</option>
        <option value="ISSUE">出庫</option>
      </select>
      <label for="itemQuantity">数量</label>
      <input id="itemQuantityField" type="number" v-model="newTransaction.quantity" min="0" required>
      <button>登録</button>
    </form>
    <datalist id="codeDatalist">
      <option  v-for="item in items" :key="item.code" :label="item.name">{{ item.code }}
      </option>
    </datalist>
    <hr>
    <div v-if="error" class="error-message">
      {{ error }}
    </div>
    <div>
      <table>
        <thead id="itemListHeader">
        <tr>
          <th>コード</th>
          <th>名称</th>
          <th>在庫数量</th>
        </tr>
        </thead>
        <tbody id="itemList">
        <tr v-for="inventory in inventories" :key="inventory.code">
          <td>{{ inventory.code }}</td>
          <td>{{ inventory.name }}</td>
          <td>{{ inventory.quantity }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </section>
</template>