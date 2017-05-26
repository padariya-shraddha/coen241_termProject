package chord;

public class Finger {
	private int key;
	private int span;
	private int successorNode;
	private String ip;
	private int port;
	Finger(int gap,int span,int successorNode, String ip, int port) {
		this.key = gap;
		this.span = span;
		this.successorNode = successorNode;
	}
	public void setKey(int key) {
		this.key = key;
	}
	
	public void setRange(int span) {
		this.span = span;
	}
	
	public void setSuccessorNode(int successorNode) {
		this.successorNode = successorNode;
	}
	
	public int getKey() {
		return this.key;
	}
	
	public int getSpan() {
		return this.span;
	}
	
	public int getSuccessor()  {
		return this.successorNode;
	}
	
}
