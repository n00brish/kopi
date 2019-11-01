package no.kristiania.webshop;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;



public class ProductDaoTest {
    private  JdbcDataSource dataSource = TestDatabase.testDataSource();

    @Test
    void shouldListInsertedProduct()throws SQLException {
        ProductDao dao = new ProductDao(dataSource);
        Product product = sampleProduct();
        dao.insert(product);
        assertThat(dao.listAll ())
                .contains (product);
    }
    @Test
    public void shouldSaveAllProductFields() throws  SQLException{
        ProductDao dao = new ProductDao(dataSource);
        Product product = sampleProduct();
        long id = dao.insert(product);
        assertThat(dao.retrieve(id))
                .isEqualToComparingFieldByField(product);
    }




    private Product sampleProduct() {
        Product product = new Product();
        product.setName(sampleProductName());
        return null;
    }
    private  String sampleProductName() {
        String[] alternatives = {


                "apples", "bananas", "coconuts", "dates"  
        };
        return alternatives[new Random().nextInt(alternatives.length)] ;


    }


}
