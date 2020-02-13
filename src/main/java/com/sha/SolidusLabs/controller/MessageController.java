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
import org.json.simple.JSONObject;
@RestController
public class MessageController {

    @Autowired
    private MessageRepository repository;

    @GetMapping("/messages/{hash}")
    JSONObject findByHash(@PathVariable String hash) {
        System.out.println("findByHash");
        List<Message> ls = repository.findAll();
        JSONObject jsonObject = new JSONObject();
        for (Message msg : ls) {
            if (msg.getHash().equals(hash)) {
                jsonObject.put("digest", msg.getMessage());
                return jsonObject;
            }
        }
        jsonObject.put("err_msg", "Message not found");
        return jsonObject;
    }

    @GetMapping("/allmessages")
    List<Message> findAll() {
        System.out.println("findAll");
        return repository.findAll();
    }

    @PostMapping("/messages")
    @ResponseStatus(HttpStatus.CREATED)
    JSONObject saveMessage(@RequestBody Message message) {
        System.out.println("saveMessages");
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String hashedMessaged = HashMessage.toHexString(md.digest(message.getMessage().getBytes(StandardCharsets.UTF_8)));
        message.setHash(hashedMessaged);
        repository.save(message);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("digest", hashedMessaged);
        return jsonObject;
    }

}
