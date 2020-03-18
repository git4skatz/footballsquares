(in-ns 'footballsquares.core)

(defn- ones-digit [x] (rem x 10))

(defn- who-has [home away sqrs]
  (aget sqrs (ones-digit home) (ones-digit away)))

(defn- populate-squares [coll] {:pre [(= 100 (count coll))]}
  (to-array-2d (partition 10 coll)))

(defn- expand-players [coll] {:pre [(< 0 (count coll) 101)]}
  (let [num-players (count coll)
        num-each (quot 100 num-players)]
    (if (= 100 num-players)
      coll
      (mapcat (partial repeat num-each) coll))))

(defn- add-charity [coll] {:pre [(< 0 (count coll) 101)]}
  (let [num-charity (- 100 (count coll))]
    (if (= 0 num-charity)
      coll
      (into coll (repeat num-charity "CHARITY")))))

(defn- project [who scores opp-points board]
  (let [possible (valid-scores scores)
        mapper (fn [s]
                 (list (name s)
                       (if (= who :home)
                         (who-has (total-points (conj scores s)) opp-points board)
                         (who-has opp-points (total-points (conj scores s)) board))))]
    (map mapper possible)))

(def squares (comp populate-squares shuffle add-charity expand-players))