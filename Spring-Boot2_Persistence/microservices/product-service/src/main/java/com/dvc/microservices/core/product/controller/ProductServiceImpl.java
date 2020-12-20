package com.dvc.microservices.core.product.controller;

import com.dvc.microservices.api.core.product.Product;
import com.dvc.microservices.api.core.product.ProductService;
import com.dvc.microservices.core.product.persistence.ProductEntity;
import com.dvc.microservices.core.product.persistence.ProductRepository;
import com.dvc.microservices.util.exceptions.InvalidInputException;
import com.dvc.microservices.util.exceptions.NotFoundException;
import com.dvc.microservices.util.http.ServiceUtil;
import com.mongodb.DuplicateKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductServiceImpl implements ProductService {

    private final ServiceUtil serviceUtil;

    private final ProductRepository repository;

    private final ProductMapper mapper;

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ProductMapper mapper, ServiceUtil serviceUtil) {
        this.repository = repository;
        this.mapper = mapper;
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Product createProduct(Product body) {
        try {
            ProductEntity entity = mapper.apiToEntity(body);
            ProductEntity newEntity = repository.save(entity);
            return mapper.entityToApi(newEntity);
        } catch (DuplicateKeyException dke) {
            throw new InvalidInputException("Duplicate key, Product Id: " +
                    body.getProductId());
        }
    }

    @Override
    public Product getProduct(int productId) {
        if (productId < 1) throw new InvalidInputException("Invalid productId: " + productId);
        ProductEntity entity = repository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException("No product found for productId: " + productId));
        Product response = mapper.entityToApi(entity);
        response.setServiceAddress(serviceUtil.getServiceAddress());
        return response;
    }

    @Override
    public void deleteProduct(int productId) {
        repository.findByProductId(productId).ifPresent(e -> repository.delete(e));
    }
}
