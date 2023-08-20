import java.util.Iterator;
import java.util.ArrayList;
class Block{
  ArrayList<Statement> a1;
  public Block(Statement statement){
    a1= new ArrayList<Statement>();
      a1.add(statement);
  }
  public int size(){
    return this.a1.size();
  }
  public void go(){
    Iterator<Statement> loop = this.a1.iterator();
    while(loop.hasNext())
      loop.next().check();
  }
  public void add(Statement statement){
    a1.add(statement);
  }
}
