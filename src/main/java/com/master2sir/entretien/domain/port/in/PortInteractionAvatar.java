package com.master2sir.entretien.domain.port.in;

public interface PortInteractionAvatar {
    String genererVideoAvatar(String texte);
    byte[] genererFrameAvatar(String texte, String emotion);
}
