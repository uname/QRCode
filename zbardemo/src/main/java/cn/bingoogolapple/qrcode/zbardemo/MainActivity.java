package cn.bingoogolapple.qrcode.zbardemo;

import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;

public class MainActivity extends ActionBarActivity implements QRCodeView.ResultHandler {
    private ZBarView mZBarView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mZBarView = (ZBarView) findViewById(R.id.zbarview);
        mZBarView.setResultHandler(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mZBarView.startCamera();
    }

    @Override
    protected void onStop() {
        mZBarView.stopCamera();
        super.onStop();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void handleResult(String result) {
        Log.i("bingo", "result:" + result);
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        vibrate();
        mZBarView.startSpot();
    }

    @Override
    public void handleCameraError() {
        Log.e("bingo", "打开相机出错");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_spot:
                mZBarView.startSpot();
                break;
            case R.id.stop_spot:
                mZBarView.stopSpot();
                break;
            case R.id.start_spot_showrect:
                mZBarView.startSpotAndShowRect();
                break;
            case R.id.stop_spot_hiddenrect:
                mZBarView.stopSpotAndHiddenRect();
                break;
            case R.id.show_rect:
                mZBarView.showScanRect();
                break;
            case R.id.hidden_rect:
                mZBarView.hiddenScanRect();
                break;
            case R.id.start_preview:
                mZBarView.startCamera();
                break;
            case R.id.stop_preview:
                mZBarView.stopCamera();
                break;
        }
    }
}