package com.master2sir.entretien.application.usecase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDemarrerEntretien {
    private UUID idEntretien;

	public UUID getIdEntretien() {
		return idEntretien;
	}

	public void setIdEntretien(UUID idEntretien) {
		this.idEntretien = idEntretien;
	}
    
    
}
