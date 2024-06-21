package com.example.inventory.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * シングルページアプリケーション用フォールバックコントローラー
 * <p>
 * シングルページアプリケーションは、ページごとにパスを割り当てクライアントサイドで
 * ルーティング（ページ遷移の制御）を行っているが、Webブラウザーのページ更新や
 * 履歴からの選択などの操作により、それらのパスでサーバーサイドにリクエストを送信することがある。
 * その際、シングルページアプリケーションのメインページのHTMLを返すことで、
 * クライアントサイドに制御を戻し、適切なページを表示させるようにする。
 */
@Controller
public class SPAFallbackController {

    @GetMapping("/pages/**")
    public String doFilter() {
        /* シングルページアプリケーションのメインページのHTMLをレスポンスとして返す */
        return "forward:/index.html";
    }

}
