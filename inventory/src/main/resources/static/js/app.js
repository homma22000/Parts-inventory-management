$(() => {

    // ポップアップメッセージを表示する関数
    const $dialog = $('#messageDialog');
    window.popupMessage = (type, message) => {

        // クラス属性の値を一旦初期化する
        $dialog.removeClass('info');
        $dialog.removeClass('error');

        // 表示内容を設定しダイアログを開く
        $dialog.addClass(type);
        $dialog.html(`<p>${message}</p>`);
        $dialog.prop('open', true);

        // 一定時間後にダイアログを閉じるようにタイマーを設定
        window.setTimeout(() => {
            $dialog.prop('open', false);
        }, 3000);
    }

    // ルーターをセットアップしてグローバル変数として公開する
    window.router = new Router()
        .register(new InventoryPage('inventoryPage', /pages\/inventories$/))
        .register(new ItemDetailPage('itemDetailPage', /pages\/items\/(.*?)$/))
        .register(new ItemPage('itemPage', /pages\/items$/))
        .register(new StocktakingPage('stocktakingPage', /pages\/stocktaking$/))
        .listen('pages/inventories');

});
