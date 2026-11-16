package augustus.modules.misc;

import client.module.Category;
import client.module.Module;
import client.util.FoundMouseManager;
import java.awt.Color;
import net.java.games.input.DefaultControllerEnvironment;
import net.java.games.input.Mouse;

public class RawMouseInput extends Module {
    public static boolean activeState;

    public RawMouseInput() {
        super("RawMouseInput", "RohMausEingabe", new Color(255, 211, 99), Category.MISC);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        FoundMouseManager.foundMouseManager.setMouse((Mouse)null);
        setActive4(false);
    }

    @Override
    public void onPreDisable() {
        FoundMouseManager.foundMouseManager.defaultControllerEnvironment = new DefaultControllerEnvironment();
        FoundMouseManager.foundMouseManager.setMouse((Mouse)null);
        setActive4(true);
    }

    public static void setActive4(boolean enabled) {
        activeState = enabled;
    }

    public static synchronized boolean canExecute5() {
        return activeState;
    }
}
