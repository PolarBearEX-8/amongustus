package baritone.api.command.datatypes;

import baritone.api.command.exception.CommandException;
import baritone.api.command.helpers.TabCompleteHelper;
import baritone.api.utils.BlockOptionalMeta;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.ResourceLocation;

public enum ForBlockOptionalMeta implements IDatatypeFor {
   INSTANCE;
   private static Pattern PATTERN = Pattern.compile(
      "(?:[a-z0-9_.-]+:)?(?:[a-z0-9/_.-]+(?:\\[(?:(?:[a-z0-9_.-]+=[a-z0-9_.-]+,)*(?:[a-z0-9_.-]+(?:=(?:[a-z0-9_.-]+(?:\\])?)?)?)?|\\])?)?)?"
   );
   public BlockOptionalMeta get2(IDatatypeContext datatypeContext) throws CommandException {
      return new BlockOptionalMeta(datatypeContext.getConsumer().getString());
   }

   @Override
   public Stream tabComplete(IDatatypeContext datatypeContext) throws CommandException {
      String str = datatypeContext.getConsumer().peekString();
      if (!PATTERN.matcher(str).matches()) {
         datatypeContext.getConsumer().getString();
         return Stream.empty();
      } else if (str.endsWith("]")) {
         datatypeContext.getConsumer().getString();
         return Stream.empty();
      } else if (!str.contains("[")) {
         return datatypeContext.getConsumer().tabCompleteDatatype(BlockById.INSTANCE);
      } else {
         datatypeContext.getConsumer().getString();
         String[] strings = splitLast(str, '[');
         String str2 = strings[0];
         String str3 = strings[1];
         Block object = (Block)Block.blockRegistry.getObject(new ResourceLocation(str2));
         if (object == null) {
            return Stream.empty();
         } else {
            String[] strings2 = splitLast(str3, ',');
            String str4 = strings2[0];
            String str5 = strings2[1];
            if (!str5.contains("=")) {
               Set set = Stream.of(str4.split(",")).map(str6 -> str6.split("=")[0]).collect(Collectors.toSet());
               String str7 = str.substring(0, str.length() - str5.length());
               return new TabCompleteHelper()
                  .append(object.getBlockState().getProperties().stream().map(obj -> ((IProperty)obj).getName()))
                  .filter(obj2 -> !set.contains(obj2))
                  .filterPrefix(str5)
                  .sortAlphabetically()
                  .map(obj3 -> str7 + obj3)
                  .getStream();
            } else {
               String[] strings3 = splitLast(str5, '=');
               String str8 = strings3[0];
               String str9 = strings3[1];
               String str10 = str.substring(0, str.length() - str9.length());
               IProperty property = null;

               for (IProperty property2 : (Iterable<IProperty>)(object.getBlockState().getProperties())) {
                  if (property2.getName().equals(str8)) {
                     property = property2;
                     break;
                  }
               }

               return property == null ? Stream.empty() : new TabCompleteHelper().append(getValueNames(property)).filterPrefix(str9).sortAlphabetically().map(obj4 -> str10 + obj4).getStream();
            }
         }
      }
   }

   private static String[] splitLast(String str, char ch) {
      int n = str.lastIndexOf(ch);
      return n == -1 ? new String[]{"", str} : new String[]{str.substring(0, n), str.substring(n + 1)};
   }

   private static Stream getValueNames(IProperty property) {
      return property.getAllowedValues().stream().map(obj -> property.getName((Comparable)obj));
   }

   @Override
   public Object get(IDatatypeContext datatypeContext) throws CommandException {
      return this.get2(datatypeContext);
   }

}
