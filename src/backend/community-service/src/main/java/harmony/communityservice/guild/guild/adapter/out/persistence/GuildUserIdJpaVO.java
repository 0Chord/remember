package harmony.communityservice.guild.guild.adapter.out.persistence;

import harmony.communityservice.common.domainentity.EntityLongTypeIdentifier;
import harmony.communityservice.common.domainentity.EntityLongTypeIdentifierJavaType;

public class GuildUserIdJpaVO extends EntityLongTypeIdentifier {
    public GuildUserIdJpaVO(Long id) {
        super(id);
    }

    public Long getId() {
        return longValue();
    }

    public static class GuildUserIdJavaType extends EntityLongTypeIdentifierJavaType<GuildUserIdJpaVO> {
        public GuildUserIdJavaType() {
            super(GuildUserIdJpaVO.class);
        }
    }
}
