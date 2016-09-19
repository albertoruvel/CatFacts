package com.catfacts.app.task;

import android.os.AsyncTask;

import com.catfacts.app.data.CatFactResponse;
import com.catfacts.app.service.CatFactsService;
import com.catfacts.app.task.listener.CatFactsListener;

/**
 * Created by jose.rubalcaba on 19/09/2016.
 */

public class CatFactsTask extends AsyncTask<Void, Void, Void> {
    private CatFactsListener listener;

    public CatFactsTask(CatFactsListener listener){
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... params) {
        //get cat fact
        try{
            CatFactResponse response = CatFactsService.getCatFact();
            listener.onCatFactReceived(response);
        }catch(Exception ex){
            listener.onError(ex.getMessage());
        }
        return null;
    }
}
