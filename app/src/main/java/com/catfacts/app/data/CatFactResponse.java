package com.catfacts.app.data;

import java.util.List;

/**
 * Created by jose.rubalcaba on 19/09/2016.
 */
public class CatFactResponse {
    private List<String> facts;
    private boolean success;

    public CatFactResponse() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getFacts() {
        return facts;
    }

    public void setFacts(List<String> facts) {
        this.facts = facts;
    }
}
