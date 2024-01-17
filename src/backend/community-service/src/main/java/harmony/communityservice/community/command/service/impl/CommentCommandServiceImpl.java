package harmony.communityservice.community.command.service.impl;

import harmony.communityservice.community.command.dto.CommentRegistrationRequestDto;
import harmony.communityservice.community.command.repository.CommentCommandRepository;
import harmony.communityservice.community.command.service.CommentCommandService;
import harmony.communityservice.community.domain.Board;
import harmony.communityservice.community.domain.Comment;
import harmony.communityservice.community.mapper.ToCommentMapper;
import harmony.communityservice.community.query.service.BoardQueryService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentCommandServiceImpl implements CommentCommandService {

    private final CommentCommandRepository commentCommandRepository;
    private final BoardQueryService boardQueryService;

    @Override
    public void save(CommentRegistrationRequestDto requestDto) {

        Board findBoard = boardQueryService.findBoardByBoardId(requestDto.getBoardId());
        Comment comment = ToCommentMapper.convert(requestDto, findBoard);
        commentCommandRepository.save(comment);
    }
}
