package com.sha.SolidusLabs.database;

import com.sha.SolidusLabs.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
