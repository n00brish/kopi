package no.kristiania.webshop;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private  List<Order> orders = new ArrayList<>();

    public OrderDao(DataSource dataSource) {

    }

    public void insert(Order order) { orders.add(order); }

    public List<Order> listAll() { return orders; }

}
