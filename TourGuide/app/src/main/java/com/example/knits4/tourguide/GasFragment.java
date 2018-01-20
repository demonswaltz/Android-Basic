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
public class GasFragment extends Fragment {

    public GasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container, false);

        // Create a list of words
        final ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(new Location(getString(R.string.walgreens), getString(R.string.belvidere_walgreens), R.mipmap.ic_shopping_foreground));
        locations.add(new Location(getString(R.string.bp), getString(R.string.bp_iowa), R.mipmap.ic_gas_foreground));
        locations.add(new Location(getString(R.string.walmart), getString(R.string.walmart_lincoln_ne), R.mipmap.ic_shopping_foreground));
        locations.add(new Location(getString(R.string.d_s), getString(R.string.d_s_address), R.mipmap.ic_gas_foreground));
        locations.add(new Location(getString(R.string.target), getString(R.string.target_riverside_ca), R.mipmap.ic_shopping_foreground));
        locations.add(new Location(getString(R.string.walgreens), getString(R.string.walgreens_oakland), R.mipmap.ic_shopping_foreground));
        locations.add(new Location(getString(R.string.petsmart), getString(R.string.petsmart_dublin_ca), R.mipmap.ic_shopping_foreground));
        locations.add(new Location(getString(R.string.concord), getString(R.string.concord_toyota), R.mipmap.ic_shopping_foreground));
        locations.add(new Location(getString(R.string.walmart), getString(R.string.walmart_sterling_co), R.mipmap.ic_shopping_foreground));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        LocationAdapter adapter = new LocationAdapter(getActivity(), locations, R.color.gas_category);

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
