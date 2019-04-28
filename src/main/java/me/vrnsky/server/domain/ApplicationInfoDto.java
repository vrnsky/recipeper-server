package me.vrnsky.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationInfoDto {
    private String commitId;
    private String version;
    private String buildTime;
}