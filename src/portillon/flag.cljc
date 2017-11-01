(ns portillon.flag
  #?(:cljs (:require [goog.object :as gobj]))
  #?(:cljs (:require-macros [portillon.flag :refer [check]])))


#?(:clj (defmacro check [sym]
          `(flag-string ~(str sym))))


#?(:cljs
   (do (def flags (gobj/get js/window "portillon/flags"))
       (defn flag-string [flag]
         (gobj/get flags flag))))


