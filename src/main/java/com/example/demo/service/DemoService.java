package com.example.demo.service;

import com.example.demo.repository.DemoRepository;
import com.example.demo.repository.dao.Checkout;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {

    private DemoRepository demoRepository;

    public DemoService(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public void upsertCheckout(Checkout checkout) {
        this.demoRepository.save(checkout);
    }

    public List<Checkout> findAll() {
        return this.demoRepository.findAll();
    }

    public void deleteCheckout(String upc) {
        Checkout checkout = this.demoRepository.findByUpc(upc);

        this.demoRepository.delete(checkout);
    }
}
