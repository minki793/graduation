package ru.graduation.web.menu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.graduation.MenuItemTestData;
import ru.graduation.model.MenuItem;
import ru.graduation.service.MenuItemService;
import ru.graduation.util.exception.NotFoundException;
import ru.graduation.web.AbstractControllerTest;
import ru.graduation.web.json.JsonUtil;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.MenuItemTestData.*;
import static ru.graduation.RestaurantTestData.RESTAURANT1;
import static ru.graduation.RestaurantTestData.RESTAURANT_ID1;
import static ru.graduation.TestUtil.readFromJson;
import static ru.graduation.TestUtil.userHttpBasic;
import static ru.graduation.UserTestData.ADMIN;
import static ru.graduation.UserTestData.USER1;
import static ru.graduation.util.exception.ErrorType.VALIDATION_ERROR;


class AdminMenuItemRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/rest/admin/restaurant/" + RESTAURANT_ID1 + "/menuitems/";

    @Autowired
    private MenuItemService menuItemService;

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + MENU_ITEM_ID1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> menuItemService.get(MENU_ITEM_ID1, RESTAURANT_ID1));
    }

    @Test
    void deleteForbidden() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + MENU_ITEM_ID1)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isForbidden());
    }

    @Test
    void update() throws Exception {
        MenuItem updated = MenuItemTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + MENU_ITEM_ID1).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());

        MENU_MATCHER.assertMatch(menuItemService.get(MENU_ITEM_ID1, RESTAURANT_ID1), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        MenuItem newMenuItem = MenuItemTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenuItem))
                .with(userHttpBasic(ADMIN)));

        MenuItem created = readFromJson(action, MenuItem.class);
        int newId = created.id();
        newMenuItem.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenuItem);
        MENU_MATCHER.assertMatch(menuItemService.get(newId, RESTAURANT_ID1), newMenuItem);
    }


    @Test
    void createInvalid() throws Exception {
        MenuItem invalid = new MenuItem(null, LocalDate.now(), null, null, RESTAURANT1);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void updateInvalid() throws Exception {
        MenuItem invalid = new MenuItem(MENU_ITEM_ID1, LocalDate.now(), null, null, RESTAURANT1);
        perform(MockMvcRequestBuilders.put(REST_URL + MENU_ITEM_ID1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }
}