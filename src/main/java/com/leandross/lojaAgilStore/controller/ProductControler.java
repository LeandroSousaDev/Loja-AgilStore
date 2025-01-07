package com.leandross.lojaAgilStore.controller;

import com.leandross.lojaAgilStore.dto.CreateProductDto;
import com.leandross.lojaAgilStore.dto.ResponseProductDto;
import com.leandross.lojaAgilStore.dto.UpdateProductDto;
import com.leandross.lojaAgilStore.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductControler {

    private final ProductService productService;

    public ProductControler(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<ResponseProductDto> create(@RequestBody CreateProductDto createProductDto) {
        var product = this.productService.createProduct(createProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponseProductDto>> list() {
        var products = this.productService.listProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/name")
    public ResponseEntity<List<ResponseProductDto>> listOrderByName() {
        var products = this.productService.orderByName();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/amount")
    public ResponseEntity<List<ResponseProductDto>> listOrderByAmount() {
        var products = this.productService.orderByAmount();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/price")
    public ResponseEntity<List<ResponseProductDto>> listOrderByPrice() {
        var products = this.productService.orderByPrice();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity<ResponseProductDto> update(@PathVariable("idProduct") String idProduct,
            @RequestBody UpdateProductDto updateProductDto) {
        var product = this.productService.updateProduct(idProduct, updateProductDto);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Void> delete(@PathVariable("idProduct") String idProduct) {
        this.productService.deleteProduct(idProduct);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find/")
    public ResponseEntity<List<ResponseProductDto>> getProduct(@RequestParam(required = false) String idProduct,
            @RequestParam(required = false) String name) {

        if (idProduct != null) {
            var products = this.productService.listById(idProduct);
            return ResponseEntity.status(HttpStatus.OK).body(products);
        } else if (name != null) {
            var products = this.productService.listByNameParcial(name);
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }
        return null;
    }
}
