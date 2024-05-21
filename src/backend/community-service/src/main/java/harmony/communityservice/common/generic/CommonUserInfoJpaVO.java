package harmony.communityservice.common.generic;

import harmony.communityservice.common.domain.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonUserInfoJpaVO extends ValueObject<CommonUserInfoJpaVO> {

    @NotBlank
    @Column(name = "user_nickname")
    private String nickname;

    @NotBlank
    @Column(name = "user_profile")
    private String userProfile;

    public static CommonUserInfoJpaVO make(String nickname, String profile) {
        return new CommonUserInfoJpaVO(nickname, profile);
    }

    @Override
    protected Object[] getEqualityFields() {
        return new Object[]{nickname, userProfile};
    }
}