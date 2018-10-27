package timecounter;

import java.util.List;

/**
 * Клас, который имеет все методы для подсчета времени выполнения всех
 * методов при всех вариантах индексов элементов, для заноса всех данных
 * в матрицу и для красивого вывода результата в консоль. Все эти действия
 * происходят в методе run(), который и будет вызываться в тестере.
 */

public class TimeCounter {

    private int size;
    private List<Object> list;
    private String[][] resultMatrix = new String[5][4];


    public TimeCounter(List<Object> list, int size) {
        this.list = list;
        this.size = size;
    }

    /**
     * Метод инициализирующий список значениями
     */
    public List initializeList() {
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }


    /**
     * Метод, который заполняет матрицу результатами подсчетов времени
     * выполнения методов, а также названиями методов и позициями
     */
    public void run() {
        initializeList();
        fillResultMatrix();
        changeResultMatrix();
        outputResultMatrix();
    }


    /**
     * Метод, который заполняет матрицу результатами подсчетов времени
     * выполнения методов, а также названиями методов и позициями
     */
    private void fillResultMatrix() {
        resultMatrix[0][0] = Methods.getClassName(list) + "[" + list.size() + "]";
        int i = 1;
        for (Position position : Position.values()) {
            resultMatrix[0][i] = position.toString();
            i++;
        }
        int j = 1;
        for (MethodNames methodName : MethodNames.values()) {
            resultMatrix[j][0] = methodName.toString();
            j++;
        }
        i = 1;
        j = 1;
        for (Position position : Position.values()) {
            for (MethodNames methodName : MethodNames.values()) {
                resultMatrix[j][i] = countTimeForPosition(position, methodName);
                j++;
            }
            j = 1;
            i++;
        }
    }


    /**
     * Метод, который немного меняет элементы матрицы(добавляет пробелы и
     * разграничительные вертикальные линии '|' в строки элементы) для касивого
     * вывода в консоль
     */
    private void changeResultMatrix() {
        for (int j = 0; j < resultMatrix[0].length; j++) {
            int spaceNum = (resultMatrix[0][0].length() - resultMatrix[0][j].length()) / 2;
            resultMatrix[0][j] = Methods.outputSymbols(' ', spaceNum)
                    + resultMatrix[0][j]
                    + Methods.outputSymbols(' ', spaceNum);
        }
        for (int i = 0; i < resultMatrix.length; i++) {
            for (int j = 0; j < resultMatrix[0].length; j++) {
                resultMatrix[i][j] = Methods.outputForTable(resultMatrix[0][j], resultMatrix[i][j]);
            }
        }
        for (int i = 0; i < resultMatrix.length; i++) {
            resultMatrix[i][0] = "|" + resultMatrix[i][0];
        }
    }

    public String[][] getResultMatrix(){
        initializeList();
        fillResultMatrix();
        return resultMatrix;
    }


    /**
     * Метод, который возвращает полную длину ряда элементов матрицы
     * Он необходим чтоб построить промежуточные линии из дефисов
     */
    private int getMatrixRowSummaryLength() {
        int number = 0;
        for (int j = 0; j < resultMatrix[0].length; j++) {
            number += resultMatrix[0][j].length();
        }
        return number;
    }


    /**
     * Метод, который выводит красиво матрицу в консоль
     */
    private void outputResultMatrix() {
        for (int i = 0; i < resultMatrix.length; i++) {
            System.out.println(Methods.outputSymbols('-', getMatrixRowSummaryLength()));
            for (int j = 0; j < resultMatrix[0].length; j++) {
                System.out.print(resultMatrix[i][j]);
            }
            System.out.println();
        }
        System.out.println(Methods.outputSymbols('-', getMatrixRowSummaryLength()));
        System.out.println("\n\n\n");
    }


    /**
     * Метод подсчитывающий время выполнения метода с элементом определенного
     * индекса(имя метода и индекс элемента, с которым работает метод передаються
     * как параметры)
     * Время он возвращает как строку, а не как число.
     */
    private String countTimeForPosition(Position elementPosition, MethodNames methodNames) {
        switch (elementPosition) {
            case START:
                return countTime(0, methodNames);
            case MIDDLE:
                return countTime(list.size() / 2, methodNames);
            case END:
                return countTime(list.size() - 1, methodNames);
        }
        return null;
    }


    /**
     * Метод подсчитывающий время выполнения метода(имя метода и индекс
     * элемента, с которым работает метод передаються как параметры)
     * Время он возвращает как строку, а не как число.
     * Являеться вспомогательным методом для метода countTimeForPosition
     */
    private String countTime(int elementIndex, MethodNames methodNames) {
        long start = System.nanoTime();
        switch (methodNames) {
            case ADD:
                list.add(elementIndex, 1);
                break;
            case REMOVE:
                list.remove(elementIndex);
                break;
            case GET:
                list.get(elementIndex);
                break;
            case SET:
                list.set(elementIndex, 1);
                break;
        }
        long end = System.nanoTime();
        return String.valueOf((end - start)) + " ns";
    }

}
