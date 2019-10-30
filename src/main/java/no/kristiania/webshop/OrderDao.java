package no.kristiania.webshop;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDao extends  AbstractDao<Order> {
   // private  List<Order> orders = new ArrayList<>();

    public OrderDao(DataSource dataSource) {
        super(dataSource);

    }

    //public void insert(Order order, String sql1) { orders.add(order); }

    @Override
    protected void insertObject(Order order, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, order.getName());

    }

   // public List<Order> listAll(String sql) { return orders; }

    @Override
    protected Order readObject(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setName(rs.getString( "name"));
        return order;
    }

    public void insert (Order product) throws SQLException{
        insert(product, "insert into products (name) values(?)");
    }

    public  List<Order> listAll() throws SQLException{
        return  listAll("select * from products");
    }
}
