(in-ns 'footballsquares.core)

(defn- ones-digit [x] (rem x 10))

(defn- pad [x]
  (format "%10s" x))

(defn- who-has [home away sqrs]
  (s/trim (aget sqrs (ones-digit home) (ones-digit away))))

(defn- populate-squares [coll] {:pre [(= 100 (count coll))]}
  (to-array-2d (partition 10 coll)))

(defn- expand-players [coll] {:pre [(< 0 (count coll) 101)]}
  (let [num-players (count coll)
        num-each (quot 100 num-players)
        padded (map pad coll)]
    (if (= 100 num-players)
      padded
      (mapcat (partial repeat num-each) padded))))

(defn- add-charity [coll] {:pre [(< 0 (count coll) 101)]}
  (let [num-charity (- 100 (count coll))]
    (if (= 0 num-charity)
      coll
      (into coll (repeat num-charity (pad "CHARITY"))))))

(defn- project [who scores opp-points board]
  (let [possible (valid-scores scores)
        mapper (fn [s]
                 (list (pretty-keyword s)
                       (if (= who :home)
                         (who-has (total-points (conj scores s)) opp-points board)
                         (who-has opp-points (total-points (conj scores s)) board))))]
    (map mapper possible)))

(def squares (comp populate-squares shuffle add-charity expand-players))
