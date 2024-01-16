package harmony.communityservice.community.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "emoji_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmojiUser {

    @Id
    @Column(name = "emoji_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emojiUserId;

    @ManyToOne
    @JoinColumn(name = "emoji_id")
    private Emoji emoji;

    private Long userId;
}
