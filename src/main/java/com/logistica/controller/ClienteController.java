package com.logistica.controller;

import com.logistica.entity.Cliente;
import org.springframework.web.bind.annotation.*;

import com.logistica.service.ClienteService;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author asycuda
 */
@CrossOrigin()
@RestController
@RequestMapping({ "/api/cliente" })
public class ClienteController {
    @Autowired
    private ClienteService servicePrincipal;

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(servicePrincipal.findAll(), HttpStatus.OK);
    }
    
    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(servicePrincipal.findById(id), HttpStatus.OK);
    }
    
    /**
     *
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Cliente request){
        Cliente s = servicePrincipal.create(request);
        return new ResponseEntity<>(servicePrincipal.create(request), HttpStatus.OK);
    }
    
    /**
     *
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Cliente request) {
        return new ResponseEntity<>(servicePrincipal.update(id, request), HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        servicePrincipal.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);        
    }

}