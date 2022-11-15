package ppc.chatappbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ppc.chatappbackend.data.Message;
import ppc.chatappbackend.repositories.MessageRepository;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserService userService){
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    public Message sendMessage (String username, String content){
        var user = userService.getByUserName(username)
                .orElseThrow(RuntimeException::new);

        var message = new Message(content,user);
        return messageRepository.save(message);
    }

    public List<Message> getMessages(int latest){
        return messageRepository.findByIdLaterThan(latest);
    }
}
