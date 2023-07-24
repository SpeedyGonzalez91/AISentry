package com.example.aivirtualassistant;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;


@Service
public class OpenAIService {

    public String getResponse(String prompt) {
        // Create an HttpClient instance
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Create an HTTP POST request
        String apiUrl = "https://api.openai.com/v1/engines/davinci-codex/completions";
        HttpPost httpPost = new HttpPost(apiUrl);
        String apiKey = "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";
        httpPost.setHeader("Authorization", "Bearer " + apiKey);
        httpPost.setHeader("Content-Type", "application/json");

        try {
            // Create JSON request using Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode requestBody = objectMapper.createObjectNode();
            requestBody.put("prompt", prompt);

            String requestBodyString = objectMapper.writeValueAsString(requestBody);
            httpPost.setEntity(new StringEntity(requestBodyString, ContentType.APPLICATION_JSON));

            // Execute the request
            HttpResponse response = httpClient.execute(httpPost);

            // Get the response body
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);

            // Close the HttpClient
            httpClient.close();

            return responseBody;
        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException("Juan's Exception!");
        }
    }
}







