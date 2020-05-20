package ru.graduation.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.MenuItem;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MenuItemRepository {
    private final CrudMenuItemRepository crudMenuItemRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public MenuItemRepository(CrudMenuItemRepository crudMenuItemRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuItemRepository = crudMenuItemRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Transactional
    public MenuItem save(MenuItem menuItem, int restaurantId) {
        if (!menuItem.isNew() && get(menuItem.id(), restaurantId) == null) {
            return null;
        }
        menuItem.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMenuItemRepository.save(menuItem);
    }

    public boolean delete(int id, int restaurantId) {
        return crudMenuItemRepository.delete(id, restaurantId) != 0;
    }

    public MenuItem get(int id, int restaurantId) {
        return crudMenuItemRepository.findById(id).filter(menu -> menu.getRestaurant().getId() == restaurantId).orElse(null);
    }

    public List<MenuItem> getAll(int restaurantId) {
        return crudMenuItemRepository.getAll(restaurantId);
    }

    public MenuItem getWithRestaurant(int id, int restaurantId) {
        return crudMenuItemRepository.getWithRestaurant(id, restaurantId);
    }

    public List<MenuItem> getAllbyToday(int restaurantId) {
        return crudMenuItemRepository.getAllbyToday(restaurantId, LocalDate.now());
    }
}
