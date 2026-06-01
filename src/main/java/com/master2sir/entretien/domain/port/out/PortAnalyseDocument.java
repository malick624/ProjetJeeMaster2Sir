package com.master2sir.entretien.domain.port.out;

public interface PortAnalyseDocument {
    String extraireTexte(byte[] donneesDocument, String nomFichier);
}
