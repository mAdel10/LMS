package com.lmsllcdrdapp.lms.managers;

import com.lmsllcdrdapp.lms.backend.models.Token;
import com.lmsllcdrdapp.lms.utilities.CachingManager;

public class TokenManager {

    private static TokenManager self;
    private Token token;

    public static TokenManager getInstance() {
        if (self == null) {
            self = new TokenManager();
        }
        return self;
    }

    private TokenManager() {
        token = CachingManager.getInstance().loadToken();
    }

    public Token getToken() {
        return token;
    }

    public void saveToken(Token token) {
        if (token == null)
            return;
        this.token = token;
        CachingManager.getInstance().saveToken(token);
    }

    public String getAccessToken() {
        return token.getAuthToken();
    }

    public String getRefreshToken() {
        return token.getAuthToken();
    }

    public void delete() {
        CachingManager.getInstance().deleteToken();
        token = null;
    }

    public boolean isTokenExist() {
        return token != null;
    }
}
