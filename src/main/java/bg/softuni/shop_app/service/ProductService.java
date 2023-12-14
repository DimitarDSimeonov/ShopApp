package bg.softuni.shop_app.service;

import bg.softuni.shop_app.model.DTO.product.AddProductDTO;
import bg.softuni.shop_app.model.DTO.product.ProductOwnerViewDTO;
import bg.softuni.shop_app.model.DTO.product.ProductSearchDTO;
import bg.softuni.shop_app.model.DTO.product.ProductViewDTO;
import bg.softuni.shop_app.model.entity.Product;

import java.util.List;

public interface ProductService {
    Long createProduct(AddProductDTO addProductDTO, String username);

    void deleteById(Long id);

    ProductOwnerViewDTO getOwnerViewById(Long id);

    Product getById(Long id);

    List<ProductViewDTO> searchByInput(ProductSearchDTO productSearchDTO);

    ProductViewDTO getDetailsViewById(Long id);

    void clearOldProduct();

    List<ProductViewDTO> getLastProducts();

    List<ProductViewDTO> getAllProducts();
}
