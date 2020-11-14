package com.dvc.microservices.core.product.controller;

import com.dvc.microservices.api.core.product.Product;
import com.dvc.microservices.api.core.product.ProductService;
import com.dvc.microservices.util.exceptions.InvalidInputException;
import com.dvc.microservices.util.exceptions.NotFoundException;
import com.dvc.microservices.util.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ServiceUtil serviceUtil;

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public Product getProduct(int productId) {

        if (productId < 1) throw new InvalidInputException("Invalid productId: " + productId);

        if (productId == 13) throw new NotFoundException("No product found for productId: " + productId);

        return new Product(productId, "name-" + productId, 123,
                serviceUtil.getServiceAddress());
    }
}
