package augustus.modules.combat;

import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class Teams extends Module implements TickListener {
    public ModeSetting settingModes = new ModeSetting(this, "Modes", "Color", new String[]{"Name", "Color", "Clan"});
    public BooleanSetting settingAutoTeams = new BooleanSetting(this, "AutoTeams", false);
    public ArrayList itemList3 = new ArrayList();
    public boolean expanded2 = false;
    public String displayName1 = "";
    public static String displayName2;

    public Teams() {
        super("Teams", "Verbündete", Color.red, Category.COMBAT);
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        this.itemList3.clear();
    }

    @Override
    public void onPreDisable() {
        this.expanded2 = false;
        this.displayName1 = "";
    }

    @Override
    public void onRunTick() {
        this.itemList3.clear();
        this.expanded2 = false;

        for (Entity entity : (Iterable<Entity>) minecraft.theWorld.loadedEntityList) {
            if (this.checkEntity(entity)) {
                this.itemList3.add((EntityPlayer)entity);
            }
        }
    }

    private boolean checkEntity(Entity entity) {
        if (entity instanceof EntityLivingBase && entity instanceof EntityPlayer) {
            ScorePlayerTeam scorePlayerTeam = minecraft.theWorld.getThePlayerNameManager().getScorePlayerTeam(entity.getText());
            ScorePlayerTeam scorePlayerTeam2 = minecraft.theWorld.getThePlayerNameManager().getScorePlayerTeam(minecraft.thePlayer.getText());
            if (scorePlayerTeam != null && scorePlayerTeam2 != null) {
                if (scorePlayerTeam.getText().equals(scorePlayerTeam2.getText()) && this.settingModes.getSelected().equals("Color")) {
                    return true;
                }

                if (scorePlayerTeam.getText2().equals(scorePlayerTeam2.getText2()) && this.settingModes.getSelected().equals("Name")) {
                    return true;
                }

                this.expanded2 = true;
            }

            if (this.settingModes.getSelected().equals("Clan") && entity.getChatComponent() != null) {
                String text2 = entity.getChatComponent().getString2();
                if (text2 != null) {
                    String[] textArray = text2.split("§7 \\[§e");
                    if (textArray.length > 1) {
                        String text = textArray[1];
                        if (this.displayName1.equals("")) {
                            if (minecraft.thePlayer.getChatComponent() == null || minecraft.thePlayer.getChatComponent().getString2() == null) {
                                this.displayName1 = "IAmInNoClanBastard";
                                return false;
                            }

                            String[] textArray2 = minecraft.thePlayer.getChatComponent().getString2().split("§7 \\[§e");
                            if (textArray2.length > 1) {
                                this.displayName1 = textArray2[1];
                            } else {
                                this.displayName1 = "IAmInNoClanBastard";
                            }
                        }

                        if (text.equals(this.displayName1)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static void updateText(String text) {
        displayName2 = text;
    }

    public boolean checkEntity2(Entity entity) {
        if (!this.expanded2 && this.settingAutoTeams.isEnabled()) {
            return false;
        } else {
            return this.isToggled() && entity instanceof EntityPlayer && !this.getItemList22().isEmpty()
            ? this.getItemList22().contains(entity)
            : false;
        }
    }

    public boolean checkString(String text) {
        if (!this.expanded2 && this.settingAutoTeams.isEnabled()) {
            return false;
        } else {
            if (this.isToggled() && !this.getItemList22().isEmpty()) {
                Iterator iterator = this.getItemList22().iterator();

                while (iterator.hasNext()) {
                    if (((EntityPlayer)iterator.next()).getText().equals(text)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public ArrayList getItemList22() {
        return this.itemList3;
    }

    public static String getText2() {
        return displayName2;
    }
}
