package edu.matc.teamtriviapi.persistence;

import edu.matc.teamtriviapi.entity.Question;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    private final Logger log = Logger.getLogger(this.getClass());

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<Question>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            questions = session.createCriteria(Question.class).list();
        } catch (HibernateException he) {
            log.error("Error getting all Questions", he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return questions;
    }

    public Question getQuestionById(int questionId) {
        Question question = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            question = (Question) session.get(Question.class, questionId);
        } catch (HibernateException he) {
            log.error("Error getting Question with id: " + questionId, he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return question;
    }

    public void updateQuestion(Question question) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(question);
            transaction.commit();
        } catch (HibernateException he){
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException he2) {
                    log.error("Error rolling back save of question: " + question, he2);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public int insertQuestion(Question question) {
        int id = 0;
        Transaction transaction = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            id = (int) session.save(question);
            transaction.commit();
        } catch (HibernateException he){
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException he2) {
                    log.error("Error rolling back save of question: " + question, he2);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    public void deleteByQuestionId(int id) {
        Transaction transaction = null;
        Session session = null;
        Question question = null;
        try {

            session = SessionFactoryProvider.getSessionFactory().openSession();
            question = (Question) session.get(Question.class, id);
            transaction = session.beginTransaction();
            session.delete(question);
            transaction.commit();
        } catch (HibernateException he){
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException he2) {
                    log.error("Error rolling back save of question: " + question, he2);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


}
