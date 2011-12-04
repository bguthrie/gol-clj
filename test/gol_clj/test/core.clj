(ns gol-clj.test.core
  (:use [gol-clj.core])
  (:use [clojure.test]))

(deftest cell-alive-for-live-cell-should-use-rule
  (is (false? (cell-remains-alive? { :live true :neighbours 0 })))
  (is (false? (cell-remains-alive? { :live true :neighbours 1 })))
  (is (true?  (cell-remains-alive? { :live true :neighbours 2 })))
  (is (true?  (cell-remains-alive? { :live true :neighbours 3 })))
  (is (false? (cell-remains-alive? { :live true :neighbours 4 })))
  (is (false? (cell-remains-alive? { :live true :neighbours 8 })))
  )

(deftest cell-alive-for-dead-cell-should-use-rule
  (is (false? (cell-remains-alive? { :live false :neighbours 2 })))
  (is (true?  (cell-remains-alive? { :live false :neighbours 3 })))
  )

(deftest all-neigbhours-one-row
  (is (= 0 (all-neighbours 0 0 [[0]       [] []])))
  (is (= 1 (all-neighbours 0 0 [[0 1]     [] []])))
  (is (= 2 (all-neighbours 0 1 [[1 1 1]   [] []])))
  (is (= 2 (all-neighbours 0 1 [[1 0 1]   [] []])))
  (is (= 2 (all-neighbours 0 1 [[1 0 1 1] [] []])))
  )

(deftest all-neighbours-multiple-rows
  (is (= 0 (all-neighbours 0 0 [[0 0 0] [0 0 0] [0 0 0]])))
  (is (= 1 (all-neighbours 0 0 [[0 1 0] [0 0 0] [0 0 0]])))
  (is (= 2 (all-neighbours 0 0 [[0 1 0] [0 1 0] [0 0 0]])))
  (is (= 3 (all-neighbours 0 0 [[0 1 0] [1 1 0] [0 0 0]])))
  (is (= 3 (all-neighbours 0 0 [[1 1 0] [1 1 0] [0 0 0]])))
  (is (= 3 (all-neighbours 0 0 [[0 1 0] [1 1 0] [0 0 1]])))
  (is (= 4 (all-neighbours 1 1 [[0 1 0] [1 0 1] [0 1 0]])))
  (is (= 8 (all-neighbours 1 1 [[1 1 1] [1 1 1] [1 1 1]])))
  )

(deftest row-neighbours-should-return-subvec
  (is (= []      (row-neighbours []          0)))
  (is (= [0]     (row-neighbours [0]         0)))
  (is (= [1 0]   (row-neighbours [0 1 0]     2)))
  (is (= [1 1 1] (row-neighbours [0 1 1 1 0] 2)))
  (is (= [1 0]   (row-neighbours [1 0 1 1]   0)))
  )

(deftest gol-step-visual-test
  (is (= 
        [[0 0 0]
         [0 0 0]
         [0 0 0]]
      (gol-step 
        [[0 0 0]
         [0 1 0]
         [0 0 0]])))
  (is (= 
        [[1 1 0]
         [1 1 0]
         [0 0 0]]
      (gol-step 
        [[1 1 0]
         [1 1 0]
         [0 0 0]])))
  )