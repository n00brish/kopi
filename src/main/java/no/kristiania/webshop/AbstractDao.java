package no.kristiania.webshop;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao {
    protected DataSource dataSource;

    public AbstractDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public  void insert (String product) throws SQLException {
        try (Connection connection = dataSource.getConnection()){
            String  sql = "insert into products(name) values(?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)){
                insertObject(product, stmt);
                stmt.executeUpdate();

            }
        }
    }

    protected abstract void insertObject(String product, PreparedStatement stmt) throws SQLException;

    public List<String> listAll() throws  SQLException{
        try(Connection connection = dataSource.getConnection()){
            try (PreparedStatement stmt = connection.prepareStatement( "select * from products")){
                try(ResultSet rs = stmt.executeQuery()){
                    List<String> products = new ArrayList<>();
                    while (rs.next()){
                        products.add(readObject(rs));
                    }
                    return products;
                }

            }

        }
    }

    protected abstract String readObject(ResultSet rs) throws SQLException;
}
