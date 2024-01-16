package harmony.chatservice.controller;

import harmony.chatservice.domain.CommunityMessage;
import harmony.chatservice.dto.CommunityMessageDto;
import harmony.chatservice.dto.request.CommunityMessageDeleteRequest;
import harmony.chatservice.dto.request.CommunityMessageModifyRequest;
import harmony.chatservice.dto.request.CommunityMessageRequest;
import harmony.chatservice.service.CommunityMessageService;
import harmony.chatservice.service.kafka.MessageProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommunityMessageController {

    private final CommunityMessageService messageService;
    private final MessageProducerService messageProducerService;

    @MessageMapping("/guild/message")
    public void chatMessage(CommunityMessageRequest messageRequest) {
        CommunityMessageDto messageDto = messageService.saveMessage(messageRequest);
        messageProducerService.sendMessageForCommunity(messageDto);
    }

    @MessageMapping("/guild/modify")
    public void modifyMessage(CommunityMessageModifyRequest modifyRequest) {
        CommunityMessageDto messageDto = messageService.modifyMessage(modifyRequest);
        messageProducerService.sendMessageForCommunity(messageDto);
    }

    @MessageMapping("/guild/delete")
    public void deleteMessage(CommunityMessageDeleteRequest deleteRequest) {
        CommunityMessageDto messageDto = messageService.deleteMessage(deleteRequest);
        messageProducerService.sendMessageForCommunity(messageDto);
    }

    @MessageMapping("/guild/typing")
    public void typingMessage(CommunityMessageDto messageDto) {

        messageProducerService.sendMessageForCommunity(messageDto);
    }

    @GetMapping("/api/messages/channel/{channelId}")
    public Page<CommunityMessageDto> getMessages(@PathVariable("channelId") Long channelId) {

        Page<CommunityMessage> messagePage = messageService.getMessages(channelId);
        return messagePage.map(CommunityMessageDto::new);
    }

    @GetMapping("/api/community/comments/{parentId}")
    public Page<CommunityMessageDto> getComments(@PathVariable("parentId") Long parentId) {

        Page<CommunityMessage> messagePage = messageService.getComments(parentId);
        return messagePage.map(CommunityMessageDto::new);
    }
}