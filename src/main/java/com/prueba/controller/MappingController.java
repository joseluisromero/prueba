package com.prueba.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
@Slf4j
public class MappingController {

  @PostMapping("/map")
  public ResponseEntity<?> mapData(@RequestBody Map<String, Object> input) {
    // Lógica para consumir la API externa y mapear los datos
    Map<String, Object> mappedData = consumeAndMapExternalApi(input);
    return ResponseEntity.ok(mappedData);
  }

  private Map<String, Object> consumeAndMapExternalApi(Map<String, Object> input) {
    // Aquí consumirías la API externa y mapearías los datos
    RestTemplate restTemplate = new RestTemplate();
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setConnectTimeout(5000); // Tiempo de espera de conexión en milisegundos
    factory.setReadTimeout(5000); // Tiempo de espera de lectura en milisegundos
    restTemplate.setRequestFactory(factory);
    String apiUrl = "http://localhost:3000/datos-basicos";
    ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
    log.info("Json Map {}", response);
    String path = "data.personName.firstSurname";
    String path2 = "data.personName.firstSurname2";
    otra(response.getBody(), path);
    try {
      JsonNode rootNode = new ObjectMapper().readTree(response.getBody());
      String value = getValueByPath(rootNode, path);
      String value2 = getValueByPath(rootNode, path2);

      if (value != null) {
        System.out.println("Value path-> " + path + ":" + value);
      } else {
        System.out.println("path-> " + path + "Campo no encontrado");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }


    /*Map<String, Object> externalData = response.getBody();
    Map<String, Object> mappedData = new HashMap<>();

    // Ejemplo de mapeo
    mappedData.put("birthDate", externalData.get("birthDate"));
    mappedData.put("firstName", externalData.get("firstName"));*/
    // Agrega más mapeos según sea necesario

    // return mappedData;
    return new HashMap<>();
  }

  public static String getValueByPath(JsonNode rootNode, String path) {
    String[] keys = path.split("\\.");
    JsonNode currentNode = rootNode;
    for (String key : keys) {
      currentNode = currentNode.path(key);
      if (currentNode.isMissingNode()) {
        return null;
      }
    }
    return currentNode.asText();
  }

  public String otra(String body, String path2) {
    String jsonString = "{\"data\":{\"personName\":{\"firstSurname\":\"Doe\", \"firstName\":\"John\"}}}";
    String path = path2;
String value="";
    JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
    JsonElement resultElement = getElementByPath(jsonObject, path);

    if (resultElement != null && !resultElement.isJsonNull()) {
      System.out.println("Value: " + resultElement.getAsString());
      value= resultElement.getAsString();
    } else {
      System.out.println("Campo no encontrado");
    }
    return value;
  }

  public static JsonElement getElementByPath(JsonObject jsonObject, String path) {
    String[] keys = path.split("\\.");
    JsonElement currentElement = jsonObject;
    for (String key : keys) {
      if (currentElement.isJsonObject()) {
        currentElement = currentElement.getAsJsonObject().get(key);
      } else {
        return null;
      }
    }
    return currentElement;
  }
}
