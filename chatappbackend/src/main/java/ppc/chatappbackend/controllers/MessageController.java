package ppc.chatappbackend.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ppc.chatappbackend.dtos.MessageDTO;
import ppc.chatappbackend.services.MessageService;
import ppc.chatappbackend.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;

    @Autowired
    public MessageController(MessageService messageService, UserService userService){
        this.messageService = messageService;
        this.userService = userService;
    }


    @PostMapping("/send")
    public ResponseEntity<MessageDTO> sendMessage(
            @RequestHeader String token,
            @RequestBody SendMessage sendMessage
    ) {
        var user =  userService.verifyToken(token);
        if (user == null)
            return ResponseEntity.notFound().build();
        // TODO String.valueOf(user) vara bara user innan! what gives?
        var message = messageService.sendMessage(String.valueOf(user), sendMessage.getContent());

        var dto = new MessageDTO(
                message.getMessageId(),
                message.getContent(),
                message.getDate(),
                message.getUser().getName()
        );
        return ResponseEntity.ok(dto);
    }

    @Getter
    @Setter
    public static class SendMessage{
        private String content;
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<List<MessageDTO>> getMessage(
            @RequestHeader String token,
            @PathVariable int id
    ){
        var user = userService.verifyToken(token);
        if(user == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(messageService
                .getMessages(id)
                .stream()
                .map(message -> {
                    return new MessageDTO(
                            message.getMessageId(),
                            message.getContent(),
                            message.getDate(),
                            message.getUser().getName()
                    );
                })
                .collect(Collectors.toList()));
    }
}
