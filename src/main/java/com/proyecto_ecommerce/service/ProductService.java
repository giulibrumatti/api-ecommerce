package com.proyecto_ecommerce.service;

import com.proyecto_ecommerce.dto.request.ProductRequestDTO;
import com.proyecto_ecommerce.dto.response.ProductResponseDTO;
import com.proyecto_ecommerce.exception.ProductNotFoundException;
import com.proyecto_ecommerce.model.Product;
import com.proyecto_ecommerce.repository.IProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository repository;

    private ProductResponseDTO mapperToDTO(Product p) {
        ProductResponseDTO dto = new ProductResponseDTO();
        BeanUtils.copyProperties(p, dto);
        return dto;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        this.repository.save(product);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        BeanUtils.copyProperties(product, productResponseDTO);

        return productResponseDTO;
    }

    @Override
    public List<ProductResponseDTO> getProducts() {
        return this.repository.findAll()
                .stream()
                .map(this::mapperToDTO)
                .toList();
    }

    @Override
    public List<ProductResponseDTO> searchProductByName(String queryName) {
        List<Product> foundProducts = this.repository.findByNameContainingIgnoreCase(queryName);

        if (foundProducts.isEmpty()) {
            throw new ProductNotFoundException(queryName);
        }

        return foundProducts
                .stream()
                .map(this::mapperToDTO)
                .toList();
    }

    @Override
    public ProductResponseDTO searchProductById(Long id) {
        Product product = this.repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));

        return this.mapperToDTO(product);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productDTO) {
        Product product = this.repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));

        BeanUtils.copyProperties(productDTO, product);

        this.repository.save(product);

        return this.mapperToDTO(product);
    }

    @Override
    public ProductResponseDTO deleteProduct(Long id) {
        Product product = this.repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));

        this.repository.delete(product);

        return this.mapperToDTO(product);
    }
}
