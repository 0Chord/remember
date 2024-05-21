package harmony.communityservice.user.adapter.out.persistence;

import harmony.communityservice.user.domain.User;

class UserMapper {

    static User convert(UserEntity userEntity) {
        return User.builder()
                .userId(userEntity.getUserId().getId())
                .email(userEntity.getUserInfo().getEmail())
                .profile(userEntity.getUserInfo().getCommonUserInfo().getUserProfile())
                .nickname(userEntity.getUserInfo().getCommonUserInfo().getNickname())
                .build();
    }
}