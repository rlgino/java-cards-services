package com.rlgino.CardsService.acceptance;

import com.rlgino.CardsService.domain.CardMother;
import com.rlgino.CardsService.domain.CardNumber;
import com.rlgino.CardsService.infrastructure.PostgresContainerTest;
import com.rlgino.CardsService.CardsServiceApplication;
import com.rlgino.CardsService.domain.CardNumberMother;
import com.rlgino.CardsService.domain.CardRepository;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CardsServiceApplication.class})
@WebAppConfiguration
public class AcceptanceTest extends PostgresContainerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private CardRepository cardRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("cardController"));
    }

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Test
    public void givenCardPostURI_whenMockMVC_thenVerifyResponse() throws Exception {
        String bodyRequest = "{\n" +
                "    \"cardNumber\": \"1324123412341234\",\n" +
                "    \"brand\": \"VISA\",\n" +
                "    \"name\": \"Gino\",\n" +
                "    \"lastName\": \"Luraschi\",\n" +
                "    \"date\": \"10/2025\"\n" +
                "}";
        this.mockMvc.perform(post("/card").contentType(APPLICATION_JSON_UTF8).content(bodyRequest))
                .andDo(print()).andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void givenCardGetURI_whenMockMVC_thenVerifyNotContentResult() throws Exception {
        this.mockMvc.perform(get("/card/{id}", "1111222233334444"))
                .andDo(print()).andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    public void givenCardGetURI_whenMockMVC_thenVerifyResponse() throws Exception {
        cardRepository.SaveCard(CardMother.createCardRandom());

        CardNumber random = CardNumberMother.random();
        MvcResult mvcResult = this.mockMvc.perform(get("/card/{id}", random.toString()))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        String bodyExpected = "{\"cardNumber\":\"1234123412341234\",\"brand\":\"visa\",\"name\":\"test test\",\"lastName\":\"\",\"date\":\"02/2025\"}";
        assertEquals("application/json",
                mvcResult.getResponse().getContentType());
        assertEquals(bodyExpected, mvcResult.getResponse().getContentAsString());
    }
}
