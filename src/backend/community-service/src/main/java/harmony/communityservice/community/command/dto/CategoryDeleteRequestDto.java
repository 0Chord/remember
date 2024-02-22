package harmony.communityservice.community.command.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CategoryDeleteRequestDto {

    @NotNull
    private Long guildId;
    @NotNull
    private Long categoryId;
    @NotNull
    private Long userId;
}
