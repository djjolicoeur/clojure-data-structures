# ds-study

Fun project to implement some of the common data structures that I originally
learned in C/Java in Clojure.

Follows along with the [Data Structures and algorithm analysis in Java 2nd ed.] (http://www.amazon.com/Data-Structures-Algorithm-Analysis-Edition/dp/0132576279)
by Mark Allen Weiss, which is the book I originally was taught data structures from.  Since this book is written from the imperative, OOP perspective, I thought a good thought exercise would be to go back through the concepts in the book from a Functional perspective.

I would strongly advise against using any of these namespaces in a production environment.  Many will contain non tail-optimized recursion and may blow the stack.  Also the majority of the examples I have coded will only work for numeric data types, as they are merely examples to illustrate the concepts. For educational purposes only!

## Lists

I think it's a bit redundant to go over list data structures in a LISP so
I will begin with Trees.

## Trees

 1. Simple binary search tree.
 * Section 4.3
 * `/src/ds_study/binary_tree.clj`
 2. AVL (balanced) binary tree.
 * Section 4.4
 * `/src/ds_study/avl_tree.clj`


## Usage

Read, clone, fork, REPL....whatever you want.

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
