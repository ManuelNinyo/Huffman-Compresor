import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanEncoder {
	PriorityQueue<Node> nodes=new PriorityQueue<Node>();
	String text;	
	
	public HuffmanEncoder(String text) {
		this.text = text;
		//this.encode();
	}

	private Map<Character, Integer> generateFrequencyDictionary(){
		Map<Character, Integer> frequencies=new HashMap<Character, Integer>();
		char[] letters=text.toCharArray();
		for (char c : letters) {
			if(!frequencies.containsKey(c))
				frequencies.put(c, 0);
			frequencies.put(c, frequencies.get(c)+1);
		}
		return frequencies;
	}

	private Node createTree() {
		Map<Character, Integer> frequencies= generateFrequencyDictionary();
		frequencies.forEach((k,v)->nodes.add(new Node(k,v)));
		while(nodes.size()>1) {
			Node l=nodes.remove();
			l.dato="0";
			Node r=nodes.remove();
			r.dato="1";
			nodes.add(new Node(l , r , r.frequency+l.frequency));
		}
		Node root = nodes.remove();
		return root;
	}
	
	public Map<Character, String> encode(){
		Map<Character, String> code=new HashMap<Character, String>();
		Node root =createTree();
		encode(root,code,"");
		return code;
	}
	public Map<Character, String> encode(Node root,Map<Character, String> code,String sCode){
		if(root.c!=null)
			code.put(root.c, sCode+root.dato);
		else {
			encode(root.left,code,sCode+root.dato);
			encode(root.rigth,code,sCode+root.dato);
		}
		return code; 
	}




	private class Node implements Comparable<Node>{
		private Node rigth;
		private Node left;
		private int frequency;
		private Character c;
		private String dato;
		public Node(Character c, int frecueency) {
			this.frequency = frecueency;
			this.c = c;
		}
		public Node(Node left, Node rigth, int frecueency) {
			this.dato="";
			this.rigth = rigth;
			this.left = left;
			this.frequency = frecueency;
		}
		@Override
		public int compareTo(Node o) {
			return this.frequency-o.frequency;
		}
		
	}
	
}
