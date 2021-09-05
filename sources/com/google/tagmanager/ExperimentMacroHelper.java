package com.google.tagmanager;

import com.google.analytics.containertag.proto.Serving;
import com.google.analytics.midtier.proto.containertag.TypeSystem;
import java.util.Map;

public class ExperimentMacroHelper {
    public static void handleExperimentSupplemental(DataLayer dataLayer, Serving.Supplemental supplemental) {
        if (supplemental.experimentSupplemental == null) {
            Log.m181w("supplemental missing experimentSupplemental");
            return;
        }
        clearKeys(dataLayer, supplemental.experimentSupplemental);
        pushValues(dataLayer, supplemental.experimentSupplemental);
        setRandomValues(dataLayer, supplemental.experimentSupplemental);
    }

    private static void clearKeys(DataLayer dataLayer, Serving.GaExperimentSupplemental expSupplemental) {
        for (TypeSystem.Value value : expSupplemental.valueToClear) {
            dataLayer.clearPersistentKeysWithPrefix(Types.valueToString(value));
        }
    }

    private static void pushValues(DataLayer dataLayer, Serving.GaExperimentSupplemental expSupplemental) {
        for (TypeSystem.Value value : expSupplemental.valueToPush) {
            Map<Object, Object> map = valueToMap(value);
            if (map != null) {
                dataLayer.push(map);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        if (r15.longValue() > r8) goto L_0x0047;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void setRandomValues(com.google.tagmanager.DataLayer r22, com.google.analytics.containertag.proto.Serving.GaExperimentSupplemental r23) {
        /*
            r0 = r23
            com.google.analytics.containertag.proto.Serving$GaExperimentRandom[] r2 = r0.experimentRandom
            int r6 = r2.length
            r5 = 0
        L_0x0006:
            if (r5 >= r6) goto L_0x00fb
            r3 = r2[r5]
            java.lang.String r0 = r3.key
            r16 = r0
            if (r16 != 0) goto L_0x0018
            java.lang.String r16 = "GaExperimentRandom: No key"
            com.google.tagmanager.Log.m181w(r16)
        L_0x0015:
            int r5 = r5 + 1
            goto L_0x0006
        L_0x0018:
            java.lang.String r0 = r3.key
            r16 = r0
            r0 = r22
            r1 = r16
            java.lang.Object r14 = r0.get(r1)
            boolean r0 = r14 instanceof java.lang.Number
            r16 = r0
            if (r16 != 0) goto L_0x00be
            r15 = 0
        L_0x002b:
            long r10 = r3.minRandom
            long r8 = r3.maxRandom
            boolean r0 = r3.retainOriginalValue
            r16 = r0
            if (r16 == 0) goto L_0x0047
            if (r15 == 0) goto L_0x0047
            long r16 = r15.longValue()
            int r16 = (r16 > r10 ? 1 : (r16 == r10 ? 0 : -1))
            if (r16 < 0) goto L_0x0047
            long r16 = r15.longValue()
            int r16 = (r16 > r8 ? 1 : (r16 == r8 ? 0 : -1))
            if (r16 <= 0) goto L_0x0065
        L_0x0047:
            int r16 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r16 > 0) goto L_0x00cb
            double r16 = java.lang.Math.random()
            long r18 = r8 - r10
            r0 = r18
            double r0 = (double) r0
            r18 = r0
            double r16 = r16 * r18
            double r0 = (double) r10
            r18 = r0
            double r16 = r16 + r18
            long r16 = java.lang.Math.round(r16)
            java.lang.Long r14 = java.lang.Long.valueOf(r16)
        L_0x0065:
            java.lang.String r0 = r3.key
            r16 = r0
            r0 = r22
            r1 = r16
            r0.clearPersistentKeysWithPrefix(r1)
            java.lang.String r0 = r3.key
            r16 = r0
            r0 = r22
            r1 = r16
            java.util.Map r7 = r0.expandKeyValue(r1, r14)
            long r0 = r3.lifetimeInMilliseconds
            r16 = r0
            r18 = 0
            int r16 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r16 <= 0) goto L_0x00b7
            java.lang.String r16 = "gtm"
            r0 = r16
            boolean r16 = r7.containsKey(r0)
            if (r16 != 0) goto L_0x00d2
            java.lang.String r16 = "gtm"
            r17 = 2
            r0 = r17
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r17 = r0
            r18 = 0
            java.lang.String r19 = "lifetime"
            r17[r18] = r19
            r18 = 1
            long r0 = r3.lifetimeInMilliseconds
            r20 = r0
            java.lang.Long r19 = java.lang.Long.valueOf(r20)
            r17[r18] = r19
            java.util.Map r17 = com.google.tagmanager.DataLayer.mapOf(r17)
            r0 = r16
            r1 = r17
            r7.put(r0, r1)
        L_0x00b7:
            r0 = r22
            r0.push(r7)
            goto L_0x0015
        L_0x00be:
            r12 = r14
            java.lang.Number r12 = (java.lang.Number) r12
            long r16 = r12.longValue()
            java.lang.Long r15 = java.lang.Long.valueOf(r16)
            goto L_0x002b
        L_0x00cb:
            java.lang.String r16 = "GaExperimentRandom: random range invalid"
            com.google.tagmanager.Log.m181w(r16)
            goto L_0x0015
        L_0x00d2:
            java.lang.String r16 = "gtm"
            r0 = r16
            java.lang.Object r13 = r7.get(r0)
            boolean r0 = r13 instanceof java.util.Map
            r16 = r0
            if (r16 == 0) goto L_0x00f5
            r4 = r13
            java.util.Map r4 = (java.util.Map) r4
            java.lang.String r16 = "lifetime"
            long r0 = r3.lifetimeInMilliseconds
            r18 = r0
            java.lang.Long r17 = java.lang.Long.valueOf(r18)
            r0 = r16
            r1 = r17
            r4.put(r0, r1)
            goto L_0x00b7
        L_0x00f5:
            java.lang.String r16 = "GaExperimentRandom: gtm not a map"
            com.google.tagmanager.Log.m181w(r16)
            goto L_0x00b7
        L_0x00fb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.tagmanager.ExperimentMacroHelper.setRandomValues(com.google.tagmanager.DataLayer, com.google.analytics.containertag.proto.Serving$GaExperimentSupplemental):void");
    }

    private static Map<Object, Object> valueToMap(TypeSystem.Value value) {
        Object valueAsObject = Types.valueToObject(value);
        if (valueAsObject instanceof Map) {
            return (Map) valueAsObject;
        }
        Log.m181w("value: " + valueAsObject + " is not a map value, ignored.");
        return null;
    }
}
