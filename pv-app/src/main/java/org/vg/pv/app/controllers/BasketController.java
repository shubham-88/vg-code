package org.vg.pv.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vg.pv.app.entities.Basket;
import org.vg.pv.app.services.BasketService;

import java.util.List;

@RestController
@RequestMapping("/api/baskets")
public class BasketController {

    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping
    public ResponseEntity<Basket> createBasket(@RequestBody Basket basket) {
        Basket savedBasket = basketService.save(basket);
        return ResponseEntity.ok(savedBasket);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Basket>> createBaskets(@RequestBody List<Basket> baskets) {
        List<Basket> savedBaskets = basketService.saveAll(baskets);
        return ResponseEntity.ok(savedBaskets);
    }

    @GetMapping
    public ResponseEntity<List<Basket>> getAllBaskets() {
        List<Basket> baskets = basketService.findAll();
        return ResponseEntity.ok(baskets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Basket> getBasketById(@PathVariable Long id) {
        return basketService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Basket> updateBasket(@PathVariable Long id, @RequestBody Basket basket) {
        Basket updatedBasket = basketService.update(id, basket);
        if (updatedBasket != null) {
            return ResponseEntity.ok(updatedBasket);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBasket(@PathVariable Long id) {
        basketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
