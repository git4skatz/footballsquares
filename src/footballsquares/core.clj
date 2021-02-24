;;Copyright © 2020 Steven Katz
(ns footballsquares.core
  (:load "score" "player"))


(defn init-game [[home away & players]]
  {home   ::home
   away   ::away
   ::teams {::home {::name  home
                  ::score '()}
           ::away {::name  away
                  ::score '()}}
   ::board (squares players)})
