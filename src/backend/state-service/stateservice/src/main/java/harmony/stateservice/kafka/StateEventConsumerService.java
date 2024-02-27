package harmony.stateservice.kafka;

import harmony.stateservice.dto.ChannelEventDto;
import harmony.stateservice.dto.SessionDto;
import harmony.stateservice.service.ChatServerService;
import harmony.stateservice.service.SignalingServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StateEventConsumerService {

    private final ChatServerService chatServerService;
    private final SignalingServerService signalingServerService;

    @KafkaListener(topics = "sessionEvent", groupId = "sessionEventGroup", containerFactory = "sessionListener")
    public void consumeForSession(SessionDto sessionDto){
        chatServerService.updateSession(sessionDto);
    }

    @KafkaListener(topics = "channelEvent", groupId = "channelEventGroupState", containerFactory = "channelEventListener")
    public void consumeForChannelEvent(ChannelEventDto channelEventDto) {

        signalingServerService.updateChannelEvent(channelEventDto);
    }
}