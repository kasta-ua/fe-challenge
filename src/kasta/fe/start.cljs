(ns kasta.fe.start
  (:require [kasta.fe.main :as main]
            [figwheel.client :as figwheel :include-macros true]))


(figwheel/watch-and-reload
  :websocket-url (str "ws://" js/location.hostname ":3450/figwheel-ws")
  :jsload-callback (main/trigger-render))
