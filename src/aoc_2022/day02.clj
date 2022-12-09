(ns aoc-2022.day02
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn read-input [input-location]
  (-> input-location
       io/resource
       slurp))

(defn ->rock-paper-scissor [input]
  (mapv #(cond (some #{%} ["A" "X"]) :rock
               (some #{%} ["B" "Y"]) :paper
               (some #{%} ["C" "Z"]) :scissor)
        input))

(defn parse-input [input-location]
  (->> input-location
       read-input
       str/split-lines
       (mapv #(str/split % #"\s"))
       (mapv ->rock-paper-scissor)))

(defn play [round]
  (let [p1 (first round)
        p2 (second round)]
    {:status (cond
               (= p1 p2)                           :draw
               (and (= :rock p1) (= :scissor p2))  :lose
               (and (= :rock p1) (= :paper p2))    :win
               (and (= :paper p1) (= :rock p2))    :lose
               (and (= :paper p1) (= :scissor p2)) :win
               (and (= :scissor p1) (= :paper p2)) :lose
               (and (= :scissor p1) (= :rock p2))  :win)
     :played p2}))

(defn score [{:keys [status played]}]
  (let [s {:rock    1
           :paper   2
           :scissor 3
           :lose    0
           :draw    3
           :win     6} ]
    (+ (get s status) (get s played))))

(defn part1 [input-location]
  (->> input-location
       parse-input
       (mapv play)
       (map score)
       (reduce +)))

(comment
  (part1 "day02.txt")
  (part1 "day02sample.txt")
  ,)
