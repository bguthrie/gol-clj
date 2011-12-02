(ns gol-clj.core)

(defn cell-alive? [cell]
  (if (:live cell)
    (not (nil? (some #{(:neighbours cell)} (range 2 4))))
    (= 3 (:neighbours cell))))

(defn row-neighbours [row col-num]
  (let [prev-col (max 0 (dec col-num))
        next-col (min (count row) (+ 2 col-num))]
    (if (empty? row) [] (subvec row prev-col next-col))))

(defn all-neighbours [row-number col-number grid] 
  (let [prev-row     (nth grid (dec row-number) nil)
        next-row     (nth grid (inc row-number) nil)
        current-row  (nth grid row-number nil)
        current-cell (nth current-row col-number)
        neighbours   (map (fn [row] (row-neighbours row col-number)) [prev-row current-row next-row])]
    (- (reduce + (flatten neighbours)) current-cell)))

(defn gol-step [grid] 
  (map-indexed
    (fn [row-number row]
      (map-indexed
        (fn [col-number cell]
          (let [neighbours (all-neighbours row-number col-number grid)]
            (if (cell-alive? {:neighbours neighbours :live (= cell 1)}) 1 0)))
          row))
    grid))