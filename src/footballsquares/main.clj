(ns footballsquares.main
  (:gen-class)
  (:require [footballsquares.core :refer :all :as fsc]
            [clojure.string :as string])
  (:load "printing"))

(defn- play-game [c g]
  (let [inp (string/split c #" +")
        cmd (if (> (count inp) 1) (list* (second inp) (first inp) (nnext inp)) inp)
        action (first cmd)
        result (case action
                 "score" [nil (update g ::fsc/teams do-score (g (second cmd)) (keyword "footballsquares.core" (nth cmd 2)))]
                 "unscore" [nil (update g ::fsc/teams undo-score (g (second cmd)))]
                 "quit" (System/exit 0)
                 "print" [nil g]
                 "help" ["<team-name> score <score-type>\n<team-name> unscore\nquit\nhelp" g]
                 [(str action " is invalid input") g])
        game (result 1)
        message (result 0)]
    (if message
      (println message)
      (print-game game))
    (recur (do (print "> ") (flush) (read-line)) game)))

(defn -main [& args]
  (when (< (count args) 3)
    (println "You must supply: home-team-name visiting-team-name players*")
    (System/exit 1))
  (play-game "print" (init-game args)))
