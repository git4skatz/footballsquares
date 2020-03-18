(in-ns 'footballsquares.core)

(defn- padr [s x]
  (format (str "%-" s "s") x))

(defn- padl [s x]
  (format (str "%" s "s") x))

(defn- header []
  (doall (map print (map (partial padl 10) '("0" "1" "2" "3" "4" "5" "6" "7" "8" "9")))))

(defn- print-board [c]
  (doall (for [y (range 10)]
           (do (print (padr 6 y))
               (doall (for [x (range 10)]
                        (if (= x 9)
                          (println (padr 10 (who-has x y c)))
                          (print (padr 10 (who-has x y c))))))))))

(defn- print-game [{{{h-n :name h-s :score} :home
                     {a-n :name a-s :score} :away} :teams
                    board                          :board}]
  (let [h-points (total-points h-s)
        a-points (total-points a-s)]
    (header)
    (println (str " <-------" h-n ": " h-points))
    (println)
    (print-board board)
    (println "^")
    (println "|")
    (println (str a-n ": " a-points))
    (println)
    (println (str "Winner: " (who-has h-points a-points board)))
    (println (str h-n ":" (pr-str (reverse (map name h-s))) "\n  " (pr-str (project :home h-s a-points board))))
    (println (str a-n ":" (pr-str (reverse (map name a-s))) "\n  " (pr-str (project :away a-s h-points board))))))