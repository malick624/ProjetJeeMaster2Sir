package com.master2sir.entretien.domain.service;

import com.master2sir.entretien.domain.model.Reponse;
import com.master2sir.entretien.domain.model.Question;
import com.master2sir.entretien.domain.port.out.PortServiceChatIA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceAnalyseReponses {

    private final PortServiceChatIA portServiceChatIA;

    public Reponse analyserReponse(Question question, String contenuReponse) {
        return portServiceChatIA.evaluerReponse(question.getContent(), contenuReponse, question.getContext());
    }
}
