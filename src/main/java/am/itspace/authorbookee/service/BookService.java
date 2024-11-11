package am.itspace.authorbookee.service;


import am.itspace.authorbookee.db.DbConnectionProvider;
import am.itspace.authorbookee.model.Author;
import am.itspace.authorbookee.model.Book;
import am.itspace.authorbookee.model.Gender;


import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookService {
    private final Connection connection = DbConnectionProvider.getInstance().getConnection();
    private AuthorService authorService = new AuthorService();

    public void add(Book book) {

        String sql = """
                INSERT INTO author(name,surname,phone,gender) 
                VALUES('%s','%f','%d','%d','%s')
                """.formatted(book.getTitle(), book.getPrice(), book.getQty(), book.getAuthor().getId());
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                book.setId(id);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public List<Book> getAllBooks() {
        List<Book> result = new ArrayList<>();
        String sql = "SELECT * FROM book";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setPrice(resultSet.getDouble("price"));
                book.setQty(resultSet.getInt("qty"));
                book.setAuthor(authorService.getAuthorById(resultSet.getInt("id")));
                result.add(book);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return result;



    }
}

