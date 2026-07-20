package com.example.contacts_api;

import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class ContactService {

    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public List<Contact> getAll() {
        return repository.findAll();
    }

    public Contact getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Contact addContact(Contact contact) {
        return repository.save(contact);
    }

    public String deleteContact(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Контакт " + id + " удален";
        }
        return "Контакт не найден";
    }

    public Contact updateContact(int id, Contact updated) {
        Contact contact = repository.findById(id).orElse(null);
        if (contact != null) {
            contact.setName(updated.getName());
            contact.setPhone(updated.getPhone());
            return repository.save(contact);
        }
        return null;
    }
}

