package com.bellpro.selectshop.service;

import com.bellpro.selectshop.model.Product;
import com.bellpro.selectshop.dto.ProductMypriceRequestDto;
import com.bellpro.selectshop.repository.ProductRepository;
import com.bellpro.selectshop.dto.ProductRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository ){
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductRequestDto requestDto) {
// 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);


        productRepository.save(product);

        return product;
    }

    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto) {
        if (requestDto.getMyprice() <= 0 ){
            throw new RuntimeException("희망 최저가는 0원 이상으로 설정해 주세요!!!");
        }
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
        if (product == null) {
            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
        }

        int myprice = requestDto.getMyprice();
        product.setMyprice(myprice);
        productRepository.save(product);

        return product;
    }

    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();

        return products;
    }
}