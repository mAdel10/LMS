package com.lmsllcdrdapp.lms.managers;

import com.lmsllcdrdapp.lms.backend.models.User;
import com.lmsllcdrdapp.lms.utilities.CachingManager;

public class UserManager {

    private static UserManager self;
    private final static String TAG = "UserManager";
    private User currentUser;

    public static UserManager getInstance() {
        if (self == null) {
            self = new UserManager();
        }
        return self;
    }

    private UserManager() {
        currentUser = CachingManager.getInstance().loadUser();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void saveUser(User currentUser) {
        if (currentUser == null)
            return;
        this.currentUser = currentUser;
        CachingManager.getInstance().saveUser(currentUser);
    }


    public boolean isStudent() {
        return currentUser.getStudent() != null;
    }

    public boolean isInstructor() {
        return currentUser.getInstructor() != null;
    }
    public boolean isCenter() {
        return currentUser.getCenter() != null;
    }

    public void logout() {
        TokenManager.getInstance().delete();
        CachingManager.getInstance().deleteUser();
        currentUser = null;
    }

    public boolean isUserLoggedIn() {
        return currentUser != null;
    }
}
