package org.armstrong.ika.gerrys_motors_natal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadFragment extends Fragment {

    private int page;
    private String content;
    private String image;

    // newInstance constructor for creating fragment with arguments
    public static LoadFragment newInstance(int page, String content, String image) {
        LoadFragment myFrag = new LoadFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("content", content);
        args.putString("image", image);
        myFrag.setArguments(args);
        return myFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        page = getArguments().getInt("page", 0);
        content = getArguments().getString("content", "");
        image = getArguments().getString("image", "");

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_load_fragment, parent, false);

        ImageView iv = (ImageView) v.findViewById(R.id.imageView);
        // iv.setImageResource(R.drawable.rentals);
        iv.setImageResource(getResources().getIdentifier(image, "drawable", LoadActivity.getContext().getPackageName()));

        TextView tv = (TextView) v.findViewById(R.id.contentTextView);
        tv.setText(content);


        return v;
    }

}
