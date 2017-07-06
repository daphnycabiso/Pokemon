package cabiso.daphny.com.pokemon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Lenovo on 7/6/2017.
 */

public class ItemActivity extends AppCompatActivity{

    private ListView listItem;
    String JsonURLItem = "http://pokeapi.co/api/v2/item/";
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_item);

        listItem = (ListView) findViewById(R.id.gridView_items);
        ArrayList<String> moveList = new ArrayList<>();
        listAdapter = new ArrayAdapter<String>(this, R.layout.actvity_item, moveList);

        sendRequest();

    }

    private void sendRequest() {
        Controller.getInstance(this).add(obreq);
        Log.d("Inside sendRequest", "true");
    }

    JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURLItem, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("Inside on response", "true");
                        JSONArray obj = response.getJSONArray("results");

                        for (int init = 0; init < obj.length(); init++) {
                            JSONObject tempObj = obj.getJSONObject(init);
                            listAdapter.add(tempObj.getString("name"));
                            Toast.makeText(ItemActivity.this, tempObj.getString("name"), Toast.LENGTH_SHORT).show();
                        }

                        listItem.setAdapter(listAdapter);
                        listAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }
    );

}