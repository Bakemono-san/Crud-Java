package org.detteapp.odc.entities;

import lombok.Getter;
import lombok.Setter;
import org.detteapp.odc.Identifiable;

@Getter
@Setter
public class ClientEntity implements Identifiable {
    private int id;
    private String surname;
    private String email;
    private String phone;
    private String addresse;
    private int userId;
    private int montant_max;
    private int categorie_id;
}
