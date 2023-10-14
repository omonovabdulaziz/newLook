package it.live.newlook.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import it.live.newlook.entity.tmp.AbsLongEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Attachment extends AbsLongEntity {
    private String fileName;
    private String fileOriginalName;
    private long size;
    private String contentType;
    private String downloadUrl;
}
