import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.android.quakereport.Earthquake;
import com.example.android.quakereport.QueryUtils;

import java.util.List;

/**
 * Created by knits4 on 1/21/18.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    public EarthquakeLoader(Context context, String url) {
        super(context);

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground(String url ) {
        // Don't perform the request if there are no URLs, or the first URL is null.
        if (url.length < 1 || url[0] == null) {
            return null;
        }

        List<Earthquake> result = QueryUtils.fetchEarthquakeData(url[0]);
        return result;
    }
    }
