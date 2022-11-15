package ppc.chatappbackend.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ppc.chatappbackend.dtos.MessageDTO;
import ppc.chatappbackend.services.MessageService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<MessageDTO> sendMessage(@RequestHeader String username, @RequestBody SendMessage sendMessage){
        var message = messageService.sendMessage(username, sendMessage.getContent());

        // Här väljer man vad man skall skicka in för delar i message
        var dto = new MessageDTO(message.getMessageId(),message.getContent(),message.getDate(),message.getUser().getName());
        return ResponseEntity.ok(dto);
    }

    @Getter
    @Setter
    public static class SendMessage{
        private String content;
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<List<MessageDTO>> getMessage(@PathVariable int id){
        return ResponseEntity.ok(messageService
                .getMessages(id)
                .stream()
                .map(message -> {return new MessageDTO(message.getMessageId(),message.getContent(),message.getDate(),message.getUser().getName());
    }).collect(Collectors.toList()));
    }
}
