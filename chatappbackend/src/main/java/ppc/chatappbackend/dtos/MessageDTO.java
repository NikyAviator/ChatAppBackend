package ppc.chatappbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class MessageDTO {

    private int id;
    private String content;
    private Date date;
    private String username;
}
