package harmony.communityservice.room.mapper;

import harmony.communityservice.user.domain.CommonUserInfo;
import harmony.communityservice.user.domain.User;
import harmony.communityservice.user.domain.UserRead;
import harmony.communityservice.room.dto.SearchUserStateResponse;

public class ToSearchUserStateResponseMapper {

    public static SearchUserStateResponse convert(User user, String status) {
        CommonUserInfo commonUserInfo = user.getUserInfo().getCommonUserInfo();
        return SearchUserStateResponse.builder()
                .userId(user.getUserId())
                .profile(commonUserInfo.getProfile())
                .state(status)
                .userName(commonUserInfo.getNickname())
                .build();
    }

    public static SearchUserStateResponse convert(UserRead userRead) {
        return SearchUserStateResponse.builder()
                .userName(userRead.getUserInfo().getNickname())
                .profile(userRead.getUserInfo().getProfile())
                .userId(userRead.getUserId())
                .build();
    }
}