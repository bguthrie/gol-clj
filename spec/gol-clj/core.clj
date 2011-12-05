(ns gol-clj.spec.core
  (:use [gol-clj.core])
  (:use [speclj.core]))

(describe "Game state after a single step"
  (it "is empty given an empty set of points"
    (should= #{} (gol-step #{})))
  
  (it "is empty given only a single point"
    (should= #{} (gol-step #{[0 0]})))
  
  (it "works with a stable 4x4 configuration"
    (should= 
        #{[0 0] [0 1] [1 0] [1 1]}
      (gol-step 
        #{[0 0] [0 1] [1 0] [1 1]})))
  
  (it "turns a dead cell live as needed"
    (should=
        #{[0 0] [0 1] [1 0] [1 1]}
      (gol-step
        #{[0 0] [0 1] [1 0]})))
  
  (it "runs one of those flipper things"
    (should=
        #{[0 0] [-1 0] [1 0]}
      (gol-step
        #{[0 0] [0 -1] [0 1]})))
  )

(run-specs)