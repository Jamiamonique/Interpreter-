import java.util.ArrayList;
import java.util.List;
class Node{
  String data;
  private String token;
  private Node parent;
  private List<Node> child;
   public Node(String data ){
     this.child = new ArrayList<>();
     this.token=null;
     this.data=data;
   }
  public void addNode(Node node){
    Node c= node;
    c.setParent(this);
    child.add(c);
  }
  public void setToken(String token){
    this.token=token;
  }
  public void setParent(Node parent){
    this.parent = parent;
  }
  public List<Node> getChild(){
    return child;
  }
}
class TreeNode{
  private Node root;
  public TreeNode(Node root){
    this.root=root;
  }
  public void display(){
    print(root);
  }
  private void print(Node n){
    System.out.println(n.data);
    for(Node nodes : n.getChild())
      print(nodes);
  }
}
