package com.sha.SolidusLabs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private Long id;
    private String message;
    private String hash;

    public Message() {
    }

    public Message(Long id, String message, String hash) {
        this.id = id;
        this.message = message;
        this.hash = hash;
    }

    public Message(String message, String hash) {
        this.message = message;
        this.hash = hash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }

}
