package br.com.globallabs.springwebmvc.controller;

import br.com.globallabs.springwebmvc.model.Jedi;
import br.com.globallabs.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class JediController {

    @Autowired
    private JediRepository repository;

    @GetMapping("/jedi")
    public ModelAndView jedi() {

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        modelAndView.addObject("allJedi", repository.findAll());

        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView searchJedi(@RequestParam(value = "name") final String name) {

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");

        modelAndView.addObject("allJedi", repository.findByName(name));
        return modelAndView;
    }

    @GetMapping("/new-jedi")
    public ModelAndView newJedi() {

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new-jedi");

        modelAndView.addObject("jedi", new Jedi());
        return modelAndView;
    }

    @GetMapping("/edit-jedi/{id}")
    public ModelAndView editJedi(@PathVariable(value = "id") final Long id) {

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit-jedi");

        modelAndView.addObject("jedi", repository.findById(id));
        return modelAndView;
    }


    @PostMapping("/jedi")
    public String createJedi(@Valid @ModelAttribute Jedi jedi, BindingResult result, RedirectAttributes redirect) {

        if (result.hasErrors()) {
            return "new-jedi";
        }

        repository.save(jedi);
        redirect.addFlashAttribute("message", "Jedi cadastrado com sucesso!!");
        return "redirect:jedi";
    }

    @PostMapping("/jedi/{id}/update")
    public String updateJedi(@Valid @ModelAttribute Jedi jedi, BindingResult result, RedirectAttributes redirect) {

        if (result.hasErrors()) {
            return "edit-jedi";
        }
        repository.save(jedi);

        redirect.addFlashAttribute("message", "Jedi alterado com sucesso!");
        return "redirect:/jedi";
    }

    @GetMapping("/jedi/{id}/delete")
    public String deleteJedi(@PathVariable("id") final Long id, RedirectAttributes redirect) {

        final Optional<Jedi> jedi = repository.findById(id);
        repository.delete(jedi.get());

        redirect.addFlashAttribute("message", "Jedi removido com sucesso!");
        return "redirect:/jedi";
    }
}
