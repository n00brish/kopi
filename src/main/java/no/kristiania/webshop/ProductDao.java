package no.kristiania.webshop;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends AbstractDao<Product> {


    public ProductDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertObject(Product product, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, product.getName());
    }


    @Override
    protected Product readObject(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setName(rs.getString("name"));
        return new Product();
    }

    public long insert(Product product) throws SQLException {
        return insert(product, "insert into products (name) values(?)");
    }

    public List<Product> listAll() throws SQLException {
        return listAll("select * from products");


    }

    public Product retrieve(long id) {
        try (Connection connection = dataSource.getConnection()){
            try (PreparedStatement stmt = connection.prepareStatement("select* from products")){
                try (ResultSet rs = stmt.executeQuery()){
                    if (rs.next()) {
                        return readObject(rs);
                    } else {
                        return null;
                    }
                }
            }
        }

    }

}
