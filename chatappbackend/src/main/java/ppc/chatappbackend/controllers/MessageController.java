package ppc.chatappbackend.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ppc.chatappbackend.dtos.MessageDTO;
import ppc.chatappbackend.services.MessageService;

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
}
