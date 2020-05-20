package ru.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.MenuItem;
import ru.graduation.repository.MenuItemRepository;

import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuItemService {
    private final MenuItemRepository repository;

    public MenuItemService(MenuItemRepository repository) {
        this.repository = repository;
    }

    public MenuItem create(MenuItem menuItem, int restaurantId) {
        Assert.notNull(menuItem, "menuItem must not be null");
        return repository.save(menuItem, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    public MenuItem get(int id, int restaurantId) {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    public List<MenuItem> getAllbyToday(int restaurantId) {
        return repository.getAllbyToday(restaurantId);
    }

    public MenuItem update(MenuItem menuItem, int restaurantId, int id) {
        Assert.notNull(menuItem, "menuItem must not be null");
        return checkNotFoundWithId(repository.save(menuItem, restaurantId), id);
    }

}
