package org.jesuitas.TFG.modelo.etapaEducativa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "etapaeducativa")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EtapaEducativa {

    @Id
    @NotNull
    @Column
    private String idEtapaEducativa;

    @Column(name = "nombreEtapa")
    private String nombreEtapaEducativa;
}
