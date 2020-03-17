(in-ns 'footballsquares.core)

(def scores->points {::touchdown            6
                     ::extra-point          1
                     ::two-point-conversion 2
                     ::failed-conversion    0
                     ::field-goal           3
                     ::safety               2})

(def ^:private must-follow-touchdown #{::extra-point
                                       ::two-point-conversion
                                       ::failed-conversion})

(defn total-points [current-scores]
  (reduce + (map scores->points current-scores)))

(defn- score-allowed-1? [last-score s]
  (if (= last-score ::touchdown)
    (contains? must-follow-touchdown s)
    (not (contains? must-follow-touchdown s))))

(defn- score-allowed? [current-scores s]
  (let [[last-score] current-scores]
    (score-allowed-1? last-score s)))

(defn valid-scores [current-scores]
  (let [[last-score] current-scores]
    (filter (partial score-allowed-1? last-score) (keys scores->points))))

(defn score
  ([s] (score s '()))
  ([s current-scores]
   (if (and (contains? scores->points s) (score-allowed? current-scores s))
     (cons s current-scores)
     current-scores)))

(defn do-score [[t sc] g]
  (let [team (g t)
        current-score (get-in g [:teams team :score])
        new-score (score (keyword "footballsquares.core" sc) current-score)]
    (assoc-in g [:teams team :score] new-score)))

(defn un-score [t g]
  (let [team (g t)
        current-score (get-in g [:teams team :score])
        new-score (if (empty? current-score) current-score (rest current-score))]
    (assoc-in g [:teams team :score] new-score)))


