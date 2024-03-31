package harmony.communityservice.community.query.service.impl;

import harmony.communityservice.common.dto.VerifyGuildMemberRequest;
import harmony.communityservice.common.exception.NotFoundDataException;
import harmony.communityservice.community.domain.CategoryRead;
import harmony.communityservice.community.query.repository.CategoryReadQueryRepository;
import harmony.communityservice.community.query.service.CategoryReadQueryService;
import harmony.communityservice.community.query.service.UserReadQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryReadQueryServiceImpl implements CategoryReadQueryService {
    private final UserReadQueryService userReadQueryService;
    private final CategoryReadQueryRepository categoryReadQueryRepository;

    @Override
    public List<CategoryRead> searchListByGuildId(long guildId, long userId) {
        userReadQueryService.existsByUserIdAndGuildId(new VerifyGuildMemberRequest(userId, guildId));
        return categoryReadQueryRepository.findAllByGuildId(guildId);
    }

    @Override
    public CategoryRead searchByCategoryId(long categoryId) {
        return categoryReadQueryRepository.findCategoryReadById(categoryId).orElseThrow(NotFoundDataException::new);
    }

    @Override
    public void existsByCategoryIdAndGuildId(long categoryId, long guildId) {
        if (!categoryReadQueryRepository.existsByCategoryIdAndGuildId(categoryId, guildId)) {
            throw new NotFoundDataException();
        }
    }
}
