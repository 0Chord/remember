package harmony.communityservice.board.board.domain;

import harmony.communityservice.common.domain.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content extends ValueObject<Content> {

    @NotBlank
    @Column(name = "title")
    private String title;

    @NotBlank
    @Column(name = "content")
    private String content;

    public static Content make(String title, String content) {
        return new Content(title, content);
    }

    public Content modify(String title, String content) {
        return new Content(title, content);
    }

    @Override
    protected Object[] getEqualityFields() {
        return new Object[]{title, content};
    }
}
