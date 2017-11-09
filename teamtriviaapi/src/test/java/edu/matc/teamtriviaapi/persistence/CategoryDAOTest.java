package edu.matc.teamtriviaapi.persistence;

import edu.matc.teamtriviaapi.entity.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CategoryDAOTest {

    Category category;
    CategoryDAO categoryDAO;
    int newCategory = 0;

    @Before
    public void setUp() throws Exception {
        categoryDAO = new CategoryDAO();

        category = new Category();
        category.setCategoryName("Java");


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllCategories() throws Exception {
        categoryDAO.addCategory(category);

        List<Category> categories = new ArrayList<Category>();
        categories.add(category);

        assertEquals(1, categories.size());
    }

    @Test
    public void getCategoryById() throws Exception {
        newCategory = categoryDAO.addCategory(category);

        assertEquals("Error matching category by Id: ", category.getCategoryID(), categoryDAO.getCategoryById(newCategory).getCategoryID());

    }

    @Test
    public void addCategory() throws Exception {
        newCategory = categoryDAO.addCategory(category);

        assertEquals("Error matching category by Id: ", category.getCategoryID(), categoryDAO.getCategoryById(newCategory).getCategoryID());
        assertEquals("Error matching category name: ", category.getCategoryName(), categoryDAO.getCategoryById(newCategory).getCategoryName());
    }

    @Test
    public void deleteCategory() throws Exception {
        newCategory = categoryDAO.addCategory(category);

        assertEquals("Error matching category by Id: ", category.getCategoryID(), categoryDAO.getCategoryById(newCategory).getCategoryID());
        assertEquals("Error matching category name: ", category.getCategoryName(), categoryDAO.getCategoryById(newCategory).getCategoryName());


        categoryDAO.deleteCategory(categoryDAO.getCategoryById(newCategory).getCategoryID());

        assertNull("Category not deleted: ", categoryDAO.getCategoryById(newCategory));
    }

    @Test
    public void updateCategory() throws Exception {
        newCategory = categoryDAO.addCategory(category);

        assertEquals("Error matching category by Id: ", category.getCategoryID(), categoryDAO.getCategoryById(newCategory).getCategoryID());
        assertEquals("Error matching category name: ", category.getCategoryName(), categoryDAO.getCategoryById(newCategory).getCategoryName());

        Category updateCategory = new Category();
        updateCategory.setCategoryID(1);
        updateCategory.setCategoryName("Model, View, Controller (MVC)");

        categoryDAO.updateCategory(updateCategory);

        assertEquals("Error updating category", updateCategory.getCategoryName(), categoryDAO.getCategoryById(1).getCategoryName());


    }

    @Test
    public void findByProperty() throws Exception {

    }


}
