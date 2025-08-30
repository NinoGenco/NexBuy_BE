package org.polimi.nexbuy.service.implementation;

import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.polimi.nexbuy.model.Image;
import org.polimi.nexbuy.model.Product;
import org.polimi.nexbuy.model.Review;
import org.polimi.nexbuy.repository.ImageRepository;
import org.polimi.nexbuy.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SuppressWarnings("All")
@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final ExecutorService executorService = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() * 2,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    public void saveProductImages(List<MultipartFile> files, Product product) {
        saveImages(files, product, null);
    }

    public void saveReviewImages(List<MultipartFile> files, Review review) {
        saveImages(files, null, review);
    }

    public void saveImages(List<MultipartFile> files, Product product, Review review) {
        List<Image> images = new ArrayList<>();

        for (MultipartFile file : files) {
            images.add(convertAndSave(file, product, review));
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 10)
    public Image convertAndSave(MultipartFile file, Product product, Review review) {
        try {
            Blob blob = new SerialBlob(file.getBytes());
            Image image = Image.builder()
                    .content(blob)
                    .product(product)
                    .review(review)
                    .build();
            return imageRepository.save(image);
        } catch (Exception e) {
            throw new RuntimeException("Errore salvataggio immagine", e);
        }
    }

    @Transactional
    public void deleteImages(List<Long> imageIds) {
        if (imageIds != null && !imageIds.isEmpty()) {
            imageRepository.deleteAllById(imageIds);
        }
    }
}
