package com.lmsllcdrdapp.lms.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Center;
import com.lmsllcdrdapp.lms.backend.models.Course;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.CenterOperation;
import com.lmsllcdrdapp.lms.backend.operations.CoursesByCenterIdOperation;
import com.lmsllcdrdapp.lms.controllers.adapters.CourseAdapter;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.helpers.Constants;
import com.lmsllcdrdapp.lms.utilities.Utilities;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CenterActivity extends BaseActivity implements RequestObserver, OnMapReadyCallback {

    @BindView(R.id.center_imageView)
    CircleImageView centerImageView;
    @BindView(R.id.center_name_textView)
    TextView nameTextView;
    @BindView(R.id.center_address_textView)
    TextView addressTextView;
    @BindView(R.id.center_ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.center_rate_textView)
    TextView rateTextView;
    @BindView(R.id.center_mapView)
    MapView centerMapView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private GoogleMap mMap;
    private CourseAdapter courseAdapter;
    private Center center;
    private String centerId;

    private final static int REQUEST_COURSE_BY_CENTER_ID = 1;
    private final static int REQUEST_CENTER_BY_ID = 2;

    public CenterActivity() {
        super(R.layout.activity_center, true);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        ButterKnife.bind(this);
        toolbarTextView.setText(getString(R.string.center_details));
        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());
        toolbarTextView.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        centerId = getIntent().getStringExtra(Constants.INTENT_ID);
        getCenterById(centerId);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(this);
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng itemPlace = new LatLng(center.getLat(), center.getLng());

        MarkerOptions itemMarker = new MarkerOptions().position(itemPlace).title(center.getName());
        mMap.addMarker(itemMarker);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(itemPlace).zoom(14).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void getCourseByCenterId(String id) {
        CoursesByCenterIdOperation operation = new CoursesByCenterIdOperation(id, REQUEST_COURSE_BY_CENTER_ID,
                false, this);
        operation.addRequestObserver(this);
        operation.execute();
    }

    private void getCenterById(String id) {
        CenterOperation operation = new CenterOperation(id, REQUEST_CENTER_BY_ID,
                false, this);
        operation.addRequestObserver(this);
        operation.execute();
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
        } else if (requestId.equals(REQUEST_COURSE_BY_CENTER_ID)) {
            List<Course> courses = (List<Course>) resultObject;
            if (courses != null && !courses.isEmpty()) {
                courseAdapter = new CourseAdapter(courses, this, center, courses.get(0).getInstructor());
                recyclerView.setAdapter(courseAdapter);
            }
        } else if (requestId.equals((REQUEST_CENTER_BY_ID))) {
            center = (Center) resultObject;
            if (center.getUser() != null) {
                Picasso.with(this)
                        .load(center.getUser().getImage())
                        .placeholder(R.drawable.img_placeholder)
                        .into(centerImageView);

                nameTextView.setText(center.getName());

                addressTextView.setText(center.getAddress());
                ratingBar.setRating(center.getRate());

                if (center.getRateCount() != 0) {
                    String rate = ((center.getRate() / center.getRateCount()) + "/5");
                    rateTextView.setText(rate);
                } else {
                    rateTextView.setText("0/5");
                }
            }

            getCourseByCenterId(centerId);

            if (centerMapView != null) {
                centerMapView.onCreate(null);
                centerMapView.onResume();
                centerMapView.getMapAsync(this);
            }
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }
}