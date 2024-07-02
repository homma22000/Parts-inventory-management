<script setup>
import { inventoryComposable } from "@/composable/inventoryComposable.js";
import { stocktakingComposable } from "@/composable/stocktakingComposable.js";
import { onMounted, ref } from 'vue';

const { inventories, error, fetchInventories } = inventoryComposable();
const { createStocktakings } = stocktakingComposable();
const transactions = ref([]);

const loadInventories = async () => {
  inventories.value.forEach(inventory => {
    inventory.actualStock = inventory.quantity;
  });
};

const registerInventoryQuantity = async () => {
  try {
    error.value = null;
    transactions.value = [];

    for (const inventory of inventories.value) {
      const newTransaction = {
        item: {
          code: inventory.code,
          name: inventory.name
        },
        type: 'ADJUST',
        quantity: inventory.actualStock - inventory.quantity,
        description: inventory.description
      };
      if (inventory.actualStock !== inventory.quantity || inventory.description != null){
        transactions.value.push(newTransaction);
      }
    }
    await createStocktakings(transactions.value);
  } catch (err) {
    error.value = err.message;
  } finally {
    await fetchInventories();
    await loadInventories();
  }
};

onMounted( async () => {
  await fetchInventories();
  await loadInventories();
});

</script>
<template>
  <body>
  <h1>棚卸</h1>
  <form id="inventoryRegisterForm" @submit.prevent = "registerInventoryQuantity">
    <button>登録</button>
    <hr />
    <div v-if="error" class="error-message">
      {{ error }}
    </div>
    <div>
      <table>
        <thead>
        <tr>
          <th>コード</th>
          <th>名称</th>
          <th>論理在庫数量</th>
          <th>実在庫数量</th>
          <th>差分</th>
          <th>備考</th>
        </tr>
        </thead>
        <tr v-for="inventory in inventories" :key="inventory.code">
          <td>{{ inventory.code }}</td>
          <td>{{ inventory.name }}</td>
          <td>{{ inventory.quantity }}</td>
          <td><input type="number" name="actualStock" min="0" v-model="inventory.actualStock"></td>
          <td >{{ inventory.actualStock - inventory.quantity }}</td>
          <td><input type="text" name="remarks" v-model="inventory.description" :required="inventory.actualStock !== inventory.quantity"></td>
        </tr>
      </table>
    </div>
  </form>
  </body>
</template>