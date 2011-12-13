(ns gol-clj.core
  (:use [clojure.set]))

(defn possible-neighbours-of [point]
  (let [x (first point) y (last point)]
    #{[(dec x) y]
      [(inc x) y]

      [(dec x) (dec y)]
      [x       (dec y)]
      [(inc x) (dec y)]

      [(dec x) (inc y)]
      [x       (inc y)]
      [(inc x) (inc y)]
    }))

(defn live-neighbours-of [existing-points point]
  (intersection existing-points (possible-neighbours-of point)))

(def overpopulated  3)
(def underpopulated 2)
(def growth-factor  3)

(defn should-live? [existing-points point]
  (let [neighbours-count (count (live-neighbours-of existing-points point))]
    (if (contains? existing-points point)
      (>= overpopulated neighbours-count underpopulated)
      (= neighbours-count growth-factor))))

(defn all-possible-points [existing-points]
  (reduce 
    (fn [points point] 
      (union points (possible-neighbours-of point)))
    #{} existing-points))

(defn gol-step [existing-points]
  (set 
    (filter
      (partial should-live? existing-points)
      (all-possible-points existing-points))))
