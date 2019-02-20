package com.lambdaschool.h2crudsnacks.repository;

import com.lambdaschool.h2crudsnacks.models.Snack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SnackRepository extends JpaRepository<Snack, Long>
{
    @Query(value = "SELECT s.name as snack, s.quantity, s.cost, v.name as location FROM snack s, vendingmachine v WHERE s.vendingid = v.id ORDER BY v.name", nativeQuery = true)
    List<Object[]> vendingSnacks();
}
