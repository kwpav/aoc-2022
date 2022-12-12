(ns aoc-2022.day04
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn read-input [input-location]
  (-> input-location
       io/resource
       slurp))

(defn ->pairs [v]
  (map
   (fn [i]
     (let [[a b] (map parse-long (str/split i #"-"))]
       (set (range a (inc b)))))
   v))

(defn part1 [input]
  (->> (read-input input)
       str/split-lines
       (map #(str/split % #","))
       (map ->pairs)
       (filter (fn [[a b]]
                 (or (every? a b)
                     (every? b a))))
       count))

(defn part2 [input]
  (->> (read-input input)
       str/split-lines
       (map #(str/split % #","))
       (map ->pairs)
       (filter (fn [[a b]]
                 (or (some a b)
                     (some b a))))
       count))

(comment
  (part1 "day04sample.txt")
  (part1 "day04.txt")

  (part2 "day04sample.txt")
  (part2 "day04.txt")
  ,)
