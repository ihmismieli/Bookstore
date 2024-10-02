package projekti.bookstoreproject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import projekti.bookstoreproject.domain.Book;
import projekti.bookstoreproject.domain.BookRepository;
import projekti.bookstoreproject.domain.Category;
import projekti.bookstoreproject.domain.CategoryRepository;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository cRepository;

    @Test
    public void findBookByAuthor() {
        List<Book> books = repository.findByAuthor("Ernest Hemingway");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualToIgnoringCase("Ernest Hemingway");
    }

    @Test
    public void createNewBook() {
        Category category = new Category("Science");
        cRepository.save(category);
        Book book = new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "978-0-7475-3274-2", 1997, 19.99,
                category);
        repository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteNewBook() {
        List<Book> books = repository.findByAuthor("Ernest Hemingway");
        Book book = books.get(0);
        repository.delete(book);
        List<Book> newBooks = repository.findByAuthor("Ernest Hemingway");
        assertThat(newBooks).hasSize(0);

    }
}
