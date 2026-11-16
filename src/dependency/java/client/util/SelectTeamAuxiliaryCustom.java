package client.util;

import client.gui.ExtendedTexturesGuiIconsPngManagerAdapter;
import client.gui.TexturesGuiIconsPngManager;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class SelectTeamAuxiliaryCustom implements AuxiliaryCustom, CurveTracker {
   public final List list = Lists.newArrayList();

   @Override
   public IChatComponent getIChatComponent() {
      return new ChatComponentText("Select a team to teleport to");
   }

   @Override
   public void applyFloat(float factor, int count) {
      Minecraft.getMinecraft().getTextureManager().applyResourceLocation3(ExtendedTexturesGuiIconsPngManagerAdapter.resourceLocation4);
      TexturesGuiIconsPngManager.applyIndex(0, 0, 16.0F, 0.0F, 16, 16, 256.0F, 256.0F);
   }

   @Override
   public boolean isEmpty() {
      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         if (((CurveTracker)iterator.next()).isEmpty()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public List getList() {
      return this.list;
   }

   @Override
   public void applySplineConverter(SplineConverter splineConverter) {
      splineConverter.applyAuxiliaryCustom(this);
   }

   @Override
   public IChatComponent getName() {
      return new ChatComponentText("Teleport to team member");
   }

   public SelectTeamAuxiliaryCustom() {
      for (ScorePlayerTeam scorePlayerTeam : (Iterable<ScorePlayerTeam>)(Iterable)Minecraft.getMinecraft().theWorld.getThePlayerNameManager().values()) {
         this.list.add(new ScorePlayerTeamCurveTracker(this, scorePlayerTeam));
      }
   }
}
