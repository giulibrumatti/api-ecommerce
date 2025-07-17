package com.proyecto_ecommerce.controller;

import com.proyecto_ecommerce.dto.request.ProductRequestDTO;
import com.proyecto_ecommerce.dto.response.ProductResponseDTO;
import com.proyecto_ecommerce.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "${FRONT_END_URL")
public class ProductController {

    @Autowired
    private IProductService service;

    @Operation(summary= "Crear producto", description="Agregar un nuevo producto al sistema")
    @ApiResponse(responseCode = "201", description = "Producto creado")
    @PostMapping("")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO pRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createProduct(pRequestDto));
    }
    @Operation(summary = "Listar Productos", description = "Obtiene un listado de todos los productos del sistema")
    @GetMapping
    public List<ProductResponseDTO> getProducts(){
        return this.service.getProducts();
    }
    @Operation(summary = "Buscar por nombre", description = "Obtiene un listado de todos los productos que contenga el termino buscado en su nombre")
    @GetMapping("/search")
    public List<ProductResponseDTO> searchProductByName(@RequestParam String queryName){
        return this.service.searchProductByName(queryName);
    }

    @Operation(summary = "Buscar por ID", description = "Obtiene un producto que coincida con el ID")
    @GetMapping("/{id}")
    public ProductResponseDTO searchProductById(@PathVariable Long id){
        return this.service.searchProductById(id);
    }

    @Operation(summary = "Actualizar producto", description = "Actualiza un producto usando las propiedades pasadas en la request, esto pasa si el ID es valido")
    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Long id,
                                            @RequestBody ProductRequestDTO pRequestDto){
        return this.service.updateProduct(id, pRequestDto);
    }

    @Operation(summary = "Eliminar producto", description = "Elimina un producto del sistema, esto pasa si el ID es valido")
    @DeleteMapping("/{id}")
    public ProductResponseDTO deleteProduct(@PathVariable Long id){
        return this.service.deleteProduct(id);
    }


}
