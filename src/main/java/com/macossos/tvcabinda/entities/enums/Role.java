package com.macossos.tvcabinda.entities.enums;

import com.macossos.tvcabinda.entities.enums.Role;

public enum Role {
	ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE");

	private Integer codigo;
	private String descricao;

	private Role(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Role toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Role role : Role.values()) {
			if (cod.equals(role.getCodigo())) {
				return role;
			}

		}
		throw new IllegalArgumentException("Role invalido");
	}

}
