package it.live.newlook.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.live.newlook.entity.tmp.AbsLongEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends AbsLongEntity {
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String phoneNumber;
}
