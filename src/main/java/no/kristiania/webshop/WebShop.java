package no.kristiania.webshop;

import org.postgresql.ds.PGSimpleDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class WebShop {

        private final OrderDao orderDao;
        private PGSimpleDataSource dataSource;
        private BufferedReader input;
        private ProductDao productDao;

        public WebShop() throws IOException {

                Properties properties = new Properties();
                properties.load(new FileReader("webshop.properties"));

                dataSource = new PGSimpleDataSource();

                dataSource.setUrl("jdbc:postgres1://localhost:5432/mywebshopdb");
                dataSource.setUser("mywebshop");
                dataSource.setPassword(properties.getProperty("dataSource.password"));
                productDao = new ProductDao(dataSource);
                orderDao = new OrderDao(dataSource)
        }


        public static void main(String[] args) throws IOException, SQLException {
                new WebShop().run();
        }


        private void run() throws IOException, SQLException {
        System.out.println("choose action: create[products]|create [order]");
        String action = input.readLine();

        switch (action.toLowerCase()){
                case "product":
                        insertProduct();
                        break;
                case "order":
                        insertOrder();
                        break;
        }
        System.out.println("Current products" + productDao.listAll());

}

        private void insertOrder() throws IOException, SQLException {
        System.out.println("Please type the name of a new order");
        Order order = new Order();
        order.setName(input.readLine());
        orderDao.insert(order);


}

        private void insertProduct() throws IOException, SQLException{
        System.out.println("Please type the name of a new product");

        String productName = input.readLine();

        productDao = new ProductDao(dataSource);
        productDao.insert(productName);

        System.out.println("Current products" + productDao.listAll());
}
}



