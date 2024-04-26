package harmony.communityservice.guild.category.repository.query.impl;

import harmony.communityservice.guild.category.repository.query.CategoryQueryRepository;
import harmony.communityservice.guild.category.repository.query.jpa.JpaCategoryQueryRepository;
import harmony.communityservice.guild.category.domain.Category;
import harmony.communityservice.guild.guild.domain.GuildId;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryQueryRepositoryImpl implements CategoryQueryRepository {

    private final JpaCategoryQueryRepository jpaCategoryQueryRepository;

    @Override
    public List<Category> findListByGuildId(GuildId guildId) {
        return jpaCategoryQueryRepository.findCategoriesByGuildId(guildId);
    }
}
