package com.example.contacts_api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ContactController {
    private final ContactService service;
    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getContacts() {
      return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable int id){
       Contact contact = service.getById(id);
       if(contact == null){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(contact);
    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> addContact(@Valid @RequestBody Contact contact) {
        Contact created = service.addContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable int id){
        Contact contact = service.getById(id);
        if(contact == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.deleteContact(id));
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable int id,@Valid @RequestBody Contact updated){
        Contact contact = service.getById(id);
        if(contact == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.updateContact(id, updated));
    }
}
