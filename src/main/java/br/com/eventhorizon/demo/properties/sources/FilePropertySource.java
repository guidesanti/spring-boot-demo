package br.com.eventhorizon.demo.properties.sources;

import br.com.eventhorizon.demo.properties.Property;
import br.com.eventhorizon.demo.properties.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FilePropertySource extends ReloadablePropertySource {

  private static final Logger LOGGER = LoggerFactory.getLogger(FilePropertySource.class);

  private final String path;

  private final Map<String, Property> fileKey2ApplicationPropertyMap;

  public FilePropertySource(String name, String path, Map<String, Property> fileKey2ApplicationPropertyMap) {
    super(name);
    this.path = path;
    this.fileKey2ApplicationPropertyMap = fileKey2ApplicationPropertyMap;
    reloadProperties();
  }

  @Override
  public void reloadProperties() {
    try {
      LOGGER.info("Loading properties from " + path);
      InputStream is;
      if (path.startsWith("external::")) {
        is = new FileInputStream(path.substring(10));
      } else {
        is = PropertyManager.class.getClassLoader().getResourceAsStream(path);
      }
      Properties props1 = new Properties();
      props1.load(is);
      Map<String, Object> props2 = new HashMap<>();
      fileKey2ApplicationPropertyMap.forEach((key, property) -> props2.put(property.getKey(), props1.getProperty(key)));
      this.source.clear();
      this.source.putAll(props2);
      LOGGER.info("Successfully loaded properties from " + path);
    } catch (IOException e) {
      LOGGER.error("Failed to load properties from " + path, e);
    }
  }
}
