package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.midtier.proto.containertag.TypeSystem;
import java.util.Map;

class ContainerVersionMacro extends FunctionCallImplementation {

    /* renamed from: ID */
    private static final String f908ID = FunctionType.CONTAINER_VERSION.toString();
    private final Runtime mRuntime;

    public static String getFunctionId() {
        return f908ID;
    }

    public ContainerVersionMacro(Runtime runtime) {
        super(f908ID, new String[0]);
        this.mRuntime = runtime;
    }

    public boolean isCacheable() {
        return true;
    }

    public TypeSystem.Value evaluate(Map<String, TypeSystem.Value> map) {
        String containerVersion = this.mRuntime.getResource().getVersion();
        return containerVersion == null ? Types.getDefaultValue() : Types.objectToValue(containerVersion);
    }
}
