package timecounter.hard;

import java.util.*;

public class MyLinkedList<E> implements List<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;


    public MyLinkedList(){
        this.first = null;
        this.last = null;
        this.size = 0;
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
    public boolean contains(Object element) {
        for(E elem : this){
            if(elem.equals(element)){
                return true;
            }
        }
        return false;
    }

    /**
     * Метод, который возвращает итератор
     */
    @Override
    public Iterator<E> iterator(){
        return listIterator();
    }


    /**
     * Метод, который возвращает масив типа Object[] созданый из списка
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
     * Метод, который возвращает масив типа Object созданый из списка
     */
    @Override
    public <E> E[] toArray(E[] a) {
        int counter = 0;
        LinkedListIterator iterator = new LinkedListIterator();
        while(iterator.hasNext()){
            a[counter] = (E)iterator.get();
            iterator.next();
            counter++;
        }
        return a;
    }

    /**
     * Метод, который добавляет обьект в конец списка
     */
    @Override
    public boolean add(E elem) {
        add(size, elem);
        return true;
    }


    /**
     * Метод, который удаляет первое вхождения обькта elem в список если
     * такой есть
     */
    @Override
    public boolean remove(Object elem) {
        if(first == null || elem == null){
            return false;
        }
        if(size == 1){
            first = null;
            last = null;
            return true;
        }
        int index = indexOf(elem);
        remove(index);
        return true;
    }


    /**
     * Метод, который проверяет входят ли все элементы передвной
     * колекции в список
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        if(c == null){
            return false;
        }
        for(Object object : c){
            if(!contains(object)){
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
    public boolean addAll(Collection<? extends E> c) {
        if (c == null){
            return false;
        }
        for(E elem : c){
            add(elem);
        }
        return true;
    }


    /**
     * Метод, который вставляет в список все элементы переданой
     * колекции. Вставляет начиная с номера єлемента index
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c == null){
            return false;
        }
        for(E elem : c){
            add(index, elem);
            index++;
        }
        return true;
    }


    /**
     * Метод, который удаляет из списка все элементы, значения которых равны любому
     * из элементов переданой колекции
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null){
            return false;
        }
        for(Object object : c){
            while(contains(object)){
                remove(object);
            }
        }
        return true;
    }


    /**
     * Метод, который удаляет из списка все элементы,
     * которые не равны не одному элементу колекции
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null){
            return false;
        }
        for(E elem : this){
            if(!c.contains(elem)){
                remove(elem);
            }
        }
        return true;
    }


    /**
     * Метод, который удаляет все элементы из списка
     */
    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Метод, который возвращает элемент списка по индексу
     */
    @Override
    public E get(int index) {
        if(index < 0 || index > size - 1){
            return null;
        }
        LinkedListIterator iterator = new LinkedListIterator();
        if(index < size / 2) {
            int counter = 0;
            while (iterator.hasNext()) {
                if (counter == index) {
                    return iterator.get();
                }
                iterator.next();
                counter++;
            }
        }
        else{
            int counter = size - 1;
            while (iterator.hasPrevious()) {
                if (counter == index) {
                    return iterator.getElemInverse();
                }
                iterator.previous();
                counter--;
            }
        }
        return null;
    }


    /**
     * Метод, который заменяет элемент списка по индексу на новый
     */
    @Override
    public E set(int index, E element){
        if(element == null || index < 0 || index > size - 1){
            return null;
        }
        LinkedListIterator iterator = new LinkedListIterator();
        int counter = 0;
        if(index < size / 2) {
            while(iterator.hasNext()){
                if(counter == index){
                    iterator.set(element);
                    return iterator.getElemInverse();
                }
                iterator.next();
                counter++;
            }
        }
        else{
            counter = size - 1;
            while (iterator.hasPrevious()) {
                if (counter == index) {
                    iterator.setElemInverse(element);
                    return iterator.getElemInverse();
                }
                iterator.previous();
                counter--;
            }
        }
        return null;
    }


    /**
     * Метод, который добавляет обьект в список по заданому индексу
     */
    @Override
    public void add(int index, E element) {
        if(isEmpty()){
            last = new Node(null, element, null);
            first = last;
            size++;
        }
        else if(index == 0){
            first.prev = new Node(null, element, first);
            first = first.prev;
            size++;
        }
        else if(index == size ){
            last.next = new Node(last, element, null);
            last = last.next;
            size++;
        }
        else if(index < size && index > 0 && element != null) {
            addForMiddleElements(index, element);
        }
    }


    /**
     * Метод, который удаляет элемент списка по индексу
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index > size + 1 || isEmpty()){
            return null;
        }
        Node<E> current = first;
        if(index == 0){
            current = first.next;
            first = current;
            first.prev = null;
        }
        else if(index == size - 1){
            current = last.prev;
            last = current;
            last.next = null;
        }
        else {
            deleteForMiddleElements(index);
        }
        size--;
        return current.elem;
    }


    /**
     * Метод, который находит индекс первого вхождения данного элемента
     * в список
     */
    @Override
    public int indexOf(Object o) {
        int counter = 0;
        for(E elem : this){
            if(elem.equals(o)){
                return counter;
            }
            counter++;
        }
        return -1;
    }


    /**
     * Метод, который находит индекс последнего вхождения данного
     * элемента в список
     */
    @Override
    public int lastIndexOf(Object o) {
        LinkedListIterator iterator = new LinkedListIterator();
        int counter = size - 1;
        while(iterator.hasPrevious()){
            if(iterator.getElemInverse().equals(o)){
                return counter;
            }
            iterator.previous();
            counter--;
        }
        return -1;
    }


    /**
     * Метод, который возвращает итератор LinkedListIterator для MyLinkedList,
     */
    @Override
    public ListIterator<E> listIterator() {
        return new LinkedListIterator();
    }

    /**
     * Метод, который возвращает итератор LinkedListIterator(index) для MyLinkedList,
     * Итерация по элементам будет начинаться с элемента с индексом index
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        if(index < 0 || index > size - 1){
            return null;
        }
        return new LinkedListIterator(index);
    }


    /**
     * Метод, который возвращает список, состоящий из элементов нашего списка
     * с промежутка [fromIndex, toIndex]
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if(fromIndex < 0 || toIndex > size - 1 || fromIndex > toIndex){
            return null;
        }
        MyLinkedList sublist = new MyLinkedList();
        int counter = 0;
        for(E elem : this){
            if (counter >= fromIndex && counter <= toIndex){
                sublist.add(elem);
            }
            counter++;
        }
        return sublist;
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


    /** Вспомагательный вложеный клас*/
    private static class Node<E> {
        E elem;         /** значение самого элемента*/
        Node<E> next;   /** отсылка на следующий элемент*/
        Node<E> prev;   /** отсылка на предыдущий элемент*/

        Node(Node<E> prev, E element, Node<E> next) {
            this.elem = element;
            this.next = next;
            this.prev = prev;
        }
    }


    /**
     * Клас итератора для MyLinkedList
     */
    private class LinkedListIterator implements ListIterator<E> {
        private Node<E> current = first;
        private Node<E> currentInverse = last;

        /** Конструктор для обычного итератора*/
        LinkedListIterator(){}

        /** Конструктор для итератора с индексом*/
        LinkedListIterator(int index){
            int counter = 0;
            while(counter != index){
                current = current.next;
                counter++;
            }
        }

        /**
         * Метод для получения значения элемента во время итерации по списку
         * в обратном порядке
         */
        public E getElemInverse(){
            return currentInverse.elem;
        }

        /**
         * Метод для получения значения элемента во время итерации по списку
         * по порядку
         */
        public E get(){
            return current.elem;
        }

        /**
         * Метод для изменения значения элемента во время итерации по списку
         * в обратном порядке
         */
        public void setElemInverse(E elem){
            currentInverse.elem = elem;
        }

        /**
         * Метод для изменения значения элемента во время итерации по списку
         * по порядку
         */
        @Override
        public void set(E element) {
            current.elem = element;
        }

        @Override
        public E next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            E elem = current.elem;
            current = current.next;
            return elem;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public boolean hasPrevious() {
            return currentInverse != null;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) { throw new NoSuchElementException(); }
            E elem = currentInverse.elem;
            currentInverse = currentInverse.prev;
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



    /** Вспомагательный метод для добавления элемента в середину списка(не по
     * краям). Метод начинает пробег по списку с конца или начала до нужного
     * нам индекса в зависимости к чему индекс ближе - к началу или к концу*/
    private void addForMiddleElements(int index, E element) {
        int counter = 0;
        Node current = first;
        Node prevCurrent;
        if(index < size / 2) {
            prevCurrent = first.prev;
            while (counter != index) {
                current = current.next;
                prevCurrent = current.prev;
                counter++;
            }
        }
        else{
            counter = size - 1;
            current = last;
            prevCurrent = last.prev;
            while (counter != index) {
                current = current.prev;
                prevCurrent = current.prev;
                counter--;
            }
        }
        Node newNode = new Node(prevCurrent, element, current);
        current.prev = newNode;
        prevCurrent.next = newNode;
        size++;
    }


    /** Вспомагательный метод для удаления элемента из середины списка(не по
     * краям). Метод начинает пробег по списку с конца или начала до нужного
     * нам индекса в зависимости от того к чему индекс ближе - к началу или к
     * концу*/
    private void deleteForMiddleElements(int index) {
        Node<E> current;
        if(index < size / 2){
            current = first;
            int counter = 0;
            while (counter != index) {
                current = current.next;
                counter++;
            }
        }
        else{
            current = last;
            int counter = size - 1;
            while (counter != index) {
                current = current.prev;
                counter--;
            }
        }
        Node<E> newNext = current.next;
        Node<E> newPrev = current.prev;
        newNext.prev = newPrev;
        newPrev.next = newNext;
    }
}
