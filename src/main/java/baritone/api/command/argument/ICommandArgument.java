package baritone.api.command.argument;

import baritone.api.command.exception.CommandInvalidTypeException;

public interface ICommandArgument {
   boolean is(Class clazz);

   int getIndex();

   String getValue();

   String getRawRest();

   Object getAs(Class clazz) throws CommandInvalidTypeException;

   Object getAs(Class clazz, Class clazz2, Object obj) throws CommandInvalidTypeException;

   boolean is(Class clazz, Class clazz2, Object obj);

   Enum getEnum(Class clazz) throws CommandInvalidTypeException;
}
