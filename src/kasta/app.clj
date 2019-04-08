(ns kasta.app
  (:require [aleph.http :as http]))


(defn campaigns []
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (:body @(http/get "https://kasta.ua/api/v2/campaigns"))})


(defn app [req]
  (case (:uri req)
    "/api/campaigns" (campaigns)

    {:status 404
     :body   "not found"}))
