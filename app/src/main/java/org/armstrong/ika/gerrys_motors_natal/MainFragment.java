package org.armstrong.ika.gerrys_motors_natal;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.activity_items, SectionTitles.TITLES);
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        int ID = position;

        String CASE = "" + ID;
        Intent a = new Intent(getActivity(), LoadActivity.class);
        a.putExtra("CASE", CASE);
        startActivity(a);

    }

}
