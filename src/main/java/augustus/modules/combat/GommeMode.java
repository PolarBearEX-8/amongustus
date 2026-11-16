package augustus.modules.combat;

import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.event.MotionListener;
import client.module.Category;
import client.module.Module;
import client.util.InventoryUtil;
import client.util.Pair;
import client.util.SlotManager;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class GommeMode extends Module implements AttackEntityListener, MotionListener {
    public Entity targetEntity = null;

    public GommeMode() {
        super("GommeMode", "GommeModus", new Color(167, 85, 250), Category.COMBAT);
    }

    @Override
    public void onEnable() {
        this.registerEvent(1);
        this.registerEvent(34);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onAttackEntity(EventAttackEntity event) {
        this.targetEntity = event.getEntity();
    }

    @Override
    public void onPostMotion() {
        if (this.targetEntity != null) {
            if (this.targetEntity instanceof EntityLivingBase) {
                if (((EntityLivingBase)this.targetEntity).hurtTime >= 6) {
                    ItemStack itemStack5 = minecraft.thePlayer.getHeldItem();
                    if (itemStack5 == null || itemStack5.getItem() instanceof ItemSword || !minecraft.thePlayer.isUsingItem()) {
                        if (!minecraft.playerController.state) {
                            ArrayList arrayList = InventoryUtil.getArrayList(minecraft.thePlayer.container, 36);
                            if (!arrayList.isEmpty()) {
                                Pair pair = (Pair)arrayList.get(0);
                                if ((Integer)pair.getFirst() - 36 != minecraft.thePlayer.inventoryPlayer.currentItem) {
                                    SlotManager.applyModule3(this, 8, (Integer)pair.getFirst() - 36);
                                }
                            }
                        }
                    }
                } else {
                    this.targetEntity = null;
                }
            }
        }
    }
}
