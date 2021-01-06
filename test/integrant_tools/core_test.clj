(ns integrant-tools.core-test
  (:require [clojure.test :refer :all]
            [integrant-tools.core :as core]))

(deftest core-test

  (testing "get-config-filename"
    (are [files path] (= (set files) (set (core/get-config-filename path)))
      ["test1.edn" "test2.edn"] "test-resources"
      nil "invalid-path")))
