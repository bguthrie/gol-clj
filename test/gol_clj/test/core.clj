(ns gol-clj.test.core
  (:use [gol-clj.core])
  (:use [clojure.test]))

(deftest cell-alive-for-live-cell
  (is (false? (cell-alive? { :live true :neighbours 0 })))
  (is (false? (cell-alive? { :live true :neighbours 1 })))
  (is (true?  (cell-alive? { :live true :neighbours 2 })))
  (is (true?  (cell-alive? { :live true :neighbours 3 })))
  (is (false? (cell-alive? { :live true :neighbours 4 })))
  (is (false? (cell-alive? { :live true :neighbours 8 })))
  )

(deftest cell-alive-for-dead-cell
  (is (false? (cell-alive? { :live false :neighbours 2 }))))
  ; (is (true? (cell-alive? { :live false :neighbours 3 }))))