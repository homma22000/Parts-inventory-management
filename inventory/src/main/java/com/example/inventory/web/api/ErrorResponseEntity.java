package com.example.inventory.web.api;

import java.io.Serializable;
import java.util.Objects;

/**
 * RFC7807 Problem Details for HTTP APIsに基づくエラー・レスポンス
 */
public class ErrorResponseEntity implements Serializable {

    /**
     * エラー種別
     */
    private String type;

    /**
     * 概要説明
     */
    private String title;

    /**
     * ステータスコード
     */
    private Integer status;

    /**
     * 詳細説明
     */
    private String detail;

    public ErrorResponseEntity(String type, String title, Integer status, String detail) {
        this.type = type;
        this.title = title;
        this.status = status;
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponseEntity that = (ErrorResponseEntity) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(title, that.title) &&
                Objects.equals(status, that.status) &&
                Objects.equals(detail, that.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, title, status, detail);
    }

    @Override
    public String toString() {
        return "ErrorResponseEntity{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", detail='" + detail + '\'' +
                '}';
    }
}
