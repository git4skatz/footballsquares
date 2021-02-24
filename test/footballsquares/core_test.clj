;;Copyright © 2020 Steven Katz
(ns footballsquares.core-test
  (:require [clojure.test :refer :all]
            [footballsquares.core :refer :all :as fb]))

(def basegame (init-game ["pats" "bills" "steve" "tricia" "don"]))

(deftest scoretest
  (testing "Building scores"
    (is (= {::fb/home {::fb/score '(::fb/touchdown)}}
           (do-score {::fb/home {::fb/score '()}} ::fb/home ::fb/touchdown)) "Empty to touchdown [home]")
    (is (= {::fb/away {::fb/score '(::fb/touchdown)}}
           (do-score {::fb/away {::fb/score '()}} ::fb/away ::fb/touchdown)) "Empty to touchdown [away]")))