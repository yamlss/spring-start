package com.example.demo.repository.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
public class Checkout {

    @Id
    private String upc;

    private String libraryId;

    private LocalDate checkoutDate;

    private boolean overdue;

    public Checkout(String upc, String libraryId) {
        this.upc = upc;
        this.libraryId = libraryId;
        this.checkoutDate = LocalDate.now();
        this.overdue = this.isOverdue();
    }

    public boolean isOverdue() {
        long maxDays = 30;
        return this.checkoutDate.plusDays(maxDays).compareTo(LocalDate.now()) < 0;
    }
}
