package com.example.beautyconnectapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GeoRefService {
    private static final String GEOREF_BASE_URL = "https://apis.datos.gob.ar/georef/api";

    @Autowired
    private RestTemplate restTemplate;

    @Cacheable("provincias")
    public Map<String, Object> getProvincias() {
        try {
            String url = GEOREF_BASE_URL + "/provincias?campos=id,nombre&max=100";
            return restTemplate.getForObject(url, Map.class);
        } catch (ResourceAccessException e) {
            // Timeout o problema de conexión
            throw new RuntimeException("Timeout al conectar con el servicio de georef", e);
        }
    }

    @Cacheable(value = "localidades", key = "#provincia + '_' + #max")
    public Map<String, Object> getLocalidades(String provincia, int max) {
        try {
            String url = GEOREF_BASE_URL + "/localidades?provincia={provincia}&campos=id,nombre&max={max}&orden=nombre";

            Map<String, String> params = new HashMap<>();
            params.put("provincia", provincia);
            params.put("max", String.valueOf(Math.min(max, 5000)));

            return restTemplate.getForObject(url, Map.class, params);
        } catch (ResourceAccessException e) {
            throw new RuntimeException("Timeout al obtener localidades para: " + provincia, e);
        }
    }

    public Map<String, Object> searchLocalidades(String provincia, String query, int limit) {
        // Obtener todas las localidades (usa cache)
        Map<String, Object> response = getLocalidades(provincia, 5000);

        if (response != null && response.containsKey("localidades")) {
            List<Map<String, Object>> localidades = (List<Map<String, Object>>) response.get("localidades");
            String queryLower = query.toLowerCase();

            List<Map<String, Object>> filtered = localidades.stream()
                    .filter(loc -> {
                        String nombre = loc.get("nombre").toString().toLowerCase();
                        return nombre.contains(queryLower);
                    })
                    .limit(limit)
                    .collect(Collectors.toList());

            Map<String, Object> filteredResponse = new HashMap<>(response);
            filteredResponse.put("localidades", filtered);
            filteredResponse.put("totalResults", filtered.size());
            return filteredResponse;
        }

        return response;
    }
}