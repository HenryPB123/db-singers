package com.henrypb.dbSingers.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> tClass) {
        try{
            //Elimina comillas inversas y la palabra json que estoy recibiendo de gemini además del json
                        String jsonLimpio = json
                                .replace("```json", "")
                                .replace("```", "")
                                .trim();
            return objectMapper.readValue(jsonLimpio, tClass);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
