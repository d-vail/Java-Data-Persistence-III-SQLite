package com.lambdaschool.h2crudsnacks;

import com.lambdaschool.h2crudsnacks.models.Customer;
import com.lambdaschool.h2crudsnacks.models.Snack;
import com.lambdaschool.h2crudsnacks.models.VendingMachine;
import com.lambdaschool.h2crudsnacks.repository.CustomerRepository;
import com.lambdaschool.h2crudsnacks.repository.SnackRepository;
import com.lambdaschool.h2crudsnacks.repository.VendingMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class SnackBarController
{
    @Autowired
    CustomerRepository custrepos;

    @Autowired
    SnackRepository snackrepos;

    @Autowired
    VendingMachineRepository vendingrepos;


    @GetMapping("/customer")
    public List<Customer> allcust()
    {
        return custrepos.findAll();
    }

    @GetMapping("/customer/{name}")
    public Customer findCustomer(@PathVariable String name)
    {
        return custrepos.findByName(name);
    }

    @GetMapping("/snack")
    public List<Snack> allsnacks()
    {
        return snackrepos.findAll();
    }

    @GetMapping("/snack/vending")
    public List<Object[]> vendingSnacks()
    {
        return snackrepos.vendingSnacks();
    }

    @GetMapping("/vending")
    public List<VendingMachine> allvending()
    {
        return vendingrepos.findAll();
    }

    @GetMapping("/vending/{name}")
    public List<VendingMachine> namedvending(@PathVariable String name)
    {
        return vendingrepos.findByName(name);
    }


    @DeleteMapping("/vending/{id}")
    public void deleteVendingMachine(@PathVariable Long id)
    {
        vendingrepos.deleteById(id);
    }


    @DeleteMapping("/snack/{id}")
    public void deleteSnack(@PathVariable Long id)
    {
        snackrepos.deleteById(id);
    }
}
