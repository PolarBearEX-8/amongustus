package baritone.command.argparser;

import baritone.api.command.argparser.IArgParserManager;
import baritone.api.command.argparser.IStatedArgParser;
import baritone.api.command.argparser.IStatelessArgParser;
import baritone.api.command.argument.ICommandArgument;
import baritone.api.command.exception.CommandNoParserForTypeException;
import baritone.api.command.exception.CommandInvalidTypeException;
import baritone.api.command.registry.Registry;

public enum ArgParserManager implements IArgParserManager {
   INSTANCE;
   public final Registry registry = new Registry();
   @Override
   public IStatedArgParser getParserStated(Class clazz, Class clazz2) {
      return (IStatedArgParser)this.registry
         .descendingStream()
         .filter(IStatedArgParser.class::isInstance)
         .map(IStatedArgParser.class::cast)
         .filter(obj -> ((IStatedArgParser)obj).getTarget().isAssignableFrom(clazz))
         .filter(obj2 -> ((IStatedArgParser)obj2).getStateType().isAssignableFrom(clazz2))
         .map(IStatedArgParser.class::cast)
         .findFirst()
         .orElse(null);
   }

   @Override
   public IStatelessArgParser getParserStateless(Class clazz) {
      return (IStatelessArgParser)this.registry
         .descendingStream()
         .filter(IStatelessArgParser.class::isInstance)
         .map(IStatelessArgParser.class::cast)
         .filter(obj -> ((IStatelessArgParser)obj).getTarget().isAssignableFrom(clazz))
         .findFirst()
         .orElse(null);
   }

   @Override
   public Object parseStateless(Class clazz, ICommandArgument commandArgument) throws CommandInvalidTypeException {
      IStatelessArgParser parserStateless = this.getParserStateless(clazz);
      if (parserStateless == null) {
         throw new CommandNoParserForTypeException(clazz);
      } else {
         try {
            return parserStateless.parseArg(commandArgument);
         } catch (Exception e) {
            throw new CommandInvalidTypeException(commandArgument, clazz.getSimpleName());
         }
      }
   }

   @Override
   public Object parseStated(Class clazz, Class clazz2, ICommandArgument commandArgument, Object obj) throws CommandInvalidTypeException {
      IStatedArgParser parserStated = this.getParserStated(clazz, clazz2);
      if (parserStated == null) {
         throw new CommandNoParserForTypeException(clazz);
      } else {
         try {
            return parserStated.parseArg(commandArgument, obj);
         } catch (Exception e) {
            throw new CommandInvalidTypeException(commandArgument, clazz.getSimpleName());
         }
      }
   }

@Override
   public Registry getRegistry() {
      return this.registry;
   }

   private ArgParserManager() {
      DefaultArgParsers.ALL.forEach(this.registry::register);
   }

}
