package borell.com.suino.Http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;


public class HttpManager {
    private static OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    public HttpManager(){

    }


    public void getRequest(String url, HttpCallback callback){
        
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        RequestTask requestTask = new RequestTask(callback);
        requestTask.execute(request);
    }


    public void postRequest(String url, String json, HttpCallback callback){
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            RequestTask requestTask = new RequestTask(callback);
            requestTask.execute(request);
    }

    public void loadImageRequest(String url, HttpCallback callback){
        DownloadImageTask task = new DownloadImageTask(callback);
        task.execute(url);
    }

    private class RequestTask extends AsyncTask<Request, Integer, Boolean> {
        Response response;
        HttpCallback callback;

        public RequestTask(HttpCallback callback){
            this.callback = callback;
        }

        protected Boolean doInBackground(Request... params) {
            try {
                response = client.newCall(params[0]).execute();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if(result && response != null){
                callback.onSuccess(response);
            }else{
                callback.onError();
            }
        }

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {


        HttpCallback callback;

        public DownloadImageTask(HttpCallback callback){
            this.callback = callback;

        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            if(result != null){
                callback.onSuccessLoadingImage(result);
            }else{
                callback.onError();
            }
        }
    }
}
