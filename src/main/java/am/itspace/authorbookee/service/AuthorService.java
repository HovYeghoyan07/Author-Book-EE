package am.itspace.authorbookee.service;


import am.itspace.authorbookee.db.DbConnectionProvider;
import am.itspace.authorbookee.model.Author;
import am.itspace.authorbookee.model.Gender;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class AuthorService {
    private Connection connection = DbConnectionProvider.getInstance().getConnection();

    public void add(Author author) {

        String sql = """
                INSERT INTO author(name,surname,phone,gender) 
                VALUES('%s','%s','%s','%s')
                """.formatted(author.getName(), author.getSurname(), author.getPhone(), author.getGender().name());
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                author.setId(id);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public List<Author> getAllAuthors() {
        List<Author> result = new ArrayList<>();
        String sql = "SELECT * FROM author";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt("id"));
                author.setName(resultSet.getString("name"));
                author.setSurname(resultSet.getString("surname"));
                author.setPhone(resultSet.getString("phone"));
                author.setGender(Gender.valueOf(resultSet.getString("gender")));
               result.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Author getAuthorById(int id) {
        try(Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM author WHERE id = "+id;
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt("id"));
                author.setName(resultSet.getString("name"));
                author.setSurname(resultSet.getString("surname"));
                author.setPhone(resultSet.getString("phone"));
                author.setGender(Gender.valueOf(resultSet.getString("gender")));
               return author;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
