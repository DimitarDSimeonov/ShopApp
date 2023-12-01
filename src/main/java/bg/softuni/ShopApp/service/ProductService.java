package bg.softuni.ShopApp.service;

import bg.softuni.ShopApp.model.DTO.product.AddProductDTO;
import bg.softuni.ShopApp.model.DTO.product.ProductOwnerViewDTO;
import bg.softuni.ShopApp.model.DTO.product.ProductSearchDTO;
import bg.softuni.ShopApp.model.DTO.product.ProductViewDTO;
import bg.softuni.ShopApp.model.entity.Product;

import java.util.List;

public interface ProductService {
    Long createProduct(AddProductDTO addProductDTO, String username);

    void removeById(Long id);

    ProductOwnerViewDTO getOwnerViewById(Long id);

    Product getById(Long id);

    List<ProductViewDTO> searchByInput(ProductSearchDTO productSearchDTO);

    ProductViewDTO getDetailsViewById(Long id);

    void clearOldProduct();

    List<ProductViewDTO> getLastProducts();
}
