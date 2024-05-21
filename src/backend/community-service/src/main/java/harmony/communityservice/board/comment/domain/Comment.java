package harmony.communityservice.board.comment.domain;

import harmony.communityservice.board.board.domain.Board.BoardId;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Comment {

    private CommentId commentId;

    private String comment;

    private BoardId boardId;

    private WriterInfo writerInfo;
    private Instant createdAt;
    private ModifiedType type;

    @Builder
    public Comment(BoardId boardId, String comment, CommentId commentId, Long writerId, String nickname,
                   String profile, Instant createdAt, ModifiedType type) {
        this.boardId = boardId;
        this.comment = comment;
        this.commentId = commentId;
        this.writerInfo = makeWriterInfo(writerId, nickname, profile);
        this.createdAt = createdAt;
        this.type = type;
    }

    private WriterInfo makeWriterInfo(Long writerId, String nickname, String profile) {
        return WriterInfo.builder()
                .userName(nickname)
                .profile(profile)
                .writerId(writerId)
                .build();
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CommentId {
        private final Long id;

        public static CommentId make(Long commentId) {
            return new CommentId(commentId);
        }
    }
}