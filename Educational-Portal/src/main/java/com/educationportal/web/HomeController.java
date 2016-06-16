package com.educationportal.web;

import com.educationportal.entity.PersonEntity;
import com.educationportal.form.PersonSearchForm;
import com.educationportal.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by leniz on 16.05.2016.
 */
@Controller
public class HomeController {

    private PersonService personService;

    @Autowired
    public void setPersonService(PersonService productService) {
        this.personService = productService;
    }

    @Autowired
    private EntityManager em;


    @RequestMapping(value = "/", method=RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("searchForm", new PersonSearchForm());
        model.addAttribute("persons",personService.listAllPerson());
        return "index";
    }
    @RequestMapping(value = "/", method=RequestMethod.POST)
    public String indexForm(@ModelAttribute PersonSearchForm form,Model model) {

        model.addAttribute("searchForm", form);

        model.addAttribute("persons",personService.search(form));
        return "index";
    }

    @RequestMapping(value = "/about", method=RequestMethod.GET)
    public String about(Model model) {
        return "about";
    }

    @RequestMapping(value = "/generate", method=RequestMethod.GET)
    public String generate(Model model) {
       /* em.createQuery("TRUNCATE TABLE person_documents ").getSingleResult();
        em.createQuery("TRUNCATE TABLE document").getSingleResult();
        em.createQuery("TRUNCATE TABLE person").getSingleResult();*/

        List<PersonEntity> persons = personService.generatePersons(30,5);
        personService.savePersons(persons);
        model.addAttribute("persons",persons);
        model.addAttribute("generateMessage","Generate +30 Person , and less 5 docs per Person");
    return "index";
    }


}
