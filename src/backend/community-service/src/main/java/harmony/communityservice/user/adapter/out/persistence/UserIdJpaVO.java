package harmony.communityservice.user.adapter.out.persistence;

import harmony.communityservice.common.domainentity.EntityValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
public class UserIdJpaVO extends EntityValueObject<UserIdJpaVO> {

    @Column(name = "user_id")
    private Long id;

    public static UserIdJpaVO make(Long userId) {
        return new UserIdJpaVO(userId);
    }

    @Override
    protected Object[] getEqualityFields() {
        return new Object[] { id };
    }
}
