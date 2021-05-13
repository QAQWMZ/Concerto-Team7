package com.example.concerto.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.concerto.adapter.SubProjectAdapter;
import com.example.concerto.bean.SubProject;
import com.example.concerto.popwindow.PopWindowTest;
import com.example.concerto.R;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyprojectTest extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private List<SubProject> list = new ArrayList<>();
    SubProjectAdapter subProjectAdapter;
    private RecyclerView recyclerView;
    private TextView titleTextView1;
    private TextView titleTextView2;
    private TextView titleTextView3;
    private TextView titleTextView4;
    private TextView descriptonTextView1;
    private TextView descriptonTextView2;
    private TextView descriptonTextView3;
    private TextView descriptonTextView4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myproject_test);
        imageView = findViewById(R.id.addImageView);
        imageView.setOnClickListener(this);
        titleTextView1 = findViewById(R.id.titileTextView1);
        titleTextView2 = findViewById(R.id.titileTextView2);
        titleTextView3 = findViewById(R.id.titileTextView3);
        titleTextView4 = findViewById(R.id.titileTextView4);

        descriptonTextView1 = findViewById(R.id.descriptionTextView1);
        descriptonTextView2 = findViewById(R.id.descriptionTextView2);
        descriptonTextView3 = findViewById(R.id.descriptionTextView3);
        descriptonTextView4 = findViewById(R.id.descriptionTextView4);

        initList();
        recyclerView = (RecyclerView)findViewById(R.id.ProjectRecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void initList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4In0.9i1K1-3jsGh3tbTh2eMmD64C3XOE-vX9c1JywsqSoT0";
                    OkHttpClient client=new OkHttpClient();
                    Request.Builder reqBuild = new Request.Builder();
                    reqBuild.addHeader("token",token);
                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://81.69.253.27:7777//Project")
                            .newBuilder();
                    reqBuild.url(urlBuilder.build());

                    Request request = reqBuild.build();

                    Response response = client.newCall(request).execute();
                    String data=response.body().string();
                    if(data != null && data.startsWith("\ufeff"))
                    {
                        data =  data.substring(1);
                    }

                    JSONObject jsonObject=new JSONObject(data);
                    JSONArray jsonArray=(JSONArray)jsonObject.getJSONArray("data");

                    for (int j = 0; j < jsonArray.length(); j++)
                    {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(j);
                        String projectName = jsonObject2.getString("projectName");
                        String projectDescription = jsonObject2.getString("projectDescription");
                        String projectStartTime = jsonObject2.getString("projectStartTime");
                        
                        JSONObject adminJsonObject = jsonObject2.getJSONObject("admin");
                        String laucher = null;
                        laucher = adminJsonObject.getString("userName");
                        SubProject subProject = new SubProject(projectName,projectDescription,"发起人："+laucher,projectStartTime);
                        list.add(subProject);

                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            subProjectAdapter = new SubProjectAdapter(list);
                            recyclerView.setAdapter(subProjectAdapter);
                            if (list.size()>0)
                            {
                                for (int i=0;i<list.size();i++)
                                {
                                    if (i==0)
                                    {
                                        SubProject subProject = list.get(i);
                                        String name = subProject.getName();
                                        String description = subProject.getDescription();
                                        titleTextView1.setText(name);
                                        descriptonTextView1.setText(description);
                                    }
                                    else if (i==1)
                                    {
                                        SubProject subProject = list.get(i);
                                        String name = subProject.getName();
                                        String description = subProject.getDescription();
                                        titleTextView2.setText(name);
                                        descriptonTextView2.setText(description);
                                    }
                                    else if (i==2)
                                    {
                                        SubProject subProject = list.get(i);
                                        String name = subProject.getName();
                                        String description = subProject.getDescription();
                                        titleTextView3.setText(name);
                                        descriptonTextView3.setText(description);
                                    }
                                    else if (i==3)
                                    {
                                        SubProject subProject = list.get(i);
                                        String name = subProject.getName();
                                        String description = subProject.getDescription();
                                        titleTextView4.setText(name);
                                        descriptonTextView4.setText(description);
                                    }
                                }
                            }
                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();

                }
            }
        }).start();
    }



    @Override
    public void onClick(View view) {
        PopWindowTest popWindow = new PopWindowTest(this);
        popWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = getWindow().getAttributes();
            lp1.alpha = 1f;
           getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            getWindow().setAttributes(lp1);
        });
        popWindow.showPopupWindow(view.findViewById(R.id.addImageView));
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f; //设置透明度
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    public void testUrl() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4In0.9i1K1-3jsGh3tbTh2eMmD64C3XOE-vX9c1JywsqSoT0";
                    OkHttpClient client=new OkHttpClient();
                    Request.Builder reqBuild = new Request.Builder();
                    reqBuild.addHeader("token",token);
                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://81.69.253.27:7777//Project")
                            .newBuilder();
                    reqBuild.url(urlBuilder.build());
                    Request request = reqBuild.build();
                    Response response = client.newCall(request).execute();
                    String data=response.body().string();
                    if(data != null && data.startsWith("\ufeff"))
                    {
                        data =  data.substring(1);
                    }

                    JSONObject jsonObject=new JSONObject(data);
                    JSONArray jsonArray=(JSONArray)jsonObject.getJSONArray("data");
                    Log.v("MyprojectTest","--------------"+jsonArray.toString());
                    for (int j = 0; j < jsonArray.length(); j++)
                    {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(j);
                        String name = jsonObject2.getString("projectName");
                        Log.v("MyprojectTest","--------------"+name);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }


}