package cabiso.daphny.com.pokemon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String JsonURLPokemon = "http://pokeapi.co/api/v2/pokemon/";


    private GridView mGridViewPokemons;
    private Adapter pokemonListAdapter;
    private List<Pokemon> pokeList;


    Button moves, items, mpokemon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridViewPokemons = (GridView) findViewById(R.id.gridView_pokemons);
        pokeList = new ArrayList<Pokemon>();
        pokemonListAdapter = new Adapter(MainActivity.this, pokeList);
        mGridViewPokemons.setAdapter(pokemonListAdapter);

        sendRequest();


    }
    private void sendRequest() {
        Controller.getInstance(this).add(obreq);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_items:
                Intent intent = new Intent(this, ItemActivity.class);
                this.startActivity(intent);
                break;
            case R.id.menu_move:
                // another startActivity, this is for item with id "menu_item2"
                intent = new Intent(this, MoveActivity.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }



    JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURLPokemon, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray obj = response.getJSONArray("results");

                        for (int init = 0; init < obj.length(); init++) {
                            JSONObject tempObj = obj.getJSONObject(init);
                            Pokemon poke = new Pokemon();
                            poke.setPokeName(tempObj.getString("name"));
                            poke.setImgURL(tempObj.getString("url"));
                            pokeList.add(poke);
                        }

                        pokemonListAdapter.notifyDataSetChanged();
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

