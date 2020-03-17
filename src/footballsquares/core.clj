(ns footballsquares.core
    (:require [clojure.string :as s]))

(load "score")
(load "player")

(defn -main [& args]
    (let [home (first args)
          away (first (rest args))
          game {home :home
                away :away
                :teams {:home {:name  home
                               :score '()}
                        :away {:name  away
                               :score '()}}
                :board (squares (nthrest args 2))}
          play-game (fn [c g]
                        (let [cmd (s/split c #" ")
                              new-game (condp = (first cmd)
                                         "score" (do-score (rest cmd) g)
                                         "unscore" (un-score (first (rest cmd)) g)
                                         "quit" (System/exit 0)
                                         g)]
                          (print-squares new-game)
                          (recur (read-line) new-game)))]
        (play-game "print" game)))