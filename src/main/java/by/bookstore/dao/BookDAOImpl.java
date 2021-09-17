package by.bookstore.dao;

import by.bookstore.entity.Book;
import by.bookstore.entity.User;
import by.bookstore.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDAOImpl implements BookDAO {


    @Override
    public void saveBook(Book book) {
        try (Connection connection = MySQLConnection.getConnection()){
            connection.setAutoCommit(false);
            String query = " INSERT INTO books VALUES (NULL, ?, ?, ?, ?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setDouble(3, book.getRating());
            statement.setDouble(4, book.getPrice());
            statement.setBoolean(5, book.isReserved());
            statement.setLong(6, book.getUser().getId());
            statement.execute();
            connection.commit();
            connection.setAutoCommit(true);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteById(long id) {
        try (Connection connection = MySQLConnection.getConnection()){
            connection.setAutoCommit(false);
            String query = " DELETE FROM books WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.execute();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateBook(Book book) {
        try (Connection connection = MySQLConnection.getConnection()){
            connection.setAutoCommit(false);
            String query = " UPDATE books b SET b.name = ?, b.author = ?, b.rating = ?, b.price = ?, b.reserved = ?, b.user_id = ? " +
                    " WHERE b.id = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setDouble(3, book.getRating());
            statement.setDouble(4, book.getPrice());
            statement.setBoolean(5, book.isReserved());
            statement.setLong(6, book.getUser().getId());
            statement.execute();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateBookStatus(long id, boolean flag) {
        try (Connection connection = MySQLConnection.getConnection()){
            connection.setAutoCommit(false);
            String query = " UPDATE books b SET b.reserved = ? WHERE b.id = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, flag);
            statement.setLong(2, id);
            statement.execute();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateBookOwner(long id, long idUser) {
        try (Connection connection = MySQLConnection.getConnection()){
            connection.setAutoCommit(false);
            String query = " UPDATE books b SET b.user_id = ? WHERE b.id = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, idUser);
            statement.setLong(2, id);
            statement.execute();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean isExistById(long id) {
        try (Connection connection = MySQLConnection.getConnection()){
            connection.setAutoCommit(false);
            String query = " SELECT b.id FROM books b WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                long idResult = resultSet.getLong("id");
                if (idResult == id) {
                    return true;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean isExistByNameAuthor(String name, String author) {
        try (Connection connection = MySQLConnection.getConnection()){
            connection.setAutoCommit(false);
            String query = " SELECT b.name, b.author FROM books b WHERE b.name = ? AND b.author = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(1, author);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String bookName = resultSet.getString("name");
                String bookAuthor = resultSet.getString("author");
                if(name.equals(bookName) && author.equals(bookAuthor) ){
                    return true;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    @Override
    public Optional<Book> getBookById(long id) {
        Optional<Book> book = Optional.empty();
        try (Connection connection = MySQLConnection.getConnection()){
            connection.setAutoCommit(false);
            String query = " SELECT * FROM books b WHERE b.id = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                book = Optional.of(new Book(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getDouble("rating"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("reserved"),
                        new User(resultSet.getLong("user_id"))
                ));
            }
            connection.commit();
            connection.setAutoCommit(true);
            return  book;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }


    @Override
    public Optional<Book> getBookByNameAuthor(String name, String author) {
        Optional<Book> book = Optional.empty();
        try (Connection connection = MySQLConnection.getConnection()){
            connection.setAutoCommit(false);
            String query = " SELECT * FROM books b WHERE b.name = ? AND b.author = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, author);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                book = Optional.of(new Book(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getDouble("rating"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("reserved"),
                        new User(resultSet.getLong("user_id"))
                ));
            }
            connection.commit();
            connection.setAutoCommit(true);
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }


    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()){
            connection.setAutoCommit(false);
            String query = " SELECT * FROM books ";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                books.add(new Book(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getDouble("rating"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("reserved"),
                        new User(resultSet.getLong("user_id"))
                ));
            }
            connection.commit();
            connection.setAutoCommit(true);
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }



    @Override
    public List<Book> getBooksByUser(User user) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()){
            connection.setAutoCommit(false);
            String query = " SELECT * FROM books b WHERE b.user_id = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                books.add(new Book(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getDouble("rating"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("reserved"),
                        new User(resultSet.getLong("user_id"))
                ));
            }
            connection.commit();
            connection.setAutoCommit(true);
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> getReservedBookByUser(long userId, boolean flag) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()){
            connection.setAutoCommit(false);
            String query = " SELECT * FROM books b WHERE b.reserved = ? AND b.user_id = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, flag);
            statement.setLong(2, userId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                books.add(new Book(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getDouble("rating"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("reserved"),
                        new User(resultSet.getLong("user_id"))
                ));
            }
            connection.commit();
            connection.setAutoCommit(true);
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

}
