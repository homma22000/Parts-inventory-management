// ページを表すクラス
class Page {

    // コンストラクター
    constructor(id, pathPattern, initialState = {}) {
        this.pathPattern = RegExp(pathPattern);
        this.state = initialState;
        this.$element = $('#' + id);    // ページの要素を取得
        this.$element.hide();    // 初期状態で非表示
    }

    // 現在のURLを解析して
    parseURL() {
        const baseURI = new URL(document.baseURI);
        const currentURL = new URL(location.href);
        const relativePath = currentURL.pathname.substr(baseURI.pathname.length);
        return {
            isMatch: this.pathPattern.test(relativePath),        // パターンと一致するか否か（true/false）
            relativePath: relativePath,                            // ドキュメントのbaseURIからの相対パス（文字列）
            pathParams: this.pathPattern.exec(relativePath),    // パスパラメーター（配列）
            searchParams: currentURL.searchParams                // クエリーパラメーター（URLSearchParamsオブジェクト）
        };
    }

    // 現在のURLに対して有効なページか判定する
    isActive() {
        return this.parseURL().isMatch;
    }

    // URLに対応して表示／非表示を切り替える
    correspondToURL(state = {}) {
        if (this.isActive()) {
            const url = this.parseURL();
            this.activate(state, url.pathParams, url.searchParams);
            this.$element.show();
        } else {
            this.$element.hide();
        }
    }

    // ページを有効化する（オーバーライドにて独自処理に書き換える）
    activate(state, pathParams, searchParams) {
        this.state = state;
    }
}

// ページ遷移を制御するクラス
class Router {

    // コンストラクター
    constructor() {
        this.pages = [];    // 管理対象のページを保管する配列
    }

    // ページの登録
    register(page) {
        this.pages.push(page);
        return this;
    }

    // 現在のURLに基づくページ切り替え
    dispatch(state) {
        this.pages.forEach(page => page.correspondToURL(state));
    }

    // ページ制御の開始
    listen(defaultPath = '') {

        // 履歴操作が行われた際に該当するページを表示するようイベントリスナーを設定
        $(window).on('popstate', event => {
            // 現在のURLに基づきページを表示（ページの状態を渡す）
            this.dispatch(event.originalEvent.state);
        });

        // リンクのクリックでページ遷移できるようイベントリスナーを設定
        $('body').on('click', 'a', event => {
            event.preventDefault();
            this.navigate($(event.target).attr('href'));
        });

        // 現在のURLに該当するページがなければデフォルトページのURLを設定
        if (!this.pages.some(page => page.isActive())) {
            history.replaceState(null, '', defaultPath);
        }

        // 現在のURLに基づきページを表示
        this.dispatch();
    }

    // パス指定によるページ遷移
    navigate(relativePath) {

        // 現在表示しているページの状態を保存して遷移先ページのパスに切り替える
        const currentPage = this.pages.find(page => page.isActive());
        const state = (currentPage === null) ? null : currentPage.state;
        history.pushState(state, '', relativePath);

        // ページを切り替える
        this.dispatch();
    }

}