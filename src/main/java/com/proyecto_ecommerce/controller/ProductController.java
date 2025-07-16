package com.proyecto_ecommerce.controller;

import com.proyecto_ecommerce.dto.request.ProductRequestDTO;
import com.proyecto_ecommerce.dto.response.ProductResponseDTO;
import com.proyecto_ecommerce.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private IProductService service;

    @Operation(summary= "Crear producto", description="Agregar un nuevo producto al sistema")
    @PostMapping("")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO pRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createProduct(pRequestDto));
    }


}
