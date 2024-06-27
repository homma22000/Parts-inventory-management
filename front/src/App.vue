<script setup>
import { RouterView } from 'vue-router'
import Header from "@/components/Header.vue";
</script>

<template>
  <Header />
  <RouterView />
</template>

<style>
table, td, th {
  border-collapse: collapse;
  border: 1px solid #999999;
}

input:read-only {
  outline: none;
  border: none;
}

.quantity {
  text-align: right;
}

.short {
  color: red;
  font-weight: bolder;
}

.error-message {
  color: #721c24;
  background-color: #f8d7da;
  border: 1px solid #f5c6cb;
  padding: 1rem;
  border-radius: .25rem;
  margin: 1rem 0;
  max-width: 400px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  text-align: left;
  margin-left: 0;
  width: auto;
}

#messageDialog {
  border: 1px solid transparent;
  border-radius: .25rem;
  height: auto;
}

#messageDialog.info {
  color: #004085;
  background-color: #cce5ff;
  border-color: #b8daff;
}

#messageDialog.error {
  color: #721c24;
  background-color: #f8d7da;
  border-color: #f5c6cb;
}

#messageDialog > p {
  margin-block-start: 0px;
  margin-block-end: 0px;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
}

nav#mainMenu {
  display: flex;
  gap: 1rem;
  margin-top: 1rem;
}

#itemNameField {
  width: 30%;
  box-sizing: border-box;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>>
