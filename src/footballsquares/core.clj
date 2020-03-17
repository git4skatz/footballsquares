(ns footballsquares.core
    (:require [clojure.string :as s]))

(defn- pretty-keyword [k]
  (let [s (format "%s" k)
        i (inc (s/index-of s "/"))]
    (subs s i)))

(load "score")
(load "player")
(load "printing")

(defn- init-game [[home away & players]]
  {home   :home
   away   :away
   :teams {:home {:name  home
                  :score '()}
           :away {:name  away
                  :score '()}}
   :board (squares players)})

(defn- play-game [c g]
  (let [cmd (s/split c #" ")
        new-game (condp = (first cmd)
                   "score" (do-score (rest cmd) g)
                   "unscore" (un-score (first (rest cmd)) g)
                   "quit" (System/exit 0)
                   g)]
    (print-squares new-game)
    (recur (read-line) new-game)))

(defn -main [& args]
  (when (< (count args) 3)
    (println "You must supply: home-team-name visiting-team-name players*")
    (System/exit -1))
  (play-game "print" (init-game args)))