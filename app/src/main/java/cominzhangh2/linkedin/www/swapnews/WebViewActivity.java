package cominzhangh2.linkedin.www.swapnews;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import cominzhangh2.linkedin.www.swapnews.common.BasicActivity;

public class WebViewActivity extends BasicActivity implements PopupMenu.OnMenuItemClickListener {
    public static final String URL = "url";
    private String url = null ;
    private ProgressBar progressBar;

    // need override and declare the showSnackBar in the FragmentManager.
    @Override
    public void showSnackBar(String message) {

    }

    @Override
    protected int getLayout() { // the return is int, in R like a indentifyer for the acitivity_web_view.
        return R.layout.activity_web_view;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_web_view); // link to the xml layout, use  protected int getLayout() to clear logic.
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        progressBar = findViewById(R.id.progress_bar);
        final WebView webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE); // GONE invisible, but still there.
            }
        });
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            Bundle innerBundle = bundle.getBundle(BUNDLE);
//            if (innerBundle != null) {
//                url = innerBundle.getString(URL);
//                webView.loadUrl(url);
//            }
//        }
        url = "http://www.google.com/";
        webView.loadUrl(url);

        // why use inner class? https://www.quora.com/Why-do-we-need-inner-classes-in-Java
        // Inner classes are used for logical grouping of classes. In cases where your class B is used only by class A it’s better to put Class B as an inner class to Class A. This improves encapsulation and readability of the code.
        findViewById(R.id.more).setOnClickListener(new View.OnClickListener() { //Anonymous Inner Class, declare and instantiate them at the same time. Generally, they are used whenever you need to override the method of a class or an interface. // https://www.tutorialspoint.com/java/java_innerclasses.htm
            @Override
            public void onClick(View v) {
            showMenu(v);
            }
        }); // a statement, don't forget adding the ;
    }

    @SuppressLint("RestrictedApi")
    private void showMenu(View view) {
        PopupMenu menu = new PopupMenu(this, view); // all the this is the class instance, the webviewactivity.
        menu.setOnMenuItemClickListener(this); // give the interface to the menu. it's a callback. when every you click it it triggers.
        MenuInflater inflater = menu.getMenuInflater();
        inflater.inflate(R.menu.web_view_items, menu.getMenu());
        MenuPopupHelper menuHelper = new MenuPopupHelper(this, (MenuBuilder) menu.getMenu(), view); // this is inherited from context
        menuHelper.setForceShowIcon(true);
        menuHelper.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "From Swapnews: \n" + url;
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                this.startActivity(Intent.createChooser(sharingIntent, "Share Swapnews"));
                break;
            case R.id.menu_copy:
                ClipboardManager clipboard = (ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text", url);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, "Link Copied", Toast.LENGTH_SHORT).show();
            default:
                break;
        }
        return true;
    }
//
////    life cycle, after the 1.onCreate
//    @Override
//    public void onStart() {
//        super.onStart();  // 2.onStart
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume(); // 3.everything rendered after onResume.
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause(); // 4.when ever click on the share, the webview
//    }
//
//    @Override
//    public void onStop() { 5. click the upper left cross btn, onBackPressed();
//        super.onStop();
//    }
//
//    @Override
//    public void onDestroy() { 6. after stop. the webviewactivity is poped out. if the mian is not destroed (set finished under intenet.), mainactivity will on resume.
//        super.onDestroy();
//    }

}


