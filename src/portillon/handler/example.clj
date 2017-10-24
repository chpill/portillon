(ns portillon.handler.example
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response]
            [clojure.java.io :as io]
            [integrant.core :as ig]
            [manifold.deferred :as d]))

(defn hello-world-handler
  [req]
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "hello world!"})

(defmethod ig/init-key :portillon.handler/example [_ options]
  (fn [{[req] :ataraxy/result}]
    (println req)
    (d/timeout!
     (d/deferred)
     1000
     (hello-world-handler req))
    #_[::response/ok (io/resource "portillon/handler/example/example.html")]))

