package com.fedilinks.fedilinksapi.community;

import com.fedilinks.fedilinksapi.comment.Comment;
import com.fedilinks.fedilinksapi.instance.Instance;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "communities")
public class Community implements Serializable {
    /**
     * Relationships
     */
    @ManyToOne
    @JoinColumn(name = "instance_id")
    private Instance instance;

    @OneToMany(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private List<Comment> comments;

    /**
     * Attributes
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "activity_pub_id")
    private String activityPubId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, name = "title_slug")
    private String titleSlug;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, name = "is_deleted")
    private boolean isDeleted;

    @Column(nullable = false, name = "is_removed")
    private boolean isRemoved;

    @Column(nullable = false, name = "is_local")
    private boolean isLocal;

    @Column(nullable = false, name = "is_nsfw")
    private boolean isNsfw;

    @Column(nullable = false, name = "is_posting_restricted_to_mods")
    private boolean isPostingRestrictedToMods;

    @Column(nullable = false, name = "icon_image_url")
    private String iconImageUrl;

    @Column(nullable = false, name = "banner_image_url")
    private String bannerImageUrl;

    @Column(nullable = false, name = "public_key")
    private String publicKey;

    @Column(nullable = true, name = "private_key")
    private String privateKey;

    @CreationTimestamp
    @Column(updatable = false, nullable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(updatable = false, nullable = false, name = "updated_at")
    private Date updatedAt;
}