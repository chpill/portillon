(ns portillon.handler.example
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response]
            [clojure.java.io :as io]
            [integrant.core :as ig]
            [manifold.deferred :as d]))

(defn hello-handler
  [req]
  (println "hello handler")
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "FBO!"})

(def plop (d/deferred))

(defmethod ig/init-key :portillon.handler/example [_ options]
  (fn [req respond raise]

    (future
      (Thread/sleep 2000)
      (respond (hello-handler req)))

    (println "PLOP?")
    nil))

