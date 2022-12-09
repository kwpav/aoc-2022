(ns aoc-2022.day01
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn read-input [input-location]
  (-> input-location
       io/resource
       slurp
       (str/split #"\n\n")))

(defn parse-input [input-location]
  (let [input (read-input input-location)]
    (->> input
         (mapv #(str/split-lines %))
         (mapv #(mapv parse-long %)))))

(defn add-calories [input]
  (->> input
       (map #(reduce + %))
       flatten))

(defn part1 [input-location]
  (->> input-location
       parse-input
       add-calories
       (apply max)))

(defn part2 [input-location]
  (->> input-location
       parse-input
       add-calories
       ;; could fix sort here, but reverse is easier to use and I'm lazy
       sort
       reverse
       (take 3)
       (reduce +)))

(comment
  (part1 "day01.txt")
  (part2 "day01.txt")
  ,)
