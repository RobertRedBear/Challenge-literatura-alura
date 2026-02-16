package com.ferobt.challengeliteratura.service;

public interface IConvertirDatos {
    <T> T convertirDatos(String json, Class<T> clase);
}
