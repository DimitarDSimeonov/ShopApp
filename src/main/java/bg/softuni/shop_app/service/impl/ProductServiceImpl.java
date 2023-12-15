package bg.softuni.shop_app.service.impl;

import bg.softuni.shop_app.helper.MyTime;
import bg.softuni.shop_app.model.dto.product.AddProductDTO;
import bg.softuni.shop_app.model.dto.product.ProductOwnerViewDTO;
import bg.softuni.shop_app.model.dto.product.ProductSearchDTO;
import bg.softuni.shop_app.model.dto.product.ProductViewDTO;
import bg.softuni.shop_app.model.entity.Product;
import bg.softuni.shop_app.repository.ProductRepository;
import bg.softuni.shop_app.service.ProductService;
import bg.softuni.shop_app.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final MyTime myTime;
    private static final String PRODUCT_NOT_FOUND_BY_ID_ERROR_MESSAGE = "Not found product with id: ";


    public ProductServiceImpl(UserService userService, ModelMapper modelMapper, ProductRepository productRepository, MyTime myTime) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.myTime = myTime;
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
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductOwnerViewDTO getOwnerViewById(Long id) {
        return productRepository.findById(id)
                .map(product ->modelMapper.map(product, ProductOwnerViewDTO.class))
                .orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND_BY_ID_ERROR_MESSAGE + id));
    }


    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND_BY_ID_ERROR_MESSAGE + id));
    }

    @Override
    public List<ProductViewDTO> searchByInput(ProductSearchDTO productSearchDTO) {

        return productRepository.findByTitleContainingIgnoreCaseAndPriceLessThanEqualAndLocationAndCategory(productSearchDTO.getTitle(), productSearchDTO.getMaxPrice(),
                productSearchDTO.getLocation(), productSearchDTO.getCategory())
                .stream()
                .map(product -> modelMapper.map(product, ProductViewDTO.class))
                .toList();
    }

    @Override
    public ProductViewDTO getDetailsViewById(Long id) {
        return productRepository
                .findById(id)
                .map(product ->
            modelMapper.map(product, ProductViewDTO.class)
        )
                .orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND_BY_ID_ERROR_MESSAGE + id));
    }

    @Override
    public void clearOldProduct() {

        List<Product> oldProduct = productRepository.findAllByDateOfPostBefore(myTime.getNow().minusMonths(1)).orElse(new ArrayList<>());

        if (!oldProduct.isEmpty()) {
            productRepository.deleteAll(oldProduct);
        }
    }

    @Override
    public List<ProductViewDTO> getLastProducts() {
        return productRepository
                .findTop10OrderByDateOfPostDesc()
                .stream()
                .map(product -> modelMapper.map(product, ProductViewDTO.class))
                .toList();
    }

    @Override
    public List<ProductViewDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductViewDTO.class))
                .toList();
    }
}

//ToDo: change text with details view in html!
