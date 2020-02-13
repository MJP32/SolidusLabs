package com.sha.SolidusLabs.controller;

import com.sha.SolidusLabs.domain.Message;
import com.sha.SolidusLabs.database.MessageRepository;
import com.sha.SolidusLabs.util.HashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository repository;

    @GetMapping("/message")
    List<Message> findAll() {
        return repository.findAll();
    }

    @PostMapping("/message")
    @ResponseStatus(HttpStatus.CREATED)
    Message saveMessage(@RequestBody Message message) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String hashedMessaged = HashMessage.toHexString(md.digest(message.getMessage().getBytes(StandardCharsets.UTF_8)));
        message.setHash(hashedMessaged);
        return repository.save(message);
    }

}
