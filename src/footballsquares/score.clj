;;Copyright © 2020 Steven Katz
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

(defn total-points "Convert scores to values and sum"
  [current-scores]
  (reduce + (map scores->points current-scores)))

(defn- score-allowed-touchdown? "Is a score allowed"
  [last-score s]
  (if (= last-score ::touchdown)
    (contains? must-follow-touchdown s)
    (not (contains? must-follow-touchdown s))))

(defn- score-allowed? "Is a score allowed"
  [current-scores s]
  (let [[last-score] current-scores]
    (score-allowed-touchdown? last-score s)))

(defn valid-scores "Get list of valid next scores"
  [current-scores]
  (let [[last-score] current-scores]
    (filter (partial score-allowed-touchdown? last-score) (keys scores->points))))

(defn- score "Add score to list of scores"
  ([s] (score s '()))
  ([s current-scores]
   (if (and (contains? scores->points s) (score-allowed? current-scores s))
     (cons s current-scores)
     current-scores)))

(defn do-score [[t sc] g]
  (let [team (g t)]
    (update-in g [:teams team :score]
               #(score (keyword "footballsquares.core" sc) %))))

(defn un-score [t g]
  (let [team (g t)]
    (update-in g [:teams team :score]
               #(if (empty? %) % (rest %)))))