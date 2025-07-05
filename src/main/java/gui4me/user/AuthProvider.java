package gui4me.user;

public enum AuthProvider {
    LOCAL,
    GOOGLE,
    GITHUB;

    public static AuthProvider fromString(String value) {
        for (AuthProvider provider : AuthProvider.values()) {
            if (provider.name().equalsIgnoreCase(value)) {
                return provider;
            }
        }
        throw new IllegalArgumentException("Unknown auth provider: " + value);
    }
}
