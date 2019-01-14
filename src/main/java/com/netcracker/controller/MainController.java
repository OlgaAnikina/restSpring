package com.netcracker.controller;

import com.netcracker.model.Buyer;
import com.netcracker.services.IBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("buyer")
public class MainController {
    @Autowired
    private IBuyerService buyerService;

    @GetMapping("/{id}")
    public ResponseEntity<Buyer> getArticleById(@PathVariable("id") Integer id) {
        Buyer article = buyerService.findBuyerById(id);
        return new ResponseEntity<Buyer>(article, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Buyer>> getAllBuyers() {
        List<Buyer> list = buyerService.findAllBuyers();
        return new ResponseEntity<List<Buyer>>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addBuyer(@RequestBody Buyer buyer, UriComponentsBuilder builder) {
        buyerService.saveBuyer(buyer);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/buyer/{id}").buildAndExpand(buyer.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Buyer> updateBuyer(@RequestBody Buyer buyer) {
        buyerService.updateBuyer(buyer);
        return new ResponseEntity<Buyer>(buyer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyer(@PathVariable("id") Integer id) {
        buyerService.deleteBuyerById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
