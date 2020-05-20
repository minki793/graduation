package ru.graduation.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.Restaurant;
import ru.graduation.service.RestaurantService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.graduation.util.ValidationUtil.assureIdConsistent;
import static ru.graduation.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    @Autowired
    private RestaurantService service;
    protected final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/rest/restaurants";

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping("/fullData")
    public List<Restaurant> getAllWithMenu() {
        log.info("getAllWithMenu");
        return service.getAllWithMenu();
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    @GetMapping("/{id}/fullData")
    public Restaurant getWithMenu(@PathVariable int id) {
        log.info("getWithMenu {}", id);
        return service.getWithMenu(id);
    }
}