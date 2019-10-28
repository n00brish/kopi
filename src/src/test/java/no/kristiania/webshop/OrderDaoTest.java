package no.kristiania.webshop;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;


public class OrderDaoTest {

    private DataSource dataSource;


    @Test
    void shouldFindSavedOrders() {
        Order order = new Order();
        OrderDao dao = new OrderDao(dataSource);

        dao.insert(order);
        assertThat(dao.listAll()).contains(order);

    }
}
