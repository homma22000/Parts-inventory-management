class ItemPage extends Page {

    constructor(id, pathPattern) {

        super(id, pathPattern);

        // DOM要素の取得と保存
        this.$itemRegisterForm = $('#itemRegisterForm');
        this.$itemCodeField = $('#itemCodeField');
        this.$itemNameField = $('#itemNameField');
        this.$itemList = $('#itemList');

        // イベントリスナーの設定（フォームの送信）
        this.$itemRegisterForm.on('submit', event => {

            // デフォルトの動作（フォームの送信）を抑止
            event.preventDefault();

            // 入力データの取得
            const code = this.$itemCodeField.val();
            const name = this.$itemNameField.val();

            // サーバー側の登録処理の呼び出し
            this.register(code, name);

            // 入力データのクリア
            this.$itemCodeField.val('');
            this.$itemNameField.val('');

        });

    }

    activate(state, pathParams, searchParams) {
        super.activate(state, pathParams, searchParams);
        this.refresh();
    }

    refresh() {
        $.ajax({
            url: '/api/items',
            method: 'GET',
        }).then(items => {
            let html = '';
            for (const item of items) {
                html += `<tr><td>${item.code}</td><td><a href="pages/items/${item.code}">${item.name}</a></td></tr>`
            }
            this.$itemList.html(html);
        });
    }

    register(code, name) {

        $.ajax({
            url: '/api/items',
            method: 'POST',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify({
                code: code,
                name: name
            })
        }).then(item => {
            popupMessage('info', '登録しました。');
            this.refresh();
        }).catch((jqXHR, textStatus, errorThrown) => {
            const errorType = jqXHR.responseJSON ? jqXHR.responseJSON.type : null;
            if (errorType === '/inventory/error/item-code-duplicate') {
                popupMessage('error', '既に同じコードが存在します。');
            } else {
                popupMessage('error', 'エラーが発生しました。');
            }
        });

    }
}
