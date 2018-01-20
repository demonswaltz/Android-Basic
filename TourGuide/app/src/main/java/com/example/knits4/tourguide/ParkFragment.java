package com.example.knits4.tourguide;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParkFragment extends Fragment {

    public ParkFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container, false);

        // Create a list of words
        final ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(new Location(getString(R.string.rocky_mountain), R.mipmap.ic_park_foreground));
        locations.add(new Location(getString(R.string.encinitas), getString(R.string.encinatas_address), R.mipmap.ic_park_foreground));
        locations.add(new Location(getString(R.string.carlsbad), R.mipmap.ic_carlsbad_foreground));
        locations.add(new Location(getString(R.string.sycamore_valley), getString(R.string.sycamore_address), R.mipmap.ic_park_foreground));
        locations.add(new Location(getString(R.string.joaquin_miller), getString(R.string.jmiller_address), R.mipmap.ic_jmiller_foreground));
        locations.add(new Location(getString(R.string.golden_gate_park), R.mipmap.ic_gg_park_foreground));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        LocationAdapter adapter = new LocationAdapter(getActivity(), locations, R.color.park_category);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Location location = locations.get(position);
                String address = location.getmAddress();
                if (address == null) {
                    address = location.getmLocationName();
                }
                Context context = getActivity().getApplicationContext();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" +address);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                context.startActivity(mapIntent);
            }
        });
           return rootView;
    }
}
