# Boggle - or currently more interestingly a Solver for the game Boggle

This weekend I learned about the game Boggle, when watching a talk on what makes a good tech interview on YouTube. 

More specifically I learned about the fact that it makes an interesting interview question, as it can be solved/played using graph traversal and Tries.

This repo contains:

* a BoggleBoard, which generates a random board from dice, just like the physical.
* a Trie implementation used as a dictionary when searching for words on the board
* a BoggleSolver that searches a given board for words from a given dictionary by:
    * building a Trie from the input dictionary
    * traversing the board to find all characters that start a word (via lookup in the Set of neighbours of th Trie's root node)
    * for each starting character traverse the board using an informed DepthFirstSearch, using the Trie dictionary to check 
        * if the current path/word (e.g. the characters of the path to the current node + the currently considered neighbour) forms a complete word, if so adding it to a set of found words
        * if the current path is still a prefix to some longer word, if so continuing on, by recurisvely calling the search step on the neighbour
        * else returing it's current set of found words
    * Thus it only continues DFS along neighbours that may actually lead to a word from the dictinary, interupting the graph traversal early, if it can not lead to a word

As the Boggle board is only 4x4 these choices wouldn't be necesarry to be able to solve the game board. 
However for larger boards an efficient algorithm is necessary.

This solution is somewhat acceptable for game boards up to 1000x1000. 
As the solution is recursive it fails for 10000, running out of space. Using a language that optimizes tail recursion, and making the solution tail-recursive might help.

Other improvments could be to implement an iterative version, or to parallelize each search that starts at a starting character.   
