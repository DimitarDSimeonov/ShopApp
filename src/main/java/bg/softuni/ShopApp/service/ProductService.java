package bg.softuni.ShopApp.service;

import bg.softuni.ShopApp.model.DTO.product.AddProductDTO;

public interface ProductService {
    void createProduct(AddProductDTO addProductDTO, String username);

    void removeById(Long id);
}
