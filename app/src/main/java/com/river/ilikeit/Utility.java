package com.river.ilikeit;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ImageView;

import com.river.ilikeit.main.photo.PhotoInfo;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Utility {

    private Context context;

    private static Utility instance;

    public static synchronized Utility getInstance(Context context) {
        if (instance == null) {
            instance = new Utility(context);
        }
        return instance;
    }

    public Utility(Context context) {
        this.context = context;
    }

    public void displayImage(ImageView imageView, String url) {
        Picasso.with(context)
                .load(url)
                .placeholder(R.color.primary_dark_material_light)
                .error(R.drawable.pic1)
                .into(imageView);
    }

    public boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String className = serviceClass.getName();
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (className.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    static String ACCESS_TOKEN = "AecaPzoHfORIHsPcbvrvnAeio2akFASl_yFyUhZCfLKhoAAEegAAAAA";
    static String URL_BOARD = "https://api.pinterest.com/v1/boards/280560320475343984/pins/?access_token=" + ACCESS_TOKEN;
    static String URL_TRAVEL = "https://api.pinterest.com/v3/pidgets/boards/highquality/travel/pins/";

    public static String requestPin() throws URISyntaxException, IOException {
        URI uri = new URI(URL_BOARD);

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        request.setURI(uri);
        HttpResponse response = client.execute(request);
        InputStream in = response.getEntity().getContent();
        return convertStreamToString(in);
    }

    public static String convertStreamToString(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"),1024);
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                inputStream.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }

    public static List<PhotoInfo> getPhotos() throws IOException, URISyntaxException, JSONException {
        JSONObject data = new JSONObject(requestPin());
        JSONArray jArray = data.getJSONArray("data");
        List<PhotoInfo> list = new ArrayList<>();
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject jObj = jArray.getJSONObject(i);
            String id = jObj.getString("id");
            String content = jObj.getString("note");
            String link = jObj.getString("url");
//            String creator = jObj.getString("creator");
//            String image = jObj.getString("image");
            PhotoInfo item = new PhotoInfo(id, content, link, "RIVER TEST", 123, 11, true, false);
            list.add(item);
        }
        return list;
    }

    public static ProgressDialog initLoadingDialog(Context context) {
        ProgressDialog loadingDialog = new ProgressDialog(context);
        loadingDialog.setTitle("Loading");
        loadingDialog.setMessage("Please wait ...");
        loadingDialog.setCanceledOnTouchOutside(false);
        return loadingDialog;
    }

    public static AlertDialog initErrorDialog(Context context, String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error");
        if (error != null) {
            builder.setMessage(error);
        }
        return builder.create();
    }
}
