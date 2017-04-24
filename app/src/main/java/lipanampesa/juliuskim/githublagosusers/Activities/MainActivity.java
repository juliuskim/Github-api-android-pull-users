package lipanampesa.juliuskim.githublagosusers.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lipanampesa.juliuskim.githublagosusers.Adapters.Users_dataadapter;
import lipanampesa.juliuskim.githublagosusers.R;
import lipanampesa.juliuskim.githublagosusers.models.User;

public class MainActivity extends AppCompatActivity {
//TextView tv;
    private ArrayList<User> users;
    Users_dataadapter adapter;
    RecyclerView view1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // tv=(TextView)findViewById(R.id.tv);
        view1=(RecyclerView)findViewById(R.id.card_recycler_view);
        view1.setHasFixedSize(true);
        // LinearLayoutManager layoutManager = new LinearLayoutManager(this);


        users = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        view1.setItemAnimator(new DefaultItemAnimator());
        view1.setLayoutManager(layoutManager);

        adapter = new Users_dataadapter(this,users);
        view1.setAdapter(adapter);
        Fetchlagosusers();
    }
    private void Fetchlagosusers(){
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        //final String message = mess.getText().toString().trim();

        //final String password= logpass.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.github.com/search/users?q=repos%3A1+location%3Alagos",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        pDialog.hide();
                       // Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                        Log.e("users ",response);

                        // Toast.makeText(getApplicationContext(),getTimeStamp(Date),Toast.LENGTH_LONG).show();
                        try {
                            JSONObject jSONObject = new JSONObject(response);
// Getting the JSON array node
                            JSONArray valarray = jSONObject.getJSONArray("items");

                                //JSONArray valarray = new JSONArray(data);
                                for (int i = 0; i < valarray.length(); i++) {

                                     String username = valarray.getJSONObject(i).getString("login");
                                    String id = valarray.getJSONObject(i).getString("id");
                                    String photo=valarray.getJSONObject(i).getString("avatar_url");
                                    String profileurl=valarray.getJSONObject(i).getString("html_url");
                                    User user = new User(id,username,photo,profileurl);
                                    user.setId(id);
                                    user.setName(username);
                                    user.setImage(photo);
                                    user.seturl(profileurl);
                                   Log.e("LOGIN",username);
                                   // tv.append(username+"\n");
                                    users.add(user);
                        }
                        adapter.notifyDataSetChanged();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.hide();
                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();


                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
