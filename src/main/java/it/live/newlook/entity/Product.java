package it.live.newlook.entity;

import it.live.newlook.entity.tmp.AbsLongEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends AbsLongEntity {
    @Column(nullable = false)
    private String name;
    @OneToOne
    private Attachment attachment;
}
