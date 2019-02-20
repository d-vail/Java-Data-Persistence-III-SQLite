package com.lambdaschool.h2crudsnacks.controllers;

import com.lambdaschool.h2crudsnacks.models.Snack;
import com.lambdaschool.h2crudsnacks.repository.SnackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/snack", produces = MediaType.APPLICATION_JSON_VALUE)
public class SnackController {
  @Autowired
  private SnackRepository snackRepo;

  /**
   * Return all snacks
   *
   * @return  A list of all snacks
   */
  @GetMapping()
  public List<Snack> allSnacks()
  {
    return snackRepo.findAll();
  }

  /**
   * Return snack based on id
   *
   * @param id  Snack id
   * @return    A snack if found or null
   */
  @GetMapping("/id/{id}")
  public Snack snackById(@PathVariable long id)
  {
    return snackRepo.findById(id).orElse(null);
  }

  @GetMapping("/vending")
  public List<Object[]> vendingSnacks()
  {
    return snackRepo.vendingSnacks();
  }

  /**
   * Adds a snack
   *
   * @param snack A snack JSON object
   * @return      The saved snack
   */
  @PostMapping()
  public Snack createSnack(@RequestBody Snack snack)
  {
    return snackRepo.save(snack);
  }

  /**
   * Updates a snack based on id
   *
   * @param snack A snack JSON object
   * @param id    A snack id
   * @return      The saved snack or null if id is invalid
   */
  @PutMapping("/id/{id}")
  public Snack updateSnack(@RequestBody Snack snack, @PathVariable long id)
  {
    Optional<Snack> updatedSnack = snackRepo.findById(id);
    if(updatedSnack.isPresent())
    {
      snack.setId(id);
      snackRepo.save(snack);
      return snack;
    }
    else
    {
      return null;
    }
  }

  /**
   * Delete snack based on id.
   *
   * @param id  The snack id
   * @return    The snack deleted or null if not found
   */
  @DeleteMapping("/id/{id}")
  public Snack deleteSnack(@PathVariable Long id)
  {
    Optional<Snack> snack = snackRepo.findById(id);
    if(snack.isPresent())
    {
      snackRepo.deleteById(id);
    }
    return snack.orElse(null);
  }
}
