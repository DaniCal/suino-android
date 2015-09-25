package borell.com.suino.Http;

import com.squareup.okhttp.Response;

public interface HttpCallback {

    void onSuccess(Response response);
    void onError();


}
