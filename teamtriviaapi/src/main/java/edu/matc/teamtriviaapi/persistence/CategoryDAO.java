package edu.matc.teamtriviaapi.persistence;

import edu.matc.teamtriviaapi.entity.Category;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private final Logger log = Logger.getLogger(this.getClass());

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<Category>();
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            categories = session.createCriteria(Category.class).list();
        } catch (HibernateException he) {
            log.error("Hibernate exception is caught ", he);
        } catch (Exception e) {
            log.error("General exception is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return categories;
    }


}
