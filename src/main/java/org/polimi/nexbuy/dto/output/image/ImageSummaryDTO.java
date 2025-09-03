package org.polimi.nexbuy.dto.output.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.polimi.nexbuy.model.Image;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageSummaryDTO {

    private Long id;
    private byte[] content;

    public ImageSummaryDTO(Image image) {
        this.id = image.getId();
        try {
            this.content = image.getContent() != null ? image.getContent().getBytes(1, (int) image.getContent().length()) : null;
        } catch (Exception e) {
            this.content = null;
        }
    }

}
