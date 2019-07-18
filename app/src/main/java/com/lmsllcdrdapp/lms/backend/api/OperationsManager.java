package com.lmsllcdrdapp.lms.backend.api;

import android.util.Log;

import com.lmsllcdrdapp.lms.R;
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
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.utilities.BaseApplication;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class OperationsManager {

    private static final String TAG = "OperationsManager";
    private static OperationsManager _instance = null;

    public static OperationsManager getInstance() {
        if (_instance == null)
            _instance = new OperationsManager();
        return _instance;
    }

    /**
     * This method to get the new token after old is expired.
     *
     * @param refreshToken String
     * @return the new token
     */
    public Token doRefreshToken(String refreshToken) throws IOException {
        Log.v(TAG, "doRefreshToken");
        Map<String, Object> data = new HashMap<>();
        data.put("refreshtoken", refreshToken);
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Token> call = apiService.doRefreshToken(headers, data);
        Response<Token> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public ResponseBody doStudentSignUp(SignForm signForm) throws IOException {
        Log.v(TAG, "doStudentSignUp");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.doStudentSignUp(headers, signForm);
        Response<ResponseBody> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public ResponseBody doInstructorSignUp(SignForm signForm) throws IOException {
        Log.v(TAG, "doInstructorSignUp");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.doInstructorSignUp(headers, signForm);
        Response<ResponseBody> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public ResponseBody doCenterSignUp(SignForm signForm) throws IOException {
        Log.v(TAG, "doCenterSignUp");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.doCenterSignUp(headers, signForm);
        Response<ResponseBody> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public Token doUserSignIn(SignForm signForm) throws IOException {
        Log.v(TAG, "doUserSignIn");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Token> call = apiService.doUserSignIn(headers, signForm);
        Response<Token> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public Token doUserSignInSocial(SignForm signForm) throws IOException {
        Log.v(TAG, "doUserSignInSocial");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Token> call = apiService.doUserSignInSocial(headers, signForm);
        Response<Token> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Group> doGetChat() throws IOException {
        Log.v(TAG, "doGetChat");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Group>> call = apiService.doGetChat(headers);
        Response<List<Group>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Course> doGetCourse() throws IOException {
        Log.v(TAG, "doGetCourse");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Course>> call = apiService.doGetCourse(headers);
        Response<List<Course>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }


    public User doUserProfile() throws IOException {
        Log.v(TAG, "doUserProfile");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<User> call = apiService.doUserProfile(headers);
        Response<User> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }


    public ResponseBody doUpdateProfile(EditProfileForm editProfileForm) throws IOException {
        Log.v(TAG, "doUpdateProfile");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.doUpdateProfile(headers, editProfileForm);
        Response<ResponseBody> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Notification> doGetNotification() throws IOException {
        Log.v(TAG, "doUserProfile");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Notification>> call = apiService.doGetNotification(headers);
        Response<List<Notification>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Group> doGetEnrollment() throws IOException {
        Log.v(TAG, "doUserProfile");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Group>> call = apiService.doGetEnrollment(headers);
        Response<List<Group>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public Instructor doGetInstructor(String id) throws IOException {
        Log.v(TAG, "doGetInstructor");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Instructor> call = apiService.doGetInstructor(headers, id);
        Response<Instructor> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Center> doGetCenters() throws IOException {
        Log.v(TAG, "doGetCenters");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Center>> call = apiService.doGetCenters(headers);
        Response<List<Center>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public Center doGetCenter(String id) throws IOException {
        Log.v(TAG, "doGetCenter");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Center> call = apiService.doGetCenter(headers, id);
        Response<Center> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Course> doGetCoursesByInstructorId(String id) throws IOException {
        Log.v(TAG, "doGetCoursesByInstructorId");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Course>> call = apiService.doGetCoursesByInstructorId(headers, id);
        Response<List<Course>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Course> doGetCoursesByCenterId(String id) throws IOException {
        Log.v(TAG, "doGetCoursesByCenterId");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Course>> call = apiService.doGetCoursesByCenterId(headers, id);
        Response<List<Course>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Group> doGetGroupsByInstructorId(String id) throws IOException {
        Log.v(TAG, "doGetGroupsByInstructorId");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Group>> call = apiService.doGetGroupsByInstructorId(headers, id);
        Response<List<Group>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Group> doGetGroupsByCenterId(String id) throws IOException {
        Log.v(TAG, "doGetGroupsByCenterId");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Group>> call = apiService.doGetGroupsByCenterId(headers, id);
        Response<List<Group>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public ResponseBody doInstructorAddCourse(Course course) throws IOException {
        Log.v(TAG, "doInstructorAddCourse");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.doInstructorAddCourse(headers, course);
        Response<ResponseBody> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public ResponseBody doCenterAddGroup(Group group) throws IOException {
        Log.v(TAG, "doCenterAddGroup");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.doCenterAddGroup(headers, group);
        Response<ResponseBody> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Category> doGetCategory() throws IOException {
        Log.v(TAG, "doGetCategory");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Category>> call = apiService.doGetCategory(headers);
        Response<List<Category>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Language> doGetLanguage() throws IOException {
        Log.v(TAG, "doGetLanguage");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Language>> call = apiService.doGetLanguage(headers);
        Response<List<Language>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Country> doGetCountry() throws IOException {
        Log.v(TAG, "doGetCountry");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Country>> call = apiService.doGetCountry(headers);
        Response<List<Country>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<City> doGetCity() throws IOException {
        Log.v(TAG, "doGetCity");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<City>> call = apiService.doGetCity(headers);
        Response<List<City>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Course> doSearchCourses(SearchFrom searchFrom) throws IOException {
        Log.v(TAG, "doSearchCourses");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Course>> call = apiService.doSearchCourses(headers, searchFrom);
        Response<List<Course>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Instructor> doSearchInstructors(SearchFrom searchFrom) throws IOException {
        Log.v(TAG, "doSearchInstructors");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Instructor>> call = apiService.doSearchInstructors(headers, searchFrom);
        Response<List<Instructor>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Center> doSearchCenters(SearchFrom searchFrom) throws IOException {
        Log.v(TAG, "doSearchInstructors");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Center>> call = apiService.doSearchCenters(headers, searchFrom);
        Response<List<Center>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Group> doGetGroupsByCourseId(String id) throws IOException {
        Log.v(TAG, "doGetGroupsByCourseId");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Group>> call = apiService.doGetGroupsByCourseId(headers, id);
        Response<List<Group>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Chat> doGetChatByGroupId(String id) throws IOException {
        Log.v(TAG, "doGetChatByGroupId");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Chat>> call = apiService.doGetChatByGroupId(headers, id);
        Response<List<Chat>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public ResponseBody doSendChat(Chat chat) throws IOException {
        Log.v(TAG, "doSendChat");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.doSendChat(headers, chat);
        Response<ResponseBody> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public ResponseBody doGetGroupID(GroupID groupID) throws IOException {
        Log.v(TAG, "doGetGroupID");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.doGetGroupID(headers, groupID);
        Response<ResponseBody> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Instructor> doGetInstructorsByCenterId(String id) throws IOException {
        Log.v(TAG, "doGetInstructorsByCenterId");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Instructor>> call = apiService.doGetInstructorsByCenterId(headers, id);
        Response<List<Instructor>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public ResponseBody doAddSession(Session session) throws IOException {
        Log.v(TAG, "doAddSession");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.doAddSession(headers, session);
        Response<ResponseBody> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public ResponseBody doCourseRate(CourseRate courseRate) throws IOException {
        Log.v(TAG, "doCourseRate");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.doCourseRate(headers, courseRate);
        Response<ResponseBody> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public ResponseBody doInstructorRate(InstructorRate instructorRate) throws IOException {
        Log.v(TAG, "doInstructorRate");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.doInstructorRate(headers, instructorRate);
        Response<ResponseBody> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public ResponseBody doCenterRate(CenterRate centerRate) throws IOException {
        Log.v(TAG, "doCenterRate");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.doCenterRate(headers, centerRate);
        Response<ResponseBody> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    public List<Session> doGetSessionByGroupId(String id) throws IOException {
        Log.v(TAG, "doGetSessionByGroupId");
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Session>> call = apiService.doGetSessionByGroupId(headers, id);
        Response<List<Session>> response = call.execute();

        ensureHttpSuccess(response);

        return response.body();
    }

    /**
     * Ensure http request has success
     *
     * @param response of the api
     * @throws IOException if an error found, then throw an exception with the error, and the above layer (Operation) will catch it.
     */
    private void ensureHttpSuccess(Response response) throws IOException {
        if (!response.isSuccessful() && response.errorBody() != null) {
            ResponseBody errorBody = response.errorBody();
            // assert errorBody != null;
            String errorMSG = errorBody.string();
            int code = response.code();
            if (code == 504 && Utilities.isNullString(errorMSG)) // Request timeout
                errorMSG = BaseApplication.getContext().getString(R.string.request_error);
            else if (!Utilities.isNullString(errorMSG) && errorMSG.trim().startsWith("{")
                    && errorMSG.trim().endsWith("}")) {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(errorMSG);
                    errorMSG = jsonObject.getString("message");
                    if (jsonObject.has("code"))
                        code = jsonObject.optInt("code");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            throw new CTHttpError(errorMSG, code);
        }
    }
}
