package ua.danit.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import ua.danit.domain.Product;

import java.util.Comparator;

public class JpaUseCase {
    private final EntityManager manager;

    public JpaUseCase(EntityManager manager) {
        this.manager = manager;
    }

    public void runInsertOne() {
        System.out.print("=Inserting record...");
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(
                new Product("java book (V100)", 1001L)
        );
        tx.commit();
        System.out.println("=Done");
    }

    public void runSelectAll() {
        System.out.println("=Running select all...");
        manager.createQuery("Select a From Product a", Product.class)
                .getResultList()
                .stream()
                .sorted((o1, o2) -> (int) (o1.getId()-o2.getId()))
                .forEach(p -> System.out.println(p.toString()));
        System.out.println("=Done");
    }

    public void runSelectOne(long pk) {
        System.out.printf("=Running select by id:%d...\n",pk);
        Product product = manager.find( Product.class, pk);
        System.out.println(product.toString());
        System.out.println("=Done");
    }

    public void runDeleteOne(long pk) {
        System.out.printf("=Running delete by id:%d...\n",pk);
        Product found = manager.find(Product.class, pk);
        manager.remove(found);
        System.out.println("=Done");
    }

    public void runUpdateOne(long pk) {
        System.out.printf("=Running update by id:%d...\n",pk);
        Product found = manager.find(Product.class, pk);
        found.setPrice(999);
        manager.merge(found);
        System.out.println("=Done");
    }
}