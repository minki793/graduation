package ru.graduation.web.vote;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.VoteTestData;
import ru.graduation.model.Vote;
import ru.graduation.service.VoteService;
import ru.graduation.util.exception.NotFoundException;
import ru.graduation.web.AbstractControllerTest;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.RestaurantTestData.RESTAURANT_ID3;
import static ru.graduation.TestUtil.readFromJson;
import static ru.graduation.TestUtil.userHttpBasic;
import static ru.graduation.UserTestData.*;
import static ru.graduation.VoteTestData.*;
import static ru.graduation.util.ValidationUtil.TIME_LIMIT;
import static ru.graduation.util.exception.ErrorType.DATA_ERROR;
import static ru.graduation.util.exception.ErrorType.TIME_ERROR;

class VoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteRestController.REST_URL + '/';

    @Autowired
    private VoteService voteService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + VOTE_ID1)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTE1));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + VOTE_ID1))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + (VOTE_ID1 + 1))
                .with(userHttpBasic(USER1)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "today")
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTE2));
    }


    @Test
    void delete() throws Exception {
        if (LocalTime.now().isAfter(TIME_LIMIT)) {
            perform(MockMvcRequestBuilders.delete(REST_URL + VOTE_ID2)
                    .with(userHttpBasic(USER1)))
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(errorType(TIME_ERROR));
        } else {
            perform(MockMvcRequestBuilders.delete(REST_URL + VOTE_ID2)
                    .with(userHttpBasic(USER1)))
                    .andExpect(status().isNoContent());
            assertThrows(NotFoundException.class, () -> voteService.get(VOTE_ID2, USER_ID1));
        }
    }

    @Test
    void deleteNotToday() throws Exception {
        if (LocalTime.now().isAfter(TIME_LIMIT)) {
            perform(MockMvcRequestBuilders.delete(REST_URL + VOTE_ID1)
                    .with(userHttpBasic(USER1)))
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(errorType(TIME_ERROR));
        } else {
            perform(MockMvcRequestBuilders.delete(REST_URL + VOTE_ID1)
                    .with(userHttpBasic(USER1)))
                    .andExpect(status().isUnprocessableEntity());
        }
    }

    @Test
    void update() throws Exception {
        if (LocalTime.now().isAfter(TIME_LIMIT)) {
            Vote updated = VoteTestData.getUpdated();
            perform(MockMvcRequestBuilders.put(REST_URL + VOTE_ID2 + "/restaurant/" + updated.getRestaurant().getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .with(userHttpBasic(USER1)))
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(errorType(TIME_ERROR));
        } else {
            Vote updated = VoteTestData.getUpdated();
            perform(MockMvcRequestBuilders.put(REST_URL + VOTE_ID2 + "/restaurant/" + updated.getRestaurant().getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .with(userHttpBasic(USER1)))
                    .andExpect(status().isOk());
            VOTE_MATCHER.assertMatch(voteService.get(VOTE_ID2, USER_ID1), updated);
        }

    }

    @Test
    void createWithLocation() throws Exception {
        Vote newVote = VoteTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "/restaurant/" + newVote.getRestaurant().getId())
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN)));
        Vote created = readFromJson(action, Vote.class);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteService.get(newId, ADMIN_ID), newVote);
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTE2, VOTE1));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void createDuplicate() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL + "/restaurant/" + RESTAURANT_ID3)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER1)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(errorType(DATA_ERROR));
    }
}
