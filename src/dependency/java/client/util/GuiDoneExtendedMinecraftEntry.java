package client.util;

import client.setting.FactoryOptionType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StatCollector;

public class GuiDoneExtendedMinecraftEntry extends ExtendedMinecraftEntry {
   public final List list;
   public final Map map;
   public final GeometryService geometryService;

   public GuiDoneExtendedMinecraftEntry(GeometryService geometryService2, Minecraft mc) {
      super(mc, geometryService2.width, geometryService2.height, 32, geometryService2.height - 65 + 4, 18);
      this.geometryService = geometryService2;
      this.list = Lists.newArrayList();
      this.map = Maps.newHashMap();

      for (BrandProperty brandProperty : (Iterable<BrandProperty>) (GeometryService.resolveLanguageManager(geometryService2).getSortedSet())) {
         this.map.put(brandProperty.getString(), brandProperty);
         this.list.add(brandProperty.getString());
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      this.geometryService.fontRenderer.setState1(true);
      GeometryService.applyFontRenderer4(
         this.geometryService.fontRenderer, ((BrandProperty)this.map.get(this.list.get(count))).toString(), this.count9 / 2, count3 + 1, 16777215
      );
      this.geometryService.fontRenderer.setState1(GeometryService.resolveLanguageManager(this.geometryService).getBrandProperty().isEnabled());
   }

   @Override
   public void applyIndex(int count, boolean flag, int count2, int count3) {
      BrandProperty brandProperty = (BrandProperty)this.map.get(this.list.get(count));
      GeometryService.resolveLanguageManager(this.geometryService).applyBrandProperty(brandProperty);
      GeometryService.resolveGameSettings(this.geometryService).text93 = brandProperty.getString();
      this.mc.updateState11();
      this.geometryService
         .fontRenderer
         .setState(
            GeometryService.resolveLanguageManager(this.geometryService).isActiveAsFlag() || GeometryService.resolveGameSettings(this.geometryService).state87
         );
      this.geometryService.fontRenderer.setState1(GeometryService.resolveLanguageManager(this.geometryService).isActive());
      GeometryService.resolveValueSliderControlByGeometryService(this.geometryService).labelText = StatCollector.getText("gui.done", new Object[0]);
      GeometryService.resolveValueSliderControl(this.geometryService).labelText = GeometryService.resolveGameSettings(this.geometryService)
         .resolveTextByFactoryOptionTyp(FactoryOptionType.FORCE_UNICODE_FONT);
      GeometryService.resolveGameSettings(this.geometryService).updateState();
   }

   @Override
   public int getCount() {
      return this.size() * 18;
   }

   @Override
   public boolean checkIndex(int count) {
      return ((String)this.list.get(count)).equals(GeometryService.resolveLanguageManager(this.geometryService).getBrandProperty().getString());
   }

   @Override
   public int size() {
      return this.list.size();
   }

   @Override
   public void updateState2() {
      this.geometryService.renderElement3();
   }
}
