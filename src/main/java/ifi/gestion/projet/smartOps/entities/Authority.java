package ifi.gestion.projet.smartOps.entities;

public enum Authority {
	ADMIN("user"), MEMBER("member");
    @SuppressWarnings("unused")
	private final String value;

    Authority(String value) {
        this.value = value;
    }
}
