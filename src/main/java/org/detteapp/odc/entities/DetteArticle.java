package org.detteapp.odc.entities;

import lombok.Getter;
import lombok.Setter;
import org.detteapp.odc.Identifiable;

@Getter
@Setter
public class DetteArticle implements Identifiable {
    private int id;
    private int detteId;
    private int articleId;
    private int quantite;
    private float prixVente;


    @Override
    public int getId() {
        return id;
    }
}
