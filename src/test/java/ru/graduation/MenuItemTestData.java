package ru.graduation;

import ru.graduation.model.MenuItem;

import java.time.LocalDate;
import java.time.Month;

import static java.time.LocalDate.of;
import static ru.graduation.RestaurantTestData.RESTAURANT1;
import static ru.graduation.model.AbstractBaseEntity.START_SEQ;

public class MenuItemTestData {
    public static TestMatcher<MenuItem> MENU_MATCHER = TestMatcher.usingFieldsComparator(MenuItem.class, "restaurant");

    public static final int MENU_ITEM_ID1 = START_SEQ + 8;
    public static final int MENU_ITEM_ID2 = START_SEQ + 9;
    public static final int MENU_ITEM_ID3 = START_SEQ + 14;
    public static final int MENU_ITEM_ID4 = START_SEQ + 15;
    public static final int MENU_ITEM_ID5 = START_SEQ + 16;

    public static final MenuItem MENU_ITEM_1 = new MenuItem(MENU_ITEM_ID1, of(2020, Month.MAY, 1), "dish 5", 200, RESTAURANT1);
    public static final MenuItem MENU_ITEM_2 = new MenuItem(MENU_ITEM_ID2, of(2020, Month.MAY, 1), "dish 2", 220, RESTAURANT1);
    public static final MenuItem MENU_ITEM_3 = new MenuItem(MENU_ITEM_ID3, LocalDate.now(), "dish 1", 265, RESTAURANT1);
    public static final MenuItem MENU_ITEM_4 = new MenuItem(MENU_ITEM_ID4, LocalDate.now(), "dish 2", 220, RESTAURANT1);
    public static final MenuItem MENU_ITEM_5 = new MenuItem(MENU_ITEM_ID5, LocalDate.now(), "dish 5", 200, RESTAURANT1);


    public static MenuItem getNew() {
        return new MenuItem(null, LocalDate.now(), "dish 3", 300, RESTAURANT1);
    }

    public static MenuItem getUpdated() {
        return new MenuItem(MENU_ITEM_ID1, MENU_ITEM_1.getDate(), "dish up", 100, RESTAURANT1);
    }
}
