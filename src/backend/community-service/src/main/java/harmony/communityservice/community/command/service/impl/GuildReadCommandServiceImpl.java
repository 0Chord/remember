package harmony.communityservice.community.command.service.impl;

import harmony.communityservice.common.service.ProducerService;
import harmony.communityservice.community.command.dto.RegisterGuildReadRequest;
import harmony.communityservice.community.command.repository.GuildReadCommandRepository;
import harmony.communityservice.community.command.service.GuildReadCommandService;
import harmony.communityservice.community.domain.GuildRead;
import harmony.communityservice.community.mapper.ToGuildReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class GuildReadCommandServiceImpl implements GuildReadCommandService {

    private final GuildReadCommandRepository repository;
    private final ProducerService producerService;

    @Override
    public GuildRead register(RegisterGuildReadRequest registerGuildReadRequest) {
        GuildRead guildRead = ToGuildReadMapper.convert(registerGuildReadRequest);
        repository.save(guildRead);
//        producerService.sendCreateGuild(guildRead);
        return guildRead;
    }

    @Override
    public void delete(long guildId) {
        repository.delete(guildId);
    }
}
