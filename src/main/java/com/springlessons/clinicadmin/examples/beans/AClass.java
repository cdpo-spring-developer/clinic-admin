package com.springlessons.clinicadmin.examples.beans;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Component - объект будет создан и помещен в контекст спринг, как бин-объект; <br>
 * Primary - при инъекции зависимсоти одного типа (IType) в отсутствие аннотации Qualifier или
 * инъекции по имени класса, данному бину будет отдаваться предпочтение (дефолтная инъекция).
 * Аннотация Primary используется для разрешения кофликта при инъекции зависимостей одного типа.
 * */
@Primary
@Component
public class AClass implements IType{
}