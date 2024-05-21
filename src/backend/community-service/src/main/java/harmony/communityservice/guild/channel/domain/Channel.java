package harmony.communityservice.guild.channel.domain;

import harmony.communityservice.guild.category.domain.Category.CategoryId;
import harmony.communityservice.guild.guild.domain.Guild.GuildId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Channel {

    private ChannelId channelId;

    private GuildId guildId;

    private CategoryId categoryId;
    private String name;

    private ChannelType type;

    @Builder
    public Channel(CategoryId categoryId, ChannelId channelId, GuildId guildId, String name, String type) {
        this.categoryId = categoryId;
        this.channelId = channelId;
        this.guildId = guildId;
        this.name = name;
        this.type = ChannelType.valueOf(type);
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ChannelId {
        private Long id;

        public static ChannelId make(Long channelId) {
            return new ChannelId(channelId);
        }
    }
}