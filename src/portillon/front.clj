(ns portillon.front
  (:require [integrant.core :as ig]
            [net.cgrand.enlive-html :as enlive]
            [net.cgrand.xforms :as x]
            ring.util.response))


(defn flags-script [flags]
  (let [lines (vec (mapcat (fn [[k v]] ["\"" (name k) "\"" ": " v ",\n"])
                           flags))]
    (apply str "
window[\"portillon/flags\"] = {
"
           (conj lines
                 "
};"))))


(enlive/deftemplate root-html "portillon/front/index.html" [flags]
  ;; [:head] (enlive/append
  ;;          (enlive/html
  ;;           [:link {:rel "stylesheet"
  ;;                   :media "all"
  ;;                   :href "/plip/plop/plouf"}]))

  [:script#portillon-flags] (enlive/html-content (flags-script flags))

  ;; [:script#portfolio-data] (enlive/content
  ;;                           (data->transit-string world :json nil))
  )


(defn parse-flags [query-params]
  (x/into {}
          (x/by-key (keep #(case %
                             "true" true
                             "false" false
                             nil)))
          query-params))


(defn front-handler [req]
  {:body (root-html (parse-flags (:params req)))
   :headers {"Content-Type" "text/html; charset=UTF-8"}})

(defmethod ig/init-key :portillon/front [_ options]
  (fn [req respond raise]
    (future
      ;; (Thread/sleep 200)
      (respond (front-handler req)))

    (println "PLOP?")
    nil))
