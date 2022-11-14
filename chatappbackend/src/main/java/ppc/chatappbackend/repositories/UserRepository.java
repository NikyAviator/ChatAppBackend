package ppc.chatappbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ppc.chatappbackend.data.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
