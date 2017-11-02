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

    public Category getCategoryById(int Id) {
        Category category = null;
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            category = (Category) session.get(Category.class, id);
            Hibernate.initialize(category.getQuestion());
        } catch (HibernateException he) {
            log.error("Error getting category by id", he);
        } catch (Exception e) {
            log.error("General exception for getCategoryById() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return category;
    }

    public int addCategory(Category category) {
        int id = 0;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            id = (int) session.save(category);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error adding category", he);
        } catch (Exception e) {
            log.error("General exception for addCategory() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    public void deleteCategory(int id) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Category category = (Category) session.load(Category.class, id);
            session.delete(category);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error deleting category", he);
        } catch (Exception e) {
            log.error("General exception for deleteCategory() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateCategory(Category category) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error updating category", he);
        } catch (Exception e) {
            log.error("General exception for updateCategory() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
