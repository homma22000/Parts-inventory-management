import { ref } from 'vue';

export function itemComposable() {
    const baseUrl = 'http://localhost:8080';
    const items = ref([]);
    const item = ref(null);
    const error = ref(null);

    // 部品の全件取得
    const fetchItems = async () => {
        try {
            const response = await fetch(`${baseUrl}/api/items`, {
                headers: {'Accept': 'application/json'}
            });
            items.value = await response.json();
            if (!response.ok) throw new Error(`Error: ${response.status}`);
        } catch (err) {
            error.value = err.message;
            throw err;
        }
    };

    // 部品の1件登録
    const createItem = async (newItem) => {
        try {
            const response = await fetch(`${baseUrl}/api/items`, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(newItem)
            });
            if (response.status === 409) throw new Error('部品コード重複による登録失敗');
            if (!response.ok) throw new Error(`Error: ${response.status}`);
        } catch (err) {
            error.value = err.message;
            throw err;
        } finally {
            fetchItems();
        }
    };

    // 部品の1件取得
    const fetchItem = async (code) => {
        try {
            const response = await fetch(`${baseUrl}/api/items/${code}`, {
                headers: {
                    'Accept': 'application/json'
                }
            });
            if (response.status === 404) throw new Error('指定した部品コードのリソースが存在しない');
            if (!response.ok) throw new Error(`Error: ${response.status}`);
            item.value = await response.json();
        } catch (err) {
            error.value = err.message;
            throw err;
        }
    };

    return {
        items,
        error,
        fetchItems,
        fetchItem,
        createItem
    }


}