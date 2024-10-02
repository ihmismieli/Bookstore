package projekti.bookstoreproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindErnesthemingway() throws Exception {
        this.mockMvc.perform(get("/books")
                .with(user("user").password("user")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ernest Hemingway")));
    }

    @Test
    public void testFindFictionBooks() throws Exception{
        this.mockMvc.perform(get("/books")
                    .with(user("user").password("user")))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Fiction")));
    }

    @Test
    public void testGetAllBooks() throws Exception {
        this.mockMvc.perform(get("/books")
                .with(user("user").password("password")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetBookById() throws Exception {
        Long bookId = 1L;

        System.out.println("Testing with bookId: " + bookId);

        this.mockMvc.perform(get("/book/{id}", bookId)
                .with(user("user").password("user")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(bookId));
    }

}
