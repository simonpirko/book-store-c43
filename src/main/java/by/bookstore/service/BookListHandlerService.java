package by.bookstore.service;

import by.bookstore.entity.Book;
import by.bookstore.service.valueListHandler.BookListHandler;

import java.util.List;

public class BookListHandlerService {
    private int numPagesForResponse = 0;

    public List<Book> getCurrentPage(int numCurrentPage, int numValuesPage){
        BookListHandler<Book> booListHandler = new BookListHandler<>(new FacadeService().getBooks());
        numPagesForResponse = setNumPages(getSize(booListHandler), numValuesPage);
        return booListHandler.getCurrentElement(numCurrentPage, numValuesPage);
    }

    public int getNumPagesForResponse() {
        return numPagesForResponse;
    }

    private int getSize(BookListHandler<Book> bookListHandler){
        return bookListHandler.getSize();
    }

    private int setNumPages(int sizeList, int numValuesPage){
        if(sizeList % numValuesPage == 0){
            return sizeList / numValuesPage;
        }else{
            return sizeList / numValuesPage + 1;
        }
    }
}
