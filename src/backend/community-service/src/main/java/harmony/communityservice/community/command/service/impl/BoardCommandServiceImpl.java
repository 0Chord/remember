package harmony.communityservice.community.command.service.impl;

import harmony.communityservice.common.service.ContentService;
import harmony.communityservice.community.command.dto.DeleteBoardRequest;
import harmony.communityservice.community.command.dto.RegisterBoardRequest;
import harmony.communityservice.community.command.dto.ModifyBoardRequest;
import harmony.communityservice.community.command.repository.BoardCommandRepository;
import harmony.communityservice.community.command.service.BoardCommandService;
import harmony.communityservice.community.command.service.ImageCommandService;
import harmony.communityservice.community.domain.Board;
import harmony.communityservice.community.domain.Channel;
import harmony.communityservice.community.domain.UserRead;
import harmony.communityservice.community.mapper.ToBoardMapper;
import harmony.communityservice.community.query.service.BoardQueryService;
import harmony.communityservice.community.query.service.ChannelQueryService;
import harmony.communityservice.community.query.service.UserReadQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
public class BoardCommandServiceImpl implements BoardCommandService {

    private final ContentService contentService;
    private final UserReadQueryService userReadQueryService;
    private final ChannelQueryService channelQueryService;
    private final ImageCommandService imageCommandService;
    private final BoardCommandRepository boardCommandRepository;
    private final BoardQueryService boardQueryService;

    @Override
    public void register(RegisterBoardRequest registerBoardRequest, List<MultipartFile> images) {
        userReadQueryService.existsByUserIdAndGuildId(registerBoardRequest.getUserId(), registerBoardRequest.getGuildId());
        List<String> uploadedImageUrls = images.stream()
                .map(contentService::convertFileToUrl).toList();
        UserRead boardWriter = userReadQueryService.searchByUserIdAndGuildId(registerBoardRequest.getUserId(),
                registerBoardRequest.getGuildId());
        Channel targetChannel = channelQueryService.searchByChannelId(registerBoardRequest.getChannelId());
        Board board = ToBoardMapper.convert(registerBoardRequest, boardWriter, targetChannel);
        boardCommandRepository.save(board);
        imageCommandService.registerImagesInBoard(uploadedImageUrls, board);
    }

    @Override
    public void modify(ModifyBoardRequest modifyBoardRequest) {
        Board targetBoard = boardQueryService.searchByBoardId(modifyBoardRequest.getBoardId());
        targetBoard.verifyWriter(modifyBoardRequest.getUserId());
        targetBoard.modifyTitleAndContent(modifyBoardRequest.getTitle(), modifyBoardRequest.getContent());
    }

    @Override
    public void delete(DeleteBoardRequest deleteBoardRequest) {
        Board targetBoard = boardQueryService.searchByBoardId(deleteBoardRequest.getBoardId());
        targetBoard.verifyWriter(deleteBoardRequest.getUserId());
        boardCommandRepository.delete(targetBoard);
    }
}
