package harmony.communityservice.community.query.dto;

import harmony.communityservice.community.domain.EmojiUser;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EmojiResponseDto {
    private Long emojiId;
    private Long boardId;
    private Long commentId;
    private Long emojiType;
    private List<Long> emojiUsers = new ArrayList<>();

    @Builder
    public EmojiResponseDto(Long emojiId, Long boardId, Long commentId, Long emojiType, List<Long> emojiUsers) {
        this.emojiId = emojiId;
        this.boardId = boardId;
        this.commentId = commentId;
        this.emojiType = emojiType;
        this.emojiUsers = emojiUsers;
    }
}
