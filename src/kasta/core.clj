(ns kasta.core
  (:require [clojure.tools.logging :as log]
            [clojure.java.io :as io]

            [mount.core :as mount]
            [nrepl.server :as nrepl]
            [figwheel-sidecar.repl-api :as ra]
            [libsass.build :as sass]
            [hawk.core :as hawk]

            [kasta.server :as server]))


(defn start-nrepl! []
  (nrepl/start-server
    :port 3333
    :bind "127.0.0.1")
  (log/info "Started nREPL on 127.0.0.1 3333" ))


(def dev-build
  {:id           "dev"
   :source-paths ["src/kasta/fe"]
   :compiler     {:output-to            "target/main.js"
                  :output-dir           "target/main.out"
                  :asset-path           "/static/main.out"
                  :aot-cache            true
                  :main                 'kasta.fe.start
                  :optimizations        :none
                  :source-map           true
                  :source-map-timestamp true
                  :parallel-build       true
                  :language-in          :ecmascript5}})


(defn now []
  (System/currentTimeMillis))


(defn start-sass! [src dst]
  (let [-last-recompile (volatile! 0)
        compile         (fn [_ _]
                          (when (> (now) (+ @-last-recompile 1000))
                            (vreset! -last-recompile (now))
                            (log/info "Compiling SCSS...")
                            (sass/build (.getPath (io/resource src))
                              {:output-dir dst
                               :source-map true})))]
    (hawk/watch!
      [{:paths   ["resources/scss"]
        :filter  (fn [_ {:keys [file]}]
                   (and (.isFile file)
                        (re-find #".scss$" (.getName file))))
        :handler compile}])
    (compile nil nil)))


(defn start-figwheel! [build-ids]
  (ra/start-figwheel!
    {:figwheel-options {:open-file-command "figwheel-open"
                        :css-dirs          ["target"]
                        :validate-config   false
                        :reload-clj-files  {:clj true :cljc false}}
     :all-builds       [dev-build]
     :build-ids        build-ids})
  (ra/cljs-repl))


(defn -main [& args]
  (mount/start)
  (start-nrepl!)
  (start-sass! "scss/main.scss" "target")
  (start-figwheel! ["dev"]))
