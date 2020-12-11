;;Copyright © 2020 Steven Katz
(ns footballsquares.core
  (:gen-class)
  (:require [clojure.string :as string]))

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
  (let [inp (string/split c #" +")
        cmd (if (> (count inp) 1) (list* (second inp) (first inp) (nnext inp)) inp)
        action (first cmd)
        new-game (case action
                   "score" (update g :teams do-score (g (second cmd)) (keyword "footballsquares.core" (nth cmd 2)))
                   "unscore" (update g :teams un-score (g (second cmd)))
                   "quit" (System/exit 0)
                   g)]
    (print-game new-game)
    (recur (read-line) new-game)))

(defn -main [& args]
  (when (< (count args) 3)
    (println "You must supply: home-team-name visiting-team-name players*")
    (System/exit 1))
  (play-game "print" (init-game args)))