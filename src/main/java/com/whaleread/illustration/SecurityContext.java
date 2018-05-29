package com.whaleread.illustration;

/**
 * @author Dolphin
 */
public class SecurityContext {
    private long id;
    private String displayName;

    private boolean authenticated = false;

    public SecurityContext() {
    }

    public SecurityContext(long id, String displayName) {
        this.id = id;
        this.displayName = displayName;
        this.authenticated = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
