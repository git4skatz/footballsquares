;;Copyright © 2020 Steven Katz
(ns footballsquares.core-test
  (:require [clojure.test :refer :all]
            [footballsquares.core :refer :all :as fb]))

(deftest scoretest
  (testing "Building scores"
    (is (= (score ::fb/touchdown) '(::fb/touchdown)) "Empty to touchdown")
    (is (= (score ::fb/extra-point (score ::fb/touchdown)) '(::fb/extra-point ::fb/touchdown))) "Extra point after touchdown"))
