package com.henrypb.dbSingers.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import io.github.cdimascio.dotenv.Dotenv;

public class ConsultarGemini {

    // Prompt base para indicar la tarea
//    private static final String PROMPT_BASE = """
//Proporciona información breve y clara del cantante: %s.
//
//Responde únicamente con los siguientes datos:
//- Nombre completo
//- Fecha de nacimiento (y fecha de fallecimiento solo si aplica)
//- Nacionalidad
//- Tres canciones más representativas
//
//Formato de respuesta:
//Nombre:
//Nacimiento:
//Fallecimiento:
//Nacionalidad:
//Canciones principales:
//1.
//2.
//3.
//
//No agregues explicaciones adicionales ni texto extra.
//""";

    private static final String PROMPT_BASE = """
            Devuelve información del cantante "%s" exclusivamente en formato JSON válido.
            
            Estructura exacta requerida:
            {
              "nombre": "string",
              "nacimiento": "YYYY-MM-DD",
              "nacionalidad": "string",
              "genero": "string",
              "biografia": "string (máximo 240 caracteres)",
              "canciones": [
                {
                  "titulo": "string",
                  "fechaLanzamiento": "YYYY-MM-DD o null"
                }
              ]
            }
            
            Reglas:
            - El arreglo "canciones" debe contener exactamente 3 elementos.
            - Si una fecha no es conocida, usa null.
            - No incluyas comentarios, explicaciones ni texto fuera del JSON.
            - Usa siempre comillas dobles.
            """;


    // Cargar .env
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("./") // Ajusta esto si tu .env no está en la raíz
            .filename(".env")
            .load();

    public static String obtenerInfoCantante(String nombreCantante) {
        // 1. Obtener la API KEY de Gemini (Asegúrate de cambiar el nombre en tu archivo .env)
        String apiKey = dotenv.get("GEMINI_API_KEY");

        // Validación simple
        if (apiKey == null || apiKey.isEmpty()) {
            throw new RuntimeException("La variable de entorno GEMINI_API_KEY no está configurada.");
        }

        // 2. Configurar el cliente de Gemini
        // Usamos el modelo "gemini-1.5-flash" porque es el más rápido y entra en la capa gratuita.
        ChatLanguageModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-2.5-flash")
                .temperature(0.3) // Baja temperatura para traducciones más literales y precisas
                .build();

        // 3. Construir el prompt
        String prompt = PROMPT_BASE.formatted(nombreCantante);

        // System.out.println("PROMPT A ENVIAR ==> " + prompt); // Descomentar para debug

        try {
            // 4. Llamada a la API (LangChain4j maneja la conexión HTTP y el JSON por ti)
            String respuesta = model.generate(prompt);

            // System.out.println("RESPUESTA GEMINI ==> " + respuesta); // Descomentar para debug

            return respuesta;

        } catch (Exception e) {
            System.err.println("Error al conectar con Gemini: " + e.getMessage());
            return "No encontre información de: " + nombreCantante; // Retorna el original en caso de fallo para no romper la UI
        }
    }
}

