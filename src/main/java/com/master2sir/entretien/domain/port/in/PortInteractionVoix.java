package com.master2sir.entretien.domain.port.in;

public interface PortInteractionVoix {
    String transcrireAudio(byte[] donneesAudio);
    byte[] synthetiserParole(String texte);
}
