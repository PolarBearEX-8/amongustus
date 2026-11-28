package baritone.command.argument;

import baritone.api.command.exception.CommandInvalidTypeException;
import baritone.command.argparser.ArgParserManager;
import baritone.api.command.argument.ICommandArgument;
import java.util.stream.Stream;

public class CommandArgument implements ICommandArgument {
   private final int index;
   private final String value;
   private final String rawRest;

   @Override
   public boolean is(Class clazz) {
      try {
         this.getAs(clazz);
         return true;
      } catch (Throwable e) {
         return false;
      }
   }

   @Override
   public int getIndex() {
      return this.index;
   }

   @Override
   public String getValue() {
      return this.value;
   }

   public CommandArgument(int n, String str, String str2) {
      this.index = n;
      this.value = str;
      this.rawRest = str2;
   }

   @Override
   public Object getAs(Class clazz, Class clazz2, Object obj) throws CommandInvalidTypeException {
      return ArgParserManager.INSTANCE.parseStated(clazz, clazz2, this, obj);
   }

   @Override
   public boolean is(Class clazz, Class clazz2, Object obj) {
      try {
         this.getAs(clazz, clazz2, obj);
         return true;
      } catch (Throwable e) {
         return false;
      }
   }

   @Override
   public String getRawRest() {
      return this.rawRest;
   }

   @Override
   public Object getAs(Class clazz) throws CommandInvalidTypeException {
      return ArgParserManager.INSTANCE.parseStateless(clazz, this);
   }

   @Override
   public Enum getEnum(Class clazz) throws CommandInvalidTypeException {
      return (Enum)Stream.of(clazz.getEnumConstants())
         .filter(obj -> ((Enum)obj).name().equalsIgnoreCase(this.value))
         .findFirst()
         .orElseThrow(() -> new CommandInvalidTypeException(this, clazz.getSimpleName()));
   }
}
