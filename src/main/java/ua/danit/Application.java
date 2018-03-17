package ua.danit;

import ua.danit.jpa.JpaUseCase;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
    public static void main(String[] args) {
        // create the EntityManagerFactory from chosen implementation
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnit");
        // create the EntityManager from EntityManagerFactory
        EntityManager man = factory.createEntityManager();
        // create instance our test class
        JpaUseCase test = new JpaUseCase(man);

        //test.runInsertOne();
        //test.runSelectAll();
        //test.runSelectOne(1002);
        //test.runUpdateOne(1002);
        test.runSelectAll();
        //test.runDeleteOne(1551);
    }
}
