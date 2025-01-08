package com.leandross.lojaAgilStore.service;

import com.leandross.lojaAgilStore.dto.CreateProductDto;
import com.leandross.lojaAgilStore.dto.ResponseProductDto;
import com.leandross.lojaAgilStore.dto.UpdateProductDto;
import com.leandross.lojaAgilStore.entity.ProductEntity;
import com.leandross.lojaAgilStore.exception.CustomException;
import com.leandross.lojaAgilStore.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {

        private final ProductRepository productRepository;

        public ProductService(ProductRepository productRepository) {
                this.productRepository = productRepository;
        }

        public ResponseProductDto createProduct(CreateProductDto createProductDto) {

                var newProduct = new ProductEntity();
                newProduct.setName(createProductDto.name());
                newProduct.setCategory(createProductDto.category());
                newProduct.setAmount(createProductDto.amount());
                newProduct.setPrice(createProductDto.price());

                this.productRepository.save(newProduct);

                return new ResponseProductDto(
                                newProduct.getIdProduct(),
                                newProduct.getName(),
                                newProduct.getCategory(),
                                newProduct.getAmount(),
                                newProduct.getPrice());
        }

        public List<ResponseProductDto> listProducts() {
                var allProducts = this.productRepository.findAll();

                return allProducts
                                .stream()
                                .map(product -> new ResponseProductDto(
                                                product.getIdProduct(),
                                                product.getName(),
                                                product.getCategory(),
                                                product.getAmount(),
                                                product.getPrice()))
                                .toList();
        }

        public List<ResponseProductDto> orderByName() {
                var allProducts = this.productRepository.findAll();

                var orderedList = allProducts.stream()
                                .sorted(Comparator.comparing(ProductEntity::getName))
                                .toList();

                return orderedList
                                .stream()
                                .map(product -> new ResponseProductDto(
                                                product.getIdProduct(),
                                                product.getName(),
                                                product.getCategory(),
                                                product.getAmount(),
                                                product.getPrice()))
                                .toList();
        }

        public List<ResponseProductDto> orderByAmount() {
                var allProducts = this.productRepository.findAll();

                var orderedList = allProducts.stream()
                                .sorted(Comparator.comparing(ProductEntity::getAmount))
                                .toList();

                return orderedList
                                .stream()
                                .map(product -> new ResponseProductDto(
                                                product.getIdProduct(),
                                                product.getName(),
                                                product.getCategory(),
                                                product.getAmount(),
                                                product.getPrice()))
                                .toList();
        }

        public List<ResponseProductDto> orderByPrice() {
                var allProducts = this.productRepository.findAll();

                var orderedList = allProducts.stream()
                                .sorted(Comparator.comparing(ProductEntity::getPrice))
                                .toList();

                return orderedList
                                .stream()
                                .map(product -> new ResponseProductDto(
                                                product.getIdProduct(),
                                                product.getName(),
                                                product.getCategory(),
                                                product.getAmount(),
                                                product.getPrice()))
                                .toList();
        }

        public ResponseProductDto updateProduct(String idProduct, UpdateProductDto updateProductDto)
                        throws CustomException {
                var product = this.productRepository.findById(Integer.valueOf(idProduct));

                if (product.isEmpty()) {
                        throw new CustomException("Produto não encontrado");
                }

                var updatedProduct = product.get();

                if (updateProductDto.name() != null) {
                        updatedProduct.setName(updateProductDto.name());
                }

                if (updateProductDto.category() != null) {
                        updatedProduct.setCategory(updateProductDto.category());
                }

                if (updateProductDto.amount() != null) {
                        updatedProduct.setAmount(updateProductDto.amount());
                }

                if (updateProductDto.price() != null) {
                        updatedProduct.setPrice(updateProductDto.price());
                }

                this.productRepository.save(updatedProduct);

                return new ResponseProductDto(
                                updatedProduct.getIdProduct(),
                                updatedProduct.getName(),
                                updatedProduct.getCategory(),
                                updatedProduct.getAmount(),
                                updatedProduct.getPrice());

        }

        public void deleteProduct(String idProduct) throws CustomException {

                var product = this.productRepository.findById(Integer.valueOf(idProduct));

                if (product.isEmpty()) {
                        throw new CustomException("Produto não encontrado");

                }

                if (product != null) {
                        this.productRepository.deleteById(Integer.valueOf(idProduct));
                }
        }

        public List<ResponseProductDto> listById(String idProduct) throws CustomException {
                var listProduct = this.productRepository.findByIdProduct(Integer.valueOf(idProduct));

                if (listProduct.isEmpty()) {
                        throw new CustomException("Produto não encontrado");
                }

                return listProduct
                                .stream()
                                .map(product -> new ResponseProductDto(
                                                product.getIdProduct(),
                                                product.getName(),
                                                product.getCategory(),
                                                product.getAmount(),
                                                product.getPrice()))
                                .toList();

        }

        public List<ResponseProductDto> listByNameParcial(String name) throws CustomException {
                var listProduct = this.productRepository.findByNameContaining(name);

                if (listProduct.isEmpty()) {
                        throw new CustomException("Produto não encontrado");
                }

                return listProduct
                                .stream()
                                .map(product -> new ResponseProductDto(
                                                product.getIdProduct(),
                                                product.getName(),
                                                product.getCategory(),
                                                product.getAmount(),
                                                product.getPrice()))
                                .toList();

        }

}
