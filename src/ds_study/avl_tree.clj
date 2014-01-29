(ns ds-study.avl-tree
  "Code for creating an avl-tree"
  (:require [ds-study.binary-tree :refer :all]))


(defn rotate-left-child
  "Rotates root of tree to right branch,
   taking its left branch right child as its
   new left and make left child the new root"
  [tree]
  (let [r0 (root tree)
        l (left tree)
        r (right tree)
        new-r0 (root l)
        new-r0-left (left l)
        new-r-left (right l)
        partial-new-right (list new-r-left r)
        new-right (if (every? nil? partial-new-right)
                    (list r0)
                    (conj partial-new-right r0))]
    (list new-r0 new-r0-left new-right)))


(defn rotate-right-child
  "Symmetric  opposite of rotate-left-child"
  [tree]
  (let [r0 (root tree)
        l (left tree)
        r (right tree)
        new-r0 (root r)
        new-r0-right (right r)
        new-l-right (left r)
        partial-new-left (list l new-l-right)
        new-left (if (every? nil? partial-new-left)
                   (list r0)
                   (conj partial-new-left r0))]
    (list new-r0 new-left new-r0-right)))


(defn double-left
  "rotate-left-child on new tree
   created by preforming rotate-right-child on the
   left branch of the tree"
  [tree]
  (let [r0 (root tree)
        l  (left tree)
        r  (right tree)
        with-right (list r0 (rotate-right-child l) r)]
    (rotate-left-child with-right)))


(defn double-right
  "Symmetric opposite of double-left"
  [tree]
  (let [r0 (root tree)
        l (left tree)
        r (right tree)
        with-left (list r0 l (rotate-left-child r))]
    (rotate-right-child with-left)))


(defn avl-insert
  "Inserts nodes into the tree such that
   the height of each side of the tree never
   differs by more than one.  This ensures
   the depth of the tree is O(log n), thus
   ensuring O(log n) searches"
  [node tree]
  (let [r0 (root tree)
        l (left tree)
        r (right tree)]
    (if (nil? tree) (list node)
        (cond (< node r0)
              (let [new-tree (list r0 (avl-insert node l) r)
                    h-right (height (right new-tree))
                    h-left  (height (left new-tree))
                    h-diff (- h-left h-right)
                    l-r0 (root (left new-tree))]
                (if (= h-diff 2)
                  (if (< node l-r0)
                    (rotate-left-child new-tree)
                    (double-left new-tree))
                  new-tree))
             (> node 0)
             (let [new-tree (list r0 l (avl-insert node r))
                   h-right (height (right new-tree))
                   h-left (height (left new-tree))
                   h-diff (- h-right h-left)
                   r-r0 (root (right new-tree))]
               (if (= h-diff 2)
                 (if (> node r-r0)
                   (rotate-right-child new-tree)
                   (double-right new-tree))
                 new-tree))
             0 tree))))


(defn mk-avl-tree
  "Given a sequence of numbers, mk-avl-tree
   will recursively build an AVL tree out of
   those numbers"
  ([nodes tree]
     (if (empty? nodes) tree
         (recur (rest nodes) (avl-insert (first nodes) tree))))
  ([nodes]
     (mk-avl-tree nodes nil)))
