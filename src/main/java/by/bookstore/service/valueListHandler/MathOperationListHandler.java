package by.bookstore.service.valueListHandler;

import java.util.ArrayList;
import java.util.List;

public class MathOperationListHandler<T> implements ValueListIterator<T> {
    private final List<T> elements;

    public MathOperationListHandler(List<T> elements) {
        this.elements = elements;
    }

    @Override
    public int getSize() {
        return elements.size();
    }

    @Override
    public List<T> getCurrentElement(int numCurrentPage, int numValuesPage) {
        List<T> list = new ArrayList<>();
        if (numCurrentPage <= 1) {
            for (int i = 0; i < numValuesPage; i ++){
                if (i < elements.size()){
                    list.add(elements.get(i));
                }
            }
        } else {
            int start = ((numCurrentPage - 1) * numValuesPage);
            for (int i = start; i < start + numValuesPage; i++) {
                if(i < elements.size()){
                    list.add(elements.get(i));
                }
            }
        }
        return list;
    }

    @Override
    public List<T> getPreviousElements(int numCurrentPage, int numValuesPage) {
        List<T> list = new ArrayList<>();
        if (numCurrentPage <= 1) {
            for (int i = 0; i < numValuesPage; i ++){
                if (i < elements.size()){
                    list.add(elements.get(i));
                }
            }
        } else {
            int end = ((numCurrentPage - 1) * numValuesPage);
            int start = end - numValuesPage;
            if(start >= 0 && end > 0){
                list = elements.subList(start, end);
            }
        }
        return list;
    }

    @Override
    public List<T> getNextElements(int numCurrentPage, int numValuesPage) {
        List<T> list = new ArrayList<>();
        if (numCurrentPage <= 1) {
            for (int i = 0; i < numValuesPage; i ++){
                if (i < elements.size()){
                    list.add(elements.get(i));
                }
            }
        } else {
            int startNext = ((numCurrentPage - 1) * numValuesPage) + numValuesPage;
            for (int i = startNext; i < startNext + numValuesPage; i++) {
                if(i < elements.size()){
                    list.add(elements.get(i));
                }
            }
        }
        return list;
    }
}
