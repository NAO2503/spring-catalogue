package com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.catalogue.cleanarchitecture.domain.model.Price;
import com.catalogue.cleanarchitecture.domain.usecase.GetPriceUseCase;
import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.dto.PriceRequestDto;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Date;

@AutoConfigureMockMvc
@SpringBootTest
class CatalogueRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GetPriceUseCase getPriceUseCase;

    @Test
    void returnGetPriceDataOK() throws Exception {
        // given
        String requestJson = "";

        // when
        mockGetPriceUseCaseEmpty(false);

        // then
        MvcResult mvcResult = mvc
                .perform(get("/api/catalogue/price/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(response);
    }

    @Test
    void returnGetPriceDataEmpty() throws Exception {
        // given
        String requestJson = "";

        // when
        mockGetPriceUseCaseEmpty(false);

        // then
        MvcResult mvcResult = mvc
                .perform(get("/api/catalogue/price/8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(response);
    }

    @Test
    void returnGetPriceDataNotFound() throws Exception {
        // given
        String requestJson = "";

        // when
        mockGetPriceUseCaseEmpty(false);

        // then
        MvcResult mvcResult = mvc
                .perform(get("/api/catalogue/price/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isNotFound())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(response);
    }

    @Test
    void returnPriceDataNull() throws Exception {
        // given
        PriceRequestDto requestDto = new PriceRequestDto("2020-06-14 10:00:00", 2L, 2L);
        String requestJson = requestToJson(requestDto);

        // when
        mockGetPriceUseCaseEmpty(false);

        // then
        MvcResult mvcResult = mvc
                .perform(post("/api/catalogue/price/findByBrandProductBetweenDate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(response);
    }

    @Test
    void returnListPricesDataNull() throws Exception {
        // given
        PriceRequestDto requestDto = new PriceRequestDto("2020-06-14 10:00:00", 2L, 2L);
        String requestJson = requestToJson(requestDto);

        // when
        mockGetPriceUseCaseEmpty(true);

        // then
        MvcResult mvcResult = mvc
                .perform(post("/api/catalogue/price/listAllByBrandProductBetweenDate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(response);
    }

    @Test
    void returnEmptyBadRequest() throws Exception {
        // given
        String requestDto = "";

        // when
        mockGetPriceUseCase(false);

        // then
        MvcResult mvcResult = mvc
                .perform(post("/api/catalogue/price/findByBrandProductBetweenDate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestDto))
                .andExpect(status().isBadRequest())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals("", response);
    }

    @Test
    void returnMessageBadRequest() throws Exception {
        // given
        PriceRequestDto requestDto = new PriceRequestDto("", 1L, 1L);
        String requestJson = requestToJson(requestDto);

        // when
        mockGetPriceUseCase(false);

        // then
        MvcResult mvcResult = mvc
                .perform(post("/api/catalogue/price/findByBrandProductBetweenDate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(response);
    }

    @Test
    void returnPriceDataAsJsonObject() throws Exception {
        // given
        PriceRequestDto requestDto = new PriceRequestDto("2020-06-14 10:00:00", 1L, 1L);
        String requestJson = requestToJson(requestDto);

        // when
        mockGetPriceUseCase(false);

        // then
        MvcResult mvcResult = mvc
                .perform(post("/api/catalogue/price/findByBrandProductBetweenDate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(response);
    }

    @Test
    void returnListPricesDataAsJsonObject() throws Exception {
        // given
        PriceRequestDto requestDto = new PriceRequestDto("2020-06-14 10:00:00", 1L, 1L);
        String requestJson = requestToJson(requestDto);

        // when
        mockGetPriceUseCase(true);

        // then
        MvcResult mvcResult = mvc
                .perform(post("/api/catalogue/price/listAllByBrandProductBetweenDate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(response);
    }

    private String requestToJson(PriceRequestDto requestDto) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(requestDto);
    }

    private void mockGetPriceUseCase(boolean isList) {
        Price price1 = new Price(1L, new Date(), new Date(), 1L, 1L, null, 35.5, null);
        Price price2 = new Price(1L, new Date(), new Date(), 2L, 1L, null, 25.5, null);
        when(getPriceUseCase.findByBrandProductBetweenDate(1L, 1L, "2020-06-14 10:00:00")).thenReturn(price1);
        if (!isList) {
            when(getPriceUseCase.listAllByBrandProductBetweenDate(1L, 1L, "2020-06-14 10:00:00")).thenReturn(asList(price1, price2));
        }
    }

    private void mockGetPriceUseCaseEmpty(boolean isList) {
        when(getPriceUseCase.findByBrandProductBetweenDate(1L, 1L, "2020-06-14 10:00:00")).thenReturn(null);
        if (isList) {
            when(getPriceUseCase.listAllByBrandProductBetweenDate(1L, 1L, "2020-06-14 10:00:00")).thenReturn(new ArrayList<>());
        }
    }
}
