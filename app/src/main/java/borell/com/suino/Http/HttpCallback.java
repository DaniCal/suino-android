package borell.com.suino.Http;

import android.graphics.Bitmap;

import com.squareup.okhttp.Response;

public interface HttpCallback {

    void onSuccess(Response response);
    void onError();
    void onSuccessLoadingImage(Bitmap result);


}
