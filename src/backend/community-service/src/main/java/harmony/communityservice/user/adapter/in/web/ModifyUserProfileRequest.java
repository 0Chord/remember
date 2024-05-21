package harmony.communityservice.user.adapter.in.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ModifyUserProfileRequest(@NotNull Long userId,
                                       @NotBlank String profile) {

}