package me.vrnsky.server.controllers;

import me.vrnsky.server.domain.ApplicationInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RestController
@PropertySource(value = "classpath:git.properties")
public class EchoController {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy");
    @Autowired
    private BuildProperties buildProperties;
    @Value("${git.commit.id.abbrev}")
    private String commitId;

    @RequestMapping(value = "version", method = RequestMethod.GET)
    public ApplicationInfoDto getVersion() {
        return new ApplicationInfoDto(
                commitId,
                buildProperties.getVersion(),
                DATE_TIME_FORMATTER.format(buildProperties.getTime()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime())
        );
    }
}
