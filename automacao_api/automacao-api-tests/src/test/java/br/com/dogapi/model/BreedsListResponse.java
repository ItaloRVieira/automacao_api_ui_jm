package br.com.dogapi.model;

import java.util.List;
import java.util.Map;

public class BreedsListResponse {
    private Map<String, List<String>> message;
    private String status;

    public Map<String, List<String>> getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

}