package com.master2sir.entretien.domain.port.out;

public interface PortGenerationAvatar {
    String genererUrlVideo(String texte, String idAvatar);
    byte[] genererFrameVideo(String texte, String idAvatar, String emotion);
}
