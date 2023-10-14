package it.live.newlook.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    @NotNull(message = "Miqdor kiriting")
    private Integer count;
    @NotNull(message = "product kiriting")

    private Long productId;
    @NotNull(message = "user kiriting")
    private Long userId;
}
