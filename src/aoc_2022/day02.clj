(ns aoc-2022.day02
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn read-input [input-location]
  (-> input-location
       io/resource
       slurp))

(defn parse-input [input-location]
  (->> input-location
       read-input
       str/split-lines
       (mapv #(str/split % #"\s"))))

(defn play [[p1 p2]]
  {:status (cond
             (= p1 p2)                           :draw
             (and (= :rock p1) (= :scissor p2))  :lose
             (and (= :rock p1) (= :paper p2))    :win
             (and (= :paper p1) (= :rock p2))    :lose
             (and (= :paper p1) (= :scissor p2)) :win
             (and (= :scissor p1) (= :paper p2)) :lose
             (and (= :scissor p1) (= :rock p2))  :win)
   :played p2})

(defn score [{:keys [status played]}]
  (let [score-card {:rock    1
                    :paper   2
                    :scissor 3
                    :lose    0
                    :draw    3
                    :win     6}]
    (+ (get score-card status) (get score-card played))))

(defn ->rock-paper-scissor [input]
  (mapv #(cond (some #{%} ["A" "X"]) :rock
               (some #{%} ["B" "Y"]) :paper
               (some #{%} ["C" "Z"]) :scissor)
        input))

(defn part1 [input-location]
  (->> input-location
       parse-input
       (mapv ->rock-paper-scissor)
       (mapv play)
       (map score)
       (reduce +)))

(defn cheat [[p1 p2]]
  (cond
    ;; win
    (= p2 "Z") (cond (= p1 "A") ["A" "Y"]
                     (= p1 "B") ["B" "Z"]
                     (= p1 "C") ["C" "X"])
    ;; lose
    (= p2 "X") (cond (= p1 "A") ["A" "Z"]
                     (= p1 "B") ["B" "X"]
                     (= p1 "C") ["C" "Y"])
    ;; draw
    (= p2 "Y") [p1 p1]))

(defn part2 [input-location]
  (->> input-location
       parse-input
       (map cheat)
       (map ->rock-paper-scissor)
       (map play)
       (map score)
       (reduce +)))

(comment
  (part1 "day02sample.txt")
  (part1 "day02.txt")
  (part2 "day02sample.txt")
  (part2 "day02.txt")
  ,)
