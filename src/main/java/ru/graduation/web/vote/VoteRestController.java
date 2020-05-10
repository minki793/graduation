package ru.graduation.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.Vote;
import ru.graduation.service.VoteService;
import ru.graduation.web.SecurityUtil;
import ru.graduation.web.user.UniqueMailValidator;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.graduation.util.ValidationUtil.*;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    @Autowired
    private VoteService service;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/votes";

   @GetMapping
    public List<Vote> getAll() {
        log.info("getAll");
        int userId = SecurityUtil.authUserId();
        return service.getAll(userId);
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        log.info("get {}", id);
        int userId = SecurityUtil.authUserId();
        return service.get(id, userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        checkTime();
        int userId = SecurityUtil.authUserId();
        service.delete(id, userId);
    }

    @GetMapping("/today")
    public Vote getToday() {
        log.info("getToday");
        int userId = SecurityUtil.authUserId();
        return service.getToday(userId);
    }

    @PostMapping(value = "/restaurant/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@PathVariable int restaurantId) {
        log.info("create vote");
        int userId = SecurityUtil.authUserId();
        Vote created = service.create(restaurantId, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}/restaurant/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Vote>  update(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("update {}", id);
        checkTime();
        int userId = SecurityUtil.authUserId();
        Vote updated = service.update(id, restaurantId, userId);
        return ResponseEntity.ok(updated);

    }
}
