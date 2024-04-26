package harmony.communityservice.guild.guild.repository.command.jpa;

import harmony.communityservice.guild.guild.domain.Guild;
import harmony.communityservice.guild.guild.domain.GuildId;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface JpaGuildCommandRepository extends JpaRepository<Guild, GuildId> {
    @Lock(value = LockModeType.PESSIMISTIC_READ)
    Optional<Guild> findByInviteCode(String code);
}
