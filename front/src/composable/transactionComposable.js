import { ref } from 'vue';
import { inventoryComposable } from "@/composable/inventoryComposable.js";
const { fetchInventories } = inventoryComposable();

export function transactionComposable() {
    const baseUrl = 'http://localhost:8080';
    const transactions = ref([]);
    const transaction = ref(null);
    const error = ref(null);

    // 在庫取引一覧の取得
    const fetchTransactions = async (itemCode) => {
        try {
            const url = new URL(`${baseUrl}/api/transactions`);
            if (itemCode) {
                url.searchParams.append('itemCode', itemCode);
            }
            const response = await fetch(url, {
                headers: {
                    'Accept': 'application/json'
                }
            });
            if (!response.ok) throw new Error(`Error: ${response.status}`);
            transactions.value = await response.json();
        } catch (err) {
            error.value = err.message;
            throw err;
        }
    };


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
        transactions,
        fetchTransactions,
        createTransaction
    }
}