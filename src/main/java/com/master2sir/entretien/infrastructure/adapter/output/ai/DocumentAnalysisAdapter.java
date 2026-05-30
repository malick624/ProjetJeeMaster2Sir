package com.master2sir.entretien.infrastructure.adapter.output.ai;

import com.master2sir.entretien.domain.port.out.PortAnalyseDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DocumentAnalysisAdapter implements PortAnalyseDocument {

    @Override
    public String extraireTexte(byte[] donneesDocument, String nomFichier) {
        try {
            // C'est une implémentation placeholder
            // En production, vous utiliseriez Apache Tika ou une bibliothèque similaire
            log.info("Extraction de texte du document: {} (taille: {} octets)", nomFichier, donneesDocument.length);
            
            // Extraction de texte placeholder
            return "Texte extrait du document";
            
            /* L'implémentation de production serait:
            Tika tika = new Tika();
            InputStream inputStream = new ByteArrayInputStream(donneesDocument);
            return tika.parseToString(inputStream);
            */
        } catch (Exception e) {
            log.error("Erreur lors de l'extraction de texte du document", e);
            throw new RuntimeException("Échec de l'extraction de texte du document", e);
        }
    }
}
