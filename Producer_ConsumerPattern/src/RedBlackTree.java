

public class RedBlackTree {

//    1.节点是红色或黑色。
//    2.根是黑色。
//    3.所有叶子都是黑色（叶子是NIL节点）。
//    4.每个红色节点必须有两个黑色的子节点。（从每个叶子到根的所有路径上不能有两个连续的红色节点。）
//    5.从任一节点到其每个叶子的所有简单路径都包含相同数目的黑色节点。
//    0:black
//    1:red
    static final boolean Black = false;
    static final boolean Red = true;
    private Node root;

     class Node{
        public Node parent;

        public int key;
        public int value;

        public int blackHeight;
        public boolean color;
        public int size;
         public Node(int key, int value, int size, boolean color) {
             this.key = key;
             this.value = value;
             this.size = size;
             this.color = color;
         }



        public Node leftChild;
        public Node rightChild;

    }


    boolean recolor(Node x){
         return !x.color;
    }

//    search(){
//
//    }

//    public void put(int k, int v){
//        if (k == null) {
//            throw new NullPointerException("first argument to put() is null");
//        }
//        if (v == null) {
////            delete(key);
//            return;
//        }
//        root = put(root,k,v);
//        root.color = Black;
//    }

    //rec insert
//    private Node put(Node h, int k, int v){
//        if (h == null) { ///// case 0
//            Node x = new Node(k,v,1,Red);
//
//            return x;
//        }
//        if(k > h.key){
//            Node s =  put(h.rightChild,k,v);
//            h.rightChild = s;
//            s.parent = h;
//            h.size ++;
//
//        }else if (k < h.key){
//            Node s = put(h.leftChild,k,v);
//            h.leftChild = s;
//            s.parent = h;
//            h.size++;
//        }
//        else{
//            h.value = v;
//        }
//
//        //change color
//        InsertfixUp(h);
//
//
//
//    }
    private Node rotateLeft(Node a){
        Node r = a.rightChild;
        if(r.leftChild != null){
            a.rightChild = r.leftChild;
        }

        if (a.parent == null ){
            root = r;
            r.parent = null;
        }
        else if(a.parent.leftChild == a){
            a.parent.leftChild  = r;
        }
        else {
            a.parent.rightChild  = r;
        }
        r.parent = a.parent;
        r.leftChild= a;
        a.parent = r;

        /////calculate size;
        r.size = a.size;
        a.size = size(a.leftChild)+size(a.rightChild)+1;
        //calc color??
        return  r;
    }
    private Node rotateRight(Node a){
        Node l = a.leftChild;
        if(l.rightChild != null){
            a.rightChild = l.rightChild;
        }

        if (a.parent == null ){
            root = l;
            l.parent = null;
        }
        else if(a.parent.leftChild == a){
            a.parent.leftChild  = l;
        }
        else {
            a.parent.rightChild  = l;
        }
        l.parent = a.parent;
        l.rightChild= a;
        a.parent = l;

        /////calculate size;
        l.size = a.size;
        a.size = size(a.leftChild)+size(a.rightChild)+1;
        //calc color??
        return  l;

    }



//    void rotateLeft(Node a){
//         Node r = a.rightChild;
//        a.rightChild = r.leftChild;
//
//        if(r.leftChild ! = null){
////            r.leftChild = a ;
//            r.parent = a.parent;
//
//        }
//        if (a.parent == null) {
//            //set root
//            root = r;
//        }else if ( a.parent.leftChild= a){
//            a.parent.leftChild  = r;
//        }
//        else {
//            a.parent.rightChild  = r;
//        }
//        r.leftChild = a ;
//        a.parent = r;
//
//    }
//    void rotateRight(Node a){
//        Node l = a.leftChild;
//        a.leftChild = l.rightChild;
//
//        if(l.rightChild ! = null){
////            l.rightChild = a ;
//            l.parent = a.parent;
//
//        }
//        if (a.parent == null) {
////            set root
//            root = l;
//        }else if ( a.parent.rightChild= a){
//            a.parent.rightChild  = l;
//        }
//        else {
//            a.parent.leftChild  = l;
//        }
//        l.rightChild = a ;
//        a.parent = l;
//    }
    void InsertfixUp(Node a){
         ///
        if(a.parent == null ){
            //a is root case 0
            root = a;
            a.color = Black;
            return;
        }
        while (a.parent.color == Red){ // important
            if(a.parent == a.parent.parent.leftChild){
                //uncle
                Node r = a.parent.parent.rightChild;
                if(r.color == Red){

                }


            }
        }
    }

//    void remove() {
//
//    }
    /*
    *
    * helpers
    *
    *
    * */

    public  boolean isRed(Node a) throws Exception {
        try {
            return  a.color;
        }catch (Exception e){
            throw new NullPointerException();
        }


    }
    public int size(Node a){
        return (a==null) ? 0 : a.size;
    }

    public static void main(String[] args) {

    }







}
