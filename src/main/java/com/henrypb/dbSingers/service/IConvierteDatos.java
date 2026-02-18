package com.henrypb.dbSingers.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> tClass);
}
