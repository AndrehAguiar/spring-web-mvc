package br.com.globallabs.springwebmvc.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Jedi {

    @Id
    @Column(name = "jedi_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "jedi_nema")
    @NotBlank(message = "Name may not be blank")
    @Size(min = 3, max = 20, message = "Name must have between 3 and 20 letters")
    private String name;

    @Column(name = "jedi_lastname")
    @Size(max = 20, message = "Lastname must not have more than 20 letters")
    private String lastname;

    public Jedi(final String name, final String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public Jedi() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
