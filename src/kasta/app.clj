(ns kasta.app
  (:require [clojure.java.io :as io]
            [clojure.string :as str]

            [ring.util.response :as response]
            [ring.middleware.content-type :as content-type]

            [aleph.http :as http]))


(defn static [{:keys [uri] :as req}]
  (or (some-> (str/replace uri #"^/static/" "")
              (response/resource-response)
              (content-type/content-type-response req)
              (update :body io/input-stream))
      {:status 404}))


(defn campaigns []
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (:body @(http/get "https://kasta.ua/api/v2/campaigns"))})


(defn app [{:keys [uri] :as req}]
  (cond
    (= "/" uri)
    (static (assoc req :uri "/static/index.html"))

    (str/starts-with? uri "/static/")
    (static req)

    "/api/campaigns"
    (campaigns)

    :else
    {:status 404
     :body   "not found"}))
