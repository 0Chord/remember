package harmony.communityservice.common.event.dto.inner;

import harmony.communityservice.board.board.domain.Board.BoardId;

public record DeleteEmojiEvent(BoardId boardId) {
}
