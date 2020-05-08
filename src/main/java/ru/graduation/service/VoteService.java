package ru.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.graduation.model.Vote;
import ru.graduation.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFound;
import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    private final VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId, false), id);
    }

    public Vote getToday(int userId) {
        return checkNotFound(repository.getToday(userId), "date=" + LocalDate.now());
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<Vote> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Transactional
    public Vote update(int id, int restaurantId, int userId) {
        Vote vote = new Vote(id);
        return checkNotFoundWithId(repository.save(vote, userId, restaurantId), id);
    }

    @Transactional
    public Vote create(int restaurantId, int userId) {
        Vote vote = new Vote();
        return repository.save(vote, userId, restaurantId);
    }

    public Vote getWithUser(int id, int userId) {
        return checkNotFoundWithId(repository.getWithUser(id, userId), id);
    }
}
