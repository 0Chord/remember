package harmony.communityservice.community.query.service;

import harmony.communityservice.community.domain.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserQueryService {

    User searchByUserId(Long userId);
}
