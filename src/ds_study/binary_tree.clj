(ns ds-study.binary-tree
  "Functions for creating, using and manipulating a simple binary
   search tree for numberic data types.")

(defn root
  "Given a tree, return the root"
  [tree]
  (first tree))


(defn children
  "Given a tree, return child nodes"
  [tree]
  (rest tree))


(defn left
  "Given a tree, return left branch"
  [tree]
  (first (children tree)))


(defn right
  "Given a tree, return right branch"
  [tree]
  (second (children tree)))


(defn b-tree
  "Given a node and a tree, adds element to tree.
   If tree = nil, a tree is created root = node.
   only nodes of numeric types are supported"
  ([node tree]
     (let [r0 (root tree)
           l (left tree)
           r (right tree)]
       (cond
        (nil? tree) (list node)
        (= r0 node) tree
        (< node r0) (list r0 (b-tree node l) r)
        0 (list r0 l (b-tree node r)))))
  ([node]
     (b-tree node nil)))


(defn mk-tree
  "Makes a tree out of a collection of
   numbers
   EX: (mk-tree '(10 4 3 6 5 7 8 1 11 2 45))
       :=> (10 (4 (3 (1 nil (2)) nil) (6 (5) (7 nil (8)))) (11 nil (45)))"
  ([nodes tree]
     (if (empty? nodes) tree
         (recur (rest nodes) (b-tree (first nodes) tree))))
  ([nodes]
     (mk-tree nodes nil)))


(defn tree-contains?
  "Returns true if tree contains node
   otherwise false"
  [node tree]
  (let [r0 (root tree)
        l (left tree)
        r (right tree)]
    (cond (nil? tree) false
          (= r0 node) true
          (< node r0) (tree-contains? node l)
          0 (tree-contains? node r))))


(defn find-min
  "Returns min node of tree"
  [tree]
  (let [r0 (root tree)
        l (left tree)]
    (cond (nil? tree) nil
          (nil? l) r0
          0 (find-min l))))


(defn find-max
  "Returns max node of tree"
  [tree]
  (let [r0 (root tree)
        r  (right tree)]
    (cond (nil? tree) nil
          (nil? (right r)) (root r)
          0 (find-max r))))


(defn tree-remove
  "Remove node from tree given node and tree"
  [node tree]
  (let [r0 (root tree)
        l (left tree)
        r (right tree)]
    (cond
     (nil? tree) tree
     (< node r0) (let [new-l (tree-remove node l)]
                   (if (and (nil? new-l) (nil? r))
                     (list r0)
                     (list r0 new-l r)))
     (> node r0) (let [new-r (tree-remove node r)]
                   (if (and (nil? l) (nil? new-r))
                     (list r0)
                     (list r0 l new-r)))
     (and (not (nil? r))
          (not (nil? l))) (list min l (tree-remove min r))
     0 (if (nil? l) r l))))
