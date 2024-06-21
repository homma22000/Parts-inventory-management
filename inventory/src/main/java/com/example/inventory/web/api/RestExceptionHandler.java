package com.example.inventory.web.api;

import com.example.inventory.service.InventoryQuantityShortageException;
import com.example.inventory.service.ItemCodeDuplicateException;
import com.example.inventory.service.NoSuchItemException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * RestControllerからスローされた例外を補足します。
 */
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * 存在しない部品の指定時に発生した例外をハンドリングする
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseEntity toResponse(NoSuchItemException exception) {
        ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity(
                "/inventory/error/no-such-item",
                exception.getLocalizedMessage(),
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage());
        return errorResponseEntity;
    }

    /**
     * 重複した部品コードの指定時に発生した例外をハンドリングする
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseEntity toResponse(ItemCodeDuplicateException exception) {
        ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity(
                "/inventory/error/item-code-duplicate",
                exception.getLocalizedMessage(),
                HttpStatus.CONFLICT.value(),
                exception.getMessage());
        return errorResponseEntity;
    }

    /**
     * 部品在庫の不足時に発生した例外をハンドリングする
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseEntity toResponse(InventoryQuantityShortageException exception) {
        ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity(
                "/inventory/error/quantity-shortage",
                exception.getLocalizedMessage(),
                HttpStatus.CONFLICT.value(),
                exception.getMessage());
        return errorResponseEntity;
    }

    /**
     * リクエストボディのJSON変換時に発生した例外をハンドリングする
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseEntity handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity(
                "/inventory/error/http-message-not-readable",
                "JSONの型変換エラーが発生しました。",
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage());
        return errorResponseEntity;
    }

    /**
     * パスやクエリパラメータの型変換で発生した例外をハンドリングする
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseEntity handleTypeMismatch(TypeMismatchException ex) {
        ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity(
                "/inventory/error/type-mismatch",
                "引数の型変換エラーが発生しました。",
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage());
        return errorResponseEntity;
    }

    /**
     * バリデーション時に発生した例外をハンドリングする
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        // Requestクラスのフィールドの全検証エラーを取得する
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        // ErrorResponseに、フィールドの全検証エラーを追加
        List<String> errorMessages = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            // エラーが起こったフィールド名と、それに対応するエラーメッセージを追加
            errorMessages.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }
        // 400 Bad Requestでレスポンス
        ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity(
                "/inventory/error/method-argument-not-valid",
                "入力エラー",
                HttpStatus.BAD_REQUEST.value(),
                String.join(", ", errorMessages));
        return errorResponseEntity;
    }

}
