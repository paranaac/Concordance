# Concordance

Word counting algorithm using a custom AVL TreeMap and TreeSet implementation. 

# Date: 3/25/2015

In this project, implement a Map class AVLMap and a Set class AVLSet by using AVL tree class implemented by author Mark Allen Weiss. 
Your AVLMap and AVLSet do not have to have all methods as in TreeMap and TreeSet class in Java API. Your only need the methods necessary for building a software tool described next.

Use your AVLMap and AVLSet classes to build a software tool Concordance_1, which is the first version of this tool. Concordance_1 reads a text file( any text file beyond 100kb and make sure to parse it to take away all spaces and anything that is not letter) and extracts all of the identifiers in the file, along with the line numbers on which the identifier appear. 
An identifier is defined as a string that begins with a letter (A-Z, a-z) and is followed by zero or more other characters that are a letter or a digit (0-9). Note that, if an identifier appears multiple times in a line, only one line number is recorded. 

The input of the concordance is a text file, for example a source code or a text document.
The concordance outputs the information for each identifier in the following format:
identifier _1 n_1: L_1, L_2, L_3, …
identifier _2 n_2: L_1, L_2, L_3, …
…
Here n_1 is the number of lines containing the identifier _1 and L_i (i >= 1) is the list of line numbers on which it appears.
Come up with an algorithm using a Set and Map to count the occurences and list our all the lines where they occurr. 
