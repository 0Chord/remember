package harmony.communityservice.community.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Emoji> emojis = new ArrayList<>();

    @NotBlank
    @Column(name = "title")
    private String title;

    @NotBlank
    @Column(name = "content")
    private String content;

    @NotBlank
    @Column(name = "writer_name")
    private String writerName;

    @NotBlank
    @Column(name = "writer_profile")
    private String writerProfile;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "modified")
    private boolean modified;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Builder
    public Board(Channel channel, List<Image> images,
                 String title, String content, String writerName, Long userId, String writerProfile) {
        this.channel = channel;
        this.images = images;
        this.title = title;
        this.content = content;
        this.writerName = writerName;
        this.userId = userId;
        this.modified = false;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.writerProfile = writerProfile;
    }

    public int commentCount() {
        return comments.size();
    }

    public void checkWriter(Long userId) {
        if (!this.userId.equals(userId)) {
            throw new IllegalStateException();
        }
    }

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
        this.modified = true;
        this.modifiedAt = LocalDateTime.now();
    }
}
