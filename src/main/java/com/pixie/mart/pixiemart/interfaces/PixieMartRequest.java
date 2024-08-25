package com.pixie.mart.pixiemart.interfaces;

public interface PixieMartRequest<T> {
    T create();

    T update(T data);
}
