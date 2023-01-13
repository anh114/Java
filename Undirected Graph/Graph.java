package Exercise2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private ArrayList<Node> nodes;
    private boolean[] visited;

    private int[][] matrix;
    public Graph(int n){
        if(n > 0 && n <= 10){
            nodes = new ArrayList<>();
            visited = new boolean[n];
            matrix = new int[n][n];
        } else {
            System.out.println("n khong thoa man dieu kien");
        }

    }

    public void addNode(Node node){
        nodes.add(node);
    }

    public void addEdge(int src, int dst){
        matrix[src][dst] = 1;
    }



    public void print(){
        System.out.print("  ");
        //Display all vertices in row
        for(Node node: nodes){
            System.out.print(node.getData() + " ");
        }
        System.out.println();
        for(int i = 0; i< matrix.length; i++){
            //display all vertices
            System.out.print(nodes.get(i).getData() + " ");
            for(int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void breathFirstSearch(int src){
        Queue<Integer> queue = new LinkedList<>();
        //add source vao into queue
        queue.offer(src);
        while (!queue.isEmpty()){
            //lay phan tu ra tu head of queue => phan tu do da visited
            src = queue.poll();
            visited[src] = true;
            //Check row of the source
            for(int i = 0; i < matrix[src].length; i++){
                //Neu phan tu co lien ket voi source (=1) va chua dc vivited
                if(matrix[src][i] == 1 && !visited[i]){
                    //them phan tu do vao queue
                    queue.offer(i);
                    System.out.print(i + " ");
                    //chuyen phan tu do thanh da visited
                    visited[i] = true;
                }
            }
        }
    }

    //Check graph is connected or not by Depth first search
    public void isConnected(int src){
        visited = new boolean[matrix.length];
        dFSHelper(src, visited);
        boolean connected = false;
        for(int i = 0; i < matrix[src].length; i++){
            if(visited[i]) {
                connected = true;
            } else{
                connected = false;
                break;
            }
        }
        if(connected)
            System.out.println("G is connected");
        else
            System.out.println("G is disconnected");
    }

    //Depth First Search
    private void dFSHelper(int src, boolean[] visited){
        //If the source is already visited => return
        if(visited[src]){
            return;
        }else { //If the source not yet, then keep going by check the node is visited
            visited[src] = true;
        }
        //Check follow by row of the source
        for(int i = 0; i < matrix[src].length; i++){
            //source = row, i = column
            if(matrix[src][i] == 1){ //if source edge with other nodes
                dFSHelper(i, visited); //keep check the nodes connected with source and find other nodes
            }
        }
    }




    //Kiem tra edge co lien ket khong
    public boolean checkEdge(int src, int dst){
        if(matrix[src][dst] == 1){
            return true;
        }
        return false;
    }

    //Ham tinh bac cua dinh
    public int degree(int src){
        int degree = 0;
        for(int i = 0; i< matrix.length; i++){
            if(checkEdge(src,i)){
                degree++;
            }
        }
        return degree;
    }


}
