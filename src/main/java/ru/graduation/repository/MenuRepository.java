package ru.graduation.repository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MenuRepository {
    private final CrudMenuRepository crudMenuRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public MenuRepository(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Transactional
    public Menu save(Menu menu, int restaurantId) {
        if (!menu.isNew() && get(menu.id(), restaurantId) == null) {
            return null;
        }
        menu.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMenuRepository.save(menu);
    }

    public boolean delete(int id, int restaurantId) {
        return crudMenuRepository.delete(id, restaurantId) != 0;
    }

    public Menu get(int id, int restaurantId) {
        return crudMenuRepository.findById(id).filter(menu -> menu.getRestaurant().getId() == restaurantId).orElse(null);
    }

    public List<Menu> getAll(int restaurantId) {
        return crudMenuRepository.getAll(restaurantId);
    }

    public Menu getWithRestaurant(int id, int restaurantId) {
        return crudMenuRepository.getWithRestaurant(id, restaurantId);
    }

    public List<Menu> getAllbyToday(int restaurantId) {
        return crudMenuRepository.getAllbyToday(restaurantId, LocalDate.now());
    }
}
