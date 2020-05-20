package ru.graduation.web.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.MenuItem;
import ru.graduation.service.MenuItemService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.graduation.util.ValidationUtil.assureIdConsistent;
import static ru.graduation.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = ru.graduation.web.menu.MenuItemRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuItemRestController {
    @Autowired
    private MenuItemService service;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/restaurant/{restaurantId}/menuitems";

    @GetMapping
    public List<MenuItem> getAllbyDate(@PathVariable int restaurantId) {
        log.info("getAllbyToday");
        return service.getAllbyToday(restaurantId);
    }

    @GetMapping("/{id}")
    public MenuItem get(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("get {}", id);
        return service.get(id, restaurantId);
    }
}