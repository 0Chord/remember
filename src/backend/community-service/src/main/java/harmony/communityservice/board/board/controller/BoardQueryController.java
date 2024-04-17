package harmony.communityservice.board.board.controller;

import harmony.communityservice.board.board.dto.SearchBoardDetailResponse;
import harmony.communityservice.board.board.dto.SearchBoardResponse;
import harmony.communityservice.board.board.service.query.BoardQueryService;
import harmony.communityservice.common.annotation.AuthorizeUser;
import harmony.communityservice.common.dto.BaseResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AuthorizeUser
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/community")
public class BoardQueryController {

    private final BoardQueryService boardQueryService;

    @GetMapping("/search/board/list/{guildId}/{channelId}/{cursor}/{userId}")
    public BaseResponse<?> searchList(@PathVariable Long userId, @PathVariable Long guildId,
                                      @PathVariable Long channelId,
                                      @PathVariable Long cursor) {
        List<SearchBoardResponse> searchBoardResponses = boardQueryService.searchList(channelId, cursor);
        return new BaseResponse<>(HttpStatus.OK.value(), "OK", searchBoardResponses);
    }

    @GetMapping("/search/board/{boardId}/{userId}")
    public BaseResponse<?> search(@PathVariable Long userId, @PathVariable Long boardId) {
        SearchBoardDetailResponse searchBoardDetailResponse = boardQueryService.searchDetail(boardId);
        return new BaseResponse<>(HttpStatus.OK.value(), "OK", searchBoardDetailResponse);
    }
}
