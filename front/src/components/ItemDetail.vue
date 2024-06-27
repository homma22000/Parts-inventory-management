<script setup>
import { computed } from 'vue';
import { useRoute } from 'vue-router';
import { itemComposable } from "@/composable/itemComposable.js";
import { transactionComposable } from "@/composable/transactionComposable.js";

const { item, error, fetchItem } = itemComposable();
const { transactions, fetchTransactions } = transactionComposable();

const route = useRoute();
const itemCode = route.params.code;

fetchItem(itemCode);
fetchTransactions(itemCode);

const sortedTransactions = computed(() => {
  return transactions.value.slice().sort((a, b) => new Date(b.dateTime) - new Date(a.dateTime));
});
</script>
<template>
  <div>
    <main>
      <section id="itemDetailPage">
        <div v-if="error" class="error-message">
          {{ error }}
        </div>
        <div v-if="item && item.code && item.name">
          <h2>{{ item.code }}：{{ item.name }}</h2>
        </div>
        <div>
          <table v-if="transactions && transactions.length > 0">
            <thead>
            <tr>
              <th>ID</th>
              <th>区分</th>
              <th>日時</th>
              <th>数量</th>
              <th>備考</th>
            </tr>
            </thead>
            <tbody v-for="transaction in sortedTransactions" :key="transaction.id">
            <tr>
              <td></td>
              <td></td>
              <td>{{ transaction.dateTime }}</td>
              <td>{{ transaction.quantity }}</td>
              <td>{{ transaction.description }}</td>
            </tr>
            </tbody>
          </table>
          <div v-else>
            <p>取引情報がありません。</p>
          </div>
        </div>
      </section>
    </main>
    <footer></footer>
  </div>
</template>