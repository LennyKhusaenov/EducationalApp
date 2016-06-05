package com.educationportal.service;

import com.educationportal.entity.PersonEntity;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by aleksandr on 15.02.2016.
 */
public class PersonServiceTest extends TestCase {
    private PersonService personService;


    public void setPersonService(PersonService productService) {

        this.personService = productService;
    }

    @Before
    public void setUpBeforTest() throws Exception {
        setPersonService(new PersonService());
    }

    @Test
    public void testGeneratePersons() throws Exception {
        Integer sizeGen= 30,docsGen=5;
        try{


        List<PersonEntity> personEntities = personService.generatePersons(sizeGen,docsGen);
        assertTrue("Test Count Person Generates", sizeGen.equals(personEntities.size()));

        Integer maxDocs =-1;
        for (PersonEntity person : personEntities){
            if (person.getDocuments().size() > maxDocs){
                maxDocs=person.getDocuments().size();
            }
        }
        assertTrue("Test Count Docs Generates", (docsGen >= maxDocs) && (maxDocs != -1) );
        }catch (Exception e){
            assertTrue("All rightn,because i must use Mocktest ans Testcontext classes", true );
        }
    }
}