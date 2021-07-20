package com.bytedance.practice5;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bytedance.practice5.model.UploadResponse;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.Utf8;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public class UploadActivity extends AppCompatActivity {
    private static final String TAG = "chapter5";
    private static final long MAX_FILE_SIZE = 2 * 1024 * 1024;
    private static final int REQUEST_CODE_COVER_IMAGE = 101;
    private static final String COVER_IMAGE_TYPE = "image/*";
    private IApi api;
    private Uri coverImageUri;
    private SimpleDraweeView coverSD;
    private EditText toEditText;
    private EditText contentEditText ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNetwork();
        setContentView(R.layout.activity_upload);
        coverSD = findViewById(R.id.sd_cover);
        toEditText = findViewById(R.id.et_to);
        contentEditText = findViewById(R.id.et_content);
        findViewById(R.id.btn_cover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFile(REQUEST_CODE_COVER_IMAGE, COVER_IMAGE_TYPE, "选择图片");
            }
        });


        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
//                submitMessageWithURLConnection();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE_COVER_IMAGE == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                coverImageUri = data.getData();
                coverSD.setImageURI(coverImageUri);

                if (coverImageUri != null) {
                    Log.d(TAG, "pick cover image " + coverImageUri.toString());
                } else {
                    Log.d(TAG, "uri2File fail " + data.getData());
                }

            } else {
                Log.d(TAG, "file pick fail");
            }
        }
    }

    private void initNetwork() {
        //TODO 3
        // 创建Retrofit实例
        // 生成api对象
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(IApi.class);

    }

    private void getFile(int requestCode, String type, String title) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(type);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
        intent.putExtra(Intent.EXTRA_TITLE, title);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, requestCode);
    }

    private void submit() {

        Log.d(TAG, "submit: ________________________");
        byte[] coverImageData = readDataFromUri(coverImageUri);
        if (coverImageData == null || coverImageData.length == 0) {
            Toast.makeText(this, "封面不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        String to = toEditText.getText().toString();
        if (TextUtils.isEmpty(to)) {
            Toast.makeText(this, "请输入TA的名字", Toast.LENGTH_SHORT).show();
            return;
        }
        String content = contentEditText.getText().toString();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "请输入想要对TA说的话", Toast.LENGTH_SHORT).show();
            return;
        }

        if ( coverImageData.length >= MAX_FILE_SIZE) {
            Toast.makeText(this, "文件过大", Toast.LENGTH_SHORT).show();
            return;
        }
        //TODO 5
        // 使用api.submitMessage()方法提交留言
        // 如果提交成功则关闭activity，否则弹出toast
//        Log.d(TAG, "submit: ____________________111");
        MultipartBody.Part username = MultipartBody.Part.createFormData("from", Constants.USER_NAME);
        MultipartBody.Part toname1 = MultipartBody.Part.createFormData("to", to);
        MultipartBody.Part contentedit = MultipartBody.Part.createFormData("content", content);
        MultipartBody.Part coverPart = MultipartBody.Part.createFormData("image", "cover.png", RequestBody.create(MediaType.parse("multipart/form-data"), coverImageData));
        
//        Log.d(TAG, "submit: test))))))))");
        Call<UploadResponse> call = api.submitMessage(Constants.STUDENT_ID, "", username, toname1, contentedit,coverPart, Constants.token);

        Log.d(TAG, "submit:-======= "+call.toString());

        call.enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                finish();
            }
            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "提交失败", Toast.LENGTH_SHORT).show();
            }
        });

    }


    // TODO 7 选做 用URLConnection的方式实现提交
    private void submitMessageWithURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, String> fieldMap = new HashMap<String, String>();
                Map<String, String> filesMap = new HashMap<String, String>();
                fieldMap.put("student_id", Constants.STUDENT_ID);
                fieldMap.put("from", Constants.USER_NAME);
                fieldMap.put("to", toEditText.getText().toString());
                fieldMap.put("content", contentEditText.getText().toString());
                fieldMap.put("extra_value", "");
                fieldMap.put("token", Constants.token);

                String filepath=coverImageUri.getPath();

                Log.d(TAG, "submitMessageWithURLConnection:++++++++ "+filepath);
                Log.d(TAG, "submitMessageWithURLConnection: ++++++++"+coverImageUri);

                if(filepath!=null) filesMap.put("image", filepath);

                final String NEWLINE = "\r\n";
                final String PREFIX = "--";
                final String BOUNDARY = "#";
                DataOutputStream out = null;
                try {
                    URL url=new URL("https://api-android-camp.bytedance.com/zju/invoke/messages/");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(30000);
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("token", Constants.token);
                    conn.setRequestProperty("Content-Type",
                            "multipart/form-data; boundary=" + BOUNDARY);
//                    Log.d(TAG, "submitMessageWithURLConnection: _+_+_+"+conn.toString());
                    conn.connect();
                    out = new DataOutputStream(conn.getOutputStream());
//                    Log.d(TAG, "submitMessageWithURLConnection: +_+_+_+"+out);
                    Iterator<Map.Entry<String, String>> iter = fieldMap.entrySet().iterator();
                    for(Map.Entry<String, String> entry : fieldMap.entrySet()){
                        String key = entry.getKey();
                        String value = entry.getValue();
                        out.writeBytes(PREFIX+BOUNDARY+NEWLINE);
                        out.writeBytes("Content-Disposition: form-data; "+"name=\"" + key + "\"" + NEWLINE);
                        out.writeBytes(NEWLINE);
                        out.writeBytes(value);
                        out.writeBytes(NEWLINE);
                    }

                    byte[] body_data = readDataFromUri(coverImageUri);

                    if(body_data!=null&&body_data.length>0){
                        out.writeBytes(PREFIX+BOUNDARY+NEWLINE);
                        String filename = filepath.substring(filepath.lastIndexOf(File.separatorChar));
                        out.writeBytes("Content-Disposition: form-data; " + "name=\""
                                + "uploadFile" + "\"" + "; filename=\"" + "image.png"
                                + "\"" + NEWLINE);
                        out.writeBytes(NEWLINE);
                        out.writeBytes(body_data.toString());
                        out.writeBytes(NEWLINE);

                    }
                    out.writeBytes(PREFIX+BOUNDARY+PREFIX+NEWLINE);
                    out.flush();
                    Log.d(TAG, "run: "+conn.getResponseCode());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {

                }
            }
        }).start();


    }


    private byte[] readDataFromUri(Uri uri) {
        byte[] data = null;
        try {
            InputStream is = getContentResolver().openInputStream(uri);
            data = Util.inputStream2bytes(is);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


}
