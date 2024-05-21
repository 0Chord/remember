package harmony.communityservice.board.board.application.port.in;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class LoadImagesResponse {
    private List<LoadImageResponse> image = new ArrayList<>();

    public LoadImagesResponse(List<LoadImageResponse> image) {
        this.image = image;
    }
}