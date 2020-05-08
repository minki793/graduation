package ru.graduation.repository;


import org.springframework.stereotype.Repository;
import ru.graduation.model.Restaurant;
import ru.graduation.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VoteRepository {

    private final CrudVoteRepository crudVoteRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public VoteRepository(CrudVoteRepository crudVoteRepository, CrudUserRepository crudUserRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudVoteRepository = crudVoteRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }


    public Vote save(Vote vote, int userId, int restaurantId) {
        if (!vote.isNew() && get(vote.id(), userId, true) == null) {
            return null;
        }
        Restaurant restaurant = crudRestaurantRepository.findById(restaurantId).get();
        vote.setRestaurant(restaurant);
        vote.setUser(crudUserRepository.getOne(userId));
        return crudVoteRepository.save(vote);
    }

    public boolean delete(int id, int userId) {
        return crudVoteRepository.delete(id, userId, LocalDate.now()) != 0;
    }

    public Vote get(int id, int userId, boolean today) {
        return crudVoteRepository.findById(id).filter(vote -> vote.getUser().getId() == userId && (vote.getDate().isEqual(LocalDate.now()) || !today)).orElse(null);
    }

    public Vote getToday(int userId) {
        return crudVoteRepository.getToday(userId, LocalDate.now());
    }

    public List<Vote> getAll(int userId) {
        return crudVoteRepository.getAll(userId);
    }

    public Vote getWithUser(int id, int userId) {
        return crudVoteRepository.getWithUser(id, userId);
    }
}
