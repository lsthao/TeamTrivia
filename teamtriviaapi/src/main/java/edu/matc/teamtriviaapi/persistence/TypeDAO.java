package edu.matc.teamtriviaapi.persistence;

import edu.matc.teamtriviaapi.entity.Type;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TypeDAO {

    private final Logger log = Logger.getLogger(this.getClass());

    public List<Type> getAllTypes() {
        List<Type> categories = new ArrayList<Type>();
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            categories = session.createCriteria(Type.class).list();
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

    public Type getTypeById(int id) {
        Type type = null;
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            type = (Type) session.get(Type.class, id);
            Hibernate.initialize(type.getQuestion());
        } catch (HibernateException he) {
            log.error("Error getting type by id", he);
        } catch (Exception e) {
            log.error("General exception for getTypeById() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return type;
    }

    public int addType(Type type) {
        int id = 0;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            id = (int) session.save(type);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error adding type", he);
        } catch (Exception e) {
            log.error("General exception for addType() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    public void deleteType(int id) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Type type = (Type) session.load(Type.class, id);
            session.delete(type);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error deleting type", he);
        } catch (Exception e) {
            log.error("General exception for deleteType() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateType(Type type) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(type);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error updating type", he);
        } catch (Exception e) {
            log.error("General exception for updateType() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
