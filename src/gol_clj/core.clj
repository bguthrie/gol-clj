(ns gol-clj.core)

(defn cell-alive? [cell]
  (and 
    (:live cell)
    (not (< (:neighbours cell) 2))
    (not (> (:neighbours cell) 3))))