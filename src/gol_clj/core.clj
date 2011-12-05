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

(defn should-live? [existing-points point]
  (let [neighbours-count (count (live-neighbours-of existing-points point))]
    (if (contains? existing-points point)
      (contains? (set (range 2 4)) neighbours-count)
      (= neighbours-count 3))))

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
