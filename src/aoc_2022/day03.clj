(ns aoc-2022.day03
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]
   [clojure.set :as set]))

(defn read-input [input-location]
  (-> input-location
       io/resource
       slurp))

(defn compartmentize [rucksack]
  (let [size (count rucksack)
        half-size (/ size 2)]
    [(subs rucksack 0 half-size)
     (subs rucksack half-size size)]))

(defn find-common [[compartment1 compartment2]]
  (first (set/intersection (set compartment1) (set compartment2))))

(defn priority [letter]
  (if (re-matches #"[a-z]" (str letter))
    (- (int letter) 96)
    (- (int letter) 38)))

(defn part1 [input-location]
  (->> input-location
       read-input
       str/split-lines
       (map compartmentize)
       (map find-common)
       (map priority)
       (reduce +)))

(defn part2 [input-location]
  (->> input-location
       read-input
       str/split-lines
       (partition 3)
       (map (fn [[a b c]] (first (set/intersection (set a) (set b) (set c)))))
       (map priority)
       (reduce +)))

(comment
  (part1 "day03sample.txt")
  (part1 "day03.txt")

  (part2 "day03sample.txt")
  (part2 "day03.txt")
  ,)
