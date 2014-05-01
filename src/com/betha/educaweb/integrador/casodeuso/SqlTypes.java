package com.betha.educaweb.integrador.casodeuso;

public enum SqlTypes {

	INSERTE{
		@Override
		public String getValue() {
			return super.getValue() + " INTO";
		}
	}, DELETE, UPDATE, SELECT {
		@Override
		public String getValue() {
			return super.getValue() + " * FROM";
		}
	};

	/**
	 * 
	 * @param value
	 * @return
	 * @throws IllegalArgumentException
	 *             caso o value nao corresponda a nenhuma constante
	 * @throws NullPointerException
	 *             caso o value seja nulo
	 */
	public static SqlTypes parse(String value) throws IllegalArgumentException,
			NullPointerException {
		for (SqlTypes type : SqlTypes.values()) {
			if (value.toUpperCase().equals(type.name())) {
				return type;
			}
		}
		throw new IllegalArgumentException(
				"o value passado nao corresponde a nenhuma constante em SqlTypes");
	}

	public String getValue() {
		return this.name();
	}

}
