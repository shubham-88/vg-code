package org.vg.pv.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vg.pv.app.entities.Basket;
import org.vg.pv.app.repositories.BasketRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BasketService {

    private final BasketRepository basketRepository;

    @Autowired
    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public Basket save(Basket basket) {
        basket.setCreatedAt(OffsetDateTime.now());
        basket.setUpdatedAt(OffsetDateTime.now());
        return basketRepository.save(basket);
    }

    public List<Basket> saveAll(List<Basket> baskets) {
        OffsetDateTime now = OffsetDateTime.now();
        baskets.forEach(basket -> {
            basket.setCreatedAt(now);
            basket.setUpdatedAt(now);
        });
        return basketRepository.saveAll(baskets);
    }

    public List<Basket> findAll() {
        return basketRepository.findAll();
    }

    public Optional<Basket> findById(Long id) {
        return basketRepository.findById(id);
    }

    public Basket update(Long id, Basket basketDetails) {
        return basketRepository.findById(id).map(basket -> {
            basket.setName(basketDetails.getName());
            basket.setCode(basketDetails.getCode());
            basket.setBasketNumber(basketDetails.getBasketNumber());
            basket.setSelfWeight(basketDetails.getSelfWeight());
            basket.setIsActive(basketDetails.getIsActive());
            basket.setSortOrder(basketDetails.getSortOrder());
            basket.setUpdatedAt(OffsetDateTime.now());
            return basketRepository.save(basket);
        }).orElse(null);
    }

    public void delete(Long id) {
        basketRepository.deleteById(id);
    }
}
