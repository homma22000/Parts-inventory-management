<script setup>
import { inventoryComposable } from "@/composable/inventoryComposable.js";

const { inventories, error, fetchInventories } = inventoryComposable();

fetchInventories();
</script>
<template>
  <h1>在庫管理</h1>
  <section id="itemPage">
    <form id="itemRegisterForm" >
      <label for="itemCode">コード</label>
      <input id="itemCodeField" type="text" >
      <label for="itemName">名称</label>
      <input id="itemNameField" type="text" readonly required>
      <label for="itemSection">区分</label>
      <select id="itemSectionField" required>
        <option value="RECEIPT">入庫</option>
        <option value="ISSUE">出庫</option>
      </select>
      <label for="itemQuantity">数量</label>
      <input id="itemQuantityField" type="number" required>
      <button>登録</button>
    </form>
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