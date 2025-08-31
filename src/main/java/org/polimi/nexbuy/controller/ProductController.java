package org.polimi.nexbuy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.polimi.nexbuy.dto.input.product.ProductDTO;
import org.polimi.nexbuy.dto.input.product.UpdateProductDTO;
import org.polimi.nexbuy.dto.mapper.product.ProductMapper;
import org.polimi.nexbuy.dto.mapper.product.UpdateProductMapper;
import org.polimi.nexbuy.dto.output.product.ProductSummaryDTO;
import org.polimi.nexbuy.exception.DataAccessServiceException;
import org.polimi.nexbuy.exception.DuplicateEmailException;
import org.polimi.nexbuy.exception.SendingMailException;
import org.polimi.nexbuy.exception.customExceptions.InvalidDataException;
import org.polimi.nexbuy.exception.customExceptions.product.ProductNotFoundException;
import org.polimi.nexbuy.exception.customExceptions.user.UserNotFoundException;
import org.polimi.nexbuy.response.ResponseMessage;
import org.polimi.nexbuy.service.implementation.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@SuppressWarnings("All")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductServiceImpl productService;
    private final ProductMapper productMapper;
    private final UpdateProductMapper updateProductMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            consumes = { "multipart/form-data" },
            produces = "application/json"
    )
    public ResponseEntity<ResponseMessage> createProduct(
            @RequestParam("product") String productJson,
            @RequestParam("subCategoryId") Long subCategoryId,
            @RequestParam(value = "files", required = false) List<MultipartFile> files
    ) throws DataAccessServiceException {

        try {
            ProductDTO productDTO = objectMapper.readValue(productJson, ProductDTO.class);
            productService.createProduct(productMapper.productDTOToProduct(productDTO), files, subCategoryId);

            return new ResponseEntity<>(new ResponseMessage("Prodotto registrato"), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            throw new HttpMessageNotReadableException("JSON non valido: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> update(
            @PathVariable Long id,
            @RequestPart("product") String productJson,
            @RequestPart(value = "files", required = false) List<MultipartFile> files,
            @RequestParam(value = "subCategoryId", required = false) Long subCategoryId,
            @RequestParam(value = "deleteImages", required = false) List<Long> deleteImages
    ) throws UserNotFoundException, IllegalAccessException, InvalidDataException, DuplicateEmailException, SendingMailException {

        try {
            UpdateProductDTO productDTO = objectMapper.readValue(productJson, UpdateProductDTO.class);
            boolean isUpdated = productService.updateProduct(
                    id,
                    updateProductMapper.productDTOToProduct(productDTO),
                    files,
                    subCategoryId,
                    deleteImages
            );

            if (isUpdated) {
                return ResponseEntity.ok(new ResponseMessage("Prodotto aggiornato con successo"));
            } else {
                return ResponseEntity.ok(new ResponseMessage("Nessun campo modificato"));
            }
        } catch (JsonParseException | DataAccessServiceException | JsonProcessingException e) {
            throw new HttpMessageNotReadableException("Messaggio non leggibile");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> deleteProduct(@PathVariable Long id)
            throws ProductNotFoundException, DataAccessServiceException {
        productService.deleteProduct(id);
        return new ResponseEntity<>(new ResponseMessage("Prodotto eliminato"), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductSummaryDTO> getProductById(@PathVariable Long id) throws ProductNotFoundException, IllegalAccessException {
        ProductSummaryDTO product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<ProductSummaryDTO>> getAllProducts() throws DataAccessServiceException, IllegalAccessException {
        List<ProductSummaryDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
