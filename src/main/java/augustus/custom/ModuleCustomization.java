package augustus.custom;

import client.module.Module;
import client.setting.ModeSetting;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Integrates the editable Alan modules into module collections.
 */
public final class ModuleCustomization {
    private static boolean creating;
    private static AlanESP alanESP;

    private ModuleCustomization() {}

    public static Object newChoice(Object owner, String label, String value, String[] choices) {
        return new ModeSetting((Module) owner, label, value, choices);
    }

    public static void onModuleCreated(Object module) {
        AlanVisuals.initializeModule(module);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Collection filter(Collection modules) {
        if (modules == null || modules.isEmpty()) return modules;
        Object first = modules.iterator().next();
        String moduleClass = ClientAccess.resolveClassName("client.module.Module");
        Class<?> base = ClientAccess.type(moduleClass);
        if (first == null || !base.isInstance(first)) return modules;

        try {
            if (alanESP == null && !creating) {
                creating = true;
                try {
                    alanESP = new AlanESP();
                    System.err.println("[custom] AlanESP source module registered");
                } finally {
                    creating = false;
                }
            }
            if (alanESP != null) {
                String eventBusManagerClass = ClientAccess.resolveClassName("client.event.EventBusManager");
                String eventBusField = ClientAccess.resolveFieldName("client.event.EventBusManager", "eventBus");
                Object bus = ClientAccess.getDeclared(eventBusManagerClass, null, eventBusField);

                String eventRender3DClass = ClientAccess.resolveClassName("client.event.EventRender3D");
                String eventIdField = ClientAccess.resolveFieldName("client.event.EventRender3D", "EVENT_ID");
                int event = ((Number) ClientAccess.get(ClientAccess.type(eventRender3DClass), eventIdField)).intValue();

                if (!Boolean.TRUE.equals(ClientAccess.call(bus, "isSubscriber", boolean.class, new Class<?>[]{int.class, Object.class}, event, alanESP))) {
                    ClientAccess.call(bus, "subscribe", void.class, new Class<?>[]{int.class, Object.class}, event, alanESP);
                }
            }
            if (alanESP != null && !modules.contains(alanESP)) {
                boolean all = modules.size() > 100;
                boolean renderOnly = !all;
                String categoryField = ClientAccess.resolveFieldName("client.module.Module", "category");
                for (Object object : modules) {
                    if (object != null && base.isInstance(object)) {
                        Object category = ClientAccess.getDeclared(moduleClass, object, categoryField);
                        if (!String.valueOf(category).equals("RENDER")) {
                            renderOnly = false;
                            break;
                        }
                    }
                }
                if (all || renderOnly) {
                    modules.add(alanESP);
                }
            }
        } catch (UnsupportedOperationException immutable) {
            return new ArrayList<>(modules);
        } catch (Throwable error) {
            ClientAccess.warn("module registration", error);
        }
        return modules;
    }

    public static boolean shaderEnabled(Object setting, boolean shadow) {
        if (!ClientAccess.bool(setting)) return false;
        try {
            String shaderModuleName = shadow
                    ? ClientAccess.resolveClassName("augustus.modules.render.Shadow")
                    : ClientAccess.resolveClassName("augustus.modules.render.Blur");
            Object module = ClientAccess.module(shaderModuleName);
            if (module == null) return false;

            if (!ClientAccess.enabled(module)) {
                String setToggledMethod = ClientAccess.resolveMethodName("client.module.Module", "setToggled", "(Ljava/lang/Boolean;)V");
                ClientAccess.call(module, setToggledMethod, void.class, new Class<?>[]{Boolean.class}, Boolean.TRUE);
            }
            return ClientAccess.enabled(module);
        } catch (Throwable error) {
            ClientAccess.warn("shared shader dependency", error);
            return false;
        }
    }

    public static String accountLabel(String original) {
        if (original != null && original.startsWith("Username:")) {
            return "KanyeWest";
        }
        return original;
    }

    public static String mainMenuName(String original) {
        int suffix = original == null ? -1 : original.indexOf(" §7[");
        if (suffix < 0 && original != null) {
            suffix = original.indexOf(" [");
        }
        return "§eKanyeWest" + (suffix < 0 ? "" : original.substring(suffix));
    }
}
