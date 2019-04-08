(ns kasta.core
  (:require [clojure.tools.logging :as log]
            [mount.core :as mount]
            [nrepl.server :as nrepl]
            [kasta.server :as server]))


(defn start-nrepl! []
  (nrepl/start-server
    :port 3333
    :bind "127.0.0.1")
  (log/info "Started nREPL on 127.0.0.1 3333" ))


(defn -main [& args]
  (start-nrepl!)
  (mount/start))
