package client.gui;

import client.event.PreMotionObserver;
import client.setting.DisablerSuccessfullyLoadedType;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.util.StatCollector;

public class DefaultGuiScreenAdapter extends GuiScreen {
   public PreMotionObserver preMotionObserver;
   public String labelText5;
   public String labelText6;
   public final List list3 = Lists.newArrayList();
   public String labelText3;
   public String labelText4;
   public int count2;
   public int count3;
   public NetworkManager networkManager;
   public String labelText;
   public String labelText2;

   @Override
   public void applyIndex(int count, int count4, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, this.labelText5, this.width / 2, 70, 16777215);
      int count5 = 90;

      for (String text : (Iterable<String>)(Iterable<?>)this.list3) {
         applyFontRenderer4(this.fontRenderer, text, this.width / 2, count5, 16777215);
         count5 += this.fontRenderer.indexOffset;
      }

      super.applyIndex(count, count4, factor);
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 >= 2) {
         this.networkManager.handlePacket6(new C19PacketResourcePackStatus(this.labelText2, DisablerSuccessfullyLoadedType.ACCEPTED));
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 3) {
            Futures.addCallback(
               Minecraft.getMinecraft().getResourceLanguageManager().submit(this.labelText),
               new DefaultGuiScreenAdapFutureCallback(this),
               MoreExecutors.directExecutor()
            );
         }

         Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
      } else {
         this.preMotionObserver.applyFlag(auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0, this.count2);
      }
   }

   public DefaultGuiScreenAdapter(PreMotionObserver preMotionObserver2, String text, String text2, String text3, String text4, int count) {
      this.preMotionObserver = preMotionObserver2;
      this.labelText5 = text;
      this.labelText6 = text2;
      this.labelText3 = text3;
      this.labelText4 = text4;
      this.count2 = count;
      GuiScreen.set.add(this);
   }

   @Override
   public void updateState2() {
      this.list.add(new ValueSliderControl(0, this.width / 2 - 155, this.height / 6 + 96, this.labelText3));
      this.list.add(new ValueSliderControl(1, this.width / 2 - 155 + 160, this.height / 6 + 96, this.labelText4));
      this.list3.clear();
      this.list3.addAll(this.fontRenderer.getList(this.labelText6, this.width - 50));
      if (this.labelText5.equals(StatCollector.getText("multiplayer.texturePrompt.line1", new Object[0]))) {
         this.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(2, 5, 5, 98, 20, "Fake accept"));
         this.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(3, 5, 28, 98, 20, "Fake download"));
      }
   }

   @Override
   public void updateState4() {
      super.updateState4();
      if (--this.count3 == 0) {
         Iterator iterator = this.list.iterator();

         while (iterator.hasNext()) {
            ((AuxiliaryTexturesGuiIconsPngManagerAdapter)iterator.next()).enabled3 = true;
         }
      }
   }

   public DefaultGuiScreenAdapter(PreMotionObserver preMotionObserver2, String text, String text2, int count) {
      this.preMotionObserver = preMotionObserver2;
      this.labelText5 = text;
      this.labelText6 = text2;
      this.count2 = count;
      this.labelText3 = StatCollector.getText("gui.yes", new Object[0]);
      this.labelText4 = StatCollector.getText("gui.no", new Object[0]);
      GuiScreen.set.add(this);
   }

   public void applyIndex(int count) {
      this.count3 = count;
      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         ((AuxiliaryTexturesGuiIconsPngManagerAdapter)iterator.next()).enabled3 = false;
      }
   }
}
