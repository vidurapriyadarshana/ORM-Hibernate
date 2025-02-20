import config.FactoryConfiguration;
import entity.Customer;
import entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Customer customer = new Customer();
            customer.setName("John");
            session.save(customer);

            Customer customer2 = new Customer();
            customer2.setName("Doe");
            session.save(customer2);

            Orders order1 = new Orders();
            order1.setDate("2021-01-01");
            order1.setCustomer(customer);
            session.save(order1);

            Orders order2 = new Orders();
            order2.setDate("2021-01-02");
            order2.setCustomer(customer2);
            session.save(order2);

            customer.setOrders(List.of(order1));
            customer2.setOrders(List.of(order2));

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
