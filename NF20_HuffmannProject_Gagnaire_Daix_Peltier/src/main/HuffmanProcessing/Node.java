package main.HuffmanProcessing;

import java.io.PrintStream;

/**
 * Classe correspondant � un noeud dans l'arbre de Huffman (chaque noeud peut contenir une lettre, une fr�quence et un poids, ou simplement 1 � 2 sous-noeuds)
 * @version 0.2.0
 */

public class Node implements Comparable<Node>{
	private Node leftNode;
	private Node rightNode;
	private Integer frequency;
	private final Boolean isFinalLeaf;
	private int value;
	private String weight;
	private boolean encoding[];
	private int encodingLength;


	/**
	 * Constructeur d'un noeud correspondant � une lettre (avec fr�quence, avec lettre, et sans sous-noeuds)
	 * @param letter
	 * @param frequency
	 */
	public Node(int value, int frequency) {
		this.value = value;
		this.frequency = frequency;
		this.leftNode = null;
		this.rightNode = null;
		this.isFinalLeaf = true;
		this.encoding = new boolean[24];
		this.encodingLength = 0;
	}

	public Node(int mergedvalue, Integer mergedFrequency, Node lowestEntry, Node nextLowestEntry) {
		this.value = mergedvalue;
		this.frequency = mergedFrequency;
		this.leftNode = lowestEntry;
		this.rightNode = nextLowestEntry;
		this.isFinalLeaf = false;
		this.encoding = new boolean[24];
		this.encodingLength = 0;
	}

	/**
	 * M�thode permettant d'incr�menter la fr�quence de la lettre associ�e
	 */
	public void increaseFrequency() {
		++this.frequency;
	}

	/**
	 * M�thode permettant d'assigner � chaque lettre son poids selon l'encodage de Huffman
	 * @param weight String contenant le poids � assigner
	 */
	public void assignWeightToLetter(String weight) {
		if(isFinalLeaf) {
			this.weight = weight;
		}
		if(hasLeftChild()) {
			leftNode.assignWeightToLetter(weight+"0");
		}
		if(hasRightChild()) {
			rightNode.assignWeightToLetter(weight+"1");
		}
	}
	
	/**
	 * Methode permettant d'assigner chaque lettre avec son encodage dans Huffman
	 * @param father : Tableau de l'element parent.
	 * @param fatherLength : Taille du tableau de l'element parent.
	 * @param current : Nouvelle valeur � ajouter au tableau
	 */
	public void assignEncodage(boolean father[], int fatherLength, boolean current){
		if(isFinalLeaf){
			for(int i = 0; i < fatherLength; i++)
				this.encoding[i] = father[i];
			this.encoding[fatherLength] = current;
			this.encodingLength = fatherLength + 1;
		}
		if(hasLeftChild()){
			leftNode.assignEncodage(this.encoding, this.encodingLength, false);
		}
		if(hasRightChild()){
			rightNode.assignEncodage(this.encoding, this.encodingLength, true);
		}
	}

	/**
	 * M�thode permettant d'afficher les donn�es d'un noeud, et ceux des sous-noeuds associ�s (s'ils existent)
	 * @param stream Flux de sortie sur lequel afficher le message
	 * @param depth Num�ro correspondant � la profondeur dans l'arbre (0 �tant la racine)
	 */
	public void displayNodeInfoWithDepth(PrintStream stream, int depth) {
		stream.print("[" + getValue() + "] : " + getFrequency());
		if(hasLeftChild()) {
			stream.println();
			for(int i=0; i<=depth; i++) {
				stream.print("--");
			}
			stream.print("[LEFT] ");
			this.leftNode.displayNodeInfoWithDepth(stream,depth+1);
		}
		if(hasRightChild()) {
			stream.println();
			for(int i=0; i<=depth; i++) {
				stream.print("--");
			}
			stream.print("[RIGHT] ");
			this.rightNode.displayNodeInfoWithDepth(stream,depth+1);
		}
	}

	/**
	 * M�hode permettant d'afficher le poids de chaque noeud
	 * @param stream
	 */
	public void displayWeight(PrintStream stream) {
		if(isFinalLeaf) {
			stream.println("Letter [" + getValue() + "] weights : " + getWeight());
		}
		if(hasLeftChild()) {
			this.leftNode.displayWeight(stream);
		}
		if(hasRightChild()) {
			this.rightNode.displayWeight(stream);
		}
	}

	/**
	 * M�thode permettant de r�cuperer la lettre associ�e au noeud
	 * @return La lettre associ�e au noeud
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * M�thode permettant de r�cuperer la fr�quence de la lettre associ�e au noeud
	 * @return La fr�quence de la lettre associ�e au noeud
	 */
	public Integer getFrequency() {
		return frequency;
	}

	/**
	 * M�thode permettant de r�cuperer l'encodage de Huffman pour cette lettre
	 * @return String contenant l'encodage de Huffmann associ�
	 */
	public String getWeight() {
		return this.weight;
	}
	
	/**
	 * M�thode permettant de savoir si le noeud a un fils � gauche
	 * @return true si le noeud a un fils � gauche, false sinon
	 */
	public Boolean hasLeftChild() {
		return this.leftNode != null;
	}

	/**
	 * M�thode permettant de savoir si le noeud a un fils � droite
	 * @return true si le noeud a un fils � droite, false sinon
	 */
	public Boolean hasRightChild() {
		return this.rightNode != null;
	}

	/**
	 * M�thode red�finissant la fa�on dont sont compar�s les noeuds
	 * Trie d'abord sur la fr�quence, et en cas d'�galit� trie sur la lettre
	 */
	@Override
	public int compareTo(Node other) {
		if(this.getFrequency().equals(other.getFrequency())) {
			return this.getValue() - other.getValue();
		} else {
			return this.getFrequency().compareTo(other.getFrequency());
		}
	}
}

