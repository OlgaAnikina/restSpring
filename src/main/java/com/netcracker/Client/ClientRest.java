package com.netcracker.Client;

import com.netcracker.model.Buyer;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class ClientRest {
    private static final String URL = "http://localhost:9090/buyer";
    HttpHeaders headers = new HttpHeaders();
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<String> requestEntity;

    public ClientRest() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        requestEntity = new HttpEntity<String>(headers);
    }

    public void getBuyerById() {
        String url = URL + "/{id}";
        ResponseEntity<Buyer> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, requestEntity, Buyer.class, 1);
        Buyer buyer = responseEntity.getBody();
        System.out.println("Id:" + buyer.getId() + ", Name:" + buyer.getFirstName()
                + ", District:" + buyer.getDistrict() + ", Sale: " + buyer.getSale().toString());
    }

    public void getAllBuyers() {
        ResponseEntity<Buyer[]> responseEntity = restTemplate.exchange(URL,
                HttpMethod.GET, requestEntity, Buyer[].class);
        Buyer[] buyers = responseEntity.getBody();
        for (Buyer buyer : buyers) {
            System.out.println("Id:" + buyer.getId() + ", Name:" + buyer.getFirstName()
                    + ", District:" + buyer.getDistrict() + ", Sale: " + buyer.getSale().toString());
        }
    }

    public void addBuyer() {
        Buyer buyer = new Buyer("Name", "Avtovavod", 10);
        HttpEntity<Buyer> requestEntity = new HttpEntity<Buyer>(buyer, headers);
        URI uri = restTemplate.postForLocation(URL, requestEntity);
        System.out.println(uri.getPath());
    }

    public void updateBuyer() {
        Buyer buyer = new Buyer("Namw1", "Sormovo", 20);
        buyer.setId(1);
        HttpEntity<Buyer> requestEntity = new HttpEntity<Buyer>(buyer, headers);
        restTemplate.put(URL, requestEntity);
    }

    public void deleteBuyer() {
        String url = URL + "/{id}";
        HttpEntity<Buyer> requestEntity = new HttpEntity<Buyer>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 3);
    }

}