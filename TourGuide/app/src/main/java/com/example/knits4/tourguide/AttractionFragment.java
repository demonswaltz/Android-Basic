package com.example.knits4.tourguide;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttractionFragment extends Fragment implements LoaderManager.LoaderCallbacks {

    public AttractionFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.e("AttractionFragment", "was created");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.e("AttractionFragment", "was destroyed");
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.e("Attraction", "view was destroyed");
    }
    @Override
    public void onLoadFinished(){
        super.

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("AttractoinFragment", "view was created");
        View rootView = inflater.inflate(R.layout.location_list, container, false);

        // Create a list of words
        final ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(new Location(getString(R.string.worlds_largest), getString(R.string.worlds_largest_address), R.mipmap.ic_worlds_largest_foreground));
        locations.add(new Location(getString(R.string.lily_mountain), getString(R.string.lily_mountain_address), R.mipmap.ic_lily_mountain_foreground));
        locations.add(new Location(getString(R.string.socal_surf), R.mipmap.ic_socal_foreground));
        locations.add(new Location(getString(R.string.sea_otter), R.mipmap.ic_hwy1_foreground));
        locations.add(new Location(getString(R.string.golden_gate_brdige), getString(R.string.golden_gate_bridge_address), R.mipmap.ic_golden_gate_foreground));
        locations.add(new Location(getString(R.string.bay_area_maker), getString(R.string.bay_area_address), R.mipmap.ic_maker_faire_foreground));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        LocationAdapter adapter = new LocationAdapter(getActivity(), locations, R.color.attractions_category);

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
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + address);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                context.startActivity(mapIntent);


            }
        });

        return rootView;
    }
}
