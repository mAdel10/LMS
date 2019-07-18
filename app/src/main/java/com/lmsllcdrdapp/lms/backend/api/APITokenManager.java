package com.lmsllcdrdapp.lms.backend.api;

/**
 * This interfaced used by AuthorizationInterceptor to manage token states.
 */
public interface APITokenManager {

    String getToken();

    boolean hasToken();

    void clearToken();

    String refreshToken();
}
