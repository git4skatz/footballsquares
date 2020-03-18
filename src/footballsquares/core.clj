(ns footballsquares.core
  (:gen-class)
  (:require [clojure.string :as s]))

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
  (let [inp (s/split c #" ")
        cmd (if (> (count inp) 1) (cons (second inp) (cons (first inp) (nnext inp))) inp)
        new-game (condp = (first cmd)
                   "score" (do-score (rest cmd) g)
                   "unscore" (un-score (second cmd) g)
                   "quit" (System/exit 0)
                   g)]
    (print-squares new-game)
    (recur (read-line) new-game)))

(defn -main [& args]
  (when (< (count args) 3)
    (println "You must supply: home-team-name visiting-team-name players*")
    (System/exit -1))
  (play-game "print" (init-game args)))