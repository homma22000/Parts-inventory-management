import { ref } from 'vue';

export function itemComposable() {
    const baseUrl = 'http://localhost:8080';
    const items = ref([]);
    const item = ref(null);
    const error = ref(null);

    // 部品の全件取得
    const fetchItems = async () => {
        try {
            const responce = await fetch(`${baseUrl}/api/items`, {
                headers: {'Accept': 'application/json'}
            });
            items.value = await response.json();
            if (!response.ok) throw new Error(`Error: ${response.status}`);
        } catch (err) {
            error.value = err.message;
            throw err;
        }
    };
    return {
        items,
        fetchItems
    }
}