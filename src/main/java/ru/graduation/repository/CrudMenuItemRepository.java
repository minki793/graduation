package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.MenuItem;

import java.time.LocalDate;
import java.util.List;


@Transactional(readOnly = true)
public interface CrudMenuItemRepository extends JpaRepository<MenuItem, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM MenuItem m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.id=:restaurantId ORDER BY m.name ASC")
    List<MenuItem> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT m from MenuItem m WHERE m.restaurant.id=:restaurantId AND m.date = :date ORDER BY m.name ASC")
    List<MenuItem> getAllbyToday(@Param("restaurantId") int restaurantId, @Param("date") LocalDate date);

    @Query("SELECT m FROM MenuItem m JOIN FETCH m.restaurant WHERE m.id = ?1 AND m.restaurant.id=?2")
    MenuItem getWithRestaurant(int id, int restaurantId);
}