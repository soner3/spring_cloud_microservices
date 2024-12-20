package net.sonerapp.recommendation.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "recommendations")
@Data
@NoArgsConstructor
public class Recommendation {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private UUID recommendationId;

    private UUID productId;

    private String author;

    private int rate;

    private String content;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Recommendation(UUID productId, String author, int rate, String content) {
        this.recommendationId = UUID.randomUUID();
        this.productId = productId;
        this.author = author;
        this.rate = rate;
        this.content = content;
    }

}
