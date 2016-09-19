package com.catfacts.app;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.catfacts.app.data.CatFactResponse;
import com.catfacts.app.exception.CatFactException;
import com.catfacts.app.service.CatFactsService;
import com.catfacts.app.task.CatFactsTask;
import com.catfacts.app.task.listener.CatFactsListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get components
        final TextView textView = (TextView)findViewById(R.id.activityMainCatFactText);
        Button button = (Button)findViewById(R.id.activityMainGetCatFact);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout)findViewById(R.id.mainActivityCoordinatorLayout);

        textView.setText("No Cat Fact has been retrieved, get you cat facts now!\nBy: Alberto Rubalcaba");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get progress bar
                final ProgressBar bar = (ProgressBar)findViewById(R.id.mainActivityProgressBar);
                if(bar.isShown())return;
                bar.setVisibility(View.VISIBLE);

                CatFactsTask task = new CatFactsTask(new CatFactsListener() {
                    @Override
                    public void onCatFactReceived(final CatFactResponse response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(response.getFacts().get(0));
                                bar.setVisibility(View.GONE);
                            }
                        });
                    }

                    @Override
                    public void onError(final String error) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bar.setVisibility(View.GONE);
                                Snackbar.make(coordinatorLayout, "Could not get CatFact: " + error, Snackbar.LENGTH_LONG);
                            }
                        });
                    }
                });
                task.execute();

            }
        });
    }
}
