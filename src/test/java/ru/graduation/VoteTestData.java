package ru.graduation;

import ru.graduation.model.Vote;

import java.time.LocalDate;
import java.time.Month;

import static ru.graduation.RestaurantTestData.RESTAURANT1;
import static ru.graduation.RestaurantTestData.RESTAURANT3;
import static ru.graduation.RestaurantTestData.RESTAURANT2;
import static ru.graduation.UserTestData.USER1;
import static ru.graduation.UserTestData.ADMIN;
import static ru.graduation.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingFieldsComparator(Vote.class, "user");

    public static final int VOTE_ID1 = START_SEQ + 22;
    public static final int VOTE_ID2 = START_SEQ + 26;

    public static final Vote VOTE1 = new Vote(VOTE_ID1, LocalDate.of(2020, Month.MAY, 1), USER1, RESTAURANT1);
    public static final Vote VOTE2 = new Vote(VOTE_ID2, LocalDate.now(), USER1, RESTAURANT2);

    public static Vote getNew() {
        return new Vote(null, LocalDate.now(), ADMIN, RESTAURANT3);
    }

    public static Vote getUpdated() {
        return new Vote(VOTE_ID2, VOTE2.getDate(), USER1, RESTAURANT3);
    }
}
