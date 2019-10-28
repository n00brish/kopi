package no.kristiania.webshop;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;


public class OrderDaoTest {

    private DataSource dataSource;


    @Test
    void shouldFindSavedOrders() throws SQLException {
        Order order = new Order();
        order.setName("Test");
        OrderDao dao = new OrderDao(dataSource);

        dao.insert(order);
        assertThat(dao.listAll()).contains(order);

    }
}
