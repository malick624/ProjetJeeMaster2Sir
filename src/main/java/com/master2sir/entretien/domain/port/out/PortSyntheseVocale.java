package com.master2sir.entretien.domain.port.out;

public interface PortSyntheseVocale {
    String transcrire(byte[] donneesAudio, String langue);
}
