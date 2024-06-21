import { createRouter, createWebHistory } from 'vue-router'
import InventoryManagement from "@/components/InventoryManagement.vue";
import PartsManagement from "@/components/PartsManagement.vue";
import ItemDetail from "@/components/ItemDetail.vue";
import InventoryDetail from "@/components/InventoryDetail.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: InventoryManagement
    },
    {
      path: '/pages/inventories',
      name: 'inventories',
      component: InventoryManagement
    },
    {
      path: '/pages/parts',
      name: 'parts',
      component: PartsManagement
    },
    {
      path: '/pages/stocktaking',
      name: 'stocktaking',
      component: InventoryDetail
    },
    {
      path: '/pages/items/:code',
      name: 'item-detail',
      component: ItemDetail
    }
  ]
})

export default router
