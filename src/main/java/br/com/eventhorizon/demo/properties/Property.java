package br.com.eventhorizon.demo.properties;

public enum Property {

  VERSION(Key.VERSION, false, null),
  GIT_HASH(Key.GIT_HASH, false, null),
  BUILD(Key.BUILD, false, null),
  PROP1(Key.PROP1, false, null),
  PROP2(Key.PROP2, true, null);

  private final String key;

  private final boolean required;

  private final Object defaultValue;

  Property(String key, boolean required, Object defaultValue) {
    this.key = key;
    this.required = required;
    this.defaultValue = defaultValue;
  }

  public String getKey() {
    return key;
  }

  public boolean isRequired() {
    return required;
  }

  public Object getDefaultValue() {
    return defaultValue;
  }

  @Override
  public String toString() {
    return "Property{" + "key='" + key + '\'' + ", required=" + required + ", defaultValue="
        + defaultValue + '}';
  }

  public static class Key {

    private static final String PREFIX = "br.com.eventhorizon.demo";

    public static final String VERSION = PREFIX + ".manifest.version";

    public static final String GIT_HASH = PREFIX + ".manifest.gitHash";

    public static final String BUILD = PREFIX + ".manifest.build";

    public static final String PROP1 = PREFIX + ".test.prop1";

    public static final String PROP2 = PREFIX + ".test.prop2";

    private Key() { }
  }
}
