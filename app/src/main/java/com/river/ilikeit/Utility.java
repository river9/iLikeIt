package com.river.ilikeit;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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

//    public static String requestPin() throws URISyntaxException, IOException {
//        URI uri = new URI(URL_BOARD);
//
//        HttpClient client = new DefaultHttpClient();
//        HttpGet request = new HttpGet();
//        request.setURI(uri);
//        HttpResponse response = client.execute(request);
//        InputStream in = response.getEntity().getContent();
//        return convertStreamToString(in);
//    }
//
//    public static String convertStreamToString(InputStream inputStream) throws IOException {
//        if (inputStream != null) {
//            Writer writer = new StringWriter();
//
//            char[] buffer = new char[1024];
//            try {
//                Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"),1024);
//                int n;
//                while ((n = reader.read(buffer)) != -1) {
//                    writer.write(buffer, 0, n);
//                }
//            } finally {
//                inputStream.close();
//            }
//            return writer.toString();
//        } else {
//            return "";
//        }
//    }
//
//    public static List<PhotoInfo> getPhotos() throws IOException, URISyntaxException, JSONException {
//        JSONObject data = new JSONObject(requestPin());
//        JSONArray jArray = data.getJSONArray("data");
//        List<PhotoInfo> list = new ArrayList<>();
//        for (int i = 0; i < jArray.length(); i++) {
//            JSONObject jObj = jArray.getJSONObject(i);
//            String id = jObj.getString("id");
//            String content = jObj.getString("note");
//            String link = jObj.getString("url");
//            PhotoInfo item = new PhotoInfo(id, content, link, "RIVER TEST", 123, 11, true, false);
//            list.add(item);
//        }
//        return list;
//    }

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
