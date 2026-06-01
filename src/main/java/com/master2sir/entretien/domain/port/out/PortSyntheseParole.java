package com.master2sir.entretien.domain.port.out;

public interface PortSyntheseParole {
    byte[] synthetiser(String texte, String idVoix);
}
