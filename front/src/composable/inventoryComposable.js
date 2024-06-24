import { ref } from 'vue';

export function inventoryComposable() {
    const baseUrl = 'http://localhost:8080';
    const inventories = ref([]);
    const error = ref(null);

    // 在庫の全件取得
    const fetchInventories = async () => {
        try {
            const response = await fetch(`${baseUrl}/api/inventories`, {
                headers: {
                    'Accept': 'application/json'
                }
            });
            if (!response.ok) throw new Error(`Error: ${response.status}`);
            inventories.value = await response.json();
        } catch (err) {
            error.value = err.message;
            throw err;
        }
    };

    return {
        inventories,
        error,
        fetchInventories
    }
}