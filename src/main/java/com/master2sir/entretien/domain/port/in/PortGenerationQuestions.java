package com.master2sir.entretien.domain.port.in;

import com.master2sir.entretien.domain.model.SessionEntretien;
import com.master2sir.entretien.domain.model.Question;
import java.util.UUID;
import java.util.List;

public interface PortGenerationQuestions {
    List<Question> genererQuestions(SessionEntretien sessionEntretien);
    Question obtenirQuestionSuivante(UUID idEntretien);
}
