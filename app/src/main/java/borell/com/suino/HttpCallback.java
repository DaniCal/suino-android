package borell.com.suino;

import com.squareup.okhttp.Response;

/**
 * Created by daniellohse on 9/25/15.
 */
public interface HttpCallback {

    public void onSuccess(Response response);
    public void onError();


}
