package br.com.eventhorizon.demo.web.dto;

public class Health {

  private HealthStatus status;

  private String version;

  private String gitHash;

  private String build;

  public Health(HealthStatus status) {
    this.status = status;
  }

  public HealthStatus getStatus() {
    return status;
  }

  public void setStatus(HealthStatus status) {
    this.status = status;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getGitHash() {
    return gitHash;
  }

  public void setGitHash(String gitHash) {
    this.gitHash = gitHash;
  }

  public String getBuild() {
    return build;
  }

  public void setBuild(String build) {
    this.build = build;
  }
}
