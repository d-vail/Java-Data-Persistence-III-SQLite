package com.lambdaschool.h2crudsnacks.controllers;

import com.lambdaschool.h2crudsnacks.models.Customer;
import com.lambdaschool.h2crudsnacks.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
  @Autowired
  private CustomerRepository customerRepo;

  /**
   * Return all customers
   *
   * @return  A list of all customers
   */
  @GetMapping()
  public List<Customer> allCustomers()
  {
    return customerRepo.findAll();
  }

  /**
   * Return customer based on id
   *
   * @param id    Customer id
   * @return      A customer if found or null
   */
  @GetMapping("/id/{id}")
  public Customer customerById(@PathVariable long id)
  {
    return customerRepo.findById(id).orElse(null);
  }

  /**
   * Return customer based on name
   *
   * @param name  Customer name
   * @return      A customer if found or null
   */
  @GetMapping("/name/{name}")
  public Customer findCustomer(@PathVariable String name)
  {
    return customerRepo.findByName(name);
  }

  /**
   * Add a customer
   *
   * @param customer              A customer JSON object
   * @return                      The saved customer
   */
  @PostMapping()
  public Customer createCustomer(@RequestBody Customer customer)
  {
    return customerRepo.save(customer);
  }

  /**
   * Updates a customer based on id
   *
   * @param customer  A customer JSON object
   * @param id        A customer id
   * @return          The saved customer or null if id is invalid
   */
  @PutMapping("/id/{id}")
  public Customer updateCustomer(@RequestBody Customer customer, @PathVariable long id)
  {
    Optional<Customer> updatedCustomer = customerRepo.findById(id);
    if(updatedCustomer.isPresent())
    {
      customer.setId(id);
      customerRepo.save(customer);
      return customer;
    }
    else
    {
      return null;
    }
  }

  /**
   * Delete customer based on id
   *
   * @param id    Customer id
   * @return      The customer deleted or null if not found
   */
  @DeleteMapping("/id/{id}")
  public Customer deleteCustomer(@PathVariable long id)
  {
    Optional<Customer> customer = customerRepo.findById(id);
    if(customer.isPresent())
    {
      customerRepo.deleteById(id);
    }
    return customer.orElse(null);
  }
}
