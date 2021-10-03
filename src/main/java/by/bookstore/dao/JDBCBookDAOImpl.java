package by.bookstore.dao;

import by.bookstore.entity.Book;
import by.bookstore.entity.TypeOfUser;
import by.bookstore.entity.User;
import by.bookstore.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCBookDAOImpl implements BookDAO {

    private static final String SAVE = " INSERT INTO books(name, author, rating, price, reserved, user_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE = " DELETE FROM books b ";
    private static final String UPDATE_BOOK = " UPDATE books b SET b.name = ?, b.author = ?, b.rating = ?, b.price = ?, b.reserved = ?, b.user_id = ? ";
    private static final String BY_ID = " WHERE b.id = ? ";
    private static final String UPDATE_BOOK_RESERVED = " UPDATE books b SET b.reserved = ? ";
    private static final String UPDATE_BOOK_OWNER = " UPDATE books b SET b.user_id = ? ";
    private static final String IS_EXIST_BY_ID = " SELECT b.id FROM books b ";
    private static final String BY_AUTHOR = " WHERE b.name = ? AND b.author = ?";
    private static final String IS_EXIST_BY_AUTHOR = " SELECT b.name, b.author FROM books b ";
    private static final String GET_BOOK = " SELECT b.id, b.name AS book_name, b.author, b.rating, b.price, b.reserved, b.user_id, " +
            " u.name, u.picture, u.typeOfUser FROM books b LEFT JOIN users u on u.id = b.user_id ";
    private static final String GET_BOOKS_BY_USER = " SELECT b.id, b.name AS book_name, b.author, b.rating, b.price, b.reserved, b.user_id, " +
            " u.name, u.picture, u.typeOfUser FROM books b LEFT JOIN users u on u.id = b.user_id WHERE b.user_id = ?";
    private static final String GET_RESERVED_BOOKS_BY_USER = " SELECT b.id, b.name AS book_name, b.author, b.rating, b.price, b.reserved, b.user_id," +
            " u.name, u.picture, u.typeOfUser FROM books b LEFT JOIN users u on u.id = b.user_id WHERE b.reserved = ? AND b.user_id = ? ";
    private static final String UPDATE_RATING = " UPDATE books b SET b.rating = ? ";

    @Override
    public boolean saveBook(Book book) {
        try (Connection connection = MySQLConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SAVE);
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setDouble(3, book.getRating());
            statement.setDouble(4, book.getPrice());
            statement.setBoolean(5, book.isReserved());
            statement.setLong(6, book.getUser().getId());
            statement.execute();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        try (Connection connection = MySQLConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(DELETE + BY_ID );
            statement.setLong(1, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBook(Book book) {
        try (Connection connection = MySQLConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK + BY_ID );
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setDouble(3, book.getRating());
            statement.setDouble(4, book.getPrice());
            statement.setBoolean(5, book.isReserved());
            statement.setLong(6, book.getUser().getId());
            statement.setLong(7, book.getId());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBookReservedStatus(long bookId, boolean flag) {
        try (Connection connection = MySQLConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK_RESERVED + BY_ID);
            statement.setBoolean(1, flag);
            statement.setLong(2, bookId);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBookOwner(long bookId, long idUser) {
        try (Connection connection = MySQLConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK_OWNER + BY_ID);
            statement.setLong(1, idUser);
            statement.setLong(2, bookId);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExistById(long id) {
        try (Connection connection = MySQLConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(IS_EXIST_BY_ID + BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExistByNameAuthor(String name, String author) {
        try (Connection connection = MySQLConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(IS_EXIST_BY_AUTHOR + BY_AUTHOR);
            statement.setString(1, name);
            statement.setString(2, author);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Book getBookById(long id) {
        Book book = new Book();
        try (Connection connection = MySQLConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(GET_BOOK + BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            book = getBookFromResult(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public Book getBookByNameAuthor(String name, String author) {
           Book book = new Book();
        try (Connection connection = MySQLConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(GET_BOOK + BY_AUTHOR);
            statement.setString(1, name);
            statement.setString(2, author);
            ResultSet resultSet = statement.executeQuery();
            book =  getBookFromResult(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(GET_BOOK);
            ResultSet resultSet = statement.executeQuery();
            books = getBooksFromResult(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> getBooksByUser(long userId) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(GET_BOOKS_BY_USER);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            books = getBooksFromResult(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> getReservedBookByUser(long userId, boolean flag) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(GET_RESERVED_BOOKS_BY_USER);
            statement.setBoolean(1, flag);
            statement.setLong(2, userId);
            ResultSet resultSet = statement.executeQuery();
            books = getBooksFromResult(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean updateRating(long id, double rating) {
        try (Connection connection = MySQLConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(UPDATE_RATING + BY_ID);
            statement.setDouble(1, rating);
            statement.setLong(2, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Book getBookFromResult(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        while (resultSet.next()) {
            book = new Book(
                    resultSet.getLong("id"),
                    resultSet.getString("book_name"),
                    resultSet.getString("author"),
                    resultSet.getDouble("rating"),
                    resultSet.getDouble("price"),
                    resultSet.getBoolean("reserved"),
                    new User(resultSet.getLong("user_id"),
                            resultSet.getString("name"),
                            resultSet.getString("picture"),
                            TypeOfUser.valueOf(resultSet.getString("typeOfUser")))
            );
        }
        return book;
    }

    private List<Book> getBooksFromResult(ResultSet resultSet) throws SQLException {
        List<Book> books = new ArrayList<>();
        while(resultSet.next()) {
            books.add(new Book(
                    resultSet.getLong("id"),
                    resultSet.getString("book_name"),
                    resultSet.getString("author"),
                    resultSet.getDouble("rating"),
                    resultSet.getDouble("price"),
                    resultSet.getBoolean("reserved"),
                    new User(resultSet.getLong("user_id"),
                            resultSet.getString("name"),
                            resultSet.getString("picture"),
                            TypeOfUser.valueOf(resultSet.getString("typeOfUser")))
            ));
        }
        return books;
    }
}
