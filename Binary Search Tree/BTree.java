package Exercise1;

public class BTree {
    private Node root;

    public boolean isEmpty(){
        return root == null;
    }

    private Node insertHelper(Node root, Node node){
        int data = node.data;
        if(root == null){
            root = node;
            return root;
        } else if (data < root.data) {
            root.left = insertHelper(root.left, node);
        } else {
            root.right = insertHelper(root.right, node);
        }
        return root;
    }

    public void insert(Node node){
        root = insertHelper(root, node);
    }

    public void display(int choice){
        switch (choice) {
            case 1:
                preOrder(root);
                System.out.println();
                break;
            case 2:
                inOrder(root);
                System.out.println();
                break;
            default:
                System.out.println("Please choose 1 or 2");
        }

    }

    //Root -> left -> right
    private void preOrder(Node root){
        if(root != null){
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);

        }
    }

    //left -> root -> right
    private void inOrder(Node root){
        if(root != null){
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    public void find(int value){
        if(root != null){
            findHelper(root, value);
        }
    }




    //find nodes > value
    private void findHelper(Node root, int value){
        if(root != null){
            //root + all rightChild of root + continue check leftChild
          if(value < root.data){
              System.out.print(root.data + " ");
              inOrder(root.right);
              findHelper(root.left, value);
              //chi co rightChild > value
          } else if (value == root.data) {
              inOrder(root.right);
              //tim kiem tiep ben rightChild of root
          } else {
              findHelper(root.right, value);
          }
        }

    }



}
