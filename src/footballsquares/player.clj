;;Copyright © 2020 Steven Katz
(in-ns 'footballsquares.core)

(defn- ones-digit [x] (rem x 10))

(defn who-has "Find out who has the given square"
  [home away sqrs]
  (aget sqrs (ones-digit home) (ones-digit away)))

(defn- populate-squares "Convert 1-d to 2-d structure"
  [xs]
  {:pre [(= 100 (count xs))]}
  (to-array-2d (partition 10 xs)))

(defn- expand-players "List of players names repeated in equal amounts"
  [xs]
  {:pre [(< 0 (count xs) 101)]}
  (let [num-players (count xs)
        num-each (quot 100 num-players)]
    (if (= 100 num-players)
      xs
      (mapcat (partial repeat num-each) xs))))

(defn- add-charity "Add the correct number of CHARITY markers"
  [xs]
  {:pre [(< 0 (count xs) 101)]}
  (let [num-charity (- 100 (count xs))]
    (if (= 0 num-charity)
      xs
      (into xs (repeat num-charity "CHARITY")))))

(defn predict "Determine winner for next possible scores"
  [who scores opp-points board]
  (let [possible (valid-scores scores)
        mapper (fn [s]
                 (list (name s)
                       (if (= who ::home)
                         (who-has (total-points (conj scores s)) opp-points board)
                         (who-has opp-points (total-points (conj scores s)) board))))]
    (map mapper possible)))

(def squares (comp populate-squares shuffle add-charity expand-players))