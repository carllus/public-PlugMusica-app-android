package com.google.android.gms.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public final class zzapb {
    private final Map<Type, zzaod<?>> bkY;

    public zzapb(Map<Type, zzaod<?>> map) {
        this.bkY = map;
    }

    private <T> zzapg<T> zzc(final Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            return SortedSet.class.isAssignableFrom(cls) ? new zzapg<T>() {
                /* renamed from: bg */
                public T mo10134bg() {
                    return new TreeSet();
                }
            } : EnumSet.class.isAssignableFrom(cls) ? new zzapg<T>() {
                /* renamed from: bg */
                public T mo10134bg() {
                    if (type instanceof ParameterizedType) {
                        Type type = ((ParameterizedType) type).getActualTypeArguments()[0];
                        if (type instanceof Class) {
                            return EnumSet.noneOf((Class) type);
                        }
                        String valueOf = String.valueOf(type.toString());
                        throw new zzaoi(valueOf.length() != 0 ? "Invalid EnumSet type: ".concat(valueOf) : new String("Invalid EnumSet type: "));
                    }
                    String valueOf2 = String.valueOf(type.toString());
                    throw new zzaoi(valueOf2.length() != 0 ? "Invalid EnumSet type: ".concat(valueOf2) : new String("Invalid EnumSet type: "));
                }
            } : Set.class.isAssignableFrom(cls) ? new zzapg<T>() {
                /* renamed from: bg */
                public T mo10134bg() {
                    return new LinkedHashSet();
                }
            } : Queue.class.isAssignableFrom(cls) ? new zzapg<T>() {
                /* renamed from: bg */
                public T mo10134bg() {
                    return new LinkedList();
                }
            } : new zzapg<T>() {
                /* renamed from: bg */
                public T mo10134bg() {
                    return new ArrayList();
                }
            };
        }
        if (Map.class.isAssignableFrom(cls)) {
            return SortedMap.class.isAssignableFrom(cls) ? new zzapg<T>() {
                /* renamed from: bg */
                public T mo10134bg() {
                    return new TreeMap();
                }
            } : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(zzapx.zzl(((ParameterizedType) type).getActualTypeArguments()[0]).mo10300by())) ? new zzapg<T>() {
                /* renamed from: bg */
                public T mo10134bg() {
                    return new zzapf();
                }
            } : new zzapg<T>() {
                /* renamed from: bg */
                public T mo10134bg() {
                    return new LinkedHashMap();
                }
            };
        }
        return null;
    }

    private <T> zzapg<T> zzd(final Type type, final Class<? super T> cls) {
        return new zzapg<T>() {
            private final zzapj blB = zzapj.m68bl();

            /* renamed from: bg */
            public T mo10134bg() {
                try {
                    return this.blB.zzf(cls);
                } catch (Exception e) {
                    String valueOf = String.valueOf(type);
                    throw new RuntimeException(new StringBuilder(String.valueOf(valueOf).length() + 116).append("Unable to invoke no-args constructor for ").append(valueOf).append(". ").append("Register an InstanceCreator with Gson for this type may fix this problem.").toString(), e);
                }
            }
        };
    }

    private <T> zzapg<T> zzl(Class<? super T> cls) {
        try {
            final Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new zzapg<T>() {
                /* renamed from: bg */
                public T mo10134bg() {
                    try {
                        return declaredConstructor.newInstance((Object[]) null);
                    } catch (InstantiationException e) {
                        String valueOf = String.valueOf(declaredConstructor);
                        throw new RuntimeException(new StringBuilder(String.valueOf(valueOf).length() + 30).append("Failed to invoke ").append(valueOf).append(" with no args").toString(), e);
                    } catch (InvocationTargetException e2) {
                        String valueOf2 = String.valueOf(declaredConstructor);
                        throw new RuntimeException(new StringBuilder(String.valueOf(valueOf2).length() + 30).append("Failed to invoke ").append(valueOf2).append(" with no args").toString(), e2.getTargetException());
                    } catch (IllegalAccessException e3) {
                        throw new AssertionError(e3);
                    }
                }
            };
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public String toString() {
        return this.bkY.toString();
    }

    public <T> zzapg<T> zzb(zzapx<T> zzapx) {
        final Type bz = zzapx.mo10301bz();
        Class<? super T> by = zzapx.mo10300by();
        final zzaod zzaod = this.bkY.get(bz);
        if (zzaod != null) {
            return new zzapg<T>() {
                /* renamed from: bg */
                public T mo10134bg() {
                    return zzaod.zza(bz);
                }
            };
        }
        final zzaod zzaod2 = this.bkY.get(by);
        if (zzaod2 != null) {
            return new zzapg<T>() {
                /* renamed from: bg */
                public T mo10134bg() {
                    return zzaod2.zza(bz);
                }
            };
        }
        zzapg<T> zzl = zzl(by);
        if (zzl != null) {
            return zzl;
        }
        zzapg<T> zzc = zzc(bz, by);
        return zzc == null ? zzd(bz, by) : zzc;
    }
}
