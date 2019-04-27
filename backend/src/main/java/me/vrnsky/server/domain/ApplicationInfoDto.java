package me.vrnsky.server.domain;

public class ApplicationInfoDto {
    private String commitId;
    private String version;
    private String buildTime;

    public ApplicationInfoDto(String commitId, String version, String buildTime) {
        this.commitId = commitId;
        this.version = version;
        this.buildTime = buildTime;
    }
    public String getCommitId() {
        return commitId;
    }

    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }
}
