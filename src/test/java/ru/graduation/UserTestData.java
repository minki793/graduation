package ru.graduation;

import ru.graduation.model.Role;
import ru.graduation.model.User;
import ru.graduation.web.json.JsonUtil;

import java.util.Collections;
import java.util.Date;

import static ru.graduation.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsComparator(User.class, "registered", "password");

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }

    public static final int USER_ID1 = START_SEQ;
    public static final int USER_ID2 = START_SEQ + 1;
    public static final int USER_ID3 = START_SEQ + 2;
    public static final int USER_ID4 = START_SEQ + 3;
    public static final int ADMIN_ID = START_SEQ + 4;

    public static final User USER1 = new User(USER_ID1, "User 1", "user1@yandex.ru", "password1", Role.USER);
    public static final User USER2 = new User(USER_ID2, "User 2", "user2@yandex.ru", "password2", Role.USER);
    public static final User USER3 = new User(USER_ID3, "User 3", "user3@yandex.ru", "password3", Role.USER);
    public static final User USER4 = new User(USER_ID4, "User 4", "user4@yandex.ru", "password4", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN, Role.USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(USER1);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }
}
