package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import java.awt.Color;

public class CameraNoClip extends Module {
    public CameraNoClip() {
        super("CameraNoClip", "KameraNichtKlip", new Color(179, 157, 80), Category.RENDER);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }
}
