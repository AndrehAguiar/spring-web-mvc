package br.com.globallabs.springwebmvc.service;

import br.com.globallabs.springwebmvc.exception.JediNotFoundException;
import br.com.globallabs.springwebmvc.model.Jedi;
import br.com.globallabs.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JediService {

    @Autowired
    private JediRepository repository;

    public List<Jedi> findAll() {
        return repository.findAll();
    }

    public Jedi findById(final Long id) throws JediNotFoundException {
        final Optional<Jedi> jedi = repository.findById(id);

        if (jedi.isPresent()) {
            return jedi.get();
        } else {
            throw new JediNotFoundException();
        }
    }

    public Jedi save(final Jedi jedi) {
        return repository.save(jedi);
    }

    public Jedi update(final Long id, final Jedi jediDTO) throws JediNotFoundException {
        final Optional<Jedi> oldJedi = repository.findById(id);
        final Jedi jedi;

        if (oldJedi.isPresent()) {
            jedi = oldJedi.get();
        } else {
            throw new JediNotFoundException();
        }
        jedi.setName(jediDTO.getName());
        jedi.setLastname(jediDTO.getLastname());

        return repository.save(jedi);
    }

    public void delete(final Long id) throws JediNotFoundException {

        final Jedi jedi = findById(id);
        repository.delete(jedi);
    }
}
