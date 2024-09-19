package org.detteapp.odc.entities;

import lombok.Getter;
import lombok.Setter;
import org.detteapp.odc.Identifiable;

@Getter
@Setter
public class DetteEntity implements Identifiable {
    private int id;
    private float montant;
    private int clientId;

    @Override
    public int getId() {
        return this.id;
    }
}
