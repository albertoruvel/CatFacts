package com.catfacts.app.task.listener;

import com.catfacts.app.data.CatFactResponse;

/**
 * Created by jose.rubalcaba on 19/09/2016.
 */

public interface CatFactsListener {
    public void onCatFactReceived(CatFactResponse response);
    public void onError(String error);
}
