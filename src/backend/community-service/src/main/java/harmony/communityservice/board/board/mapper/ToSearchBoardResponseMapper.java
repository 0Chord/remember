package harmony.communityservice.board.board.mapper;

import harmony.communityservice.board.domain.Board;
import harmony.communityservice.board.board.dto.SearchBoardDetailResponse;
import harmony.communityservice.board.comment.dto.SearchCommentsResponse;
import harmony.communityservice.board.emoji.dto.SearchEmojisResponse;

public class ToSearchBoardResponseMapper {
    public static SearchBoardDetailResponse convert(Board board, SearchCommentsResponse searchCommentsResponse,
                                                    SearchEmojisResponse searchEmojisResponse) {
        return SearchBoardDetailResponse.builder()
                .title(board.getContent().getTitle())
                .content(board.getContent().getContent())
                .searchCommentsResponse(searchCommentsResponse)
                .searchEmojisResponse(searchEmojisResponse)
                .boardId(board.getBoardId())
                .searchImagesResponse(board.makeSearchImagesResponse())
                .modified(board.getModifiedInfo().getModifiedType())
                .createdAt(board.getCreationTime().getCreatedAt())
                .userId(board.getWriterInfo().getWriterId())
                .writerName(board.getWriterInfo().getCommonUserInfo().getNickname())
                .build();
    }
}