package com.lambdaschool.h2crudsnacks.repository;

import com.lambdaschool.h2crudsnacks.models.VendingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendingMachineRepository extends JpaRepository<VendingMachine, Long>
{
    @Query(value = "SELECT * FROM vendingmachine WHERE name = :name", nativeQuery=true)
    List<VendingMachine> findByName (@Param("name") String name);
}
