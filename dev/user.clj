(ns user
  (:require [clojure.tools.namespace.repl :as tn]
            [mount.core :as mount]))

(defn start []
  (mount/start))


(defn stop []
  (mount/stop))


(defn reload []
  (tn/refresh :after 'user/start))
