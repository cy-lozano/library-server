package com.grow.library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grow.library.LibraryServerApplication;
import com.grow.library.model.Book;
import com.grow.library.search.SearchType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = LibraryServerApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookService bookService;

    private static final String BOOK_ENDPOINT = "/books";

    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(get(BOOK_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(100))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].isbn").value("ISBN1"));
    }

    @Test
    public void testGetBookOne() throws Exception {
        mockMvc.perform(get(String.format("%s/%s", BOOK_ENDPOINT, 1L)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.isbn").value("ISBN1"));
    }

    @Test
    public void testSearchBookByISBN() throws Exception {
        Map<SearchType, String> criteria = new HashMap<>();
        criteria.put(SearchType.ISBN, "ISBN1");

        mockMvc.perform(post("/books/search").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(criteria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].isbn").value("ISBN1"));
    }

    @Test
    public void testSearchBookByTitle() throws Exception {
        String title = UUID.randomUUID().toString();
        createBookWithTitle(title);
        Map<SearchType, String> criteria = new HashMap<>();
        criteria.put(SearchType.TITLE, title);

        mockMvc.perform(post("/books/search").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(criteria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value(title));
    }

    @Test
    public void testSearchBookByMultipleCriteria() throws Exception {
        String uuid = UUID.randomUUID().toString();
        createBookWithIsbnAndTitle(uuid);
        Map<SearchType, String> criteria = new HashMap<>();
        criteria.put(SearchType.ISBN, uuid);
        criteria.put(SearchType.TITLE, uuid);

        mockMvc.perform(post("/books/search").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(criteria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(uuid))
                .andExpect(jsonPath("$[0].isbn").value(uuid));
    }

    private void createBookWithIsbnAndTitle(String uuid) throws Exception {
        Book book = new Book();
        book.setTitle(uuid);
        book.setIsbn(uuid);

        mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());
    }

    private void createBookWithTitle(String title) throws Exception {
        Book book = new Book();
        book.setTitle(title);

        mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());
    }
}
