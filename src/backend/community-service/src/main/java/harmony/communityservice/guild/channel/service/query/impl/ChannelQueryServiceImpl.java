package harmony.communityservice.guild.channel.service.query.impl;

import harmony.communityservice.common.annotation.AuthorizeGuildMember;
import harmony.communityservice.common.dto.SearchParameterMapperRequest;
import harmony.communityservice.guild.channel.dto.SearchChannelResponse;
import harmony.communityservice.guild.channel.mapper.ToSearchChannelResponseMapper;
import harmony.communityservice.guild.channel.service.query.ChannelQueryService;
import harmony.communityservice.guild.domain.Channel;
import harmony.communityservice.guild.domain.Guild;
import harmony.communityservice.guild.guild.service.query.GuildQueryService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChannelQueryServiceImpl implements ChannelQueryService {

    private final GuildQueryService guildQueryService;

    @Override
    @AuthorizeGuildMember
    public Map<Integer, SearchChannelResponse> searchMapByGuildId(SearchParameterMapperRequest parameterMapperRequest) {
        return findChannels(parameterMapperRequest.guildId());
    }

    private Map<Integer, SearchChannelResponse> findChannels(Long guildId) {
        Guild targetGuild = guildQueryService.searchById(guildId);
        Map<Integer, SearchChannelResponse> channelReads = new HashMap<>();
        List<Channel> channels = targetGuild.getChannels();
        channels.forEach(channel -> {
            SearchChannelResponse searchChannelResponse = ToSearchChannelResponseMapper.convert(channel,
                    channels.indexOf(channel), guildId);
            channelReads.put(channels.indexOf(channel), searchChannelResponse);
        });
        return channelReads;
    }
}