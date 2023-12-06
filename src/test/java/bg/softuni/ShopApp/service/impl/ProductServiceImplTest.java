package bg.softuni.ShopApp.service.impl;

import bg.softuni.ShopApp.model.DTO.product.AddProductDTO;
import bg.softuni.ShopApp.model.DTO.product.ProductOwnerViewDTO;
import bg.softuni.ShopApp.model.DTO.product.ProductSearchDTO;
import bg.softuni.ShopApp.model.DTO.product.ProductViewDTO;
import bg.softuni.ShopApp.model.entity.Product;
import bg.softuni.ShopApp.model.entity.User;
import bg.softuni.ShopApp.repository.ProductRepository;
import bg.softuni.ShopApp.service.ProductService;
import bg.softuni.ShopApp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    private ProductService productServiceToTest;

    @Mock
    private UserService mockUserService;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private ProductRepository mockProductRepository;

    @Mock
    private MyTime mockMyTime;

    @BeforeEach
    void setUp() {
        productServiceToTest = new ProductServiceImpl(mockUserService,
                 mockModelMapper, mockProductRepository, mockMyTime);
    }

    @Test
    void createProduct() {
        AddProductDTO addProductDTO = new AddProductDTO();
        User user = new User();
        Product product = new Product();
        product.setId(1L);

        when(mockUserService.getByUsername(user.getUsername()))
                .thenReturn(user);

        when(mockModelMapper.map(addProductDTO, Product.class))
                .thenReturn(product);

        when(mockProductRepository.saveAndFlush(product))
                .thenReturn(product);

        Long id = productServiceToTest.createProduct(addProductDTO, user.getUsername());

        assertEquals(product.getId(), id);
        assertEquals(1, id);
    }

    @Test
    void deleteById() {
        Long id = 1L;

        productServiceToTest.deleteById(id);

        verify(mockProductRepository).deleteById(id);
    }

    @Test
    void getOwnerViewById() {
        Long id = 1L;
        ProductOwnerViewDTO productOwnerViewDTO = new ProductOwnerViewDTO();
        productOwnerViewDTO.setId(id);
        Product product = new Product();

        when(mockModelMapper.map(product, ProductOwnerViewDTO.class))
                .thenReturn(productOwnerViewDTO);

        when(mockProductRepository.findById(id))
                .thenReturn(Optional.of(product));

        assertNotNull(productServiceToTest.getOwnerViewById(id));
    }

    @Test
    void getById() {
        Long id = 1L;
        Product product = new Product();
        product.setId(id);

        when(mockProductRepository.findById(id))
                .thenReturn(Optional.of(product));

        assertNotNull(productServiceToTest.getById(id));
        assertEquals(product, productServiceToTest.getById(id));
    }

    @Test
    void searchByInput() {
        ProductSearchDTO productSearchDTO = new ProductSearchDTO();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            products.add(new Product());
        }
        ProductViewDTO productViewDTO = new ProductViewDTO();

        when(mockProductRepository.findByTitleContainingIgnoreCaseAndPriceLessThanEqualAndLocationAndCategory(productSearchDTO.getTitle(),
                productSearchDTO.getMaxPrice(), productSearchDTO.getLocation(), productSearchDTO.getCategory()))
                .thenReturn(products);

        when(mockModelMapper.map(any(Product.class), eq(ProductViewDTO.class)))
                .thenReturn(productViewDTO);

        List<ProductViewDTO> result = productServiceToTest.searchByInput(productSearchDTO);

        assertEquals(3, result.size());
    }

    @Test
    void getDetailsViewById() {
        Long id = 1L;
        Product product = new Product();
        ProductViewDTO productViewDTO = new ProductViewDTO();

        when(mockProductRepository.findById(id))
                .thenReturn(Optional.of(product));

        when(mockModelMapper.map(product, ProductViewDTO.class))
                .thenReturn(productViewDTO);

        assertNotNull(productServiceToTest.getDetailsViewById(id));
    }

    @Test
    void clearOldProduct() {
        List<Product> old = new ArrayList<>();
        old.add(new Product());

        when(mockMyTime.getNow())
                .thenReturn(LocalDateTime
                        .of(2222,2,22,2,2));
        LocalDateTime localDateTime = mockMyTime.getNow().minusMonths(1);

        when(mockProductRepository.findAllByDateOfPostBefore(localDateTime))
                .thenReturn(Optional.of(old));

        productServiceToTest.clearOldProduct();

        verify(mockProductRepository).deleteAll(old);
    }

    @Test
    void getLastProducts() {
        ProductViewDTO productViewDTO = new ProductViewDTO();
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            products.add(new Product());
        }

        when(mockModelMapper.map(any(Product.class), eq(ProductViewDTO.class)))
                .thenReturn(productViewDTO);

        when(mockProductRepository.findTop10OrderByDateOfPostDesc())
                .thenReturn(products);

        List<ProductViewDTO> result = productServiceToTest.getLastProducts();

        assertEquals(3, result.size());
    }

    @Test
    void getAllProducts() {
        ProductViewDTO productViewDTO = new ProductViewDTO();
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            products.add(new Product());
        }

        when(mockProductRepository.findAll())
                .thenReturn(products);

        when(mockModelMapper.map(any(Product.class), eq(ProductViewDTO.class)))
                .thenReturn(productViewDTO);

        List<ProductViewDTO> result = productServiceToTest.getAllProducts();

        assertNotNull(result);
        assertEquals(3, result.size());
    }
}