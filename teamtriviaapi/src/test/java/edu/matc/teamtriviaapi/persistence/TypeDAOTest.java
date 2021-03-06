package edu.matc.teamtriviaapi.persistence;

import edu.matc.teamtriviaapi.entity.Type;
import org.hibernate.criterion.MatchMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TypeDAOTest {

    Type type;
    TypeDAO typeDAO;

    int newType = 0;

    @Before
    public void setUp() throws Exception {
        typeDAO = new TypeDAO();

        type = new Type();
        type.setTypeName("T/F");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllTypes() throws Exception {

        List<Type> types = typeDAO.getAllTypes();

        assertTrue(types.size() > 0);
    }

    @Test
    public void getTypeById() throws Exception {
        newType = typeDAO.addType(type);

        assertEquals("Error matching type by Id: ", type.getTypeID(), typeDAO.getTypeById(newType).getTypeID());

    }

    @Test
    public void addType() throws Exception {
        newType = typeDAO.addType(type);

        assertEquals("Error matching type by Id: ", type.getTypeID(), typeDAO.getTypeById(newType).getTypeID());
        assertEquals("Error matching type name: ", type.getTypeName(), typeDAO.getTypeById(newType).getTypeName());

    }

    @Test
    public void deleteType() throws Exception {
        newType = typeDAO.addType(type);

        assertEquals("Error matching type by Id: ", type.getTypeID(), typeDAO.getTypeById(newType).getTypeID());
        assertEquals("Error matching type name: ", type.getTypeName(), typeDAO.getTypeById(newType).getTypeName());


        typeDAO.deleteType(typeDAO.getTypeById(newType).getTypeID());

        assertNull("Type not deleted: ", typeDAO.getTypeById(newType));

    }

    @Test
    public void updateType() throws Exception {
        newType = typeDAO.addType(type);

        assertEquals("Error matching type by Id: ", type.getTypeID(), typeDAO.getTypeById(newType).getTypeID());
        assertEquals("Error matching type name: ", type.getTypeName(), typeDAO.getTypeById(newType).getTypeName());

        Type updateType = new Type();
        updateType.setTypeID(1);
        updateType.setTypeName("Short Answer");

        typeDAO.updateType(updateType);

        assertEquals("Error updating type", updateType.getTypeName(), typeDAO.getTypeById(1).getTypeName());

    }

    @Test
    public void findByProperty() throws Exception {
        List<Type> types = typeDAO.findByProperty("TypeName", "Short Answer", MatchMode.EXACT);

        assertEquals(1, types.size());
        assertEquals("Short Answer", types.get(0).getTypeName());
    }

}
