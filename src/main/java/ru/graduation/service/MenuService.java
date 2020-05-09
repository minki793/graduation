package ru.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Menu;
import ru.graduation.repository.MenuRepository;

import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {
    private final MenuRepository repository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        return repository.save(menu, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    public Menu get(int id, int restaurantId) {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    public List<Menu> getAllbyToday(int restaurantId) {
        return repository.getAllbyToday(restaurantId);
    }

    public Menu update(Menu menu, int restaurantId, int id) {
        Assert.notNull(menu, "menu must not be null");
        return checkNotFoundWithId(repository.save(menu, restaurantId), id);
    }

}
