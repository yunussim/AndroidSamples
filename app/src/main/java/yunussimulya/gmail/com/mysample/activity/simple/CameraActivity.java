package yunussimulya.gmail.com.mysample.activity.simple;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;
import java.util.List;

import yunussimulya.gmail.com.mysample.R;


public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private static final String ANALYTIC_CATEGORY = "Camera";

    public int ORIENTATION = 90;

    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private boolean previewing = false;

    private int screenHeight = 0;
    private int screenWidth = 0;

    private int cameraFacing = Camera.CameraInfo.CAMERA_FACING_BACK;
    private String path = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(0, 0, 0));
        }

        setContentView(R.layout.activity_camera);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFormat(PixelFormat.UNKNOWN);

        surfaceView = findViewById(R.id.camerapreview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void rotate() {
        try {
            if (Camera.getNumberOfCameras() >= 2) {
                camera.stopPreview();
                camera.release();
                switch (cameraFacing) {
                    case Camera.CameraInfo.CAMERA_FACING_BACK:
                        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
                        cameraFacing = Camera.CameraInfo.CAMERA_FACING_FRONT;
                        break;
                    case Camera.CameraInfo.CAMERA_FACING_FRONT:
                        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
                        cameraFacing = Camera.CameraInfo.CAMERA_FACING_BACK;
                        break;
                    default :
                        break;
                }
                try {
                    camera.setPreviewDisplay(surfaceHolder);
                    camera.setDisplayOrientation(90);
                    Camera.Parameters parameters = camera.getParameters();
                    List<Size> previews = parameters.getSupportedPreviewSizes();
                    Size optimalPreview = getOptimalSize(previews, 0);
                    if (optimalPreview != null) {
                        parameters.setPreviewSize(optimalPreview.width, optimalPreview.height);
                    }
                    List<Size> pictures = parameters.getSupportedPictureSizes();
                    Size optimalPicture = getOptimalSize(pictures, 2000);
                    if (optimalPicture != null) {
                        parameters.setPictureSize(optimalPicture.width, optimalPicture.height);
                    }
                    try {
                        camera.setParameters(parameters);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    camera.startPreview();
                } catch (IOException exception) {
                    camera.release();
                    camera = null;
                }
            }
        } catch (Exception e) {
            initCamera();
        }
    }

    private Size getOptimalSize(List<Size> lists, int maxWidth) {
        Size result = null;
        int w;
        int h;
        int i = 0;

        float aspectRatio = (float) screenHeight / (float) screenWidth;
        float bestAspectRatioDiff = 100;
        float bestHeight = 0;

        while (i < lists.size()) {
            Size s = lists.get(i);
            w = s.width;
            h = s.height;
            float currentAspectRatio = (float) w / (float) h;
            float diff = Math.abs(currentAspectRatio - aspectRatio);

            if (diff <= bestAspectRatioDiff && bestHeight <= h) {
                if (maxWidth > 0 && w < maxWidth) {
                    bestHeight = h;
                    bestAspectRatioDiff = diff;
                    result = s;
                } else {
                    bestHeight = h;
                    bestAspectRatioDiff = diff;
                    result = s;
                }
            }
            i++;
        }
        return result;
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        initCamera();
    }

    private void initCamera() {
        if (previewing) {
            camera.stopPreview();
            previewing = false;
        }
        if (camera != null) {
            try {
                camera.stopPreview();
                camera.release();
                switch (cameraFacing) {
                    case Camera.CameraInfo.CAMERA_FACING_BACK:
                        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
                        break;
                    case Camera.CameraInfo.CAMERA_FACING_FRONT:
                        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
                        break;
                    default:
                        break;
                }
                Camera.Parameters parameters = camera.getParameters();
                camera.setPreviewDisplay(surfaceHolder);
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);

                List<Size> previews = parameters.getSupportedPreviewSizes();
                Size optimalPreview = getOptimalSize(previews, 0);
                if (optimalPreview != null) {
                    parameters.setPreviewSize(optimalPreview.width, optimalPreview.height);
                }


                List<Size> pictures = parameters.getSupportedPictureSizes();
                Size optimalPicture = getOptimalSize(pictures, 2000);
                if (optimalPicture != null) {
                    parameters.setPictureSize(optimalPicture.width, optimalPicture.height);
                }

                List<String> supportedFocusModes = parameters.getSupportedFocusModes();
                if (supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
                    parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                }
                if (supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                    parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                }

                camera.setDisplayOrientation(ORIENTATION);
                try {
                    camera.setParameters(parameters);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                camera.startPreview();
                previewing = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            camera = Camera.open();
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameters);
            Log.e("preview", parameters.getPreviewSize().height + " : " + parameters.getPreviewSize().width);
            Log.e("picture", parameters.getPictureSize().height + " : " + parameters.getPictureSize().width);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        initCamera();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
            previewing = false;
        }
    }

}