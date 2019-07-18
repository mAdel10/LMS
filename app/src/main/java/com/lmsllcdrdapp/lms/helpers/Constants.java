package com.lmsllcdrdapp.lms.helpers;

public class Constants {

    /**
     * ---------------------------------------------------------------------------------------------
     * ---------------------------------------- API ------------------------------------------------
     */
    public static final String BASE_URL = "https://node-lms-api.herokuapp.com/api/";
    public static final String SERVICES_STUDENT_SIGN_UP = "students/register";
    public static final String SERVICES_INSTRUCTOR_SIGN_UP = "instructors/register";
    public static final String SERVICES_CENTER_SIGN_UP = "centers/register";
    public static final String SERVICES_USER_SIGN_IN = "users/login";
    public static final String SERVICES_USER_SIGN_IN_SOCIAL = "users/loginSocial";
    public static final String SERVICES_USER_PROFILE = "users/me";
    public static final String SERVICES_GET_NOTIFICATION = "notifications";
    public static final String SERVICES_UPDATE_PROFILE = "users/editProfile";
    public static final String SERVICES_GET_ENROLLMENT = "groups/enrollments";
    public static final String SERVICES_PUSH_REFRESH_TOKEN = "acl/users/refreshtoken";
    public static final String SERVICES_GET_INSTRUCTOR_BY_ID = "instructors";
    public static final String SERVICES_GET_CENTER_BY_ID = "centers";
    public static final String SERVICES_GET_COURSES_BY_INSTRUCTOR_ID = "courses/instructor";
    public static final String SERVICES_GET_COURSES_BY_CENTER_ID = "courses/center";
    public static final String SERVICES_GET_GROUPS_BY_INSTRUCTOR_ID = "groups/instructor";
    public static final String SERVICES_GET_GROUPS_BY_CENTER_ID = "groups/center";
    public static final String SERVICES_ADD_COURSE = "courses/add";
    public static final String SERVICES_ADD_GROUPS = "groups/add";
    public static final String SERVICES_GET_CATEGORIES = "categories";
    public static final String SERVICES_GET_LANGUAGES = "languages";
    public static final String SERVICES_GET_COUNTRIES = "countries";
    public static final String SERVICES_GET_CITIES = "cities";
    public static final String SERVICES_GET_CHAT_GROUP = "chats/byGroup";
    public static final String SERVICES_GET_GROUPS_BY_COURSE_ID = "groups/course";
    public static final String SERVICES_CHAT_SEND_MESSAGE = "chats/sendMessage";
    public static final String SERVICES_GET_CHAT = "groups/chats";
    public static final String SERVICES_GET_COURSES = "courses";
    public static final String SERVICES_APPLY_GROUP = "groups/apply";
    public static final String SERVICES_COURSES_SEARCH = "courses/search";
    public static final String SERVICES_INSTRUCTORS_SEARCH = "instructors/search";
    public static final String SERVICES_CENTERS_SEARCH = "centers/search";
    public static final String SERVICES_CENTERS_INSTRUCTOR = "centers/instructor";
    public static final String SERVICES_ADD_SESSION = "sessions";
    public static final String SERVICES_GET_SESSIONS_BY_GROUP_ID = "sessions/groupId";
    public static final String SERVICE_COURSE_RATE = "courseRatings";
    public static final String SERVICE_INSTRUCTOR_RATE = "instructorRatings";
    public static final String SERVICE_CENTER_RATE = "centerRatings";

    /**
     * ---------------------------------------------------------------------------------------------
     * ---------------------------------------- KEYS -----------------------------------------------
     */
    public static final String INTENT_ID = "intentId";
    public static final String INTENT_KEY = "intentKey";
    public static final String INTENT_NAME = "intentName";
    public static final String INTENT_LOCALE = "intentLocale";
    public static final String INTENT_OBJECT = "intentObject";
    public static final String INTENT_SESSION = "intentSession";
    public static final String INTENT_GROUP = "intentGroup";

    /**
     * ---------------------------------------------------------------------------------------------
     * ---------------------------------------- FONTS ----------------------------------------------
     */
    public static final String FONT_POPPINS_LIGHT = "poppins_light.ttf";
    public static final String FONT_POPPINS_REGULAR = "poppins_regular.ttf";
    public static final String FONT_POPPINS_MEDIUM = "poppins_medium.ttf";
    public static final String FONT_POPPINS_SEMI_BOLD = "poppins_semi_bold.ttf";
    public static final String FONT_POPPINS_BOLD = "poppins_bold.ttf";

    /**
     * ---------------------------------------------------------------------------------------------
     * ---------------------------------------- TIMING ---------------------------------------------
     */
    public static final int SPLASH_TIME_OUT = 3000;

    /**
     * ---------------------------------------------------------------------------------------------
     * ---------------------------------------- Language -------------------------------------------
     */
    public static final String LOCALE_ENGLISH = "en";
    public static final String LOCALE_ENGLISH_US = "en_US";
    public static final String LOCALE_ARABIC = "ar";
}
