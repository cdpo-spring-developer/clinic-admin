package com.springlessons.clinicadmin.examples;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Component - бин будет создан и помещен в контекст спринг; </br>
 * Profile - бин будет создан для всех профилей, кроме no_settings; </br>
 * ConfigurationProperties - значения свойств класса необходимо искать
 * в properties файле, настройки объеденены префиксом inject (
 * ключи начинаются с inject).</br>
 * */
@Profile("!no_settings")
@Component
@ConfigurationProperties(prefix = "inject")
public class SettingsService {
    /**
     * Значение будет устанавливаться на основе ключа -
     * inject.service.title.
     * Название свойства в классе не учитывается
     */
    @Value("${service.title}")
    private String title;
    /**
     * Значение будет устанавливаться на основе ключа -
     * inject.service.number.
     * Название свойства в классе не учитывается
     */
    @Value("${service.number}")
    private int number;

    /**
     * Значение будет устанавливаться на основе соответствия:
     * ключ в properties файле - имя свойства
     */
    private String settingsType;
}
