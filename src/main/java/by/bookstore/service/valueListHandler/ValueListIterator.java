package by.bookstore.service.valueListHandler;

import by.bookstore.entity.Book;

import java.util.List;

public interface ValueListIterator<T> {

    int getSize();

    List<T> getCurrentElement(int numCurrentPage, int numValuesPage);

    List<T> getPreviousElements(int numCurrentPage, int numValuesPage);

    List<T> getNextElements(int numCurrentPage, int numValuesPage);

}
