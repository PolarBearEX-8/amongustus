package augustus.modules.combat;

import client.event.ClickListener;
import client.event.EventClick;
import client.module.Category;
import client.module.Module;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPrimedtnt;
import net.minecraft.item.ItemSword;

public class TntBlock extends Module implements ClickListener {
    public TntBlock() {
        super("TntBlock", "TntBlockierung", new Color(252, 147, 91), Category.COMBAT);
    }

    @Override
    public void onEnable() {
        this.registerEvent(7);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onClick(EventClick event) {
        for (Entity entity : (Iterable<Entity>) minecraft.theWorld.loadedEntityList) {
            if (entity instanceof EntityPrimedtnt) {
                if ((double)entity.getDistanceSqToEntity(minecraft.thePlayer) < 8.2
                && minecraft.thePlayer.getItemStack() != null) {
                    if (minecraft.thePlayer.getItemStack().getItem() instanceof ItemSword && ((EntityPrimedtnt)entity).indexOffset4 < 4) {
                        event.setState11(true);
                    }
                }
            }
        }
    }
}
