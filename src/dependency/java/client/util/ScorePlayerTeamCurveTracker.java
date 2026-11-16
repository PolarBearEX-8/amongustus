package client.util;

import client.gui.TexturesGuiIconsPngManager;
import client.network.NetworkPlayerInfo;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class ScorePlayerTeamCurveTracker implements CurveTracker {
   public final ScorePlayerTeam scorePlayerTeam;
   public final ResourceLocation resourceLocation;
   public final List list;
   public final SelectTeamAuxiliaryCustom selectTeamAuxiliaryCustom;

   @Override
   public boolean isEmpty() {
      return !this.list.isEmpty();
   }

   public ScorePlayerTeamCurveTracker(SelectTeamAuxiliaryCustom selectTeamAuxiliaryCustom2, ScorePlayerTeam scorePlayerTeam2) {
      this.selectTeamAuxiliaryCustom = selectTeamAuxiliaryCustom2;
      this.scorePlayerTeam = scorePlayerTeam2;
      this.list = Lists.newArrayList();

      for (Object textObj : scorePlayerTeam2.getCollection()) {
         String text = (String)textObj;
         NetworkPlayerInfo networkPlayerInfo = Minecraft.getMinecraft().getNetHandlerPlayClient().resolveNetworkPlayerInfo(text);
         if (networkPlayerInfo != null) {
            this.list.add(networkPlayerInfo);
         }
      }

      if (!this.list.isEmpty()) {
         String name = ((NetworkPlayerInfo)this.list.get(new Random().nextInt(this.list.size()))).getGameProfile().getName();
         this.resourceLocation = AbstractClientPlayer.resolveResourceLocation(name);
         AbstractClientPlayer.resolveLoggerEntryHandler(this.resourceLocation, name);
      } else {
         this.resourceLocation = SlimManager.getResourceLocation();
      }
   }

   @Override
   public void applySplineConverter(SplineConverter splineConverter) {
      splineConverter.applyAuxiliaryCustom(new TeleportPlayerCurveTracker(this.list));
   }

   @Override
   public IChatComponent getName() {
      return new ChatComponentText(this.scorePlayerTeam.getText2());
   }

   @Override
   public void applyFloat(float factor, int count) {
      int count2 = -1;
      String text = FontRenderer.resolveTextByText(this.scorePlayerTeam.getText());
      if (text.length() >= 2) {
         count2 = Minecraft.getMinecraft().fontRendererObj.computeCountByChar(text.charAt(1));
      }

      if (count2 >= 0) {
         float factor2 = (float)(count2 >> 16 & 0xFF) / 255.0F;
         float factor3 = (float)(count2 >> 8 & 0xFF) / 255.0F;
         float factor4 = (float)(count2 & 0xFF) / 255.0F;
         TexturesGuiIconsPngManager.processAction(1, 1, 15, 15, MathHelper.processAction(factor2 * factor, factor3 * factor, factor4 * factor) | count << 24);
      }

      Minecraft.getMinecraft().getTextureManager().applyResourceLocation3(this.resourceLocation);
      GlStateManager.color4f2(factor, factor, factor, (float)count / 255.0F);
      TexturesGuiIconsPngManager.processAction(2, 2, 8.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
      TexturesGuiIconsPngManager.processAction(2, 2, 40.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
   }
}
