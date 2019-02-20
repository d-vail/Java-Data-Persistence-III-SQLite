package com.lambdaschool.h2crudsnacks.controllers;

import com.lambdaschool.h2crudsnacks.models.VendingMachine;
import com.lambdaschool.h2crudsnacks.repository.VendingMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/vending", produces = MediaType.APPLICATION_JSON_VALUE)
public class VendingMachineController {
  @Autowired
  private VendingMachineRepository vendingMachineRepo;

  /**
   * Return all vending machines
   *
   * @return  A list of vending machines
   */
  @GetMapping()
  public List<VendingMachine> allVendingMachines()
  {
    return vendingMachineRepo.findAll();
  }

  /**
   * Return vending machine based on id
   *
   * @param id    Vending machine id
   * @return      A vending machine
   */
  @GetMapping("/id/{id}")
  public VendingMachine vendingMachineById(@PathVariable long id)
  {
    return vendingMachineRepo.findById(id).orElse(null);
  }

  /**
   * Return vending machine based on name
   *
   * @param name  A vending machine name
   * @return      A vending machine
   */
  @GetMapping("/name/{name}")
  public List<VendingMachine> vendingMachineByName(@PathVariable String name)
  {
    return new ArrayList<>(vendingMachineRepo.findByName(name));
  }

  /**
   * Adds a vending machine
   *
   * @param vendingMachine  A vending machine JSON object
   * @return                The saved vending machine
   */
  @PostMapping()
  public VendingMachine createVendingMachine(@RequestBody VendingMachine vendingMachine)
  {
    return vendingMachineRepo.save(vendingMachine);
  }

  /**
   * Updates a vending machine.
   *
   * @param vendingMachine  A vending machine JSON object
   * @param id              The vending machine id
   * @return                The updated vending machine or null if id is invalid
   */
  @PutMapping("/id/{id}")
  public VendingMachine updateVendingMachine(@RequestBody VendingMachine vendingMachine,
                                             @PathVariable long id)
  {
    Optional<VendingMachine> updatedVendingMachine = vendingMachineRepo.findById(id);
    if(updatedVendingMachine.isPresent())
    {
      vendingMachine.setId(id);
      vendingMachineRepo.save(vendingMachine);
      return vendingMachine;
    }
    else {
      return null;
    }
  }

  /**
   * Delete vending machine based on id.
   *
   * @param id    Vending machine id
   * @return      The vending machine deleted or null if not found
   */
  @DeleteMapping("/id/{id}")
  public VendingMachine deleteVendingMachine(@PathVariable long id)
  {
    Optional<VendingMachine> vendingMachine = vendingMachineRepo.findById(id);
    if(vendingMachine.isPresent())
    {
      vendingMachineRepo.deleteById(id);
    }
    return vendingMachine.orElse(null);
  }
}
