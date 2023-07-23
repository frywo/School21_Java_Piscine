package edu.school21.repositories;

import edu.school21.models.Product;
import edu.school21.repositories.ProductsRepository;
import edu.school21.repositories.ProductsRepositoryJdbcImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest extends Assertions {

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(0L, "Apl", 96),
            new Product(1L, "Mango", 250),
            new Product(2L, "Chicken", 200),
            new Product(3L, "Milk",  95),
            new Product(4L, "Cookies", 150)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(0L, "Apl", 96);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(3L, "Milk",  100);

    EmbeddedDatabase dataSource;
    ProductsRepository productsRepository;

    @BeforeEach
    public void init() throws SQLException {
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        try {
            productsRepository = new ProductsRepositoryJdbcImpl(dataSource.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllTest(){
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findALl());
    }

    @Test
    public void findByIdTest(){
       Long id = 0L;
       assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepository.findById(id).get());
    }

    @Test
    public void findByIdExceptionTest(){
        Long id = 11111L;
        assertThrows(RuntimeException.class,() -> productsRepository.findById(id));
    }

    @Test
    public void updateTest(){
        Product exceptedProduct = productsRepository.findById(EXPECTED_UPDATED_PRODUCT.getId()).orElse(null);
        if(exceptedProduct!=null){
            exceptedProduct.setPrice(100);
        productsRepository.update(exceptedProduct);
        assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepository.findById(exceptedProduct.getId()).get());
        }
    }

    @Test
    public void saveTest(){
        Long count = (long) productsRepository.findALl().size();
        Product saveProduct = new Product(count, "test", 123);
        productsRepository.save(saveProduct);
        assertEquals(productsRepository.findById(count).get(), saveProduct );
    }

    @Test
    public void deleteTest(){
        Product productDelete = productsRepository.findById(0L).get();
        productsRepository.delete(productDelete.getId());
        assertThrows(RuntimeException.class, () -> productsRepository.findById(productDelete.getId()));
    }

}
