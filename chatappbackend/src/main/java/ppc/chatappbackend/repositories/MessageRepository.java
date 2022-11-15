package ppc.chatappbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ppc.chatappbackend.data.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query(value = "SELECT * FROM message WHERE message_id > ?1",nativeQuery = true)
    List<Message> findByIdLaterThan(int id);
}
