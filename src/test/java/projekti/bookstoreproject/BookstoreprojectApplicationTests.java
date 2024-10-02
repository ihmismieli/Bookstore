package projekti.bookstoreproject;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import projekti.bookstoreproject.web.BookController;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class BookstoreprojectApplicationTests {

	@Autowired
	private BookController controller;

	//tests if controller is injected right
	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();

}
}
