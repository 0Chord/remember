package harmony.communityservice.guild.guild.adapter.in.web;

import harmony.communityservice.common.annotation.WebAdapter;
import harmony.communityservice.common.dto.BaseResponse;
import harmony.communityservice.guild.guild.application.port.in.LoadUserGuildListQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/api/community")
public class LoadUserGuildListController {

    private final LoadUserGuildListQuery loadUserGuildListQuery;

    @GetMapping("/search/guild/{userId}")
    public BaseResponse<?> searchBelongToUser(@PathVariable Long userId) {
        return new BaseResponse<>(HttpStatus.OK.value(), "OK", loadUserGuildListQuery.loadGuilds(userId));
    }
}