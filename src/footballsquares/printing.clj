;;Copyright © 2020 Steven Katz
(in-ns 'footballsquares.core)

(defn- padr [s x]
  (format (str "%-" s "s") x))

(defn- padl [s x]
  (format (str "%" s "s") x))

(defn- header [s]
  (doseq [x (range 10)]
    (print (padl 10 x)))
  (println s))

(defn- footer [s]
  (println "^")
  (println "|")
  (println s))

(defn- print-board [c]
  (doseq [y (range 10)]
    (print (padr 6 y))
    (doseq [x (range 10)]
      (if (= x 9)
        (println (padr 10 (who-has x y c)))
        (print (padr 10 (who-has x y c)))))))

(defn- print-game [{{{h-n :name h-s :score} :home
                     {a-n :name a-s :score} :away} :teams
                    board                          :board}]
  (let [h-points (total-points h-s)
        a-points (total-points a-s)]
    (header (str " <-------" h-n ": " h-points))
    (println)
    (print-board board)
    (footer (str a-n ": " a-points))
    (println)
    (println (str "Winner: " (who-has h-points a-points board)))
    (println (str h-n ":" (pr-str (reverse (map name h-s))) "\n  " (pr-str (predict :home h-s a-points board))))
    (println (str a-n ":" (pr-str (reverse (map name a-s))) "\n  " (pr-str (predict :away a-s h-points board))))))