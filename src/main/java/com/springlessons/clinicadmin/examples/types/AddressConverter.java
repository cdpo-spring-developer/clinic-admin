package com.springlessons.clinicadmin.examples.types;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AddressConverter implements AttributeConverter<Address, String> {


    @Override
    public String convertToDatabaseColumn(Address attribute) {
        return attribute.getCity() + ":" + attribute.getCity();
    }

    @Override
    public Address convertToEntityAttribute(String dbData) {
        String[] parts = dbData.split(":");
        Address address = new Address();
        address.setCity(parts[0]);
        address.setCity(parts[1]);
        return address;
    }
}
