package timecounter.hard;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] values;
    private int size;
    private int capacity;


    /**
     * Конструктор по умолчанию
     */
    public MyArrayList() {
        this.capacity = 10;
        this.values = (E[]) new Object[capacity];
    }

    /**
     * Конструктор с заданой вместимостью
     */
    MyArrayList(int capacity) {
        this.capacity = capacity;
        this.values = (E[]) new Object[capacity];
    }

    /**
     * Метод, который возвращает размер списка
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод, который проверяет являеться ли список пустым
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Метод, который проверяет есть ли в списке такой обьект как переданый
     */
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод, который возвращает масив типа Object[] созданый
     * из списка
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int counter = 0;
        for(E elem : this){
            array[counter] = elem;
            counter++;
        }
        return array;
    }

    /**
     * Метод, который возвращает масив созданый из списка
     */
    @Override
    public <E> E[] toArray(E[] a) {
        int counter = 0;
        ArrayListIterator iterator = new ArrayListIterator();
        while(iterator.hasNext()){
            iterator.next();
            a[counter] = (E)iterator.get();
            counter++;
        }
        return a;
    }

    /**
     * Метод, который добавляет обьект в конец списка
     */
    @Override
    public boolean add(E o) {
        if (o == null) {
            return true;
        }
        makeCapacityBigger();
        values[size] = o;
        size++;
        return true;
    }

    /**
     * Метод, который удаляет первое вхождения обькта о в список если
     * такое есть
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return true;
        }
        int removedElementIndex;
        boolean isFound = false;
        for (int i = 0; i < size; i++) {
            if (values[i].equals(o)) {
                isFound = true;
                removedElementIndex = i;
                for (int j = removedElementIndex; j < size - 1; j++) {
                    values[j] = values[j + 1];
                }
                break;
            }
        }
        if (isFound) {
            values[size - 1] = null;
            size--;
        }
        //makeCapacitySmaller();
        return true;
    }



    /**
     * Метод, который удаляет все элементы из списка
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            values[i] = null;
        }
        size = 0;
    }

    /**
     * Метод, который возвращает элемент списка по индексу
     */
    @Override
    public E get(int index) {
        if (index < size && index >= 0) {
            return values[index];
        }
        return null;
    }

    /**
     * Метод, который заменяет элемент списка по индексу на новый
     */
    @Override
    public E set(int index, E element) {
        if (index < size && index >= 0) {
            values[index] = element;
            return values[index];
        }
        return null;
    }

    /**
     * Метод, который добавляет обьект в список по заданому индексу
     */
    @Override
    public void add(int index, E element) {
        if (index <= size && index >= 0) {
            makeCapacityBigger();
            for (int i = size; i > index; i--) {
                values[i] = values[i - 1];
            }
            values[index] = element;
            size++;
        }
    }

    /**
     * Метод, который удаляет элемент списка по индексу
     */
    @Override
    public E remove(int index) {
        if (index < size && index >= 0) {
            for (int i = index; i < size; i++) {
                if (i == size - 1) {
                    values[i] = null;
                    size--;
                    return values[i];
                }
                values[i] = values[i + 1];
            }
        }
        //makeCapacitySmaller();
        return null;
    }

    /**
     * Метод, который находит индекс первого вхождения данного элемента
     * в список
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Метод, который находит индекс последнего вхождения данного
     * элемента в список
     */
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i > 0; i--) {
            if (values[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Метод, который удаляет из списка все элементы, значения которых равны любому
     * из элементов переданой колекции
     */
    @Override
    public boolean removeAll(Collection c) {
        if (c == null) {
            return false;
        }
        for (Object object : c) {
            while (contains(object)) {
                remove(object);
            }
        }
        return true;
    }


    /**
     * Метод, который проверяет входят ли все элементы передвной
     * колекции в список
     */
    @Override
    public boolean containsAll(Collection c) {
        if (c == null) {
            return false;
        }
        for (Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Метод, который добавляет в конец списка все элементы переданой
     * колекции
     */
    @Override
    public boolean addAll(Collection c) {
        return addAll(size, c);
    }


    /**
     * Метод, который вставляет в список все элементы переданой
     * колекции. Вставляет начиная с номера єлемента index
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c == null) {
            return false;
        }
        for (E element : c) {
            add(index, element);
            index++;
        }
        return true;
    }


    /** Дополнительный метод
     * Метод для разширения списка
     */
    private void makeCapacityBigger() {
        if (capacity == size + 1) {
            capacity = (int) (capacity * 1.5);
            E[] newValues = (E[]) new Object[capacity];
            copy(values, newValues, 0, size);
            values = newValues;
            //System.out.println("BIGGER");
        }
    }


    /**
     * Дополнительный метод
     * Метод для сужения списка
     */
    /*private void makeCapacitySmaller() {
        if (size < capacity / 3) {
            capacity = (int) (capacity * 0.5);
            E[] newValues = (E[]) new Object[capacity];
            copy(values, newValues, 0, size);
            values = newValues;
            //System.out.println("SMALLER");
        }
    }*/

    /** Дополнительный метод
     * Метод для копирования списка
     */
    private void copy(E[] arrayWhichCopy, E[] arrayWhereCopy, int from, int to) {
        for (int i = from, j = 0; i < to; i++, j++) {
            arrayWhereCopy[i] = arrayWhichCopy[j];
        }
    }


    /**
     * Метод, который возвращает итератор
     */
    @Override
    public Iterator iterator() {
        return listIterator();
    }

    /**
     * Метод, который возвращает итератор ArrayListIterator для MyArrayList,
     */
    @Override
    public ListIterator listIterator() {
        return new ArrayListIterator();
    }


    /**
     * Метод, который возвращает итератор ArrayListIterator(index) для MyArrayList,
     * Итерация по элементам будет начинаться с элемента с индексом index
     */
    @Override
    public ListIterator listIterator(int index) {
        return new ArrayListIterator(index);
    }


    /**
     * Метод, который возвращает список, состоящий из элементов нашего списка
     * с промежутка [fromIndex, toIndex]
     */
    @Override
    public List subList(int fromIndex, int toIndex) {
        if(fromIndex < 0 || toIndex > size - 1 || fromIndex > toIndex){
            return null;
        }
        List<E> sublist = new MyArrayList<>();
        for(int i = fromIndex; i <= toIndex; i++){
            sublist.add(values[i]);
        }
        return sublist;
    }


    //not used
    @Override
    public boolean retainAll(Collection c) {
        if (c == null){
            return false;
        }
        E elem = (E)new Object();
        for (int i = 0; i < size; i++) {
            if(!c.contains(get(i))){
                set(i, elem);
            }
        }
        while(contains(elem)){
            remove(elem);
        }
        return true;
    }


    /**Метод для вывода списка*/
    @Override
    public String toString() {
        String res = "[";
        int counter = 0;
        for(E elem : this){
            counter++;
            if(counter == size){
                res += elem;
            }
            else {
                res += elem + ", ";
            }
        }
        res += "]";
        return res;
    }


    /**
     * Клас итератора для MyArrayList
     */
    private class ArrayListIterator implements ListIterator<E> {
        private int size = MyArrayList.this.size;
        private E[] values = MyArrayList.this.values;
        private int current_index;


        /** Конструктор для обычного итератора*/
        ArrayListIterator(){
            current_index = -1;
        }

        /** Конструктор для итератора с индексом*/
        ArrayListIterator(int index){
            current_index = index - 1;
        }


        /**
         * Метод для получения значения элемента во время итерации по списку
         * по порядку
         */
        public E get(){
            return values[current_index];
        }


        /**
         * Метод для изменения значения элемента во время итерации по списку
         * по порядку
         */
        @Override
        public void set(E elem) {
            values[current_index] = elem;
        }

        @Override
        public E next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            current_index++;
            E elem = values[current_index];
            return elem;
        }

        @Override
        public boolean hasNext() {
            return current_index < size - 1 ;
        }

        @Override
        public boolean hasPrevious() {
            return current_index >= 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) { throw new NoSuchElementException(); }
            E elem = values[current_index];
            current_index--;
            return elem;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }


        @Override
        public void add(E e) {

        }
    }
}
