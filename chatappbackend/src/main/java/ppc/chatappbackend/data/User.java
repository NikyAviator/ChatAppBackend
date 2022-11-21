package ppc.chatappbackend.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
// Our table cannot be names User because that it is a reserved word!
@Entity(name = "chat_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    // UUID
    @Id
    private String userId;

    private String name;
    private String password;

}
