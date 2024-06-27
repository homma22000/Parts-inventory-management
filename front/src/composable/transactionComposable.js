import { ref } from 'vue';
import { inventoryComposable } from "@/composable/inventoryComposable.js";
const { fetchInventories } = inventoryComposable();

export function transactionComposable() {
    const baseUrl = 'http://localhost:8080';
    const transactions = ref([]);
    const transaction = ref(null);
    const error = ref(null);

    // 在庫取引の1件登録
    const createTransaction = async (newTransaction) => {
        try {
            const response = await fetch(`${baseUrl}/api/transactions`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newTransaction)
            });
            if (response.status === 404) throw new Error('指定した部品コードのリソースが存在しない');
            if (response.status === 409) throw new Error('在庫数量不足による登録失敗');
            if (!response.ok) throw new Error(`Error: ${response.status}`);
        } catch (err) {
            error.value = err.message;
            throw err;
        } finally {
            fetchInventories();
        }
    };

    return {
        createTransaction,
    }
}