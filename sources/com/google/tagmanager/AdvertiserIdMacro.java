package com.google.tagmanager;

import android.content.Context;
import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.midtier.proto.containertag.TypeSystem;
import java.util.Map;

class AdvertiserIdMacro extends FunctionCallImplementation {

    /* renamed from: ID */
    private static final String f900ID = FunctionType.ADVERTISER_ID.toString();
    private final Context mContext;

    public static String getFunctionId() {
        return f900ID;
    }

    public AdvertiserIdMacro(Context context) {
        super(f900ID, new String[0]);
        this.mContext = context;
    }

    public boolean isCacheable() {
        return true;
    }

    public TypeSystem.Value evaluate(Map<String, TypeSystem.Value> map) {
        return Types.getDefaultValue();
    }
}
