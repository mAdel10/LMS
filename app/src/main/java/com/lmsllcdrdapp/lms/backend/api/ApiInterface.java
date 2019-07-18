package com.lmsllcdrdapp.lms.backend.api;

import com.lmsllcdrdapp.lms.backend.models.Category;
import com.lmsllcdrdapp.lms.backend.models.Center;
import com.lmsllcdrdapp.lms.backend.models.CenterRate;
import com.lmsllcdrdapp.lms.backend.models.Chat;
import com.lmsllcdrdapp.lms.backend.models.City;
import com.lmsllcdrdapp.lms.backend.models.Country;
import com.lmsllcdrdapp.lms.backend.models.Course;
import com.lmsllcdrdapp.lms.backend.models.CourseRate;
import com.lmsllcdrdapp.lms.backend.models.EditProfileForm;
import com.lmsllcdrdapp.lms.backend.models.Group;
import com.lmsllcdrdapp.lms.backend.models.GroupID;
import com.lmsllcdrdapp.lms.backend.models.Instructor;
import com.lmsllcdrdapp.lms.backend.models.InstructorRate;
import com.lmsllcdrdapp.lms.backend.models.Language;
import com.lmsllcdrdapp.lms.backend.models.Notification;
import com.lmsllcdrdapp.lms.backend.models.SearchFrom;
import com.lmsllcdrdapp.lms.backend.models.Session;
import com.lmsllcdrdapp.lms.backend.models.SignForm;
import com.lmsllcdrdapp.lms.backend.models.Token;
import com.lmsllcdrdapp.lms.backend.models.User;
import com.lmsllcdrdapp.lms.helpers.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    /**
     * ---------------------------------------------------------------------------------------------
     * -------------------------------------- USER -------------------------------------------------
     * ---------------------------------------------------------------------------------------------
     */

    @POST(ApiClient.BASE_URL + Constants.SERVICES_PUSH_REFRESH_TOKEN)
    Call<Token> doRefreshToken(@HeaderMap Map<String, String> headers,
                               @Body Map<String, Object> params);

    @POST(ApiClient.BASE_URL + Constants.SERVICES_STUDENT_SIGN_UP)
    Call<ResponseBody> doStudentSignUp(@HeaderMap Map<String, String> headers,
                                       @Body SignForm signForm);

    @POST(ApiClient.BASE_URL + Constants.SERVICES_INSTRUCTOR_SIGN_UP)
    Call<ResponseBody> doInstructorSignUp(@HeaderMap Map<String, String> headers,
                                          @Body SignForm signForm);

    @POST(ApiClient.BASE_URL + Constants.SERVICES_CENTER_SIGN_UP)
    Call<ResponseBody> doCenterSignUp(@HeaderMap Map<String, String> headers,
                                      @Body SignForm signForm);

    @POST(ApiClient.BASE_URL + Constants.SERVICES_USER_SIGN_IN)
    Call<Token> doUserSignIn(@HeaderMap Map<String, String> headers,
                             @Body SignForm signForm);

    @POST(ApiClient.BASE_URL + Constants.SERVICES_USER_SIGN_IN_SOCIAL)
    Call<Token> doUserSignInSocial(@HeaderMap Map<String, String> headers,
                                   @Body SignForm signForm);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_CHAT)
    Call<List<Group>> doGetChat(@HeaderMap Map<String, String> headers);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_COURSES)
    Call<List<Course>> doGetCourse(@HeaderMap Map<String, String> headers);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_USER_PROFILE)
    Call<User> doUserProfile(@HeaderMap Map<String, String> headers);


    @POST(ApiClient.BASE_URL + Constants.SERVICES_UPDATE_PROFILE)
    Call<ResponseBody> doUpdateProfile(@HeaderMap Map<String, String> headers,
                                       @Body EditProfileForm editProfileForm);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_NOTIFICATION)
    Call<List<Notification>> doGetNotification(@HeaderMap Map<String, String> headers);


    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_ENROLLMENT)
    Call<List<Group>> doGetEnrollment(@HeaderMap Map<String, String> headers);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_INSTRUCTOR_BY_ID + "/{id}")
    Call<Instructor> doGetInstructor(@HeaderMap Map<String, String> headers,
                                     @Path("id") String id);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_CENTER_BY_ID)
    Call<List<Center>> doGetCenters(@HeaderMap Map<String, String> headers);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_CENTER_BY_ID + "/{id}")
    Call<Center> doGetCenter(@HeaderMap Map<String, String> headers,
                             @Path("id") String id);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_COURSES_BY_INSTRUCTOR_ID + "/{id}")
    Call<List<Course>> doGetCoursesByInstructorId(@HeaderMap Map<String, String> headers,
                                                  @Path("id") String id);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_COURSES_BY_CENTER_ID + "/{id}")
    Call<List<Course>> doGetCoursesByCenterId(@HeaderMap Map<String, String> headers,
                                              @Path("id") String id);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_GROUPS_BY_INSTRUCTOR_ID + "/{id}")
    Call<List<Group>> doGetGroupsByInstructorId(@HeaderMap Map<String, String> headers,
                                                @Path("id") String id);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_GROUPS_BY_CENTER_ID + "/{id}")
    Call<List<Group>> doGetGroupsByCenterId(@HeaderMap Map<String, String> headers,
                                            @Path("id") String id);

    @POST(ApiClient.BASE_URL + Constants.SERVICES_ADD_COURSE)
    Call<ResponseBody> doInstructorAddCourse(@HeaderMap Map<String, String> headers,
                                             @Body Course course);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_GROUPS_BY_COURSE_ID + "/{id}")
    Call<List<Group>> doGetGroupsByCourseId(@HeaderMap Map<String, String> headers,
                                            @Path("id") String id);

    @POST(ApiClient.BASE_URL + Constants.SERVICES_ADD_GROUPS)
    Call<ResponseBody> doCenterAddGroup(@HeaderMap Map<String, String> headers,
                                        @Body Group group);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_CATEGORIES)
    Call<List<Category>> doGetCategory(@HeaderMap Map<String, String> headers);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_LANGUAGES)
    Call<List<Language>> doGetLanguage(@HeaderMap Map<String, String> headers);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_COUNTRIES)
    Call<List<Country>> doGetCountry(@HeaderMap Map<String, String> headers);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_CITIES)
    Call<List<City>> doGetCity(@HeaderMap Map<String, String> headers);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_CHAT_GROUP + "/{id}")
    Call<List<Chat>> doGetChatByGroupId(@HeaderMap Map<String, String> headers,
                                        @Path("id") String id);

    @POST(ApiClient.BASE_URL + Constants.SERVICES_COURSES_SEARCH)
    Call<List<Course>> doSearchCourses(@HeaderMap Map<String, String> headers,
                                       @Body SearchFrom searchFrom);

    @POST(ApiClient.BASE_URL + Constants.SERVICES_INSTRUCTORS_SEARCH)
    Call<List<Instructor>> doSearchInstructors(@HeaderMap Map<String, String> headers,
                                               @Body SearchFrom searchFrom);

    @POST(ApiClient.BASE_URL + Constants.SERVICES_CENTERS_SEARCH)
    Call<List<Center>> doSearchCenters(@HeaderMap Map<String, String> headers,
                                       @Body SearchFrom searchFrom);

    @POST(ApiClient.BASE_URL + Constants.SERVICES_CHAT_SEND_MESSAGE)
    Call<ResponseBody> doSendChat(@HeaderMap Map<String, String> headers,
                                  @Body Chat chat);

    @POST(ApiClient.BASE_URL + Constants.SERVICES_APPLY_GROUP)
    Call<ResponseBody> doGetGroupID(@HeaderMap Map<String, String> headers,
                                    @Body GroupID groupID);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_CENTERS_INSTRUCTOR + "/{id}")
    Call<List<Instructor>> doGetInstructorsByCenterId(@HeaderMap Map<String, String> headers,
                                                      @Path("id") String id);

    @POST(ApiClient.BASE_URL + Constants.SERVICES_ADD_SESSION)
    Call<ResponseBody> doAddSession(@HeaderMap Map<String, String> headers,
                                    @Body Session session);

    @GET(ApiClient.BASE_URL + Constants.SERVICES_GET_SESSIONS_BY_GROUP_ID + "/{id}")
    Call<List<Session>> doGetSessionByGroupId(@HeaderMap Map<String, String> headers,
                                              @Path("id") String id);

    @POST(ApiClient.BASE_URL + Constants.SERVICE_COURSE_RATE)
    Call<ResponseBody> doCourseRate(@HeaderMap Map<String, String> headers,
                                    @Body CourseRate courseRate);

    @POST(ApiClient.BASE_URL + Constants.SERVICE_INSTRUCTOR_RATE)
    Call<ResponseBody> doInstructorRate(@HeaderMap Map<String, String> headers,
                                        @Body InstructorRate instructorRate);

    @POST(ApiClient.BASE_URL + Constants.SERVICE_CENTER_RATE)
    Call<ResponseBody> doCenterRate(@HeaderMap Map<String, String> headers,
                                        @Body CenterRate centerRate);

}

