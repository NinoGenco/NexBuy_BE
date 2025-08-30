package org.polimi.nexbuy.service;

import org.polimi.nexbuy.model.Image;
import org.polimi.nexbuy.model.Product;
import org.polimi.nexbuy.model.Review;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ImageService {

    void saveProductImages(List<MultipartFile> files, Product product);

    void saveReviewImages(List<MultipartFile> files, Review review);

    void saveImages(List<MultipartFile> files, Product product, Review review);

    Image convertAndSave(MultipartFile file, Product product, Review review) throws IOException, SQLException;

    void deleteImages(List<Long> imageIds);

}
