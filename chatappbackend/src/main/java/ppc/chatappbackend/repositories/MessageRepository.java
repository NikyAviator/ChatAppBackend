package ppc.chatappbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ppc.chatappbackend.data.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
