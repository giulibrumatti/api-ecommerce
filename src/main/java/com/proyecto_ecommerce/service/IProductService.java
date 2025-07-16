package com.proyecto_ecommerce.service;

import com.proyecto_ecommerce.dto.request.ProductRequestDTO;
import com.proyecto_ecommerce.dto.response.ProductResponseDTO;

import java.util.List;

public interface IProductService {

    public ProductResponseDTO createProduct(ProductRequestDTO productDTO);
    public List<ProductResponseDTO> getProducts();
    public List<ProductResponseDTO> searchProductByName(String queryName);
    public ProductResponseDTO searchProductById(Long id);
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productDTO);
    public ProductResponseDTO deleteProduct(Long id);


}
