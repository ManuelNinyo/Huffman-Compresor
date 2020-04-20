
public class Main {

	public static void main(String[] args) {
		HuffmanEncoder hE=new HuffmanEncoder("Tres tristes tigres comen trigo en tres tristes platos");
		hE.encode().forEach((k,v)->System.out.println(k+":="+v));
	}

}
