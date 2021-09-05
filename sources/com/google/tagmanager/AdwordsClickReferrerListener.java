package com.google.tagmanager;

import android.content.Context;
import android.net.Uri;
import com.google.tagmanager.DataLayer;
import java.util.Map;

class AdwordsClickReferrerListener implements DataLayer.Listener {
    private final Context context;

    public AdwordsClickReferrerListener(Context context2) {
        this.context = context2;
    }

    public void changed(Map<Object, Object> update) {
        String referrer;
        Object gtm;
        Object url = update.get("gtm.url");
        if (url == null && (gtm = update.get("gtm")) != null && (gtm instanceof Map)) {
            url = ((Map) gtm).get("url");
        }
        if (url != null && (url instanceof String) && (referrer = Uri.parse((String) url).getQueryParameter("referrer")) != null) {
            InstallReferrerUtil.addClickReferrer(this.context, referrer);
        }
    }
}
