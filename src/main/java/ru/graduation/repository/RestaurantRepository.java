package ru.graduation.repository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RestaurantRepository {
    private final CrudRestaurantRepository crudRepository;

    public RestaurantRepository(CrudRestaurantRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (!restaurant.isNew() && get(restaurant.id()) == null) {
            return null;
        }
        return crudRepository.save(restaurant);
    }

    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    public Restaurant get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    public List<Restaurant> getAll() {
        return crudRepository.findAll();
    }

    public List<Restaurant> getAllWithMenu() {
        return crudRepository.getAllWithMenu();
    }

    public Restaurant getWithMenu(int id) {
        return crudRepository.getWithMenu(id, LocalDate.now());
    }

}
