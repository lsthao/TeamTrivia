package edu.matc.teamtriviaapi.persistence;

import edu.matc.teamtriviaapi.entity.Question;
import edu.matc.teamtriviaapi.entity.Category;
import edu.matc.teamtriviaapi.entity.Type;
import edu.matc.teamtriviaapi.entity.Difficulty;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

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


    public List<Question> findByProperty(String type, String category, String difficulty, String amount){
        Session session = null;
        List<Question> items = new ArrayList<Question>();

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Question.class);
            if (type != null){
                criteria.add(Restrictions.eq("type", getSingleTypeObjectFromName(type)));
            }
            if (category != null) {
                criteria.add(Restrictions.eq("category", getSingleCategoryObjectFromName(category)));
            }
            if (difficulty != null) {
                criteria.add(Restrictions.eq("difficulty", getSingleDifficultyObjectFromName(difficulty)));
            }
            if (amount == null) {
                criteria.setMaxResults(100);
            } else if (Integer.parseInt(amount) > 100) {
                criteria.setMaxResults(100);
            } else if (amount != null) {
                criteria.setMaxResults(Integer.parseInt(amount));
            }
            items =  criteria.list();

        } catch (HibernateException he) {
            log.error("Error getting questions", he);
        } catch (NullPointerException e) {
            log.error("Error getting questions they don't exist: ", e);

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return items;
    }

    public Question getQuestionById(int questionId) {
        Question question = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            question = (Question) session.get(Question.class, questionId);
            Hibernate.initialize(question.getCategory());
            Hibernate.initialize(question.getDifficulty());
            Hibernate.initialize(question.getType());
        } catch (HibernateException he) {
            log.error("Error getting Question with id: " + questionId, he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return question;
    }

    public List<Question> getByCategoryID(Category category) {
        List<Question> question = new ArrayList<Question>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Question.class);
            criteria.add(Restrictions.eq("category", category));
            question = criteria.list();
        } catch (HibernateException he) {
            log.error("Error getting team name: " + category, he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return question;
    }

    public List<Question> getByTypeID(Type type) {
        List<Question> question = new ArrayList<Question>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Question.class);
            criteria.add(Restrictions.eq("type", type));
            question = criteria.list();
        } catch (HibernateException he) {
            log.error("Error getting team name: " + type, he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return question;
    }

    public List<Question> getByDifficultyID(Difficulty difficulty) {
        List<Question> question = new ArrayList<Question>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Question.class);
            criteria.add(Restrictions.eq("difficulty", difficulty));
            question = criteria.list();
        } catch (HibernateException he) {
            log.error("Error getting team name: " + difficulty, he);
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

    public Type getSingleTypeObjectFromName(String type) {
        TypeDAO typeDao = new TypeDAO();
        Type typeObj = new Type();
        List<Type> types = typeDao.findByProperty("TypeName", type, MatchMode.ANYWHERE);
        if (types.size() > 0) {
            typeObj = types.get(0);
        }
        return typeObj;
    }

    public Category getSingleCategoryObjectFromName(String category) {
        CategoryDAO categoryDAO = new CategoryDAO();
        Category categoryObj = new Category();
        List<Category> categories = categoryDAO.findByProperty("CategoryName", category, MatchMode.ANYWHERE);
        if (categories.size() > 0) {
            categoryObj = categories.get(0);
        }
        return categoryObj;
    }

    public Difficulty getSingleDifficultyObjectFromName(String difficulty) {
        DifficultyDAO difficultyDAO = new DifficultyDAO();
        Difficulty difficultyObj = new Difficulty();
        List<Difficulty> difficulties = difficultyDAO.findByProperty("DifficultyName", difficulty, MatchMode.ANYWHERE);
        if (difficulties.size() > 0) {
            difficultyObj = difficulties.get(0);
        }
        return difficultyObj;
    }
}
