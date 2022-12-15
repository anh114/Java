import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Algorithm implements Serializable {
    Scanner sc = new Scanner(System.in);

    public Algorithm() {
    }


    public void writeFile(String fileName, float arr[]) throws IOException {
        File file = new File(fileName);
        //Kiem tra file co ton tai khong, neu chua co file thi tao file moi
        if (!file.exists()) {
            System.out.println("No file");
            file.createNewFile();
        }

        BufferedWriter writer = null;

        try{
            writer = new BufferedWriter(new FileWriter(fileName));
            for(float num: arr){
                writer.write(num + "  ");
            }

        } finally {
            if(writer != null){
                writer.close();
            }
        }



    }

    public float[] readFile(String fileName) throws IOException {
        BufferedReader reader = null;
        //Lưu chuỗi đọc được str
        String str= "";
        float[] arr;
        try{
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            float[] array = new float[20];
            int index = 0;
            while((line = reader.readLine()) != null){
                //Thêm các ký tự đọc được vào Str
                str = line;
                //System.out.println(line);
               // arr[index] = Float.parseFloat(line);
                //index++;
            }
          //cắt Chuỗi theo khoảng trắng, ví dụ  4.2 1.5 5.0 sẽ được 1 mảng String[] = [4.2, 1.5, 5.0]
            List<String> listString = Arrays.stream(str.split(" ")).collect(Collectors.toList());
            for(String s: listString){
                if(!s.equals("")){
                array[index] = Float.parseFloat(s);
                index++;
                }
            }
            //copy sang array moi co length = input dau vao
            int currentIntdex = index;
            arr = new float[currentIntdex];
            for(int i = 0; i < currentIntdex; i++){
                arr[i] = array[i];
            }

        } finally {
            if(reader != null){
                reader.close();
            }

        }
        return arr;
    }


    public float[] bubbleSort(float arr[]) {
        int length = arr.length;
        System.out.println("Bubble sort");
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    float temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            for(float num: arr){
                System.out.print(num + "  ");
            }
            System.out.println();
        }
        return arr;
    }

    public float[] selectionSort(float arr[]) {
        int length = arr.length;
        System.out.println("Selection sort");
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            float temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
            for(float num: arr){
                System.out.print(num + "  ");
            }
            System.out.println();
        }
        return arr;
    }

    public float[] insertionSort(float arr[]) {
        int length = arr.length;
        System.out.println("Insertion sort");
        for (int i = 1; i < length; i++) {
            float current = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
            for(float num: arr){
                System.out.print(num + "  ");
            }
            System.out.println();
        }
        return arr;
    }

    public float[] search(float arr[], float value) {
        System.out.println("Linear search");
        int length = arr.length;
        int index = 0;
        float[] newArr = new float[length];
        System.out.print("Chi so la: ");
        for (int i = 0; i < length; i++) {
            if (arr[i] > value) {
                //add element thoa man dieu kien vao newArr
                newArr[index] = i;
                index++;

                //Chi so cac element > value
                System.out.print(i + "   ");
            }
        }
        //Tao array co chieu dai dung voi output can tim
        float[] finalArr = new float[index];
        for(int i = 0; i < index; i++){
            finalArr[i] = newArr[i];
        }
        System.out.println();
        return finalArr;

    }

    public int binarySearch(float arr[],int left, int right, float value){

        while (left <= right){
            int middle = (left + right) / 2;
            if(value < arr[middle]){
                right = middle -1;
                binarySearch(arr,left, right, value);
            } else if(value > arr[middle]){
                left = middle +1;
                binarySearch(arr, left, right, value);
            } else {
                return middle;
            }
        }
        return -1;
    }

    public void runtime(float[] arr){
        long startTime = System.nanoTime();
        bubbleSort(arr);
        long endTime = System.nanoTime();
        long bubbleTime = endTime - startTime;
        System.out.println("Bubble sort run time: " + bubbleTime + " nanosecond");

        startTime = System.nanoTime();
        selectionSort(arr);
        endTime = System.nanoTime();
        long selectionTime = endTime - startTime;
        System.out.println("Selection sort run time: " + selectionTime + " nanosecond");

        startTime = System.nanoTime();
        insertionSort(arr);
        endTime = System.nanoTime();
        long insertionTime = endTime - startTime;
        System.out.println("Insertion sort run time: " + insertionTime + " nanosecond");

        System.out.println("============================================================");
        if(selectionTime < bubbleTime && selectionTime < insertionTime){
            System.out.println("Selection sort is the fastest algorithm");
        } else if (insertionTime < bubbleTime && insertionTime < selectionTime) {
            System.out.println("Insertion sort is the fastest algorithm");
        } else if (bubbleTime < insertionTime && bubbleTime < selectionTime) {
            System.out.println("Bubble sort is the fastest algorithm");
        } else {
            System.out.println("There haven't the fastest algorithm yet!");
        }

    }

}
