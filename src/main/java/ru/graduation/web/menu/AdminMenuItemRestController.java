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
@RequestMapping(value = AdminMenuItemRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuItemRestController {
    @Autowired
    private MenuItemService service;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/admin/restaurant/{restaurantId}/menuitems";

   @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItem> createWithLocation(@PathVariable int restaurantId, @Valid @RequestBody MenuItem menuItem) {
        log.info("create to{}", menuItem);
        checkNew(menuItem);
        MenuItem created = service.create(menuItem, restaurantId);
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
    public ResponseEntity<MenuItem> update(@Valid @RequestBody MenuItem menuItem, @PathVariable int restaurantId, @PathVariable int id) {
        assureIdConsistent(menuItem, id);
        MenuItem updated = service.update(menuItem, restaurantId, id);
        return ResponseEntity.ok(updated);
    }

}