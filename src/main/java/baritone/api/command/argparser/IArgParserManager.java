package baritone.api.command.argparser;

import baritone.api.command.argument.ICommandArgument;
import baritone.api.command.exception.CommandInvalidTypeException;
import baritone.api.command.registry.Registry;

public interface IArgParserManager {
   Object parseStateless(Class clazz, ICommandArgument commandArgument) throws CommandInvalidTypeException;

   Registry getRegistry();

   IStatelessArgParser getParserStateless(Class clazz);

   IStatedArgParser getParserStated(Class clazz, Class clazz2);

   Object parseStated(Class clazz, Class clazz2, ICommandArgument commandArgument, Object obj) throws CommandInvalidTypeException;
}
