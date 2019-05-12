package me.vrnsky.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.vrnsky.server.dto.ResponseStatus;
import me.vrnsky.server.dto.registration.RegistrationRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenTryRegisterUserShouldCheckThatUserWasRegistered() throws Exception {
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("vrnsky@vrnsky.com");
        request.setUsername("vrnsky");
        request.setPassword("hashPassword");

        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(request)))
                .andExpect(jsonPath("$.status").value(is(ResponseStatus.SUCCESS.name())))
                .andExpect(jsonPath("$.message").value(is("")))
                .andDo(print());
    }

    @Test
    public void whenEmailIsInvalidShouldCheckThatRegisterFailed() throws Exception {
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("@invalid.com@mails.re");
        request.setUsername("vrnsky");
        request.setPassword("hashPassword");

        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(request)))
                .andExpect(jsonPath("$.status").value(is(ResponseStatus.FAIL.name())))
                .andExpect(jsonPath("$.message").value(is("Email address must be valid")))
                .andDo(print());
    }

    @Test
    public void whenUsernameIsInvalidShouldCheckThatRegisterFailed() throws Exception {
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("egor@changeset.com");
        request.setUsername("e");
        request.setPassword("hashPassword");

        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(request)))
                .andExpect(jsonPath("$.status").value(is(ResponseStatus.FAIL.name())))
                .andExpect(jsonPath("$.message").value(is("Username must contains from 6 characters to 20 characters")))
                .andDo(print());
    }

    @Test
    public void whenPasswordIsInvalidShouldCheckThatRegisterIsFailed() throws Exception {
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("egor@changeset.com");
        request.setUsername("vrnsky");
        request.setPassword("");

        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(request)))
                .andExpect(jsonPath("$.status").value(is(ResponseStatus.FAIL.name())))
                .andExpect(jsonPath("$.message").value(is("Password must contains between 6 and 20 characters")))
                .andDo(print());
    }
}