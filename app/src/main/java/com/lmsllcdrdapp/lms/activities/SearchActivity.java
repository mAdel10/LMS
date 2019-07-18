package com.lmsllcdrdapp.lms.activities;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Category;
import com.lmsllcdrdapp.lms.backend.models.Center;
import com.lmsllcdrdapp.lms.backend.models.City;
import com.lmsllcdrdapp.lms.backend.models.Country;
import com.lmsllcdrdapp.lms.backend.models.Course;
import com.lmsllcdrdapp.lms.backend.models.Instructor;
import com.lmsllcdrdapp.lms.backend.models.Language;
import com.lmsllcdrdapp.lms.backend.models.SearchFrom;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.CategoryOperation;
import com.lmsllcdrdapp.lms.backend.operations.CityOperation;
import com.lmsllcdrdapp.lms.backend.operations.CountryOperation;
import com.lmsllcdrdapp.lms.backend.operations.LanguageOperation;
import com.lmsllcdrdapp.lms.backend.operations.SearchCentersOperation;
import com.lmsllcdrdapp.lms.backend.operations.SearchCoursesOperation;
import com.lmsllcdrdapp.lms.backend.operations.SearchInstructorsOperation;
import com.lmsllcdrdapp.lms.controllers.adapters.CenterInstructorAdapter;
import com.lmsllcdrdapp.lms.helpers.Constants;
import com.lmsllcdrdapp.lms.views.AppSpinnerAdapter;
import com.lmsllcdrdapp.lms.controllers.adapters.CourseAdapter;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements RequestObserver,
        AdapterView.OnItemSelectedListener {

    @BindView(R.id.search_type_spinner)
    AppCompatSpinner typeSpinner;
    @BindView(R.id.search_category_spinner)
    AppCompatSpinner categorySpinner;
    @BindView(R.id.search_language_spinner)
    AppCompatSpinner languageSpinner;
    @BindView(R.id.search_country_spinner)
    AppCompatSpinner countrySpinner;
    @BindView(R.id.search_city_spinner)
    AppCompatSpinner citySpinner;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private final static int REQUEST_SEARCH_COURSES = 1;
    private final static int REQUEST_SEARCH_INSTRUCTORS = 2;
    private final static int REQUEST_SEARCH_CENTERS = 3;
    private final static int REQUEST_GET_CATEGORIES = 4;
    private final static int REQUEST_GET_LANGUAGES = 5;
    private final static int REQUEST_GET_COUNTRIES = 6;
    private final static int REQUEST_GET_CITIES = 7;

    private final static String TYPE_COURSES = "Courses";
    private final static String TYPE_INSTRUCTORS = "Instructors";
    private final static String TYPE_CENTERS = "Educational Centers";

    private SearchFrom searchFrom;
    private String type;

    List<Course> courses;
    List<Instructor> instructors;
    List<Center> centers;
    private List<Category> categories;
    private List<Language> languages;
    private List<Country> countries;
    private List<City> cities;

    public SearchActivity() {
        super(R.layout.activity_search, true);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        ButterKnife.bind(this);
        toolbarTextView.setVisibility(View.VISIBLE);
        toolbarSearchEditText.setVisibility(View.GONE);
        searchFrom = new SearchFrom();

        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());

        String searchWord = getIntent().getStringExtra(Constants.INTENT_KEY);
        searchFrom.setKeyword(searchWord);
        toolbarTextView.setText(searchWord);

        initRV();
        fillTypeSpinner();
        submitSearch(TYPE_COURSES, searchFrom);
        getCategories();
        getLanguages();
        getCountries();
        getCities();

        toolbarSearchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard();
                addSearchKeyword();
                runSwipeRefreshLayout();
                return true;
            }
            return false;
        });
    }

    private void hideKeyboard() {
        if (getCurrentFocus() != null) {
            Utilities.hideSoftKeyboard(this, Objects.requireNonNull(getCurrentFocus()));
        }
    }

    private void initRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @SuppressWarnings("unchecked")
    private void fillTypeSpinner() {
        List<String> types = new ArrayList<>();
        types.add(TYPE_COURSES);
        types.add(TYPE_INSTRUCTORS);
        types.add(TYPE_CENTERS);
        AppSpinnerAdapter spinnerAdapter = new AppSpinnerAdapter(this, R.layout.spinner_item, types);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(spinnerAdapter);
        typeSpinner.setOnItemSelectedListener(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handleRequestFinished(Object requestId, Throwable error, Object resultObject) {
        if (error != null) {
            if (error instanceof CTHttpError) {
                int code = ((CTHttpError) error).getStatusCode();
                String errorMsg = ((CTHttpError) error).getErrorMsg();
                if (code == -1 || Utilities.isNullString(errorMsg)) {
                    ErrorDialog.showMessageDialog(getString(R.string.invalid_request), getString(R.string.request_server_error), this);
                } else if (code != 401) {
                    ErrorDialog.showMessageDialog(getString(R.string.invalid_request), errorMsg, this);
                }
            }
        } else if (resultObject != null) {
            AppSpinnerAdapter spinnerAdapter;
            switch ((int) requestId) {
                case REQUEST_SEARCH_COURSES:
                    courses = (List<Course>) resultObject;
                    CourseAdapter courseAdapter = new CourseAdapter(courses, this, null, null);
                    recyclerView.setAdapter(courseAdapter);
                    break;
                case REQUEST_SEARCH_INSTRUCTORS:
                    instructors = (List<Instructor>) resultObject;
                    CenterInstructorAdapter centerInstructorAdapter1 = new CenterInstructorAdapter(instructors, this, CenterInstructorAdapter.INSTRUCTORS);
                    recyclerView.setAdapter(centerInstructorAdapter1);
                    break;
                case REQUEST_SEARCH_CENTERS:
                    centers = (List<Center>) resultObject;
                    CenterInstructorAdapter centerInstructorAdapter2 = new CenterInstructorAdapter(centers, this, CenterInstructorAdapter.CENTER);
                    recyclerView.setAdapter(centerInstructorAdapter2);
                    break;
                case REQUEST_GET_CATEGORIES:
                    categories = new ArrayList<>();
                    categories.add(new Category("-1", getString(R.string.category)));
                    categories.addAll((Collection<? extends Category>) resultObject);
                    spinnerAdapter = new AppSpinnerAdapter(this, R.layout.spinner_item, categories);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    categorySpinner.setAdapter(spinnerAdapter);
                    categorySpinner.setOnItemSelectedListener(this);
                    break;
                case REQUEST_GET_LANGUAGES:
                    languages = new ArrayList<>();
                    languages.add(new Language("-1", getString(R.string.language)));
                    languages.addAll((Collection<? extends Language>) resultObject);
                    spinnerAdapter = new AppSpinnerAdapter(this, R.layout.spinner_item, languages);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    languageSpinner.setAdapter(spinnerAdapter);
                    languageSpinner.setOnItemSelectedListener(this);
                    break;
                case REQUEST_GET_COUNTRIES:
                    countries = new ArrayList<>();
                    countries.add(new Country("-1", getString(R.string.country)));
                    countries.addAll((Collection<? extends Country>) resultObject);
                    spinnerAdapter = new AppSpinnerAdapter(this, R.layout.spinner_item, countries);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    countrySpinner.setAdapter(spinnerAdapter);
                    countrySpinner.setOnItemSelectedListener(this);
                    break;
                case REQUEST_GET_CITIES:
                    cities = new ArrayList<>();
                    cities.add(new City("-1", getString(R.string.city)));
                    cities.addAll((Collection<? extends City>) resultObject);
                    spinnerAdapter = new AppSpinnerAdapter(this, R.layout.spinner_item, cities);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(spinnerAdapter);
                    citySpinner.setOnItemSelectedListener(this);
                    break;
            }
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.search_type_spinner:
                if (position == 0) {
                    type = TYPE_COURSES;
                } else if (position == 1) {
                    type = TYPE_INSTRUCTORS;
                } else {
                    type = TYPE_CENTERS;
                }
                break;
            case R.id.search_category_spinner:
                if (position == 0) searchFrom.setCategoryId(null);
                else searchFrom.setCategoryId(categories.get(position).getId());
                break;
            case R.id.search_language_spinner:
                if (position == 0) searchFrom.setLanguageId(null);
                else searchFrom.setLanguageId(languages.get(position).getId());
                break;
            case R.id.search_country_spinner:
                if (position == 0) searchFrom.setCountryId(null);
                else searchFrom.setCountryId(countries.get(position).getId());
                break;
            case R.id.search_city_spinner:
                if (position == 0) searchFrom.setCityId(null);
                else searchFrom.setCityId(cities.get(position).getId());
                break;
        }
        addSearchKeyword();
        runSwipeRefreshLayout();
    }

    private void runSwipeRefreshLayout() {
        recyclerView.setAdapter(null);
        submitSearch(type, searchFrom);
    }

    private void addSearchKeyword() {
        String keyword = toolbarSearchEditText.getText().toString().trim();
        if (keyword.isEmpty()) {
            searchFrom.setKeyword(null);
            return;
        }
        searchFrom.setKeyword(keyword);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void submitSearch(String type, SearchFrom searchFrom) {
        switch (type) {
            case TYPE_COURSES:
                submitSearchCourse(searchFrom);
                break;
            case TYPE_INSTRUCTORS:
                submitSearchInstructors(searchFrom);
                break;
            case TYPE_CENTERS:
                submitSearchCenters(searchFrom);
                break;
        }
    }

    private void getCategories() {
        CategoryOperation operation = new CategoryOperation(REQUEST_GET_CATEGORIES, false, this);
        operation.addRequestObserver(this);
        operation.execute();
    }

    private void getLanguages() {
        LanguageOperation operation = new LanguageOperation(REQUEST_GET_LANGUAGES, false, this);
        operation.addRequestObserver(this);
        operation.execute();
    }

    private void getCountries() {
        CountryOperation operation = new CountryOperation(REQUEST_GET_COUNTRIES, false, this);
        operation.addRequestObserver(this);
        operation.execute();
    }

    private void getCities() {
        CityOperation operation = new CityOperation(REQUEST_GET_CITIES, false, this);
        operation.addRequestObserver(this);
        operation.execute();
    }

    private void submitSearchCourse(SearchFrom searchFrom) {
        SearchCoursesOperation operation = new SearchCoursesOperation(searchFrom, REQUEST_SEARCH_COURSES, true, this);
        operation.addRequestObserver(this);
        operation.execute();
    }

    private void submitSearchInstructors(SearchFrom searchFrom) {
        SearchInstructorsOperation operation = new SearchInstructorsOperation(searchFrom, REQUEST_SEARCH_INSTRUCTORS, true, this);
        operation.addRequestObserver(this);
        operation.execute();
    }

    private void submitSearchCenters(SearchFrom searchFrom) {
        SearchCentersOperation operation = new SearchCentersOperation(searchFrom, REQUEST_SEARCH_CENTERS, true, this);
        operation.addRequestObserver(this);
        operation.execute();
    }
}

