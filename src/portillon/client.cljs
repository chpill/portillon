(ns portillon.client
  (:require [goog.object :as gobj]
            portillon.flag
            [portillon.flag :as flag]))

(enable-console-print!)


(let [container (js/document.createElement "h1")]
  (set! (.-innerText container) "WE ARE LIVE!")
  (.append js/document.body container))



(when (flag/check disable-mixpanel?)
  (js/console.log "bingo!"))
