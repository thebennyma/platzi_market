package com.platzi.market.web.controller;

import com.platzi.market.domain.Purchase;
import com.platzi.market.domain.service.PurchaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    @ApiOperation("Get all purchases")
    @ApiResponse(code = 200, message = "Ok")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{idClient}")
    @ApiOperation("Get purchases from a user by purchase id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("idClient") String clientId) {
        return purchaseService.getByClient(clientId).map(
                purchases -> new ResponseEntity<>(purchases, HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @ApiOperation("Save a new purchases")
    @ApiResponse(code = 200, message = "Ok")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}
