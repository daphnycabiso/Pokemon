package cabiso.daphny.com.pokemon;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Lenovo on 7/6/2017.
 */

public class Controller {

    private static RequestQueue requestQueue;

    private Controller() {

    }

    public static RequestQueue getInstance(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }
}
