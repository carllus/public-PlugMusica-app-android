package com.google.tagmanager;

class DebugEventInfoDistributor implements EventInfoDistributor {
    private String containerId;
    private String containerVersion;
    private DebugInformationHandler handler;

    public DebugEventInfoDistributor(DebugInformationHandler handler2, String containerVersion2, String containerId2) {
        this.handler = handler2;
        this.containerVersion = containerVersion2;
        this.containerId = containerId2;
    }

    public EventInfoBuilder createMacroEvalutionEventInfo(String key) {
        return new DebugEventInfoBuilder(2, this.containerVersion, this.containerId, key, this.handler);
    }

    public EventInfoBuilder createDataLayerEventEvaluationEventInfo(String event) {
        return new DebugEventInfoBuilder(1, this.containerVersion, this.containerId, event, this.handler);
    }

    public boolean debugMode() {
        return true;
    }
}
