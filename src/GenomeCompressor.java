/******************************************************************************
 *  Compilation:  javac GenomeCompressor.java
 *  Execution:    java GenomeCompressor - < input.txt   (compress)
 *  Execution:    java GenomeCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   genomeTest.txt
 *                virus.txt
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 ******************************************************************************/

/**
 * The {@code GenomeCompressor} class provides static methods for compressing
 * and expanding a genomic sequence using a 2-bit code.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Zach Blick
 */
public class GenomeCompressor {

    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */
    public static void compress() {
        String input = BinaryStdIn.readString();
        int length = input.length();

        BinaryStdOut.write(length);

        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            int bits = encode(c);
            BinaryStdOut.write(bits, 2);
        }

        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {
        int length = BinaryStdIn.readInt();

        for(int i = 0; i < length; i++) {
            int bits = BinaryStdIn.readInt(2);
            char c = decode(bits);
            BinaryStdOut.write(c);
        }

        BinaryStdOut.close();
    }

    public static int encode(char c) {
        if (c == 'A') {
            return 0b00;
        } else if (c == 'C') {
            return 0b01;
        } else if (c == 'G') {
            return 0b10;
        } else {
            return 0b11;
        }
    }
    public static char decode(int code) {
        if (code == 0b00) {
            return 'A';
        } else if (code == 0b01) {
            return 'C';
        } else if (code == 0b10) {
            return 'G';
        } else {
            return 'T';
        }
    }



    /**
     * Main, when invoked at the command line, calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        if (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}