(in-ns 'footballsquares.core)

(defn- header []
  (doall (map print (map pad '("0" "1" "2" "3" "4" "5" "6" "7" "8" "9")))))

(defn- psquares [c]
  (doall (for [y (range 10)]
           (do (print y)
               (doall (for [x (range 10)]
                        (if (= x 9)
                          (println (aget c x y))
                          (print (aget c x y)))))))))

(defn- pretty-keyword [k]
  (let [s (format "%s" k)
        i (inc (s/index-of s "/"))]
    (subs s i)))

(defn print-squares [{{{h-n :name h-s :score} :home
                       {a-n :name a-s :score} :away} :teams
                      board                          :board}]
  (let [h-points (total-points h-s)
        a-points (total-points a-s)]
    (header)
    (println (str " <---" h-n ": " h-points))
    (psquares board)
    (println "^")
    (println "|")
    (println (str a-n ": " a-points))
    (println (str "Winner: " (who-has h-points a-points board)))
    (println (str h-n ":" (pr-str (map pretty-keyword h-s))))
    (println (str a-n ":" (pr-str (map pretty-keyword a-s))))))
