package org.armstrong.ika.gerrys_motors_natal;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LoadFragment extends Fragment {

    private String content;

    WebView webView;

    public static LoadFragment newInstance(String content) {
        LoadFragment myFrag = new LoadFragment();
        Bundle args = new Bundle();
        args.putString("content", content);
        myFrag.setArguments(args);
        return myFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        content = getArguments().getString("content", "");

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_layout_fragment, parent, false);

        webView = view.findViewById(R.id.webview);

        //WebSettings webSettings = webView.getSettings();
        //webSettings.setJavaScriptEnabled(true);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //webSettings.setCacheMode(webSettings.LOAD_NO_CACHE);

        //webView.addJavascriptInterface(new WebAppInterface(getActivity()), "Android");

        //webView.clearCache(true);
        //webView.clearHistory();

        webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);

        //webView.addJavascriptInterface(new WebAppInterface(getActivity()), "Android");

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
