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
    // Method to compress given genome sequence
    public static void compress() {
        // Read in the genome sequence
        String input = BinaryStdIn.readString();
        // Make the header input
        int length = input.length();

        // Read out the line of sequence;
        BinaryStdOut.write(length);

        // For every letter in the sequence
        // Convert it to an ASCII Value
        // Find it's 8 bit equivalent and return as 2 bits
        // Then write those 2 bits
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
    // Method to expand genome sequence
    public static void expand() {
        // Read in the genome sequence
        int length = BinaryStdIn.readInt();

        // For every letter in the word
        // Convert the 2 bit into 8 bit using helper method
        // Write in those 8 bits
        for(int i = 0; i < length; i++) {
            int bits = BinaryStdIn.readInt(2);
            char c = decode(bits);
            BinaryStdOut.write(c);
        }

        BinaryStdOut.close();
    }

    // For each letter see if its ASCII values are equal
    // Then return the 2 bit form
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

    // For each letter see if the 2 bit form is equal
    // Then return the 8 bit form
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