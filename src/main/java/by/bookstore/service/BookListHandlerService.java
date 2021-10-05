package by.bookstore.service;

import by.bookstore.entity.Book;
import by.bookstore.service.valueListHandler.ValueListHandler;

import java.sql.Connection;
import java.util.List;

public class BookListHandlerService {
    private int numPagesForResponse = 0;

    public List<Book> getCurrentPage(int numCurrentPage, int numValuesPage, Connection connection){
        ValueListHandler<Book> booListHandler = new ValueListHandler<>(new FacadeService().getBooks(connection));
        numPagesForResponse = setNumPages(getSize(booListHandler), numValuesPage);
        return booListHandler.getCurrentElement(numCurrentPage, numValuesPage);
    }

    public int getNumPagesForResponse() {
        return numPagesForResponse;
    }

    private int getSize(ValueListHandler<Book> valueListHandler){
        return valueListHandler.getSize();
    }

    private int setNumPages(int sizeList, int numValuesPage){
        if(sizeList % numValuesPage == 0){
            return sizeList / numValuesPage;
        }else{
            return sizeList / numValuesPage + 1;
        }
    }
}
