package org.detteapp.odc.entities;

import lombok.Getter;
import lombok.Setter;
import org.detteapp.odc.Identifiable;

import java.math.BigDecimal;

@Getter
@Setter
public class ArticleEntity implements Identifiable {
    private Integer id;
    private String libelle;
    private BigDecimal prix;
    private Integer quantity;
    private Integer seuil;

    @Override
    public int getId() {
        return id;
    }
}
