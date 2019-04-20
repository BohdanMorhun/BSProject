package resource_manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Manager for retrieving queries and reg.expression
 * */

public enum ResourceManager {

    INSTANCE;

    private String queriesResource = "queries/queries";
    private String regexResource = "regex/reg";

    private ResourceBundle resourceBundle;

    ResourceManager() {
        resourceBundle = ResourceBundle.getBundle(queriesResource, Locale.getDefault());
    }

    public String getQuery(String key) {
        return getString(key, queriesResource);
    }

    public String getRegex(String key) {
        return getString(key, regexResource);
    }

    private String getString(String key, String resourceRoute) {
        resourceBundle = ResourceBundle.getBundle(resourceRoute);
        return resourceBundle.getString(key);
    }
}
