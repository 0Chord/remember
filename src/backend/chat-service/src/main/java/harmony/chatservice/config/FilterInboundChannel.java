package harmony.chatservice.config;

import harmony.chatservice.client.CommunityClient;
import harmony.chatservice.dto.response.CommunityFeignResponse;
import harmony.chatservice.dto.response.SessionDto;
import harmony.chatservice.dto.response.StateDto;
import harmony.chatservice.service.kafka.MessageProducerService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FilterInboundChannel implements ChannelInterceptor {

    private final JwtTokenHandler jwtTokenHandler;
    private final CommunityClient communityClient;
    private final MessageProducerService messageService;
    private static final String AUTH_PREFIX = "Authorization";

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
//        if (StompCommand.CONNECT.equals(headerAccessor.getCommand())) {
//            if (!jwtTokenHandler.verifyToken(Objects.requireNonNull(headerAccessor.getFirstNativeHeader(AUTH_PREFIX)))) {
//                throw new RuntimeException("예외 발생");
//            }
//        }

        log.info("================================");
        log.info("getSessionId {}", headerAccessor.getSessionId());
        log.info("getSubscriptionId {}", headerAccessor.getSubscriptionId());
        log.info("getMessageHeaders {}", headerAccessor.getMessageHeaders());
        log.info("getCommand {}", headerAccessor.getCommand());
        log.info("userId {}", headerAccessor.getFirstNativeHeader("user-id"));
        log.info("================================");
        return message;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(headerAccessor.getCommand())) {
            log.info("sessionId {}", headerAccessor.getSessionId());
            Long userId = Long.parseLong(Objects.requireNonNull(headerAccessor.getFirstNativeHeader("user-id")));
            String sessionId = headerAccessor.getSessionId();
            SessionDto sessionDto = SessionDto.builder()
                    .userId(userId)
                    .sessionId(sessionId)
                    .type("CONNECT")
                    .state("online")
                    .build();
            messageService.sendMessageForSession(sessionDto);

            CommunityFeignResponse ids = communityClient.getGuildAndRoomIds(userId);
            log.info("getGuildIds {}", ids.getResultData().getGuildIds());
            log.info("getRoomIds {}", ids.getResultData().getRoomIds());
            StateDto stateDto = StateDto.builder()
                    .userId(userId)
                    .type("CONNECT")
                    .state("online")
                    .guildIds(ids.getResultData().getGuildIds())
                    .roomIds(ids.getResultData().getRoomIds())
                    .build();
            messageService.sendMessageForState(stateDto);
        }
    }
}