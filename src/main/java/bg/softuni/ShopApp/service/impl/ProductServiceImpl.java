package bg.softuni.ShopApp.service.impl;

import bg.softuni.ShopApp.model.DTO.product.AddProductDTO;
import bg.softuni.ShopApp.model.DTO.product.ProductOwnerViewDTO;
import bg.softuni.ShopApp.model.DTO.product.ProductSearchDTO;
import bg.softuni.ShopApp.model.DTO.product.ProductViewDTO;
import bg.softuni.ShopApp.model.entity.Product;
import bg.softuni.ShopApp.repository.ProductRepository;
import bg.softuni.ShopApp.service.ProductService;
import bg.softuni.ShopApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ProductViewDTO> searchByInput(ProductSearchDTO productSearchDTO) {
        if (productSearchDTO.getTitle().isBlank()) {
            productSearchDTO.setTitle(null);
        }
        return productRepository.findByTitleContainingIgnoreCaseAndPriceLessThanEqualAndLocationAndCategory(productSearchDTO.getTitle(), productSearchDTO.getMaxPrice(),
                productSearchDTO.getLocation(), productSearchDTO.getCategory())
                .stream()
                .map(product -> {
                    ProductViewDTO productViewDTO = modelMapper.map(product, ProductViewDTO.class);
                    return productViewDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProductViewDTO getDetailsViewById(Long id) {
        return productRepository
                .findById(id)
                .map(product ->
            modelMapper.map(product, ProductViewDTO.class)
        )
                .get();
    }
}
//ToDo: change text view in html!
