package com.example.inventory.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * トランザクションエンティティ
 */
public class Transaction implements Serializable {

    /**
     * ID
     */
    private Integer id;  // 自動採番のため、NotNullアノテーションを削除

    /**
     * 取引日時
     */
    @NotNull
    @PastOrPresent
    private LocalDateTime dateTime;

    /**
     * 部品情報
     */
    @NotNull
    private Item item;

    /**
     * 取引種別
     */
    @NotBlank
    @Size(min = 1)
    private String type;

    /**
     * 取引数量
     */
    @NotNull
    private Integer quantity;

    /**
     * 備考
     */
    @Size(max = 255)
    private String description;

    public Transaction() {
        this.dateTime = LocalDateTime.now(); // デフォルトで現在時刻を設定
    }

    public Transaction(Integer id, LocalDateTime dateTime, Item item, String type, Integer quantity, String description) {
        this.id = id;
        this.dateTime = dateTime != null ? dateTime : LocalDateTime.now(); // デフォルトで現在時刻を設定
        this.item = item;
        this.type = type;
        this.quantity = quantity;
        this.description = description;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transaction other = (Transaction) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (dateTime == null) {
            if (other.dateTime != null)
                return false;
        } else if (!dateTime.equals(other.dateTime))
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Transaction [id=" + id + ", dateTime=" + dateTime + ", item=" + item + ", type=" + type + ", quantity=" + quantity + ", description=" + description + "]";
    }
}
