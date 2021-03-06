package main.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import main.file.FileProcessor;
import main.file.basics.Dictionnary;
import main.file.basics.HuffmanBean;
import main.file.decoder.CompressedFileReader;
import main.file.encoder.CompressedFileWritter;
import main.file.encoder.HuffmanEncoder;
import main.file.reader.FileReader;

/**
 * Classe contenant le point d'entr�e du programme
 * @version 0.2.0
 */

@SuppressWarnings("unused") //temporaire
public class Main {
	public static void main (String[] args) {
		String url1 = "src/main/ressources/simpleFile.txt"; // instance "officiel" 1
		String url2 = "src/main/ressources/mediumFile.txt"; // instance "officiel" 2
		String url3 = "src/main/ressources/bigFile.txt"; 
		String url4 = "src/main/ressources/ExtraBigFile.txt"; 
		String url5 = "src/main/ressources/OverExtraBigFile.txt"; 
		String url6 = "src/main/ressources/UltraOverExtraBigFile.txt"; 
		String url7 = "src/main/ressources/ShitFile.txt"; 
		String url8 = "src/main/ressources/test_compression3.txt";
		
		String urlToCompressedFile = "src/main/ressources/compressed.bin";
		
		// Quel fichier utilise t-on?
		String url = url8;
		
		try {
			System.out.println("----- Encoding & Decoding - Display -----");
			encodeAndThenDecondeFileWithDebugDisplay(url,urlToCompressedFile);
			System.out.println("Taux de compression : " +compressionRate(url, urlToCompressedFile) +"%");
			System.out.println("----- Informations -----");
			System.out.println("Arbre de Huffman : src/main/ressources/arbreHuffman.xml");
			System.out.println("Fichier compressé : "+urlToCompressedFile);
			System.out.println("Fichier décompressé: src/main/ressources/result.txt");
			
		} catch (Exception e) {
			System.err.println("========================================");
			System.err.println("[ERROR] An error occured during runtime");
			System.err.println("========================================");
			System.err.println("Details :" + e.getMessage());
			System.err.println("============= STACK TRACE ==============");
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Renvoie le taux de compression
	 * @param fileUncompressed : Fichier non compressé
	 * @param fileCompressed : Fichier compressé
	 * @return Taux de compression
	 */
	public static float compressionRate(String fileUncompressed, String fileCompressed)
	{
		long lg_uncompressed = new File(fileUncompressed).length();
		long lg_compressed = new File(fileCompressed).length();
		return lg_compressed * 100 / lg_uncompressed ;
	}
	
	public static void encodeFile(String url, String urlToCompressedFile) {
		FileProcessor fileProcessor = new FileProcessor(false);
		fileProcessor.encodeFileAt(url, urlToCompressedFile);
	} 
	
	public static void encodeFileWithoutSaving(String url) {
		FileProcessor fileProcessor = new FileProcessor(false);
		fileProcessor.encodeFileAtWitoutSaving(url);
	}
	
	public static void encodeFileWithoutSavingWithDebugDisplay(String url) {
		FileProcessor fileProcessor = new FileProcessor(true);
		fileProcessor.encodeFileAtWitoutSaving(url);
	}
	
	public static void encodeAndThenDecondeFile(String url, String urlToCompressedFile) {
		FileProcessor fileProcessor = new FileProcessor(false);
		fileProcessor.encodeAndThenDecondeFileAt(url, urlToCompressedFile);
	}
	
	public static void encodeAndThenDecondeFileWithDebugDisplay(String url, String urlToCompressedFile) {
		FileProcessor fileProcessor = new FileProcessor(true);
		fileProcessor.encodeAndThenDecondeFileAt(url, urlToCompressedFile);
	}
}
