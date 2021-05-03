package me.vrnsky.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.vrnsky.server.DatabaseTest;
import me.vrnsky.server.controllers.dto.recipe.CreateResponse;
import me.vrnsky.server.domain.Product;
import me.vrnsky.server.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeControllerTest extends DatabaseTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(((request, response, chain) -> {
                    response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                    chain.doFilter(request, response);
                })).build();
    }

    @Test
    public void whenTryToCreateRecipeShouldCheckThatDataSavedProperly() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setTitle("Мой первый рецепт");
        recipe.setDescription("Мой первый рецепт");
        Set<Product> products = new HashSet<>();
        Product banana = new Product("Бананы", "Бананы из Эквадора");
        Product oranges = new Product("Апельсины", "Апельсины из Марокко");
        products.add(banana);
        products.add(oranges);
        recipe.setProducts(products);
        mockMvc.perform(post("/recipe/create")
                .content(OBJECT_MAPPER.writeValueAsString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0))
                .andExpect(jsonPath("$.errorMessage").value("Успешно"))
                .andExpect(jsonPath("$.recipe.title").value("Мой первый рецепт"))
                .andExpect(jsonPath("$.recipe.description").value("Мой первый рецепт"));
    }

    @Test
    public void whenTryToCreateRecipeWithoutNameOrDescriptionShouldBeRejected() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setTitle("");
        recipe.setDescription("");
        Set<Product> products = new HashSet<>();
        Product banana = new Product("Бананы", "Бананы из Эквадора");
        Product oranges = new Product("Апельсины", "Апельсины из Марокко");
        products.add(banana);
        products.add(oranges);
        recipe.setProducts(products);
        mockMvc.perform(post("/recipe/create")
                .content(OBJECT_MAPPER.writeValueAsString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name()))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenTryToUpdateRecipeShouldCheckThatReceiptWasUpdateProperly() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setTitle("Мой первый рецепт");
        recipe.setDescription("Мой первый рецепт");
        MvcResult mvcResult = mockMvc.perform(post("/recipe/create")
                .content(OBJECT_MAPPER.writeValueAsString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0))
                .andExpect(jsonPath("$.errorMessage").value("Успешно"))
                .andExpect(jsonPath("$.recipe.title").value("Мой первый рецепт"))
                .andExpect(jsonPath("$.recipe.description").value("Мой первый рецепт"))
                .andReturn();

        String rawResponse = mvcResult.getResponse().getContentAsString();
        CreateResponse createResponse = OBJECT_MAPPER.readValue(rawResponse, CreateResponse.class);

        recipe.setId(createResponse.getRecipe().getId());
        recipe.setTitle("Мой обновленный рецепт");
        recipe.setDescription("Мой обновленный рецепт");
        mockMvc.perform(put("/recipe/update")
                .content(OBJECT_MAPPER.writeValueAsString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0))
                .andExpect(jsonPath("$.errorMessage").value("Успешно"))
                .andExpect(jsonPath("$.recipe.title").value("Мой обновленный рецепт"))
                .andExpect(jsonPath("$.recipe.description").value("Мой обновленный рецепт"));
    }

    @Test
    public void whenTryToGetListOfRecipesShouldCheckThatAllIsOk() throws Exception {
        mockMvc.perform(get("/recipe/list")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0))
                .andExpect(jsonPath("$.errorMessage").value("Успешно"));
    }

    @Test
    public void whenTryToGetReceiptByIdShouldCheckThatIsRightRecipe() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setTitle("Мой первый рецепт");
        recipe.setDescription("Мой первый рецепт");
        MvcResult mvcResult = mockMvc.perform(post("/recipe/create")
                .content(OBJECT_MAPPER.writeValueAsString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0))
                .andExpect(jsonPath("$.errorMessage").value("Успешно"))
                .andExpect(jsonPath("$.recipe.title").value("Мой первый рецепт"))
                .andExpect(jsonPath("$.recipe.description").value("Мой первый рецепт"))
                .andReturn();

        String rawResponse = mvcResult.getResponse().getContentAsString();
        CreateResponse createResponse = OBJECT_MAPPER.readValue(rawResponse, CreateResponse.class);

        mockMvc.perform(get("/recipe/" + createResponse.getRecipe().getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0))
                .andExpect(jsonPath("$.errorMessage").value("Успешно"))
                .andExpect(jsonPath("$.recipe.title").value("Мой первый рецепт"))
                .andExpect(jsonPath("$.recipe.description").value("Мой первый рецепт"));
    }
}