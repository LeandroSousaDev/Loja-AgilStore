package com.leandross.lojaAgilStore.service;

import com.leandross.lojaAgilStore.dto.CreateProductDto;
import com.leandross.lojaAgilStore.dto.ResponseProductDto;
import com.leandross.lojaAgilStore.dto.UpdateProductDto;
import com.leandross.lojaAgilStore.entity.ProductEntity;
import com.leandross.lojaAgilStore.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

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

        var orderedList = allProducts.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                .collect(Collectors.toList());

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

        var orderedList = allProducts.stream().sorted((o1, o2) -> o1.getAmount().compareTo(o2.getAmount()))
                .collect(Collectors.toList());

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

        var orderedList = allProducts.stream().sorted((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()))
                .collect(Collectors.toList());

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

    public ResponseProductDto updateProduct(String idProduct, UpdateProductDto updateProductDto) {
        var product = this.productRepository.findById(Integer.valueOf(idProduct))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "produto não encontrado"));

        if (updateProductDto.name() != null) {
            product.setName(updateProductDto.name());
        }

        if (updateProductDto.category() != null) {
            product.setCategory(updateProductDto.category());
        }

        if (updateProductDto.amount() != null) {
            product.setAmount(updateProductDto.amount());
        }

        if (updateProductDto.price() != null) {
            product.setPrice(updateProductDto.price());
        }

        this.productRepository.save(product);

        return new ResponseProductDto(
                product.getIdProduct(),
                product.getName(),
                product.getCategory(),
                product.getAmount(),
                product.getPrice());

    }

    public void deleteProduct(String idProduct) {

        var product = this.productRepository.existsById(Integer.valueOf(idProduct));

        if (product) {
            this.productRepository.deleteById(Integer.valueOf(idProduct));
        }
    }

    public List<ResponseProductDto> listById(String idProduct) {
        var listProduct = this.productRepository.findByIdProduct(Integer.valueOf(idProduct));

        if (listProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
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

    public List<ResponseProductDto> listByNameParcial(String name) {
        var listProduct = this.productRepository.findByNameContaining(name);

        if (listProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
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
