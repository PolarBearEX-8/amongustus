package baritone.api.command.helpers;

import baritone.api.Settings;
import baritone.api.BaritoneAPI;
import baritone.api.command.ICommand;
import baritone.api.command.manager.ICommandManager;
import baritone.api.utils.SettingsUtil;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.minecraft.util.ResourceLocation;

public class TabCompleteHelper {
   private Stream stream;

   public TabCompleteHelper filter(Predicate predicate) {
      this.stream = this.stream.filter(predicate);
      return this;
   }

   public TabCompleteHelper append(Stream stream2) {
      this.stream = Stream.concat(this.stream, stream2);
      return this;
   }

   public TabCompleteHelper prepend(Stream stream2) {
      this.stream = Stream.concat(stream2, this.stream);
      return this;
   }

   public TabCompleteHelper sortAlphabetically() {
      return this.sort(String.CASE_INSENSITIVE_ORDER);
   }

   public TabCompleteHelper filterPrefixNamespaced(String str) {
      return this.filterPrefix(new ResourceLocation(str).toString());
   }

   public TabCompleteHelper prepend2(Class clazz) {
      return this.prepend(Stream.of((Enum[])clazz.getEnumConstants()).map(Enum::name).map(String::toLowerCase));
   }

   public TabCompleteHelper() {
      this.stream = Stream.empty();
   }

   public String[] build() {
      return (String[])this.stream.toArray(String[]::new);
   }

   public TabCompleteHelper filterPrefix(String str) {
      return this.filter(obj -> ((String)obj).toLowerCase(Locale.US).startsWith(str.toLowerCase(Locale.US)));
   }

   public TabCompleteHelper append3(Class clazz) {
      return this.append(Stream.of((Enum[])clazz.getEnumConstants()).map(Enum::name).map(String::toLowerCase));
   }

   public Stream getStream() {
      return this.stream;
   }

   public TabCompleteHelper(List list) {
      this.stream = list.stream();
   }

   public TabCompleteHelper addModifiedSettings() {
      return this.append(SettingsUtil.modifiedSettings(BaritoneAPI.getSettings()).stream().map(Settings.Setting::getName).sorted(String.CASE_INSENSITIVE_ORDER));
   }

   public TabCompleteHelper(String[] strings) {
      this.stream = Stream.of(strings);
   }

   public TabCompleteHelper addCommands(ICommandManager commandManager) {
      return this.append(commandManager.getRegistry().descendingStream().flatMap(obj -> ((ICommand)obj).getNames().stream()).distinct());
   }

   public TabCompleteHelper addSettings() {
      return this.append(BaritoneAPI.getSettings().allSettings.stream().filter(setting -> !setting.isJavaOnly()).map(Settings.Setting::getName).sorted(String.CASE_INSENSITIVE_ORDER));
   }

   public TabCompleteHelper prepend3(String[] strings) {
      return this.prepend(Stream.of(strings));
   }

   public TabCompleteHelper addToggleableSettings() {
      return this.append(BaritoneAPI.getSettings().getAllValuesByType(Boolean.class).stream().map(Settings.Setting::getName).sorted(String.CASE_INSENSITIVE_ORDER));
   }

   public TabCompleteHelper sort(Comparator comparator) {
      this.stream = this.stream.sorted(comparator);
      return this;
   }

   public TabCompleteHelper append2(String[] strings) {
      return this.append(Stream.of(strings));
   }

   public TabCompleteHelper map(Function function) {
      this.stream = this.stream.map(function);
      return this;
   }
}
