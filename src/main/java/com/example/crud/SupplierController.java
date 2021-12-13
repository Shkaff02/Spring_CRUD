package com.example.crud;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class SupplierController {
    private final SupplierRepository repository;

    public SupplierController(SupplierRepository repository){
        this.repository = repository;
    }

    @PostMapping("/createSupplier")
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier createSupplier(@RequestBody Supplier supplier){
        return repository.save(supplier);
    }

    @GetMapping("/suppliers")
    @ResponseStatus(HttpStatus.OK)
    public List<Supplier> getAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/suppliers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Supplier getSupplierById(@PathVariable Integer id){
        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id = " + id));
        return supplier;
    }

    @PutMapping("/updateSuppliers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Supplier refreshSupplier(@PathVariable("id") Integer id, @RequestBody Supplier supplier){
        return repository.findById(id)
                .map(entity -> {
                    entity.setName(supplier.getName());
                    entity.setEmail(supplier.getEmail());
                    entity.setAddress(supplier.getAddress());
                    return repository.save(entity);
                })
                .orElseThrow(()->new EntityNotFoundException("Supplier not found with id = " + id));
    }

    @DeleteMapping("/suppliers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeSupplierById(@PathVariable Integer id){
        Supplier supplier = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Supplier not found with id = " + id));
        repository.delete(supplier);
    }

    @GetMapping("/getSupplies/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object[] getSupplyBySupplier(@PathVariable Integer id){
        return repository.getSuppliesBySupplier(id);
    }
}
