package harmony.communityservice.room.dto;

import harmony.communityservice.common.dto.VerifyUserRequest;
import jakarta.validation.constraints.NotNull;

public record DeleteRoomRequest(@NotNull Long roomId, @NotNull Long firstUser, @NotNull Long secondUser) implements
        VerifyUserRequest {
    @Override
    public Long getUserId() {
        return firstUser;
    }
}
