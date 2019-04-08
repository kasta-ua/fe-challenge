(defproject fe-challenge "1.0"
  :description "Kasta FrontEnd challenge"
  :url "https://github.com/kasta-ua/fe-challenge"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :main kasta.core

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [aleph "0.4.6" :exclusions [manifold]]
                 [manifold "0.1.8"]
                 [mount "0.1.16"]
                 [nrepl "0.6.0"]

                 [ch.qos.logback/logback-classic "1.2.3"]
                 [org.clojure/tools.logging "0.4.1"]]

  :profiles
  {:dev {:source-paths #{"dev"}
         :dependencies [[org.clojure/tools.namespace "0.3.0-alpha3"]
                        [cider/cider-nrepl "0.21.1"]]}})
