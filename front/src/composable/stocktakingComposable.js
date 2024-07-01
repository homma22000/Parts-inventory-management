import { ref } from 'vue';
import { inventoryComposable } from "@/composable/inventoryComposable.js";
const { fetchInventories } = inventoryComposable();

export function itemComposable() {
    const baseUrl = 'http://localhost:8080';
    const stocktakings = ref([]);
    const stocktaking = ref(null);
    const error = ref(null);

    // 在庫取引の複数件登録
    const createStocktakings = async (newStocktakings) => {
        try {
            const response = await fetch(`${baseUrl}/api/stocktakings`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newStocktakings)
            });
            if (response.status === 409) throw new Error('在庫数量不足による登録失敗');
            if (!response.ok) throw new Error(`Error: ${response.status}`);
        } catch (err) {
            error.value = err.message;
            throw err;
        }
        finally{
            fetchInventories();
        }

        return {
            stocktakings,
            stocktaking,
            error,
            createStocktakings
        }
    };
}