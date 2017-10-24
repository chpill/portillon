(ns portillon.handler.example
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response]
            [clojure.java.io :as io]
            [integrant.core :as ig]))

(defmethod ig/init-key :portillon.handler/example [_ options]
  (fn [{[_] :ataraxy/result}]
    [::response/ok (io/resource "portillon/handler/example/example.html")]))
