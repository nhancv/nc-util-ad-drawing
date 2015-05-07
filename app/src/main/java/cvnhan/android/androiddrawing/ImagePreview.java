package cvnhan.android.androiddrawing;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cvnhan on 07-May-15.
 */
public class ImagePreview extends Activity {

    @InjectView(R.id.imgView)
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imgpreview);
        ButterKnife.inject(this);
        try {
            imgView.setImageBitmap(Base64Util.decodeToBitmap(DrawingView.BASE64BITMAP));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
