package com.project.productservice.services;

import com.project.productservice.dtos.FakeStoreProductDto;
import com.project.productservice.dtos.UserDto;
import com.project.productservice.models.Category;
import com.project.productservice.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeProductService")
@Primary
public class FakeProductService implements ProductService{

    private RestTemplate restTemplate;
    private RestTemplate fakeStoreRestTemplate;

    public FakeProductService(@Qualifier("LoadBalancingRestTemplate") RestTemplate restTemplate, @Qualifier("FakeStoreRestTemplate") RestTemplate fakeStoreRestTemplate) {
        this.restTemplate = restTemplate;
        this.fakeStoreRestTemplate = fakeStoreRestTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        System.out.println("getProductById");
        UserDto userDto = restTemplate.getForObject("https://userservice:9000/users/1", UserDto.class);
        if(userDto == null){
            throw new RuntimeException("User not found");
        }
        FakeStoreProductDto fakeStoreProductDto = fakeStoreRestTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id,
                FakeStoreProductDto.class);
        return convertDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos =
        fakeStoreRestTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(convertDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = convertProductToDto(product);
        fakeStoreProductDto = fakeStoreRestTemplate.patchForObject("https://fakestoreapi.com/products/"+id, fakeStoreProductDto, FakeStoreProductDto.class);
        return convertDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = convertProductToDto(product);
        fakeStoreRestTemplate.put("https://fakestoreapi.com/products/"+id, fakeStoreProductDto, FakeStoreProductDto.class);
        //fakeStoreProductDto = fakeStoreRestTemplate.patchForObject();
        return convertDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = convertProductToDto(product);
        fakeStoreProductDto = fakeStoreRestTemplate
                .postForObject("https://fakestoreapi.com/products",
                        fakeStoreProductDto, FakeStoreProductDto.class);
        return convertDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product deleteProduct() {
        return null;
    }

    private Product convertDtoToProduct(FakeStoreProductDto dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();

        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());

        Category category = new Category();
        category.setId(0L);
        category.setTitle(dto.getCategory());

        product.setCategory(category);
        return product;
    }

    public static FakeStoreProductDto convertProductToDto(Product product) {
        if (product == null) {
            return null;
        }

        FakeStoreProductDto dto = new FakeStoreProductDto();

        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());

        // Assuming the category title is what needs to be set in the DTO's category field
        if (product.getCategory() != null) {
            dto.setCategory(product.getCategory().getTitle());
        } else {
            dto.setCategory(null); // or set to a default value as appropriate
        }
        return dto;
    }
}
