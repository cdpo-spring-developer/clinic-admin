package com.springlessons.clinicadmin.examples.beans;

import org.springframework.stereotype.Component;

/**
 * Component - объект будет создан и помещен в контекст спринг, как бин-объект; <br>
 * Component("bBean") - при инъекции данного бина можно использовать Qualifier с именен бина - @Qualifier("task02").
 * Именование бинов используется для разрешения кофликта при инъекции зависимостей одного типа
 * */
@Component("bBean")
public class BClass implements IType{
}