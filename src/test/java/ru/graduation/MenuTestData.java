package ru.graduation;

import ru.graduation.model.Menu;

import java.time.LocalDate;
import java.time.Month;

import static java.time.LocalDate.of;
import static ru.graduation.RestaurantTestData.RESTAURANT1;
import static ru.graduation.model.AbstractBaseEntity.START_SEQ;

public class MenuTestData {
    public static TestMatcher<Menu> MENU_MATCHER = TestMatcher.usingFieldsComparator(Menu.class, "restaurant");

    public static final int MENU_ID1 = START_SEQ + 8;
    public static final int MENU_ID2 = START_SEQ + 9;
    public static final int MENU_ID3 = START_SEQ + 14;
    public static final int MENU_ID4 = START_SEQ + 15;
    public static final int MENU_ID5 = START_SEQ + 16;

    public static final Menu MENU1 = new Menu(MENU_ID1, of(2020, Month.MAY, 1), "dish 5", 200, RESTAURANT1);
    public static final Menu MENU2 = new Menu(MENU_ID2, of(2020, Month.MAY, 1), "dish 2", 220, RESTAURANT1);
    public static final Menu MENU3 = new Menu(MENU_ID3, LocalDate.now(), "dish 1", 265, RESTAURANT1);
    public static final Menu MENU4 = new Menu(MENU_ID4 , LocalDate.now(), "dish 2", 220, RESTAURANT1);
    public static final Menu MENU5 = new Menu(MENU_ID5, LocalDate.now(), "dish 5", 200, RESTAURANT1);


    public static Menu getNew() {
        return new Menu(null, LocalDate.now(), "dish 3", 300, RESTAURANT1);
    }

    public static Menu getUpdated() {
        return new Menu(MENU_ID1, MENU1.getDate(), "dish up", 100, RESTAURANT1);
    }
}
