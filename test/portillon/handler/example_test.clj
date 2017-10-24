(ns portillon.handler.example-test
  (:require [clojure.test :refer :all]
            [kerodon.core :refer :all]
            [kerodon.test :refer :all]
            [integrant.core :as ig]
            [duct.core :as duct]
            [clojure.java.io :as io]
            [portillon.handler.example :as example]))

(def router (let [router-key :duct.router/ataraxy]
              (-> (duct/read-config (io/resource "portillon/config.edn"))
                  (duct/prep [router-key])
                  (ig/init [router-key])
                  router-key)))

(deftest smoke-test
  (testing "example page exists"
    (-> (session router)
        (visit "/example")
        (has (status? 200) "page exists"))))
