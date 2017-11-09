package edu.matc.teamtriviaapi.persistence;

import edu.matc.teamtriviaapi.entity.Difficulty;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class DifficultyDAO {

    private final Logger log = Logger.getLogger(this.getClass());

    public List<Difficulty> getAllDifficulties() {
        List<Difficulty> difficulties = new ArrayList<Difficulty>();
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            difficulties = session.createCriteria(Difficulty.class).list();
        } catch (HibernateException he) {
            log.error("Hibernate exception is caught ", he);
        } catch (Exception e) {
            log.error("General exception is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return difficulties;
    }

    public Difficulty getDifficultyById(int id) {
        Difficulty difficulty = null;
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            difficulty = (Difficulty) session.get(Difficulty.class, id);
        } catch (HibernateException he) {
            log.error("Error getting difficulty by id", he);
        } catch (Exception e) {
            log.error("General exception for getDifficultyById() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return difficulty;
    }

    public int addDifficulty(Difficulty difficulty) {
        int id = 0;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            id = (int) session.save(difficulty);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error adding difficulty", he);
        } catch (Exception e) {
            log.error("General exception for addDifficulty() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    public void deleteDifficulty(int id) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Difficulty difficulty = (Difficulty) session.load(Difficulty.class, id);
            session.delete(difficulty);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error deleting difficulty", he);
        } catch (Exception e) {
            log.error("General exception for deleteDifficulty() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateDifficulty(Difficulty difficulty) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(difficulty);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error updating difficulty", he);
        } catch (Exception e) {
            log.error("General exception for updateDifficulty() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Difficulty> findByProperty(String propertyName, String value, MatchMode matchMode){
        Session session = null;
        List<Difficulty> items = new ArrayList<Difficulty>();
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            if (matchMode != null){
                items =  session.createCriteria(Difficulty.class).add(Restrictions.ilike(propertyName, value, matchMode)).list();
            }else{
                items =  session.createCriteria(Difficulty.class).add(Restrictions.ilike(propertyName, value, MatchMode.EXACT)).list();
            }
        } catch (HibernateException he) {
            log.error("Error getting difficulties", he);
        } catch (NullPointerException e) {
            log.error("Error getting difficulties they don't exist: ", e);

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return items;
    }
}
