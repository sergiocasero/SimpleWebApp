package com.simplewebapp.sergiocaserohernandez.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.simplewebapp.sergiocaserohernandez.androidtest.R;

import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkView;

import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

public class WebActivity extends AppCompatActivity {

    @Bind(R.id.container)
    LinearLayout container;

    @Bind(R.id.web_view)
    XWalkView webView;

    @BindString(R.string.base_url)
    String baseUrl;

    @BindString(R.string.no_permissions_url)
    String noPermissions;

    @BindString(R.string.app_name)
    String appName;

    @BindString(R.string.first_run)
    String firstRunKey;

    String[] permissions;


    private MultiplePermissionsListener dialogOnAnyDeniedMultiplePermissionsListener;
    private MultiplePermissionsListener multiplePermissionsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        initPermissionArray();

        if (isFirstRun()) {
            setupPermissionsListener();
            checkPermissions();
        } else {
            showWeb(areAllPermissionsGranted());
        }

    }

    private void showWeb(boolean granted) {
        if (!granted) {
            loadWebView(noPermissions);
            showPermissionDeniedSnackbar();
        } else {
            loadWebView(baseUrl);
        }
    }

    private boolean isFirstRun() {
        SharedPreferences sharedPreferences = getSharedPreferences(appName, MODE_PRIVATE);
        if (sharedPreferences.getBoolean(firstRunKey, true)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(firstRunKey, false);
            editor.commit();
            return true;
        }
        return false;
    }

    private void initPermissionArray() {
        permissions = getResources().getStringArray(R.array.permissions);
    }

    private boolean areAllPermissionsGranted() {
        for (String permission : permissions) {
            int permissionState = getApplicationContext().checkCallingOrSelfPermission(permission);
            if (permissionState != PackageManager.PERMISSION_GRANTED) {
                showPermissionDeniedSnackbar();
                return false;
            }
        }

        return true;
    }

    private void loadWebView(String url) {
        XWalkPreferences.setValue(XWalkPreferences.REMOTE_DEBUGGING, true);
        webView.load(url, null);
    }

    private void checkPermissions() {
        Dexter.checkPermissions(new CompositeMultiplePermissionsListener(multiplePermissionsListener,
                        dialogOnAnyDeniedMultiplePermissionsListener),
                permissions);
    }

    private void showPermissionDeniedSnackbar() {
        Snackbar.make(container, R.string.go_settings, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.action_settings, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = getApplicationContext();
                        Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.parse("package:" + context.getPackageName()));
                        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                        myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(myAppSettings);

                        finish();
                    }
                })
                .show();
    }

    private void setupPermissionsListener() {
        dialogOnAnyDeniedMultiplePermissionsListener =
                DialogOnAnyDeniedMultiplePermissionsListener.Builder
                        .withContext(this)
                        .withTitle(R.string.permission)
                        .withMessage(R.string.permission_description)
                        .withButtonText(android.R.string.ok)
                        .withIcon(R.mipmap.ic_launcher)
                        .build();

        multiplePermissionsListener = new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                showWeb(report.areAllPermissionsGranted());
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        };
    }
}

