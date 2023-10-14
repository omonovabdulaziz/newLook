package it.live.newlook.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.live.newlook.entity.tmp.AbsLongEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "orders")
public class Order extends AbsLongEntity {
    @ManyToOne
    private Product product;
    private Integer count;
    @ManyToOne
    private User user;
}
