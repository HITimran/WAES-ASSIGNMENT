package org.waes.differ.utils;

import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.cucumberexpressions.ParameterType;
import io.cucumber.cucumberexpressions.Transformer;
import io.cucumber.datatable.DataTableType;

import java.util.Locale;

import static java.util.Locale.ENGLISH;

public class TypeRegistryConfiguration implements TypeRegistryConfigurer {

    @Override
    public Locale locale() {
        return ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineParameterType(new ParameterType<>(
                "exceptionType",
                "\\w+",
                ResponseCode.class,
                (Transformer) (arg) -> ResponseCode.valueOf(arg)
        ));

    }
}
/*

new ParameterType<>(
        "exceptionType",
        "\\w+",
        ResponseCode.class,
        (Transformer) (arg) -> ResponseCode.valueOf(arg)
        ));
*/
