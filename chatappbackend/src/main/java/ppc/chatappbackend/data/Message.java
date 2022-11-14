package ppc.chatappbackend.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {

    @Id
    private Integer messageId;

    private String content;
    @ManyToOne
    private User user;
    private Date date;

    public Message(String content, User user) {
        this.content = content;
        this.user = user;
        this.date = new Date();
    }
}
