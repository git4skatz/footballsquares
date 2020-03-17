(ns footballsquares.core
    (:require [clojure.string :as s]))

(load "score")
(load "player")
(load "printing")

(defn init-game [[home away & players]]
  {home   :home
   away   :away
   :teams {:home {:name  home
                  :score '()}
           :away {:name  away
                  :score '()}}
   :board (squares players)})

(defn -main [& args]
    (let [game (init-game args)
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