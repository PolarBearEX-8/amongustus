package augustus.custom;

import java.lang.reflect.Field;

/**
 * Uses a crosshair player when the combat modules have no active player target.
 */
public final class TargetHudRepair {

    private static Object lastTarget;
    private static long lastSeen;

    private TargetHudRepair() {
    }

    public static Object resolve(Object original) {
        if (original != null) {
            return original;
        }
        try {
            Object registry = ClientAccess.registry();
            Object target = null;
            Class<?> playerClass = ClientAccess.type(ClientAccess.resolveClassName("net.minecraft.client.entity.AbstractClientPlayer"));

            String[][] candidates = {
                {
                    ClientAccess.resolveFieldName("client.module.ModuleManager", "killAura"),
                    ClientAccess.resolveFieldName("augustus.modules.combat.KillAura", "target")
                },
                {
                    ClientAccess.resolveFieldName("client.module.ModuleManager", "aimBot"),
                    ClientAccess.resolveFieldName("augustus.modules.legit.AimBot", "target")
                },
                {
                    ClientAccess.resolveFieldName("client.module.ModuleManager", "aimAssist"),
                    ClientAccess.resolveFieldName("augustus.modules.legit.AimAssist", "target")
                }
            };

            for (String[] pair : candidates) {
                Object owner = ClientAccess.get(registry, pair[0]);
                if (owner != null) {
                    Object found = ClientAccess.get(owner, pair[1]);
                    if (playerClass != null && playerClass.isInstance(found)) {
                        target = found;
                        break;
                    }
                }
            }

            if (target != null) {
                return target;
            }

            Object mc = ClientAccess.minecraft();
            String objectMouseOverField = ClientAccess.resolveFieldName("net.minecraft.client.Minecraft", "objectMouseOver");
            Object hit = ClientAccess.get(mc, objectMouseOverField);
            if (hit != null) {
                Class<?> entityClass = ClientAccess.type(ClientAccess.resolveClassName("net.minecraft.entity.Entity"));
                for (Field field : hit.getClass().getDeclaredFields()) {
                    if (entityClass != null && entityClass.isAssignableFrom(field.getType())) {
                        field.setAccessible(true);
                        Object found = field.get(hit);
                        if (playerClass != null && playerClass.isInstance(found)) {
                            target = found;
                            break;
                        }
                    }
                }
            }

            if (target != null) {
                lastTarget = target;
                lastSeen = System.currentTimeMillis();
            } else if (System.currentTimeMillis() - lastSeen < 1000L) {
                target = lastTarget;
            }

            // In the HUD editor show the local player so the user can place the widget.
            if (target == null) {
                String currentScreenField = ClientAccess.resolveFieldName("net.minecraft.client.Minecraft", "currentScreen");
                Object screen = ClientAccess.get(mc, currentScreenField);
                String clickGuiClass = ClientAccess.resolveClassName("client.gui.ClickGuiScreen");
                if (screen != null && screen.getClass().getName().equals(clickGuiClass)) {
                    String thePlayerField = ClientAccess.resolveFieldName("net.minecraft.client.Minecraft", "thePlayer");
                    target = ClientAccess.get(mc, thePlayerField);
                }
            }
            return target;
        } catch (Throwable error) {
            ClientAccess.warn("TargetHUD target selection", error);
            return original;
        }
    }
}
