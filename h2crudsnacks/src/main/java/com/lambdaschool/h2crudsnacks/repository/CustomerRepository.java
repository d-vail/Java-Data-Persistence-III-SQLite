package com.lambdaschool.h2crudsnacks.repository;

import com.lambdaschool.h2crudsnacks.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{
   public Customer findByName(String name);
}
