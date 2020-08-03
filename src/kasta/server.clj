(ns kasta.server
  (:require [clojure.tools.logging :as log]

            [aleph.http :as aleph]
            [mount.core :as mount]

            [kasta.app :as app])
  (:import java.net.InetSocketAddress))


(def HOST "localhost")
(def PORT 5001)


(mount/defstate server
  :start (let [address (InetSocketAddress. HOST PORT)
               server  (aleph/start-server #'app/app {:socket-address address})]
           (log/infof "Started server on http://%s:%s/" HOST PORT)
           server)
  :stop (do
          (log/infof "Stopping server on http://%s:%s" HOST PORT)
          (.close server)))
