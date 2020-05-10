package ru.graduation;

import ru.graduation.model.Restaurant;

import static ru.graduation.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingFieldsComparator(Restaurant.class, "menus");

    public static final int RESTAURANT_ID1 = START_SEQ + 5;
    public static final int RESTAURANT_ID2 = START_SEQ + 6;
    public static final int RESTAURANT_ID3 = START_SEQ + 7;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT_ID1, "rest 1");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT_ID2, "rest 2");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT_ID3, "rest 3");

    public static Restaurant getNew() {
        return new Restaurant(null, "rest 4");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID1, "res 1 Up");
    }
}
