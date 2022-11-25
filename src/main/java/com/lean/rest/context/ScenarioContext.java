package com.lean.rest.context;

import java.util.HashMap;
import java.util.Map;

import com.lean.rest.annotation.LazyComponent;
import com.lean.rest.enums.Context;

@LazyComponent
public class ScenarioContext {
    private final Map<String, Object> scenarioContext;
    public ScenarioContext() {
        scenarioContext = new HashMap<String, Object>();
    }
    public void setContext(Context key, Object value) {
        scenarioContext.put(key.toString(), value);
    }
    public Object getContext(Context key) {
        return scenarioContext.get(key.toString());
    }

    public Boolean isContains(Context key) {
        return scenarioContext.containsKey(key.toString());
    }
}

