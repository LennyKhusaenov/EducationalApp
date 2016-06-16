package com.educationportal.service;

import com.educationportal.entity.DocumentEntity;
import com.educationportal.entity.PersonEntity;
import com.educationportal.form.PersonSearchForm;
import com.educationportal.repository.DocumentRepository;
import com.educationportal.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by leniz on 16.05.2016.
 */
@Service
public class PersonService {
    private PersonRepository personRepository;
    private DocumentRepository documentRepository;
    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @Autowired
    public void setDocumentRepository(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Iterable<PersonEntity> listAllPerson() {
        return personRepository.findAll();
    }
    public Iterable<PersonEntity> savePersons(Iterable<PersonEntity> persons)
    {return personRepository.save(persons);}

    @Value("#{'${generate.names}'.split(',')}")
    private List<String> Names;
    @Value("#{'${generate.surnames}'.split(',')}")
    private List<String> Surnames;
    @Value("#{'${generate.emails}'.split(',')}")
    private List<String> Emails;
    @Value("#{'${generate.document.titles}'.split(',')}")
    private List<String> Titles;


    /**
     *
     * @param countPersons how persons
     * @param maxCountPersonDocs max count docs for one person
     * @return List<PersonEntity>
     */
    public List<PersonEntity> generatePersons(Integer countPersons,Integer maxCountPersonDocs){
        Random rn = new Random();
        Integer nextInt;
        List<PersonEntity> newPersons = new ArrayList<>();
        for (Integer i = 1;i<countPersons;i++){
            PersonEntity personNew = new PersonEntity();
            nextInt = rn.nextInt(Names.size()-1);
            personNew.setName(Names.get(nextInt));

            nextInt = rn.nextInt(Surnames.size()-1);
            personNew.setSurname(Surnames.get(nextInt));

            nextInt = rn.nextInt(Emails.size()-1);
            personNew.setEmail(Emails.get(nextInt));

            newPersons.add(generateDocuments(personNew, maxCountPersonDocs));

        }


    return newPersons;
    }

    /**
     *
     * @param person
     * @param forOne
     * @return
     */
    private PersonEntity generateDocuments(PersonEntity person, Integer forOne) {
        Random rn = new Random();
        Integer nextInt = rn.nextInt(forOne);
        List<DocumentEntity> newDocs = new ArrayList<DocumentEntity>();
        for (int i = 0;i< nextInt;i++){
            DocumentEntity docNew= new DocumentEntity();
            docNew.setPerson(person);
            nextInt = rn.nextInt(Titles.size()-1);
            docNew.setTitle(Titles.get(nextInt));
            docNew.setUniqueNumber(UUID.randomUUID().toString());
            newDocs.add(docNew);
        }
        person.setDocuments(newDocs);
        return person;
    }

    public List<PersonEntity> search(PersonSearchForm form) {
        String search = form.getSearch();
        List<PersonEntity> persons;
        if (Pattern.matches("^.*[0-9]+.*$", search)) {
            persons=searchPersonByDocs(form);

        }else{
            persons=searchPerson(form);
            if (persons.size() < 1) {
                persons=searchPersonByDocs(form);
            }
        }

        return  persons;
    }

    @Autowired
    private EntityManager em;

    private List<PersonEntity> searchPersonByDocs(PersonSearchForm form) {
        String likeQ =  form.getSearch()+"%";
        List<DocumentEntity> docs =  em.createQuery(
                "from DocumentEntity where uniqueNumber LIKE :number GROUP BY person ORDER BY uniqueNumber", DocumentEntity.class
        ).setParameter("number",likeQ)
                .setMaxResults(form.getCount())
                .getResultList();
        List<PersonEntity> persons = new ArrayList<>();
        for (DocumentEntity doc : docs){
            persons.add(doc.getPerson());
        }
        return persons;
    }


    private List<PersonEntity> searchPerson(PersonSearchForm form) {
        String likeQ =  form.getSearch()+"%";
        return em.createQuery(
                "from PersonEntity where name LIKE :name OR surname LIKE :surname ORDER BY name,surname", PersonEntity.class
        )
                .setParameter("name",likeQ)
                .setParameter("surname", likeQ)
                .setMaxResults(form.getCount())
                .getResultList();

    }
}
