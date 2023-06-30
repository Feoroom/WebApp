package webcloud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import webcloud.entity.User;
import webcloud.entity.Product;
import webcloud.repositories.OrderRepo;

import java.util.Arrays;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ShoppingCartOrders {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void AddOneProductTest(){
        var product = entityManager.find(Product.class,1);
        var customer = entityManager.find(User.class, 1);

//        var order = new OrderDetails();
//        order.setProduct(product);
//        order.setUser(customer);
//        order.setQuantity(1);

//        var svdOrd = orderRepo.save(order);
//
//        Assertions.assertTrue(svdOrd.getId() > 0);

    }

    @Test
    public void findByCustomerTest(){
        var cust = new User();
        cust.setId(1L);

        var ordersList = orderRepo.findByUser(cust);
        System.out.println(Arrays.toString(ordersList.toArray()));
        Assertions.assertEquals(0, ordersList.size());

    }

}
