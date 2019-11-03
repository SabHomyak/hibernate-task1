import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Main {

    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        try {
            emf = Persistence.createEntityManagerFactory("JPAMenu");
            em = emf.createEntityManager();
            Dish dish1 = new Dish("Soup", 95.21, 255);
            dish1.setDiscount(75d);
            Dish dish2 = new Dish("Borsh", 110.91, 232);
            Dish dish3 = new Dish("French fries", 55.92, 123);
            Dish dish4 = new Dish("Сheeseburger", 130.23, 335);
            Dish dish5 = new Dish("Coca-cola", 40.91, 250);
            Dish dish6 = new Dish("Shawarma", 999.99, 240);
            addDish(dish1, dish2, dish3, dish4, dish5, dish6);
            System.out.println("Menu order by DESC:");
            showMenu(ColumnMenu.PRICE, false);
            System.out.println("--------------------------------------");
            System.out.println("Show discounts");
            showDiscounts();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addDish(Dish... dish) {
        Dish[] dishes = dish;
        try {
            em.getTransaction().begin();
            for (int i = 0; i < dish.length; i++) {
                em.persist(dishes[i]);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static void showMenu(ColumnMenu column, boolean descOrAsc) {
        String queryString = descOrAsc == true ? "DESC" : "ASC";
        Query query = em.createQuery("select d from Dish d order by " + column.name() + " " + queryString);
        List<Dish> list = query.getResultList();
        for (Dish dish : list) {
            System.out.println(dish);
        }
    }

    public static void showDiscounts() {
        Query query = em.createQuery("select d from Dish d where d.isDiscount = true");
        List<Dish> list = query.getResultList();
        for (Dish dish : list) {
            System.out.println(dish);
        }
    }
}
