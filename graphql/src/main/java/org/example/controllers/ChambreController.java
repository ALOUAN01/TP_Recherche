package org.example.controllers;

import org.example.entitys.Chambre;
import org.example.services.ChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ChambreController {

    @Autowired
    private ChambreService chambreService;
    @QueryMapping
    public List<Chambre> getAllChambres() {
        return chambreService.getAllChambres();
    }

    @QueryMapping
    public Chambre getChambreById(@Argument Long id) {
        Chambre chambre = chambreService.getChambreById(id);
        if (chambre == null) {
            throw new RuntimeException(String.format("Chambre %s not found", id));
        }
        return chambre;
    }
    @MutationMapping
    public Chambre createChambre(@Argument Chambre chambre) {
        return chambreService.createChambre(chambre);
    }
    @MutationMapping
    public Chambre updateChambre(@Argument Long id, @Argument Chambre chambre) {
        return chambreService.updateChambre(id, chambre);
    }
    @MutationMapping
    public Boolean deleteChambre(@Argument Long id) {
        chambreService.deleteChambre(id);
        return true;
    }
}
