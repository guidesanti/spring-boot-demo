package br.com.eventhorizon.demo.properties;

import br.com.eventhorizon.demo.properties.sources.FilePropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PropertyManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(PropertyManager.class);

  private static ConfigurableEnvironment environment;

  public static void init(ConfigurableEnvironment environment) {
    PropertyManager.environment = environment;
    loadProperties();
    checkProperties();
  }

  @SuppressWarnings("unchecked")
  public static <T> T getProperty(Property property) {
    if (environment == null) {
      throw new IllegalStateException("PropertyManager is not initialized");
    }
    String value = environment.getProperty(property.getKey());
    if (value == null || value.isEmpty()) {
      return (T) property.getDefaultValue();
    }
    Object defaultValue = property.getDefaultValue();
    if (defaultValue != null) {
      if (defaultValue instanceof Boolean){
        return (T) Boolean.valueOf(value);
      }
      if (defaultValue instanceof Integer){
        return (T) Integer.valueOf(value);
      }
      if (defaultValue instanceof Long){
        return (T) Long.valueOf(value);
      }
      if (defaultValue instanceof Double){
        return (T) Double.valueOf(value);
      }
    }
    return (T) value;
  }

  private static void loadProperties() {
    LOGGER.info("Loading application properties ...");
    MutablePropertySources propertySources = environment.getPropertySources();
    loadManifestProperties(propertySources);
    loadTestProperties(propertySources);
    LOGGER.info("Loading application properties [DONE]");
  }

  private static void checkProperties() {
    List<Property> missingRequiredProperties = new ArrayList<>();
    for (Property property : Property.values()) {
      if (property.isRequired()) {
        String value = environment.getProperty(property.getKey());
        if (value == null || value.isEmpty()) {
          missingRequiredProperties.add(property);
        }
      }
    }
    if (!missingRequiredProperties.isEmpty()) {
      throw new RuntimeException("Required properties not set: " + missingRequiredProperties);
    }
  }

  private static void loadManifestProperties(MutablePropertySources propertySources) {
    Map<String, Property> map = new HashMap<>();
    map.put("Implementation-Version", Property.VERSION);
    map.put("Implementation-GitHash", Property.GIT_HASH);
    map.put("Implementation-Build", Property.BUILD);
    propertySources.addLast(new FilePropertySource("manifest", "META-INF/MANIFEST.MF", map));
  }

  private static void loadTestProperties(MutablePropertySources propertySources) {
    Map<String, Property> map = new HashMap<>();
    map.put("Reloadable-Property1", Property.PROP1);
    propertySources.addLast(new FilePropertySource("external-file-property-source", "external::/data/test.mf", map));
  }
}
