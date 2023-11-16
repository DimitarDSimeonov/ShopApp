package bg.softuni.ShopApp.service.impl;

import bg.softuni.ShopApp.model.DTO.product.AddProductDTO;
import bg.softuni.ShopApp.model.DTO.product.ProductOwnerViewDTO;
import bg.softuni.ShopApp.model.entity.Picture;
import bg.softuni.ShopApp.model.entity.Product;
import bg.softuni.ShopApp.repository.ProductRepository;
import bg.softuni.ShopApp.service.PictureService;
import bg.softuni.ShopApp.service.ProductService;
import bg.softuni.ShopApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;


    public ProductServiceImpl(UserService userService, ModelMapper modelMapper, ProductRepository productRepository) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public Long createProduct(AddProductDTO addProductDTO, String username) {
        Product product = modelMapper.map(addProductDTO, Product.class);
        product.setDateOfPost(LocalDateTime.now());

        product.setSeller(userService.getByUsername(username));

        product = productRepository.saveAndFlush(product);

        return product.getId();
    }

    @Override
    public void removeById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductOwnerViewDTO getOwnerViewById(Long id) {
        return productRepository.findById(id)
                .map(product ->modelMapper.map(product, ProductOwnerViewDTO.class))
                .get();
    }


    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }
}
