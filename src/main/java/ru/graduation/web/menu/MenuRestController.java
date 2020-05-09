package ru.graduation.web.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.Menu;
import ru.graduation.service.MenuService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.graduation.util.ValidationUtil.assureIdConsistent;
import static ru.graduation.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = ru.graduation.web.menu.MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {
    @Autowired
    private MenuService service;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/restaurant/{restaurantId}/menus";

    @GetMapping
    public List<Menu> getAllbyDate(@PathVariable int restaurantId) {
        log.info("getAllbyToday");
        return service.getAllbyToday(restaurantId);
    }

    @GetMapping("/{id}")
    public Menu get(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("get {}", id);
        return service.get(id, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@PathVariable int restaurantId, @Valid @RequestBody Menu menu) {
        log.info("create to{}", menu);
        checkNew(menu);
        Menu created = service.create(menu, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("delete {}", id);
        service.delete(id, restaurantId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Menu> update(@Valid @RequestBody Menu menu, @PathVariable int restaurantId, @PathVariable int id) {
        assureIdConsistent(menu, id);
        Menu updated = service.update(menu, restaurantId, id);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, updated.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(updated);
    }

}