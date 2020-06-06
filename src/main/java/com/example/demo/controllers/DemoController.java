package com.example.demo.controllers;

import com.example.demo.repository.dao.Checkout;
import com.example.demo.service.DemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DemoController {

    private DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/checkout")
    public ResponseEntity checkout(@RequestParam String upc,
                                   @RequestParam String libraryId) {
        this.demoService.upsertCheckout(new Checkout(upc, libraryId));

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getBooks")
    public ResponseEntity<List<Checkout>> getBooks() {
        List<Checkout> checkoutList = this.demoService.findAll();

        return new ResponseEntity<>(checkoutList, HttpStatus.OK);
    }

    @GetMapping("/checkin")
    public ResponseEntity checkin(@RequestParam String upc) {
        this.demoService.deleteCheckout(upc);

        return new ResponseEntity(HttpStatus.OK);
    }
}
