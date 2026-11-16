package augustus.modules.misc;

import client.ClientGlobals;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.MultiSelectSetting;
import client.setting.PanicCombatType;
import java.awt.Color;

public class Panic extends Module implements TickListener {
    public MultiSelectSetting settingCategoriesToDisable = new MultiSelectSetting(this, "CategoriesToDisable", PanicCombatType.values());
    public boolean expanded2 = false;

    public Panic() {
        super("Panic", "Panik", new Color(145, 34, 224), Category.MISC);
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.expanded2 = true;
    }

    @Override
    public void onTick() {
        if (this.expanded2) {
            for (Module module : ((Iterable<Module>) ClientGlobals.moduleManager.getCopyOnWriteArrayList2())) {
                if (module.isToggled()) {
                    for (PanicCombatType panicCombatTypeKind : PanicCombatType.values()) {
                        if (PanicCombatType.getCategory(panicCombatTypeKind).equals(module.getCategory()) && PanicCombatType.checkPanicCombatType(panicCombatTypeKind)) {
                            module.processBoolean(false, false);
                        }
                    }
                }
            }

            ClientGlobals.moduleManager.updateState();
        }

        this.setActive(false);
        this.expanded2 = false;
    }
}
